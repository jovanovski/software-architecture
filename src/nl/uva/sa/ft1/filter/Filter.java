package nl.uva.sa.ft1.filter;

import java.util.List;

import nl.uva.sa.ft1.pipe.Pipe;

public interface Filter<E, K> extends Runnable {
	public boolean setPipesIn(List<Pipe<E>> pipes);
	public boolean setPipesOut(List<Pipe<K>> pipes);
}
