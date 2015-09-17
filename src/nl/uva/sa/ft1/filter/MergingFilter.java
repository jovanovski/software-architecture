package nl.uva.sa.ft1.filter;

import java.util.ArrayList;
import java.util.List;

import nl.uva.sa.ft1.pipe.OperationFailedException;
import nl.uva.sa.ft1.pipe.Pipe;
import nl.uva.sa.ft1.pipe.PipeClosedException;

public class MergingFilter<E> implements Filter {
	List<Pipe<E>> pipesIn;
	Pipe<E> pipeOut;

	public MergingFilter(List<Pipe<E>> pipesIn, Pipe<E> pipeOut) {
		this.pipesIn = pipesIn;
		this.pipeOut = pipeOut;
	}
	
	public void run() {
		List<Pipe<E>> openPipes = pipesIn;
		List<Pipe<E>> closedPipes = new ArrayList<>();
		
		try {
			while (!openPipes.isEmpty()) {
				for (Pipe<E> pipe : openPipes) {
					try {
						E obj = pipe.get(false);
						if (obj != null) {
							pipeOut.put(obj);
						}
					} catch (PipeClosedException e) {
						closedPipes.add(pipe);
					}
				}
				for (Pipe<E> pipe : closedPipes) {
					openPipes.remove(pipe);
				}
			}
			pipeOut.close();
		} catch (OperationFailedException e) {
			e.printStackTrace();
		}
	}
}
