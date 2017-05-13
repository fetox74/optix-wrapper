package com.fetoxdevelopments.optix.api;

import org.bridj.Pointer;
import org.bridj.TypedPointer;

public class RTvariable
  extends TypedPointer
{
  public RTvariable(long address)
  {
    super(address);
  }

  public RTvariable(Pointer address)
  {
    super(address);
  }
}
