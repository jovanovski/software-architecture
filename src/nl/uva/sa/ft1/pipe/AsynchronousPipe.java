package nl.uva.sa.ft1.pipe;

public interface AsynchronousPipe<E> extends Pipe<E> {
    public E get(boolean nonBlocking) throws OperationFailedException;
}
