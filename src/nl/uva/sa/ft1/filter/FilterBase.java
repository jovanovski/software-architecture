package nl.uva.sa.ft1.filter;

import java.util.List;

import nl.uva.sa.ft1.pipe.Pipe;

public abstract class FilterBase<E, K> {
	protected List<Pipe<E>> inPipes;
	protected List<Pipe<K>> outPipes;
	
	protected abstract boolean filter(E input);
}
