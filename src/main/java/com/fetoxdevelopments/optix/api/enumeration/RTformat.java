package com.fetoxdevelopments.optix.api.enumeration;

import java.util.Collections;
import java.util.Iterator;

import org.bridj.FlagSet;
import org.bridj.IntValuedEnum;

/**
 * enum values<br>
 * <i>native declaration : include\internal\optix_declarations.h</i>
 */
public enum RTformat
  implements IntValuedEnum<RTformat>
{
  /** < Format unknown */
  RT_FORMAT_UNKNOWN(0x100),
  RT_FORMAT_FLOAT((0x100 + 1)),
  RT_FORMAT_FLOAT2((0x100 + 2)),
  RT_FORMAT_FLOAT3((0x100 + 3)),
  RT_FORMAT_FLOAT4((0x100 + 4)),
  RT_FORMAT_BYTE((0x100 + 5)),
  RT_FORMAT_BYTE2((0x100 + 6)),
  RT_FORMAT_BYTE3((0x100 + 7)),
  RT_FORMAT_BYTE4((0x100 + 8)),
  RT_FORMAT_UNSIGNED_BYTE((0x100 + 9)),
  RT_FORMAT_UNSIGNED_BYTE2((0x100 + 10)),
  RT_FORMAT_UNSIGNED_BYTE3((0x100 + 11)),
  RT_FORMAT_UNSIGNED_BYTE4((0x100 + 12)),
  RT_FORMAT_SHORT((0x100 + 13)),
  RT_FORMAT_SHORT2((0x100 + 14)),
  RT_FORMAT_SHORT3((0x100 + 15)),
  RT_FORMAT_SHORT4((0x100 + 16)),
  RT_FORMAT_UNSIGNED_SHORT((0x100 + 17)),
  RT_FORMAT_UNSIGNED_SHORT2((0x100 + 18)),
  RT_FORMAT_UNSIGNED_SHORT3((0x100 + 19)),
  RT_FORMAT_UNSIGNED_SHORT4((0x100 + 20)),
  RT_FORMAT_INT((0x100 + 21)),
  RT_FORMAT_INT2((0x100 + 22)),
  RT_FORMAT_INT3((0x100 + 23)),
  RT_FORMAT_INT4((0x100 + 24)),
  RT_FORMAT_UNSIGNED_INT((0x100 + 25)),
  RT_FORMAT_UNSIGNED_INT2((0x100 + 26)),
  RT_FORMAT_UNSIGNED_INT3((0x100 + 27)),
  RT_FORMAT_UNSIGNED_INT4((0x100 + 28)),
  RT_FORMAT_USER((0x100 + 29)),
  RT_FORMAT_BUFFER_ID((0x100 + 30)),
  RT_FORMAT_PROGRAM_ID((0x100 + 31)),
  RT_FORMAT_HALF((0x100 + 32)),
  RT_FORMAT_HALF2((0x100 + 33)),
  RT_FORMAT_HALF3((0x100 + 34)),
  /** < sizeof(half float)*4 */
  RT_FORMAT_HALF4((0x100 + 35));

  RTformat(long value)
  {
    this.value = value;
  }

  public final long value;

  public long value()
  {
    return this.value;
  }

  public Iterator<RTformat> iterator()
  {
    return Collections.singleton(this).iterator();
  }

  public static IntValuedEnum<RTformat> fromValue(int value)
  {
    return FlagSet.fromValue(value, values());
  }
}
