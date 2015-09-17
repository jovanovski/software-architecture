package nl.uva.sa.ft1.pipe;

public interface Pipe<E>{
    public void put(E obj) throws OperationFailedException;
    public E get(boolean blocking) throws OperationFailedException;
    public E get() throws OperationFailedException, PipeClosedException;
    public boolean isEmpty();
    public void close();
    public boolean isClosed();
}