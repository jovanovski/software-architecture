package nl.uva.sa.ft1.pipe;

import java.util.*;

public class SynchronizedArrayListPipe<E> extends AbstractPipe<E> {

    private List<E> buffer = new ArrayList<E>();
    private boolean closed = false;
    
    public synchronized void put(E obj){
        buffer.add(obj);
        notify();
    }
    
    public synchronized E get(boolean blocking) throws OperationFailedException, PipeClosedException {
    	try {    		
    		if (this.isEmpty()) {
    			if (this.isClosed()) {
    				throw new PipeClosedException();
    			}
    			
    			if (blocking) {
    				while(this.isEmpty()) {
    					wait();
    					if (this.isClosed()) {
    		    			throw new PipeClosedException();
    		    		}
    				}
    			} else {
    				return null;
    			}    				
    		}
    		
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