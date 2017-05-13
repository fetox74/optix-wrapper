package com.fetoxdevelopments.optix.api;

import java.util.Collections;
import java.util.Iterator;

import org.bridj.FlagSet;
import org.bridj.IntValuedEnum;

/**
 * enum values<br>
 * <i>native declaration : include\internal\optix_declarations.h</i>
 */
public enum RTtexturereadmode
  implements IntValuedEnum<RTtexturereadmode>
{
  /** < Read element type */
  RT_TEXTURE_READ_ELEMENT_TYPE(0),
  /** < Read normalized float */
  RT_TEXTURE_READ_NORMALIZED_FLOAT(1),
  /** < Read element type and apply sRGB to linear conversion during texture read for 8-bit integer buffer formats */
  RT_TEXTURE_READ_ELEMENT_TYPE_SRGB(2),
  /** < Read normalized float and apply sRGB to linear conversion during texture read for 8-bit integer buffer formats */
  RT_TEXTURE_READ_NORMALIZED_FLOAT_SRGB(3);

  RTtexturereadmode(long value)
  {
    this.value = value;
  }

  public final long value;

  public long value()
  {
    return this.value;
  }

  public Iterator<RTtexturereadmode> iterator()
  {
    return Collections.singleton(this).iterator();
  }

  public static IntValuedEnum<RTtexturereadmode> fromValue(int value)
  {
    return FlagSet.fromValue(value, values());
  }
}
