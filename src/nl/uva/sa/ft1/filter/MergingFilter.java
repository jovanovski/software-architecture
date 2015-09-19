package nl.uva.sa.ft1.filter;

import java.util.ArrayList;
import java.util.List;

import nl.uva.sa.ft1.pipe.OperationFailedException;
import nl.uva.sa.ft1.pipe.Pipe;
import nl.uva.sa.ft1.pipe.PipeClosedException;

//A merging filter that takes more than 1 input pipe and puts all that information into one output pipe (to support branching)
public class MergingFilter<E> implements Filter {
	List<Pipe<E>> pipesIn;
	Pipe<E> pipeOut;

	//Constructor to set the input pipe list, and output pipe
	public MergingFilter(List<Pipe<E>> pipesIn, Pipe<E> pipeOut) {
		this.pipesIn = pipesIn;
		this.pipeOut = pipeOut;
	}

	//A runnable component that can be run in a Thread or just by calling the function
	public void run() {
		List<Pipe<E>> openPipes = pipesIn;
		List<Pipe<E>> closedPipes = new ArrayList<>();
		
		//Take two lists of pipes, one of open ones, and one of closed ones
		//While each of the pipes are open, ask for an element. If it returns null, that means the pipe does not have 
		//an element to give, but will have one in the future (this avoids busy waiting).
		//When a pipe throws a PipeClosedException, presume it has finished it's output and put it in the list of closed pipes.
		//At the end, remove all closed pipes from the list of open pipes.
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
