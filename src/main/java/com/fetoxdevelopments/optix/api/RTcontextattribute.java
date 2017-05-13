package com.fetoxdevelopments.optix.api;

import java.util.Collections;
import java.util.Iterator;

import org.bridj.FlagSet;
import org.bridj.IntValuedEnum;

/**
 * enum values<br>
 * <i>native declaration : include\internal\optix_declarations.h</i>
 */
public enum RTcontextattribute
  implements IntValuedEnum<RTcontextattribute>
{
  RT_CONTEXT_ATTRIBUTE_MAX_TEXTURE_COUNT(0),
  RT_CONTEXT_ATTRIBUTE_CPU_NUM_THREADS(1),
  RT_CONTEXT_ATTRIBUTE_USED_HOST_MEMORY(2),
  RT_CONTEXT_ATTRIBUTE_GPU_PAGING_ACTIVE(3),
  RT_CONTEXT_ATTRIBUTE_GPU_PAGING_FORCED_OFF(4),
  /** < sizeof(RTsize) */
  RT_CONTEXT_ATTRIBUTE_AVAILABLE_DEVICE_MEMORY(0x10000000);

  RTcontextattribute(long value)
  {
    this.value = value;
  }

  public final long value;

  public long value()
  {
    return this.value;
  }

  public Iterator<RTcontextattribute> iterator()
  {
    return Collections.singleton(this).iterator();
  }

  public static IntValuedEnum<RTcontextattribute> fromValue(int value)
  {
    return FlagSet.fromValue(value, values());
  }
}
