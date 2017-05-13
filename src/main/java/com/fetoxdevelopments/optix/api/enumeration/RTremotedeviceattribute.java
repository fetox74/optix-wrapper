package com.fetoxdevelopments.optix.api.enumeration;

import java.util.Collections;
import java.util.Iterator;

import org.bridj.FlagSet;
import org.bridj.IntValuedEnum;

/**
 * enum values<br>
 * <i>native declaration : include\internal\optix_declarations.h</i>
 */
public enum RTremotedeviceattribute
  implements IntValuedEnum<RTremotedeviceattribute>
{
  RT_REMOTEDEVICE_ATTRIBUTE_CLUSTER_URL(0),
  RT_REMOTEDEVICE_ATTRIBUTE_HEAD_NODE_URL(1),
  RT_REMOTEDEVICE_ATTRIBUTE_NUM_CONFIGURATIONS(2),
  RT_REMOTEDEVICE_ATTRIBUTE_STATUS(3),
  RT_REMOTEDEVICE_ATTRIBUTE_NUM_TOTAL_NODES(4),
  RT_REMOTEDEVICE_ATTRIBUTE_NUM_FREE_NODES(5),
  RT_REMOTEDEVICE_ATTRIBUTE_NUM_RESERVED_NODES(6),
  RT_REMOTEDEVICE_ATTRIBUTE_NAME(7),
  RT_REMOTEDEVICE_ATTRIBUTE_NUM_GPUS(8),
  RT_REMOTEDEVICE_ATTRIBUTE_GPU_TOTAL_MEMORY(9),
  /** < List of descriptions for the available configurations */
  RT_REMOTEDEVICE_ATTRIBUTE_CONFIGURATIONS(0x040000000);

  RTremotedeviceattribute(long value)
  {
    this.value = value;
  }

  public final long value;

  public long value()
  {
    return this.value;
  }

  public Iterator<RTremotedeviceattribute> iterator()
  {
    return Collections.singleton(this).iterator();
  }

  public static IntValuedEnum<RTremotedeviceattribute> fromValue(int value)
  {
    return FlagSet.fromValue(value, values());
  }
}
