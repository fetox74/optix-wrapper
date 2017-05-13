package com.fetoxdevelopments.optix.api.struct;

import org.bridj.Pointer;
import org.bridj.TypedPointer;

public class RTprogram
  extends TypedPointer
{
  public RTprogram(long address)
  {
    super(address);
  }

  public RTprogram(Pointer address)
  {
    super(address);
  }
}
