package nl.uva.sa.ft1.pipe;

import nl.uva.sa.ft1.*;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueuePipe<E> implements Pipe<E> {

    private BlockingQueue<E> buffer;
    
    public BlockingQueuePipe(int capacity) {
    	this.buffer = new ArrayBlockingQueue<E>(capacity);
    }

    public void put(E obj) throws OperationFailedException {
    	try {
    		buffer.put(obj);
    	} catch (InterruptedException e) {
    		throw new OperationFailedException();
    	}
        
    }

    public E get() throws OperationFailedException {
        try {
        	return buffer.take();
        } catch (InterruptedException e) {
        	throw new OperationFailedException();
        }
    }
}