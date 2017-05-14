# NV Optix Wrapper
Very early state of a wrapper for the nVidia Optix Toolkit, so far only wrapping the C API but working. Future aim is to provide full access to
the C++ API aswell in nice and easy to use Java classes. Makes heavy use of BridJ and JNAerator for accessing the native lib.

## Usage

Optix SDK 4.0.2 is needed, copy the optix.1.dll into project root, aswell as the required .ptx files, so far all optixHello_* and optixSphere_* ones.
Should be able to launch this from your preferred IDE after adding these. Doesn't do much but putting a green result.png into your root dir, at least for the
moment. Working on OptixSphere which shout actually render a basic sphere :P

### Known issues:

_- OptixSphere not working_

_- C++ API missing_