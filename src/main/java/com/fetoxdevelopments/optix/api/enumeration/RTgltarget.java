package com.fetoxdevelopments.optix.api.enumeration;

import java.util.Collections;
import java.util.Iterator;

import org.bridj.FlagSet;
import org.bridj.IntValuedEnum;

/**
 * enum values<br>
 * <i>native declaration : include\internal\optix_declarations.h</i>
 */
public enum RTgltarget
  implements IntValuedEnum<RTgltarget>
{
  RT_TARGET_GL_TEXTURE_2D(0),
  RT_TARGET_GL_TEXTURE_RECTANGLE(1),
  RT_TARGET_GL_TEXTURE_3D(2),
  RT_TARGET_GL_RENDER_BUFFER(3),
  RT_TARGET_GL_TEXTURE_1D(4),
  RT_TARGET_GL_TEXTURE_1D_ARRAY(5),
  RT_TARGET_GL_TEXTURE_2D_ARRAY(6),
  RT_TARGET_GL_TEXTURE_CUBE_MAP(7),
  /** < GL array of cube maps */
  RT_TARGET_GL_TEXTURE_CUBE_MAP_ARRAY(8);

  RTgltarget(long value)
  {
    this.value = value;
  }

  public final long value;

  public long value()
  {
    return this.value;
  }

  public Iterator<RTgltarget> iterator()
  {
    return Collections.singleton(this).iterator();
  }

  public static IntValuedEnum<RTgltarget> fromValue(int value)
  {
    return FlagSet.fromValue(value, values());
  }
}
