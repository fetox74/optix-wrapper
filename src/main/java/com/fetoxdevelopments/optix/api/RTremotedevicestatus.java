package com.fetoxdevelopments.optix.api;

import java.util.Collections;
import java.util.Iterator;

import org.bridj.FlagSet;
import org.bridj.IntValuedEnum;

/**
 * enum values<br>
 * <i>native declaration : include\internal\optix_declarations.h</i>
 */
public enum RTremotedevicestatus
  implements IntValuedEnum<RTremotedevicestatus>
{
  RT_REMOTEDEVICE_STATUS_READY(0),
  RT_REMOTEDEVICE_STATUS_CONNECTED(1),
  RT_REMOTEDEVICE_STATUS_RESERVED(2),
  /** < RemoteDevice Status Disconnected */
  RT_REMOTEDEVICE_STATUS_DISCONNECTED(~0);

  RTremotedevicestatus(long value)
  {
    this.value = value;
  }

  public final long value;

  public long value()
  {
    return this.value;
  }

  public Iterator<RTremotedevicestatus> iterator()
  {
    return Collections.singleton(this).iterator();
  }

  public static IntValuedEnum<RTremotedevicestatus> fromValue(int value)
  {
    return FlagSet.fromValue(value, values());
  }
}
