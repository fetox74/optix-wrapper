package com.fetoxdevelopments.optix.api.enumeration;

import java.util.Collections;
import java.util.Iterator;

import org.bridj.FlagSet;
import org.bridj.IntValuedEnum;

/**
 * enum values<br>
 * <i>native declaration : include\internal\optix_declarations.h</i>
 */
public enum RTbuffermapflag
  implements IntValuedEnum<RTbuffermapflag>
{
  /** < Map buffer memory for reading */
  RT_BUFFER_MAP_READ(0x1),
  /** < Map buffer memory for both reading and writing */
  RT_BUFFER_MAP_READ_WRITE(0x2),
  /** < Map buffer memory for writing */
  RT_BUFFER_MAP_WRITE(0x4),
  /** < Map buffer memory for writing, with the previous contents being undefined */
  RT_BUFFER_MAP_WRITE_DISCARD(0x8);

  RTbuffermapflag(long value)
  {
    this.value = value;
  }

  public final long value;

  public long value()
  {
    return this.value;
  }

  public Iterator<RTbuffermapflag> iterator()
  {
    return Collections.singleton(this).iterator();
  }

  public static IntValuedEnum<RTbuffermapflag> fromValue(int value)
  {
    return FlagSet.fromValue(value, values());
  }
}
