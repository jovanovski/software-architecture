package nl.uva.sa.ft1;

import nl.uva.sa.ft1.pipe.OperationFailedException;

public interface Pipe<E>{
    public void put(E obj) throws OperationFailedException;
    public E get() throws OperationFailedException;
}