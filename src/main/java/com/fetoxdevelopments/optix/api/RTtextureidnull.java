package com.fetoxdevelopments.optix.api;

import java.util.Collections;
import java.util.Iterator;

import org.bridj.FlagSet;
import org.bridj.IntValuedEnum;

/**
 * enum values<br>
 * <i>native declaration : include\internal\optix_declarations.h</i>
 */
public enum RTtextureidnull
  implements IntValuedEnum<RTtextureidnull>
{
  /** < sentinel for describing a non-existent texture id */
  RT_TEXTURE_ID_NULL(0);

  RTtextureidnull(long value)
  {
    this.value = value;
  }

  public final long value;

  public long value()
  {
    return this.value;
  }

  public Iterator<RTtextureidnull> iterator()
  {
    return Collections.singleton(this).iterator();
  }

  public static IntValuedEnum<RTtextureidnull> fromValue(int value)
  {
    return FlagSet.fromValue(value, values());
  }
}
