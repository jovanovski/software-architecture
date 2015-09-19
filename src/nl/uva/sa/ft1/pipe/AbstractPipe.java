package nl.uva.sa.ft1.pipe;

//An abstract pipe class with one generic argument
public abstract class AbstractPipe<E> implements Pipe<E>{
	
	//Maps the normal put method to a blocking put method
	public boolean put(E obj) throws OperationFailedException, PipeClosedException {
		return this.put(obj, true);
	}
	
	//Maps the normal get method to a blocking get method
	public E get() throws OperationFailedException, PipeClosedException {
		return this.get(true);
	}
}
