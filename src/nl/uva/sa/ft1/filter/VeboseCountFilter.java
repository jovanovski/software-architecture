package nl.uva.sa.ft1.filter;

import java.util.List;

import nl.uva.sa.ft1.pipe.OperationFailedException;
import nl.uva.sa.ft1.pipe.Pipe;

public class VeboseCountFilter extends FilterBase<String, Integer> implements Filter<String, Integer>{
	public void run() {
		Integer nulledPipes = 0;
		Integer counter = 0;
		while(true) {
			try {
				if(nulledPipes==inPipes.size()){
					System.out.println("Counted verbose: " + counter);
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
							counter++;
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

	public boolean setPipesOut(List<Pipe<Integer>> pipes) {
		outPipes = pipes;
		return true;
	}

	@Override
	protected boolean filter(String input) {
		if(input.startsWith("log:verbose")){
			return true;
		}
		return false;
	}
}
