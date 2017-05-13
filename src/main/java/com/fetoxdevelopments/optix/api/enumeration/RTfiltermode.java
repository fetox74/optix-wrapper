package com.fetoxdevelopments.optix.api.enumeration;

import java.util.Collections;
import java.util.Iterator;

import org.bridj.FlagSet;
import org.bridj.IntValuedEnum;

/**
 * enum values<br>
 * <i>native declaration : include\internal\optix_declarations.h</i>
 */
public enum RTfiltermode
  implements IntValuedEnum<RTfiltermode>
{
  RT_FILTER_NEAREST(0),
  RT_FILTER_LINEAR(1),
  /** < No filter */
  RT_FILTER_NONE(2);

  RTfiltermode(long value)
  {
    this.value = value;
  }

  public final long value;

  public long value()
  {
    return this.value;
  }

  public Iterator<RTfiltermode> iterator()
  {
    return Collections.singleton(this).iterator();
  }

  public static IntValuedEnum<RTfiltermode> fromValue(int value)
  {
    return FlagSet.fromValue(value, values());
  }
}
