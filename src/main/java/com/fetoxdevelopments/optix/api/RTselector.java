package com.fetoxdevelopments.optix.api;

import org.bridj.Pointer;
import org.bridj.TypedPointer;

public class RTselector
  extends TypedPointer
{
  public RTselector(long address)
  {
    super(address);
  }

  public RTselector(Pointer address)
  {
    super(address);
  }
}
