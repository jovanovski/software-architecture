# Software Architecture 2015-2016 - Group FT_1
 
[![Build Status](https://travis-ci.org/jovanovski/software-architecture.svg?branch=master)](https://travis-ci.org/jovanovski/software-architecture)

## Build instructions

### Build system
Gradle is used to build all artifacts. It can be run manually from the command line or from inside an IDE.

### IDE
The system is developed in Eclipse using the Buildship plugin to interface with the Gradle build system. Any other IDE that can use Gradle will probably work.

### Further installation instructions
You need an Internet connection to be able to download dependencies.

### Build/Test instructions
To build the system run `gradle build`. The build the system and run all tests run `gradle test`. To run the sample executable run `gradle run`

## Architectural purpose

The pipe & filter pattern is an architectural pattern that is useful for applying subsequent operations on input data. Filters are algorithms operating on the data and pipes connect the output of one filter to the next, creating a pipeline. This pattern allows operations to be implemented as simple steps, that can be combined to create more complex operations. Steps can then be rearranged to create new complex operations without needing to implement new algorithms. Examples of good use-cases for pipe-filter patterns would be:

- Log parsing, filtering & redirection
- Signal processing
- Email processing 
- Or any other case that has a number of well defined steps.

## Library overview

The main part of the framework is the Pipe interface. It's main implementation is SynchronizedArrayListPipe. The pipe is intended to facilitate the transfer of processed data between filtering components. It is thread-safe and offers an optionally non-blocking API for reading and writing data. In it's current state, the buffer size of a pipeline is not limited, but future versions could add a maximum capacity. In the current form, filters are completely free form components that use pipes for input and output. filters are expected to run in separate threads and therefore the Filter interface extends Runnable. Pipes can be closed to indicate no more data is coming. For now this is only intended to be done by the source, but future development might also allow pipe destinations to close the pipe and stop the source from generating data. Filters are expected to stop processing when it's input pipe (or multiple) is closed.   

Some sample filters are provided for reference. Among the samples are filters that:

- Filter strings to pass along the next pipe (LogingFilter).
- Transform strings passed to it into a count of the number of strings (ExceptionCountfilter, VerboseCountFilter).
- Branch one input pipe into several output pipes (BranchingFilter).
- Merge several input pipes into one output pipe (MergingFilter).

There is also a sample application containing a random log message generator that uses the sample filters to filter exception log messages and compute a count of verbose and exception log messages. 

Test coverage is not complete, but tests exists for the SynchronizedArrayListPipe, BranchingFilter and MergingFilter.

