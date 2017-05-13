package com.fetoxdevelopments.optix.api.enumeration;

import java.util.Collections;
import java.util.Iterator;

import org.bridj.FlagSet;
import org.bridj.IntValuedEnum;

/**
 * enum values<br>
 * <i>native declaration : include\internal\optix_declarations.h</i>
 */
public enum RTprogramidnull
  implements IntValuedEnum<RTprogramidnull>
{
  /** < sentinel for describing a non-existent program id */
  RT_PROGRAM_ID_NULL(0);

  RTprogramidnull(long value)
  {
    this.value = value;
  }

  public final long value;

  public long value()
  {
    return this.value;
  }

  public Iterator<RTprogramidnull> iterator()
  {
    return Collections.singleton(this).iterator();
  }

  public static IntValuedEnum<RTprogramidnull> fromValue(int value)
  {
    return FlagSet.fromValue(value, values());
  }
}
