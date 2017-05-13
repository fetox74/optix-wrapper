package com.fetoxdevelopments.optix.api.struct;

import org.bridj.Pointer;
import org.bridj.TypedPointer;

public class RTbuffer
  extends TypedPointer
{
  public RTbuffer(long address)
  {
    super(address);
  }

  public RTbuffer(Pointer address)
  {
    super(address);
  }
}
