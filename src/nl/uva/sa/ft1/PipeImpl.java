package nl.uva.sa.ft1;

import java.util.*;

import nl.uva.sa.ft1.pipe.OperationFailedException;

public class PipeImpl<E> implements Pipe<E>{

    private List<E> buffer = new ArrayList<E>();

    public void put(E obj){
        buffer.add(obj);
        notify();
    }

    public E get() throws OperationFailedException {
    	try {
    		while(buffer.isEmpty()) wait();
            E obj = buffer.remove(0);
            return obj;	
    	} catch (InterruptedException e) {
    		throw new OperationFailedException();
    	}        
    }

}