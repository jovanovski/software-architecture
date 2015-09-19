package nl.uva.sa.ft1.filter;

//A simple filter interface that requires that all filters implement the run() method from Runnable, 
//in order to be thread-friendly.
public interface Filter extends Runnable {}
