package nl.uva.sa.ft1;

import java.util.*;

public class PipeImpl<E> implements Pipe<E>{

    private List<E> buffer = new ArrayList<E>();

    public synchronized boolean put(E obj){
        boolean bAdded = buffer.add(obj);
        notify();
        return bAdded;
    }

    public synchronized E get() throws InterruptedException{
        while(buffer.isEmpty()) wait();
        E obj = buffer.remove(0);
        return obj;
    }

}