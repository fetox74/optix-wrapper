package com.fetoxdevelopments.optix.api;

import java.util.Collections;
import java.util.Iterator;

import org.bridj.FlagSet;
import org.bridj.IntValuedEnum;

/**
 * enum values<br>
 * <i>native declaration : include\internal\optix_declarations.h</i>
 */
public enum RTwrapmode
  implements IntValuedEnum<RTwrapmode>
{
  RT_WRAP_REPEAT(0),
  RT_WRAP_CLAMP_TO_EDGE(1),
  RT_WRAP_MIRROR(2),
  /** < Clamp to border */
  RT_WRAP_CLAMP_TO_BORDER(3);

  RTwrapmode(long value)
  {
    this.value = value;
  }

  public final long value;

  public long value()
  {
    return this.value;
  }

  public Iterator<RTwrapmode> iterator()
  {
    return Collections.singleton(this).iterator();
  }

  public static IntValuedEnum<RTwrapmode> fromValue(int value)
  {
    return FlagSet.fromValue(value, values());
  }
}
