package com.fetoxdevelopments.optix.api;

import java.util.Collections;
import java.util.Iterator;

import org.bridj.FlagSet;
import org.bridj.IntValuedEnum;

/**
 * enum values<br>
 * <i>native declaration : include\internal\optix_declarations.h</i>
 */
public enum RTtextureindexmode
  implements IntValuedEnum<RTtextureindexmode>
{
  RT_TEXTURE_INDEX_NORMALIZED_COORDINATES(0),
  /** < Texture Index Array */
  RT_TEXTURE_INDEX_ARRAY_INDEX(1);

  RTtextureindexmode(long value)
  {
    this.value = value;
  }

  public final long value;

  public long value()
  {
    return this.value;
  }

  public Iterator<RTtextureindexmode> iterator()
  {
    return Collections.singleton(this).iterator();
  }

  public static IntValuedEnum<RTtextureindexmode> fromValue(int value)
  {
    return FlagSet.fromValue(value, values());
  }
}
