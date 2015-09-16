package nl.uva.sa.ft1;

interface Pipe<E>{
    public boolean put(E obj);
    public E get() throws InterruptedException;
}