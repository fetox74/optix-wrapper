package com.fetoxdevelopments.optix.api.enumeration;

import java.util.Collections;
import java.util.Iterator;

import org.bridj.FlagSet;
import org.bridj.IntValuedEnum;

/**
 * enum values<br>
 * <i>native declaration : include\internal\optix_declarations.h</i>
 */
public enum RTbufferflag
  implements IntValuedEnum<RTbufferflag>
{
  /** < An @ref RT_BUFFER_INPUT_OUTPUT has separate copies on each device that are not synchronized */
  RT_BUFFER_GPU_LOCAL(0x4),
  /** < A CUDA Interop buffer will only be synchronized across devices when dirtied by @ref rtBufferMap or @ref rtBufferMarkDirty */
  RT_BUFFER_COPY_ON_DIRTY(0x8),
  /** < Depth specifies the number of layers, not the depth of a 3D array */
  RT_BUFFER_LAYERED(0x200000),
  /**
   * < Enables creation of cubemaps. If this flag is set, Width must be equal to Height, and Depth must be six. If the @ref RT_BUFFER_LAYERED flag is also
   * set, then Depth must be a multiple of six
   */
  RT_BUFFER_CUBEMAP(0x400000);

  RTbufferflag(long value)
  {
    this.value = value;
  }

  public final long value;

  public long value()
  {
    return this.value;
  }

  public Iterator<RTbufferflag> iterator()
  {
    return Collections.singleton(this).iterator();
  }

  public static IntValuedEnum<RTbufferflag> fromValue(int value)
  {
    return FlagSet.fromValue(value, values());
  }
}
