package nl.uva.sa.ft1.filter;

import nl.uva.sa.ft1.pipe.OperationFailedException;
import nl.uva.sa.ft1.pipe.Pipe;
import nl.uva.sa.ft1.pipe.PipeClosedException;

public class ExceptionCountFilter extends FilterBase<String, Integer> {

	public ExceptionCountFilter(Pipe<String> inPipe, Pipe<Integer> outPipe) {
		super(inPipe, outPipe);
	}
	
	public void run() {
		Integer counter = 0;
		try {
			while(true) {
				String s = inPipe.get();
				if(s.startsWith("log:exception")){
					counter++;
				}	
			}
		} catch (PipeClosedException e) {
			System.out.println("Counted exceptions: " + counter);				
		} catch (OperationFailedException iex) { }
	}
}
