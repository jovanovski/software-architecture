package nl.uva.sa.ft1.filter;

import java.util.List;

import nl.uva.sa.ft1.pipe.OperationFailedException;
import nl.uva.sa.ft1.pipe.Pipe;
import nl.uva.sa.ft1.pipe.PipeClosedException;

public class BranchingFilter<E> implements Filter {
	Pipe<E> pipeIn;
	List<Pipe<E>> pipesOut;

	public BranchingFilter(Pipe<E> pipeIn, List<Pipe<E>> pipesOut) {
		this.pipeIn = pipeIn;
		this.pipesOut = pipesOut;
	}
	
	public void run() {
		try {
			while (true) {
				E obj = this.pipeIn.get();
				for (Pipe<E> pipeOut : this.pipesOut) {
					pipeOut.put(obj);
				}
			}
		} catch (PipeClosedException e) {
			for (Pipe<E> pipeOut : this.pipesOut) {
				pipeOut.close();
			}
		} catch (OperationFailedException e) {
			e.printStackTrace();
		}		
	}
}
