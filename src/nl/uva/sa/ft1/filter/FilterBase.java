package nl.uva.sa.ft1.filter;

import nl.uva.sa.ft1.pipe.Pipe;

public abstract class FilterBase<E, K> implements Filter {
	protected Pipe<E> inPipe;
	protected Pipe<K> outPipe;
	
	public FilterBase(Pipe<E> inPipe, Pipe<K> outPipe) {
		this.inPipe = inPipe;
		this.outPipe = outPipe;
	}
}
