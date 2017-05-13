package com.fetoxdevelopments.optix.api;

import java.util.Collections;
import java.util.Iterator;

import org.bridj.FlagSet;
import org.bridj.IntValuedEnum;

/**
 * enum values<br>
 * <i>native declaration : include\internal\optix_declarations.h</i>
 */
public enum RTbufferattribute
  implements IntValuedEnum<RTbufferattribute>
{
  RT_BUFFER_ATTRIBUTE_STREAM_FORMAT(0),
  RT_BUFFER_ATTRIBUTE_STREAM_BITRATE(1),
  RT_BUFFER_ATTRIBUTE_STREAM_FPS(2),
  /** < sizeof(float) */
  RT_BUFFER_ATTRIBUTE_STREAM_GAMMA(3);

  RTbufferattribute(long value)
  {
    this.value = value;
  }

  public final long value;

  public long value()
  {
    return this.value;
  }

  public Iterator<RTbufferattribute> iterator()
  {
    return Collections.singleton(this).iterator();
  }

  public static IntValuedEnum<RTbufferattribute> fromValue(int value)
  {
    return FlagSet.fromValue(value, values());
  }
}
