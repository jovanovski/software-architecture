package nl.uva.sa.ft1;

import java.util.List;

public abstract class FilterBase<E, K> {
	protected List<Pipe<E>> inPipes;
	protected List<Pipe<K>> outPipes;
	
	protected abstract boolean filter(E input);
}
