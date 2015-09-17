package nl.uva.sa.ft1.filter;

import nl.uva.sa.ft1.pipe.OperationFailedException;
import nl.uva.sa.ft1.pipe.Pipe;
import nl.uva.sa.ft1.pipe.PipeClosedException;

public class ExceptionFilter extends FilterBase<String, String> {
	private Pipe<String> pipe = null;

	public ExceptionFilter(Pipe<String> inPipe, Pipe<String> outPipe) {
		super(inPipe, outPipe);
	}
	
	public void run() {
		String line = null;
		try {
			while(true) {
				String s = pipe.get();

				if (s.startsWith("log:exception")) {
					System.out.println(line + " - end");	
				}
			} 
		} catch (PipeClosedException e) {			
		} catch (OperationFailedException e) {
			e.printStackTrace();
		}		
	}
}
