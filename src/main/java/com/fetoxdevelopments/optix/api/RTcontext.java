package com.fetoxdevelopments.optix.api;

import org.bridj.Pointer;
import org.bridj.TypedPointer;

public class RTcontext
  extends TypedPointer
{
  public RTcontext(long address)
  {
    super(address);
  }

  public RTcontext(Pointer address)
  {
    super(address);
  }
}
