package nl.uva.sa.ft1.pipe;

import java.util.*;

public class SynchronizedArrayListPipe<E> implements Pipe<E>{

    private List<E> buffer = new ArrayList<E>();
    private boolean closed = false;

    public synchronized void put(E obj){
        buffer.add(obj);
        notify();
    }
    
    public E get() throws OperationFailedException {
    	return get(true);
    }

    public synchronized E get(boolean blocking) throws OperationFailedException {
    	try {
    		if (buffer.isEmpty()) {
    			if (blocking) {
    				while(buffer.isEmpty()) wait();	
    			} else {
    				return null;
    			}    				
    		} 
    		
    		if (closed) {
    			throw new PipeClosedException();
    		}
    		
            E obj = buffer.remove(0);
            return obj;	
    	} catch (InterruptedException e) {
    		throw new OperationFailedException();
    	}        
    }
    
    public synchronized void close() {
    	this.closed = true;
    	this.notifyAll();
    }
    
    public boolean isClosed() {
    	return this.closed;
    }
}