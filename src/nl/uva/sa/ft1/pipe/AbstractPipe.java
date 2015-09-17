package nl.uva.sa.ft1.pipe;

public abstract class AbstractPipe<E> implements Pipe<E>{
	public synchronized E get() throws OperationFailedException {
		return this.get(true);
	}
}
