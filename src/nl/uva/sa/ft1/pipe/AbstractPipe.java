package nl.uva.sa.ft1.pipe;

public abstract class AbstractPipe<E> implements Pipe<E>{
	public void put(E obj) throws OperationFailedException, PipeClosedException {
		this.put(obj, true);
	}
	
	public E get() throws OperationFailedException, PipeClosedException {
		return this.get(true);
	}
}
