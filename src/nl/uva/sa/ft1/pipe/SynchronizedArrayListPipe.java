package nl.uva.sa.ft1.pipe;

import java.util.*;

public class SynchronizedArrayListPipe<E> implements Pipe<E>{

    private List<E> buffer = new ArrayList<E>();

    public synchronized void put(E obj){
        buffer.add(obj);
        notify();
    }

    public synchronized E get() throws OperationFailedException {
    	try {
    		while(buffer.isEmpty()) wait();
            E obj = buffer.remove(0);
            return obj;	
    	} catch (InterruptedException e) {
    		throw new OperationFailedException();
    	}        
    }
}