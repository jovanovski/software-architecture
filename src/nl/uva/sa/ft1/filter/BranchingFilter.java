package nl.uva.sa.ft1.filter;

import java.util.List;

import nl.uva.sa.ft1.pipe.OperationFailedException;
import nl.uva.sa.ft1.pipe.Pipe;
import nl.uva.sa.ft1.pipe.PipeClosedException;

//A branching filter that helps take one input pipe to multiple output pipes
public class BranchingFilter<E> implements Filter {
	Pipe<E> pipeIn;
	List<Pipe<E>> pipesOut;

	//Constructor to set the input pipe, and output pipe list
	public BranchingFilter(Pipe<E> pipeIn, List<Pipe<E>> pipesOut) {
		this.pipeIn = pipeIn;
		this.pipesOut = pipesOut;
	}
	
	//For each object received from the input pipe, it is sent to all output pipes
	//Once the input pipe has thrown a PipeClosedException, presume in input pipe has no more input to give
	//and close all output pipes
	public void run() {
		try {
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
			}
		} catch (OperationFailedException e) {
			e.printStackTrace();
		}		
	}
}
