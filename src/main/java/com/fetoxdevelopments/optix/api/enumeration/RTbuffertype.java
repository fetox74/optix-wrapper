package com.fetoxdevelopments.optix.api.enumeration;

import java.util.Collections;
import java.util.Iterator;

import org.bridj.FlagSet;
import org.bridj.IntValuedEnum;

/**
 * enum values<br>
 * <i>native declaration : include\internal\optix_declarations.h</i>
 */
public enum RTbuffertype
  implements IntValuedEnum<RTbuffertype>
{
  /** < Input buffer for the GPU */
  RT_BUFFER_INPUT(0x1),
  /** < Output buffer for the GPU */
  RT_BUFFER_OUTPUT(0x2),
  /** < Ouput/Input buffer for the GPU */
  RT_BUFFER_INPUT_OUTPUT(0x1 | 0x2),
  /** < Progressive stream buffer */
  RT_BUFFER_PROGRESSIVE_STREAM(0x10);

  RTbuffertype(long value)
  {
    this.value = value;
  }

  public final long value;

  public long value()
  {
    return this.value;
  }

  public Iterator<RTbuffertype> iterator()
  {
    return Collections.singleton(this).iterator();
  }

  public static IntValuedEnum<RTbuffertype> fromValue(int value)
  {
    return FlagSet.fromValue(value, values());
  }
}
