package com.fetoxdevelopments.optix.api.enumeration;

import java.util.Collections;
import java.util.Iterator;

import org.bridj.FlagSet;
import org.bridj.IntValuedEnum;

/**
 * enum values<br>
 * <i>native declaration : include\internal\optix_declarations.h</i>
 */
public enum RTdeviceattribute
  implements IntValuedEnum<RTdeviceattribute>
{
  RT_DEVICE_ATTRIBUTE_MAX_THREADS_PER_BLOCK(0),
  RT_DEVICE_ATTRIBUTE_CLOCK_RATE(1),
  RT_DEVICE_ATTRIBUTE_MULTIPROCESSOR_COUNT(2),
  RT_DEVICE_ATTRIBUTE_EXECUTION_TIMEOUT_ENABLED(3),
  RT_DEVICE_ATTRIBUTE_MAX_HARDWARE_TEXTURE_COUNT(4),
  RT_DEVICE_ATTRIBUTE_NAME(5),
  RT_DEVICE_ATTRIBUTE_COMPUTE_CAPABILITY(6),
  RT_DEVICE_ATTRIBUTE_TOTAL_MEMORY(7),
  RT_DEVICE_ATTRIBUTE_TCC_DRIVER(8),
  RT_DEVICE_ATTRIBUTE_CUDA_DEVICE_ORDINAL(9);

  RTdeviceattribute(long value)
  {
    this.value = value;
  }

  public final long value;

  public long value()
  {
    return this.value;
  }

  public Iterator<RTdeviceattribute> iterator()
  {
    return Collections.singleton(this).iterator();
  }

  public static IntValuedEnum<RTdeviceattribute> fromValue(int value)
  {
    return FlagSet.fromValue(value, values());
  }
}
