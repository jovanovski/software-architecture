package nl.uva.sa.ft1.filter;

import nl.uva.sa.ft1.pipe.OperationFailedException;
import nl.uva.sa.ft1.pipe.Pipe;

public class ExceptionFilter extends FilterBase<String, String> {
	private Pipe<String> pipe = null;

	public ExceptionFilter(Pipe<String> inPipe, Pipe<String> outPipe) {
		super(inPipe, outPipe);
	}
	
	public void run() {
		String line = null;
		while(true) {
			try {
				Object o = pipe.get();
				if(o==null){
					line = null;
				}
				else{
					line = (String) o;
				}
			} catch (OperationFailedException iex) { }

			if (line==null){
				break;
			}
			else if (line.startsWith("log:exception")){
				System.out.println(line + " - end");
			}
		}
	}
}
