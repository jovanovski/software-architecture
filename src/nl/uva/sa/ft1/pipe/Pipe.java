package nl.uva.sa.ft1.pipe;

public interface Pipe<E>{
    public void put(E obj) throws OperationFailedException, PipeClosedException;
    public void put(E obj, boolean blocking) throws OperationFailedException, PipeClosedException;
    public E get() throws OperationFailedException, PipeClosedException;
    public E get(boolean blocking) throws OperationFailedException, PipeClosedException;
    public boolean isEmpty();
    public void close() throws OperationFailedException;
    public boolean isClosed();
}