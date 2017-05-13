package com.fetoxdevelopments.optix.api.enumeration;

import java.util.Collections;
import java.util.Iterator;

import org.bridj.FlagSet;
import org.bridj.IntValuedEnum;

/**
 * enum values<br>
 * <i>native declaration : include\internal\optix_declarations.h</i>
 */
public enum RTbufferidnull
  implements IntValuedEnum<RTbufferidnull>
{
  /** < sentinel for describing a non-existent buffer id */
  RT_BUFFER_ID_NULL(0);

  RTbufferidnull(long value)
  {
    this.value = value;
  }

  public final long value;

  public long value()
  {
    return this.value;
  }

  public Iterator<RTbufferidnull> iterator()
  {
    return Collections.singleton(this).iterator();
  }

  public static IntValuedEnum<RTbufferidnull> fromValue(int value)
  {
    return FlagSet.fromValue(value, values());
  }
}
