package nl.uva.sa.ft1.filter;

import java.util.List;

import nl.uva.sa.ft1.pipe.OperationFailedException;
import nl.uva.sa.ft1.pipe.Pipe;

public class LogingFilter extends FilterBase<String, String> implements Filter<String, String>{

	public void run() {
		Integer nulledPipes = 0;
		while(true) {

			try {
				if(nulledPipes==inPipes.size()){
					for (Pipe<String> pipeOut : outPipes) {
						pipeOut.put(null);
					}
					break;
				}
				
				for (Pipe<String> pipe : inPipes) {
					String s = pipe.get();
					if(s==null){
						nulledPipes++;
						if(nulledPipes==inPipes.size()){
							break;
						}
					}
					else{			
						if(filter(s)){
							for (Pipe<String> pipeOut : outPipes) {
								pipeOut.put(s);
							}
						}
					}	
				}
				
			} catch (OperationFailedException iex) { }

		}
	}

	public boolean setPipesIn(List<Pipe<String>> pipes) {
		inPipes = pipes;
		return true;
	}

	public boolean setPipesOut(List<Pipe<String>> pipes) {
		outPipes = pipes;
		return true;
	}

	@Override
	protected boolean filter(String input) {
		if(input.startsWith("log:")){
			return true;
		}
		return false;
	}


}