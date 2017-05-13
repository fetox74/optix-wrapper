package com.fetoxdevelopments.optix.api.enumeration;

import java.util.Collections;
import java.util.Iterator;

import org.bridj.FlagSet;
import org.bridj.IntValuedEnum;

/**
 * enum values<br>
 * <i>native declaration : include\internal\optix_declarations.h</i>
 */
public enum RTobjecttype
  implements IntValuedEnum<RTobjecttype>
{
  /** < Object Type Unknown */
  RT_OBJECTTYPE_UNKNOWN(0x200),
  RT_OBJECTTYPE_GROUP((0x200 + 1)),
  RT_OBJECTTYPE_GEOMETRY_GROUP((0x200 + 2)),
  RT_OBJECTTYPE_TRANSFORM((0x200 + 3)),
  RT_OBJECTTYPE_SELECTOR((0x200 + 4)),
  RT_OBJECTTYPE_GEOMETRY_INSTANCE((0x200 + 5)),
  RT_OBJECTTYPE_BUFFER((0x200 + 6)),
  RT_OBJECTTYPE_TEXTURE_SAMPLER((0x200 + 7)),
  RT_OBJECTTYPE_OBJECT((0x200 + 8)),
  RT_OBJECTTYPE_MATRIX_FLOAT2x2((0x200 + 9)),
  RT_OBJECTTYPE_MATRIX_FLOAT2x3((0x200 + 10)),
  RT_OBJECTTYPE_MATRIX_FLOAT2x4((0x200 + 11)),
  RT_OBJECTTYPE_MATRIX_FLOAT3x2((0x200 + 12)),
  RT_OBJECTTYPE_MATRIX_FLOAT3x3((0x200 + 13)),
  RT_OBJECTTYPE_MATRIX_FLOAT3x4((0x200 + 14)),
  RT_OBJECTTYPE_MATRIX_FLOAT4x2((0x200 + 15)),
  RT_OBJECTTYPE_MATRIX_FLOAT4x3((0x200 + 16)),
  RT_OBJECTTYPE_MATRIX_FLOAT4x4((0x200 + 17)),
  RT_OBJECTTYPE_FLOAT((0x200 + 18)),
  RT_OBJECTTYPE_FLOAT2((0x200 + 19)),
  RT_OBJECTTYPE_FLOAT3((0x200 + 20)),
  RT_OBJECTTYPE_FLOAT4((0x200 + 21)),
  RT_OBJECTTYPE_INT((0x200 + 22)),
  RT_OBJECTTYPE_INT2((0x200 + 23)),
  RT_OBJECTTYPE_INT3((0x200 + 24)),
  RT_OBJECTTYPE_INT4((0x200 + 25)),
  RT_OBJECTTYPE_UNSIGNED_INT((0x200 + 26)),
  RT_OBJECTTYPE_UNSIGNED_INT2((0x200 + 27)),
  RT_OBJECTTYPE_UNSIGNED_INT3((0x200 + 28)),
  RT_OBJECTTYPE_UNSIGNED_INT4((0x200 + 29)),
  RT_OBJECTTYPE_USER((0x200 + 30)),
  /** < Object Type Program - Added in OptiX 3.0 */
  RT_OBJECTTYPE_PROGRAM((0x200 + 31));

  RTobjecttype(long value)
  {
    this.value = value;
  }

  public final long value;

  public long value()
  {
    return this.value;
  }

  public Iterator<RTobjecttype> iterator()
  {
    return Collections.singleton(this).iterator();
  }

  public static IntValuedEnum<RTobjecttype> fromValue(int value)
  {
    return FlagSet.fromValue(value, values());
  }
}
