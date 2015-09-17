package nl.uva.sa.ft1;

import java.util.List;

public interface Filter<E, K> extends Runnable {
	public boolean setPipesIn(List<Pipe<E>> pipes);
	public boolean setPipesOut(List<Pipe<K>> pipes);
}
