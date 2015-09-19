package nl.uva.sa.ft1.filter;

import nl.uva.sa.ft1.pipe.Pipe;

//A base filter class that is abstract and generic for 2 types of generic arguments so that the input and output can 
//be different types of data
public abstract class FilterBase<E, K> implements Filter {
	protected Pipe<E> inPipe;
	protected Pipe<K> outPipe;
	
	public FilterBase(Pipe<E> inPipe, Pipe<K> outPipe) {
		this.inPipe = inPipe;
		this.outPipe = outPipe;
	}
}
