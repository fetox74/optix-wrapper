package com.fetoxdevelopments.examples;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.fetoxdevelopments.optix.api.RT;
import com.fetoxdevelopments.optix.api.RTbuffer;
import com.fetoxdevelopments.optix.api.RTbuffertype;
import com.fetoxdevelopments.optix.api.RTcontext;
import com.fetoxdevelopments.optix.api.RTdeviceattribute;
import com.fetoxdevelopments.optix.api.RTformat;
import com.fetoxdevelopments.optix.api.RTprogram;
import com.fetoxdevelopments.optix.api.RTresult;
import com.fetoxdevelopments.optix.api.RTvariable;
import org.bridj.IntValuedEnum;
import org.bridj.Pointer;

public class OptixHello
{
  public static void main(String[] args)
  {
    final int width = 640;
    final int height = 480;

    IntValuedEnum<RTresult> rtResult;
    Pointer<Integer> optixVersion = Pointer.allocateInt();
    Pointer<Integer> deviceCount = Pointer.allocateInt();
    Pointer<Byte> result = Pointer.allocateBytes(40);

    rtResult = RT.rtGetVersion(optixVersion);
    rtResult = RT.rtDeviceGetDeviceCount(deviceCount);

    System.out.println("optixVersion: " + optixVersion.getInt());
    System.out.println("deviceCount: " + deviceCount.getInt());

    for(int device = 0; device < deviceCount.getInt(); device++)
    {
      String deviceName;
      Pointer<Integer> multiprocessorCount = Pointer.allocateInt();
      Pointer<Integer> maxThreadsPerBlock = Pointer.allocateInt();
      Pointer<Integer> clockRate = Pointer.allocateInt();

      rtResult = RT.rtDeviceGetAttribute(device, RTdeviceattribute.RT_DEVICE_ATTRIBUTE_NAME, 40, result);
      deviceName = new String(result.getBytes()).trim();

      rtResult = RT.rtDeviceGetAttribute(device, RTdeviceattribute.RT_DEVICE_ATTRIBUTE_MULTIPROCESSOR_COUNT, 4, multiprocessorCount);
      rtResult = RT.rtDeviceGetAttribute(device, RTdeviceattribute.RT_DEVICE_ATTRIBUTE_MAX_THREADS_PER_BLOCK, 4, maxThreadsPerBlock);
      rtResult = RT.rtDeviceGetAttribute(device, RTdeviceattribute.RT_DEVICE_ATTRIBUTE_CLOCK_RATE, 4, clockRate);

      System.out.println("device[" + device + "].name: " + deviceName);
      System.out.println("device[" + device + "].multiprocessorCount: " + multiprocessorCount.getInt());
      System.out.println("device[" + device + "].maxThreadsPerBlock: " + maxThreadsPerBlock.getInt());
      System.out.println("device[" + device + "].clockRate: " + clockRate.getInt());
    }

    // if we have at least one device, lets play around with the first
    if(deviceCount.getInt() >= 1)
    {
      Pointer<RTcontext> context = Pointer.allocate(RTcontext.class);

      Pointer<RTprogram> rayGenProgram = Pointer.allocate(RTprogram.class);
      Pointer<RTbuffer> buffer = Pointer.allocate(RTbuffer.class);

      Pointer<RTvariable> resultBuffer = Pointer.allocate(RTvariable.class);
      Pointer<RTvariable> drawColor = Pointer.allocate(RTvariable.class);

      Pointer<Byte> bufferNameStr = Pointer.pointerToCString("result_buffer");
      Pointer<Byte> pathToPTXStr = Pointer.pointerToCString("optixHello_generated_draw_color.cu.ptx");
      Pointer<Byte> drawSolidColorStr = Pointer.pointerToCString("draw_solid_color");
      Pointer<Byte> drawColorStr = Pointer.pointerToCString("draw_color");

      rtResult = RT.rtContextCreate(context);
      rtResult = RT.rtContextSetRayTypeCount(context.get(), 1);
      rtResult = RT.rtContextSetEntryPointCount(context.get(), 1);

      rtResult = RT.rtBufferCreate(context.get(), RTbuffertype.RT_BUFFER_OUTPUT, buffer);
      rtResult = RT.rtBufferSetFormat(buffer.get(), RTformat.RT_FORMAT_FLOAT4);
      rtResult = RT.rtBufferSetSize2D(buffer.get(), width, height);
      rtResult = RT.rtContextDeclareVariable(context.get(), bufferNameStr, resultBuffer);
      rtResult = RT.rtVariableSetObject(resultBuffer.get(), buffer.get());

      rtResult = RT.rtProgramCreateFromPTXFile(context.get(), pathToPTXStr, drawSolidColorStr, rayGenProgram);
      rtResult = RT.rtProgramDeclareVariable(rayGenProgram.get(), drawColorStr, drawColor);
      rtResult = RT.rtVariableSet3f(drawColor.get(), 0.462f, 0.725f, 0.0f);
      rtResult = RT.rtContextSetRayGenerationProgram(context.get(), 0, rayGenProgram.get());

      rtResult = RT.rtContextValidate(context.get());
      rtResult = RT.rtContextLaunch2D(context.get(), 0, width, height);

      // from here on it's display result code
      Pointer<Pointer<?>> pointer = Pointer.allocatePointer();
      rtResult = RT.rtBufferMap(buffer.get(), pointer);

      try
      {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        float[] pixels = pointer.get().as(Float.class).getFloats(width * height * 4);
        for(int y = 0; y < height; y++)
        {
          for(int x = 0; x < width; x++)
          {
            image.setRGB(x, y, ((int)(pixels[x * 4 + y * width * 4] * 255)) << 16 |
                               ((int)(pixels[x * 4 + y * width * 4 + 1] * 255)) << 8 |
                               ((int)(pixels[x * 4 + y * width * 4 + 2] * 255)));
          }
        }

        ImageIO.write(image, "png", new File("result.png"));
      }
      catch(IOException e)
      {
        e.printStackTrace();
      }

      rtResult = RT.rtBufferUnmap(buffer.get());
    }
    else
    {
      System.out.println("sorry, no CUDA capable device found!");
    }
  }
}
