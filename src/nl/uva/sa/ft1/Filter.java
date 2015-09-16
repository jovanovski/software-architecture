package nl.uva.sa.ft1;

public interface Filter<E> extends Runnable {
	public boolean setPipeIn(Pipe<E> pipe);
	public boolean setPipeOut(Pipe<E> pipe);
}
