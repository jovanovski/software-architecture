package nl.uva.sa.ft1.filter;

import nl.uva.sa.ft1.pipe.OperationFailedException;
import nl.uva.sa.ft1.pipe.Pipe;
import nl.uva.sa.ft1.pipe.PipeClosedException;

//Sample filter that prints Exception messages in the logs
//Exception messages are messages that start with 'log:exception'
public class ExceptionFilter extends FilterBase<String, String> {
	
	//Constructor that takes one input and one output pipe, and sends them up to the FilterBase class
	public ExceptionFilter(Pipe<String> inPipe, Pipe<String> outPipe) {
		super(inPipe, outPipe);
	}
	
	//A runnable component that can be run in a Thread or just by calling the function
	public void run() {
		
		//Keep calling the inPipe.get() method while it returns elements
		//When it throws a PipeClosedException, terminate the printing and presume that the pipe is done
		//outputting elements
		try {
			while(true) {
				String s = inPipe.get();
				if (s.startsWith("log:exception")) {
					System.out.println(s);	
				}
			} 
		} catch (PipeClosedException e) {			
		} catch (OperationFailedException e) {
			e.printStackTrace();
		}		
	}
}
