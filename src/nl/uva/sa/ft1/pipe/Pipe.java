package nl.uva.sa.ft1.pipe;

//A pipe interface that has one generic argument, and multiple methods
public interface Pipe<E>{
	
	//A put method to put an element inside the pipe, returns true on success, false on failure.
    public boolean put(E obj) throws OperationFailedException, PipeClosedException;
    
    //Same as above, but with parameter to indicate blocking should be enabled.
    public boolean put(E obj, boolean blocking) throws OperationFailedException, PipeClosedException;
    
    //A get method to get an element out of the pipe, blocks when no element is available
    public E get() throws OperationFailedException, PipeClosedException;
    
    //A get method with a blocking parameter, indicating whether or not filters using the pipe want to wait for next elements
    //from this pipe before continuing. When used in non-blocking mode, null should be returned when no element is available.
    public E get(boolean blocking) throws OperationFailedException, PipeClosedException;
    
    //Self explanatory
    public boolean isEmpty();
    
    //A method to indicate that the pipe will receive no more input, and can be closed
    public void close() throws OperationFailedException;
    
    //Check if the pipe is closed
    public boolean isClosed();
}