package com.fetoxdevelopments.optix.api.enumeration;

import java.util.Collections;
import java.util.Iterator;

import org.bridj.FlagSet;
import org.bridj.IntValuedEnum;

/**
 * enum values<br>
 * <i>native declaration : include\internal\optix_declarations.h</i>
 */
public enum RTexception
  implements IntValuedEnum<RTexception>
{
  /** < Program ID not valid */
  RT_EXCEPTION_PROGRAM_ID_INVALID(0x3EE),
  /** < Texture ID not valid */
  RT_EXCEPTION_TEXTURE_ID_INVALID(0x3EF),
  /** < Buffer ID not valid */
  RT_EXCEPTION_BUFFER_ID_INVALID(0x3FA),
  /** < Index out of bounds */
  RT_EXCEPTION_INDEX_OUT_OF_BOUNDS(0x3FB),
  /** < Stack overflow */
  RT_EXCEPTION_STACK_OVERFLOW(0x3FC),
  /** < Buffer index out of bounds */
  RT_EXCEPTION_BUFFER_INDEX_OUT_OF_BOUNDS(0x3FD),
  /** < Invalid ray */
  RT_EXCEPTION_INVALID_RAY(0x3FE),
  /** < Internal error */
  RT_EXCEPTION_INTERNAL_ERROR(0x3FF),
  /** < User exception */
  RT_EXCEPTION_USER(0x400),
  /** < All exceptions */
  RT_EXCEPTION_ALL(0x7FFFFFFF);

  RTexception(long value)
  {
    this.value = value;
  }

  public final long value;

  public long value()
  {
    return this.value;
  }

  public Iterator<RTexception> iterator()
  {
    return Collections.singleton(this).iterator();
  }

  public static IntValuedEnum<RTexception> fromValue(int value)
  {
    return FlagSet.fromValue(value, values());
  }
}
