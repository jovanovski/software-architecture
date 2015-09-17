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
		if (this.isClosed()) {
			throw new PipeClosedException();
		}
		
    	try {    		
    		if (buffer.isEmpty()) {
    			if (blocking) {
    				while(buffer.isEmpty() && !this.isClosed()) wait();
    			} else {
    				return null;
    			}    				
    		}
    		
    		if (this.isClosed()) {
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
    	return buffer.isEmpty() && this.closed;
    }
}