package nl.uva.sa.ft1.pipe;

import java.util.*;

public class SynchronizedArrayListPipe<E> extends AbstractPipe<E> {

	//A buffer list that stores all added elements to the pipe
    private List<E> buffer = new ArrayList<E>();
    
    //Flag for closed pipe
    private boolean closed = false;
    
    public synchronized void put(E obj, boolean blocking) throws OperationFailedException, PipeClosedException {
    	//Throw an exception if someone tries to put an element to a closed pipe
    	if (this.isClosed()) {
    		throw new PipeClosedException();
    	}
    	//Add an element to the buffer
        buffer.add(obj);
        
        //Notify any waiting process that an element has been added
        notify();
    }
    
    public synchronized E get(boolean blocking) throws OperationFailedException, PipeClosedException {
    	try {    		
    		if (this.isEmpty()) {
    			//If the buffer is empty and the pipe is closed, throw a PipeClosedException
    			if (this.isClosed()) {
    				throw new PipeClosedException();
    			}
    			//If pipe is not closed, buffer is empty and blocking in enabled, then wait while an element is added
    			//to the buffer. If the pipe gets closed in the meantime, throw an exception
    			if (blocking) {
    				while(this.isEmpty()) {
    					wait();
    					if (this.isClosed()) {
    		    			throw new PipeClosedException();
    		    		}
    				}
    			//If the pipe is not closed, buffer is empty and blocking is not enabled, then return a null as not
    			//to make the filters wait while a new element is added to the pipe (null != closed pipe, just empty
    			//buffer/nothing to return right now, continue to next pipe)
    			} else {
    				return null;
    			}    				
    		}
    		//If buffer is not empty and pipe is not closed, return an element from the buffer
            E obj = buffer.remove(0);
            return obj;	
    	} catch (InterruptedException e) {
    		throw new OperationFailedException();
    	}
    }
    
    public boolean isEmpty() {
    	return buffer.isEmpty();
    }
    
    public synchronized void close() {
    	this.closed = true;
    	this.notifyAll();
    }
    
    
    public boolean isClosed() {
    	return this.closed;
    }
}