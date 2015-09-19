package nl.uva.sa.ft1.pipe;

//A pipe interface that has one generic argument, and multiple methods
public interface Pipe<E>{
	
	//A put method to put an element inside the pipe
    public void put(E obj) throws OperationFailedException, PipeClosedException;
    
    //Same as above, blocking not yet implemented
    public void put(E obj, boolean blocking) throws OperationFailedException, PipeClosedException;
    
    //A get method to get an element out of the pipe
    public E get() throws OperationFailedException, PipeClosedException;
    
    //A get method with a blocking parameter, indicating that filters using the pipe have to wait for next elements
    //from this pipe before continuing
    public E get(boolean blocking) throws OperationFailedException, PipeClosedException;
    
    //Self explanatory
    public boolean isEmpty();
    
    //A method to indicate that the pipe will receive no more input, and can be closed
    public void close() throws OperationFailedException;
    
    //Check if the pipe is closed
    public boolean isClosed();
}