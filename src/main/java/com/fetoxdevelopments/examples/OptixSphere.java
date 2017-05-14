package com.fetoxdevelopments.examples;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.fetoxdevelopments.optix.api.RT;
import com.fetoxdevelopments.optix.api.enumeration.RTbuffertype;
import com.fetoxdevelopments.optix.api.enumeration.RTdeviceattribute;
import com.fetoxdevelopments.optix.api.enumeration.RTformat;
import com.fetoxdevelopments.optix.api.enumeration.RTresult;
import com.fetoxdevelopments.optix.api.struct.RTacceleration;
import com.fetoxdevelopments.optix.api.struct.RTbuffer;
import com.fetoxdevelopments.optix.api.struct.RTcontext;
import com.fetoxdevelopments.optix.api.struct.RTgeometry;
import com.fetoxdevelopments.optix.api.struct.RTgeometrygroup;
import com.fetoxdevelopments.optix.api.struct.RTgeometryinstance;
import com.fetoxdevelopments.optix.api.struct.RTmaterial;
import com.fetoxdevelopments.optix.api.struct.RTprogram;
import com.fetoxdevelopments.optix.api.struct.RTvariable;
import org.bridj.IntValuedEnum;
import org.bridj.Pointer;

public class OptixSphere
{
  private static final int WIDTH = 640;
  private static final int HEIGHT = 480;

