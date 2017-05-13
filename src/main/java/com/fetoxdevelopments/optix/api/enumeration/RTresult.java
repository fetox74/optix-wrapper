package com.fetoxdevelopments.optix.api.enumeration;

import java.util.Collections;
import java.util.Iterator;

import org.bridj.FlagSet;
import org.bridj.IntValuedEnum;

/**
 * enum values<br>
 * <i>native declaration : include\internal\optix_declarations.h</i>
 */
public enum RTresult
  implements IntValuedEnum<RTresult>
{
  /** < Success */
  RT_SUCCESS(0),
  /** < Timeout callback */
  RT_TIMEOUT_CALLBACK(0x100),
  /** < Invalid Context */
  RT_ERROR_INVALID_CONTEXT(0x500),
  /** < Invalid Value */
  RT_ERROR_INVALID_VALUE(0x501),
  /** < Timeout callback */
  RT_ERROR_MEMORY_ALLOCATION_FAILED(0x502),
  /** < Type Mismatch */
  RT_ERROR_TYPE_MISMATCH(0x503),
  /** < Variable not found */
  RT_ERROR_VARIABLE_NOT_FOUND(0x504),
  /** < Variable redeclared */
  RT_ERROR_VARIABLE_REDECLARED(0x505),
  /** < Illegal symbol */
  RT_ERROR_ILLEGAL_SYMBOL(0x506),
  /** < Invalid source */
  RT_ERROR_INVALID_SOURCE(0x507),
  /** < Version mismatch */
  RT_ERROR_VERSION_MISMATCH(0x508),
  /** < Object creation failed */
  RT_ERROR_OBJECT_CREATION_FAILED(0x600),
  /** < No device */
  RT_ERROR_NO_DEVICE(0x601),
  /** < Invalid device */
  RT_ERROR_INVALID_DEVICE(0x602),
  /** < Invalid image */
  RT_ERROR_INVALID_IMAGE(0x603),
  /** < File not found */
  RT_ERROR_FILE_NOT_FOUND(0x604),
  /** < Already mapped */
  RT_ERROR_ALREADY_MAPPED(0x605),
  /** < Invalid driver version */
  RT_ERROR_INVALID_DRIVER_VERSION(0x606),
  /** < Context creation failed */
  RT_ERROR_CONTEXT_CREATION_FAILED(0x607),
  /** < Resource not registered */
  RT_ERROR_RESOURCE_NOT_REGISTERED(0x608),
  /** < Resource already registered */
  RT_ERROR_RESOURCE_ALREADY_REGISTERED(0x609),
  /** < Launch failed */
  RT_ERROR_LAUNCH_FAILED(0x900),
  /** < Not supported */
  RT_ERROR_NOT_SUPPORTED(0xA00),
  /** < Connection failed */
  RT_ERROR_CONNECTION_FAILED(0xB00),
  /** < Authentication failed */
  RT_ERROR_AUTHENTICATION_FAILED(0xB01),
  /** < Connection already exists */
  RT_ERROR_CONNECTION_ALREADY_EXISTS(0xB02),
  /** < Network component failed to load */
  RT_ERROR_NETWORK_LOAD_FAILED(0xB03),
  /** < Network initialization failed */
  RT_ERROR_NETWORK_INIT_FAILED(0xB04),
  /** < No cluster is running */
  RT_ERROR_CLUSTER_NOT_RUNNING(0xB06),
  /** < Cluster is already running */
  RT_ERROR_CLUSTER_ALREADY_RUNNING(0xB07),
  /** < Not enough free nodes */
  RT_ERROR_INSUFFICIENT_FREE_NODES(0xB08),
  /** < Error unknown */
  RT_ERROR_UNKNOWN(~0);

  RTresult(long value)
  {
    this.value = value;
  }

  public final long value;

  public long value()
  {
    return this.value;
  }

  public Iterator<RTresult> iterator()
  {
    return Collections.singleton(this).iterator();
  }

  public static IntValuedEnum<RTresult> fromValue(int value)
  {
    return FlagSet.fromValue(value, values());
  }
}