  public static void main(String[] args)
  {
    int deviceCount = queryDevices();

    Pointer<RTcontext> context = Pointer.allocate(RTcontext.class);
    Pointer<RTbuffer> outputBufferObj = Pointer.allocate(RTbuffer.class);
    Pointer<RTgeometry> sphere = Pointer.allocate(RTgeometry.class);
    Pointer<RTmaterial> material = Pointer.allocate(RTmaterial.class);

    // if we have at least one device, lets play around with the first
    if(deviceCount >= 1)
    {
      /* Setup state */
      createContext(context, outputBufferObj);
      createGeometry(context.get(), sphere);
      createMaterial(context.get(), material);
      createInstance(context.get(), sphere.get(), material.get());

      /* Run */
      RT.rtContextValidate(context.get());
      RT.rtContextLaunch2D(context.get(), 0, WIDTH, HEIGHT);

      // from here on it's display result code
      Pointer<Pointer<?>> pointer = Pointer.allocatePointer();
      RT.rtBufferMap(outputBufferObj.get(), pointer);

      try
      {
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_3BYTE_BGR);
        float[] pixels = pointer.get().as(Float.class).getFloats(WIDTH * HEIGHT * 4);
        for(int y = 0; y < HEIGHT; y++)
        {
          for(int x = 0; x < WIDTH; x++)
          {
            image.setRGB(x, y, ((int)(pixels[x * 4 + y * WIDTH * 4] * 255)) << 16 |
                               ((int)(pixels[x * 4 + y * WIDTH * 4 + 1] * 255)) << 8 |
                               ((int)(pixels[x * 4 + y * WIDTH * 4 + 2] * 255)));
          }
        }

        ImageIO.write(image, "png", new File("result.png"));
      }
      catch(IOException e)
      {
        e.printStackTrace();
      }

      RT.rtBufferUnmap(outputBufferObj.get());
    }
    else
    {
      System.out.println("sorry, no CUDA capable device found!");
    }
  }

  private static int queryDevices()
  {
    Pointer<Integer> optixVersion = Pointer.allocateInt();
    Pointer<Integer> deviceCount = Pointer.allocateInt();
    Pointer<Byte> result = Pointer.allocateBytes(40);

    RT.rtGetVersion(optixVersion);
    RT.rtDeviceGetDeviceCount(deviceCount);

    System.out.println("optixVersion: " + optixVersion.getInt());
    System.out.println("deviceCount: " + deviceCount.getInt());

    for(int device = 0; device < deviceCount.getInt(); device++)
    {
      String deviceName;
      Pointer<Integer> multiprocessorCount = Pointer.allocateInt();
      Pointer<Integer> maxThreadsPerBlock = Pointer.allocateInt();
      Pointer<Integer> clockRate = Pointer.allocateInt();

      RT.rtDeviceGetAttribute(device, RTdeviceattribute.RT_DEVICE_ATTRIBUTE_NAME, 40, result);
      deviceName = new String(result.getBytes()).trim();

      RT.rtDeviceGetAttribute(device, RTdeviceattribute.RT_DEVICE_ATTRIBUTE_MULTIPROCESSOR_COUNT, 4, multiprocessorCount);
      RT.rtDeviceGetAttribute(device, RTdeviceattribute.RT_DEVICE_ATTRIBUTE_MAX_THREADS_PER_BLOCK, 4, maxThreadsPerBlock);
      RT.rtDeviceGetAttribute(device, RTdeviceattribute.RT_DEVICE_ATTRIBUTE_CLOCK_RATE, 4, clockRate);

      System.out.println("device[" + device + "].name: " + deviceName);
      System.out.println("device[" + device + "].multiprocessorCount: " + multiprocessorCount.getInt());
      System.out.println("device[" + device + "].maxThreadsPerBlock: " + maxThreadsPerBlock.getInt());
      System.out.println("device[" + device + "].clockRate: " + clockRate.getInt());
    }

    return deviceCount.getInt();
  }

  private static void createContext(Pointer<RTcontext> context, Pointer<RTbuffer> outputBufferObj)
  {
    IntValuedEnum<RTresult> rtResult;

    Pointer<RTprogram> rayGenProgram = Pointer.allocate(RTprogram.class);
    Pointer<RTprogram> exceptionProgram = Pointer.allocate(RTprogram.class);
    Pointer<RTprogram> missProgram = Pointer.allocate(RTprogram.class);
    Pointer<RTvariable> outputBuffer = Pointer.allocate(RTvariable.class);
    Pointer<RTvariable> radianceRayType = Pointer.allocate(RTvariable.class);
    Pointer<RTvariable> epsilon = Pointer.allocate(RTvariable.class);

    /* variables for ray gen program */
    Pointer<RTvariable> eye = Pointer.allocate(RTvariable.class);
    Pointer<RTvariable> u = Pointer.allocate(RTvariable.class);
    Pointer<RTvariable> v = Pointer.allocate(RTvariable.class);
    Pointer<RTvariable> w = Pointer.allocate(RTvariable.class);
    Pointer<RTvariable> badColor = Pointer.allocate(RTvariable.class);

    /* viewing params */
    float hFOV, aspectRatio;

    /* variables for miss program */
    Pointer<RTvariable> bgColor = Pointer.allocate(RTvariable.class);

    /* Setup context */
    rtResult = RT.rtContextCreate(context);
    rtResult = RT.rtContextSetRayTypeCount(context.get(), 1);
    rtResult = RT.rtContextSetEntryPointCount(context.get(), 1);

    rtResult = RT.rtContextDeclareVariable(context.get(), CString("output_buffer"), outputBuffer);
    rtResult = RT.rtContextDeclareVariable(context.get(), CString("radiance_ray_type"), radianceRayType);
    rtResult = RT.rtContextDeclareVariable(context.get(), CString("scene_epsilon"), epsilon);

    rtResult = RT.rtVariableSet1ui(radianceRayType.get(), 0);
    rtResult = RT.rtVariableSet1f(epsilon.get(), 1.e-4f);

    /* Render result buffer */
    rtResult = RT.rtBufferCreate(context.get(), RTbuffertype.RT_BUFFER_OUTPUT, outputBufferObj);
    rtResult = RT.rtBufferSetFormat(outputBufferObj.get(), RTformat.RT_FORMAT_UNSIGNED_BYTE4);
    rtResult = RT.rtBufferSetSize2D(outputBufferObj.get(), WIDTH, HEIGHT);
    rtResult = RT.rtVariableSetObject(outputBuffer.get(), outputBufferObj.get());

    /* Ray generation program */
    rtResult = RT.rtProgramCreateFromPTXFile(context.get(), CString("optixSphere_generated_pinhole_camera.cu.ptx"), CString("pinhole_camera"), rayGenProgram);
    rtResult = RT.rtContextSetRayGenerationProgram(context.get(), 0, rayGenProgram.get());
    rtResult = RT.rtContextDeclareVariable(context.get(), CString("eye"), eye);
    rtResult = RT.rtContextDeclareVariable(context.get(), CString("U"), u);
    rtResult = RT.rtContextDeclareVariable(context.get(), CString("V"), v);
    rtResult = RT.rtContextDeclareVariable(context.get(), CString("W"), w);

    Pointer<Float> camEye = Pointer.pointerToFloats(0.0f, 0.0f, 5.0f);
    Pointer<Float> lookAt = Pointer.pointerToFloats(0.0f, 0.0f, 0.0f);
    Pointer<Float> up = Pointer.pointerToFloats(0.0f, 1.0f, 0.0f);
    hFOV = 60.0f;
    aspectRatio = (float)WIDTH / (float)HEIGHT;
    Pointer<Float> cameraU = Pointer.allocateFloats(3);
    Pointer<Float> cameraV = Pointer.allocateFloats(3);
    Pointer<Float> cameraW = Pointer.allocateFloats(3);
    calculateCameraVariables(camEye, lookAt, up, hFOV, aspectRatio, cameraU, cameraV, cameraW);

    rtResult = RT.rtVariableSet3fv(eye.get(), camEye);
    rtResult = RT.rtVariableSet3fv(u.get(), cameraU);
    rtResult = RT.rtVariableSet3fv(v.get(), cameraV);
    rtResult = RT.rtVariableSet3fv(w.get(), cameraW);

    /* Exception program */
    rtResult = RT.rtContextDeclareVariable(context.get(), CString("bad_color"), badColor);
    rtResult = RT.rtVariableSet3f(badColor.get(), 1.0f, 0.0f, 1.0f );
    rtResult = RT.rtProgramCreateFromPTXFile(context.get(), CString("optixSphere_generated_pinhole_camera.cu.ptx"), CString("exception"), exceptionProgram);
    rtResult = RT.rtContextSetExceptionProgram(context.get(), 0, exceptionProgram.get());

    /* Miss program */
    rtResult = RT.rtProgramCreateFromPTXFile(context.get(), CString("optixSphere_generated_constantbg.cu.ptx"), CString("miss"), missProgram);
    rtResult = RT.rtProgramDeclareVariable(missProgram.get(), CString("bg_color") , bgColor);
    rtResult = RT.rtVariableSet3f(bgColor.get(), .3f, 0.1f, 0.2f);
    rtResult = RT.rtContextSetMissProgram(context.get(), 0, missProgram.get());
  }

  private static void createGeometry(RTcontext context, Pointer<RTgeometry> sphere)
  {
    IntValuedEnum<RTresult> rtResult;

    Pointer<RTprogram> intersectionProgram = Pointer.allocate(RTprogram.class);
    Pointer<RTprogram> boundingBoxProgram = Pointer.allocate(RTprogram.class);
    Pointer<RTvariable> s = Pointer.allocate(RTvariable.class);
    Pointer<Float> sphereLoc = Pointer.pointerToFloats(0.f, 0.f, 0.f, 1.5f);

    rtResult = RT.rtGeometryCreate(context, sphere);
    rtResult = RT.rtGeometrySetPrimitiveCount(sphere.get(), 1);

    rtResult = RT.rtProgramCreateFromPTXFile(context, CString("optixSphere_generated_sphere.cu.ptx"), CString("bounds"), boundingBoxProgram);
    rtResult = RT.rtGeometrySetBoundingBoxProgram(sphere.get(), boundingBoxProgram.get());
    rtResult = RT.rtProgramCreateFromPTXFile(context, CString("optixSphere_generated_sphere.cu.ptx"), CString("intersect"), intersectionProgram);
    rtResult = RT.rtGeometrySetIntersectionProgram(sphere.get(), intersectionProgram.get());

    rtResult = RT.rtGeometryDeclareVariable(sphere.get(), CString("sphere"), s);
    rtResult = RT.rtVariableSet4fv(s.get(), sphereLoc);
  }

  private static void createMaterial(RTcontext context, Pointer<RTmaterial> material)
  {
    IntValuedEnum<RTresult> rtResult;

    Pointer<RTprogram> chp = Pointer.allocate(RTprogram.class);

    rtResult = RT.rtProgramCreateFromPTXFile(context, CString("optixSphere_generated_normal_shader.cu.ptx"), CString("closest_hit_radiance"), chp);

    rtResult = RT.rtMaterialCreate(context, material);
    rtResult = RT.rtMaterialSetClosestHitProgram(material.get(), 0, chp.get());
  }

  private static void createInstance(RTcontext context, RTgeometry sphere, RTmaterial material)
  {
    IntValuedEnum<RTresult> rtResult;

    Pointer<RTgeometrygroup> geometrygroup = Pointer.allocate(RTgeometrygroup.class);
    Pointer<RTvariable> topObject = Pointer.allocate(RTvariable.class);
    Pointer<RTacceleration> acceleration = Pointer.allocate(RTacceleration.class);
    Pointer<RTgeometryinstance> instance = Pointer.allocate(RTgeometryinstance.class);

    /* Create geometry instance */
    rtResult = RT.rtGeometryInstanceCreate(context, instance);
    rtResult = RT.rtGeometryInstanceSetGeometry(instance.get(), sphere);
    rtResult = RT.rtGeometryInstanceSetMaterialCount(instance.get(), 1);
    rtResult = RT.rtGeometryInstanceSetMaterial(instance.get(), 0, material);

    /* Create geometry group */
    rtResult = RT.rtAccelerationCreate(context, acceleration);
    rtResult = RT.rtAccelerationSetBuilder(acceleration.get(), CString("NoAccel"));
    rtResult = RT.rtGeometryGroupCreate(context, geometrygroup);
    rtResult = RT.rtGeometryGroupSetChildCount(geometrygroup.get(), 1);
    rtResult = RT.rtGeometryGroupSetChild(geometrygroup.get(), 0, instance.get());
    rtResult = RT.rtGeometryGroupSetAcceleration(geometrygroup.get(), acceleration.get());

    rtResult = RT.rtContextDeclareVariable(context, CString("top_object"), topObject);
    rtResult = RT.rtVariableSetObject(topObject.get(), geometrygroup.get());
  }

  private static Pointer<Byte> CString(String input)
  {
    return Pointer.pointerToCString(input);
  }

  private static void calculateCameraVariables(Pointer<Float> camEye, Pointer<Float> lookAt, Pointer<Float> up, float hFOV, float aspectRatio,
                                               Pointer<Float> cameraU, Pointer<Float> cameraV, Pointer<Float> cameraW)
  {
  }
}