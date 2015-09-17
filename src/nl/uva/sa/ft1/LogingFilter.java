package nl.uva.sa.ft1;

import java.util.List;

import nl.uva.sa.ft1.pipe.OperationFailedException;

public class LogingFilter extends FilterBase<String, String> implements Filter<String, String>{

	public void run() {
		Integer nulledPipes = 0;
		while(true) {

			System.out.println(nulledPipes + "B");
			try {
				if(nulledPipes==inPipes.size()){
					for (Pipe<String> pipeOut : outPipes) {
						pipeOut.put(null);
					}

					System.out.println("DONE");
					break;
				}
				
				for (Pipe<String> pipe : inPipes) {

					System.out.println("Taking from a pipe");
					Object s = pipe.get();
					System.out.println(s + " - this is what I've got");
					if(s==null){

						System.out.println("One pipe has been nulled");
						nulledPipes++;
						if(nulledPipes==inPipes.size()){
							break;
						}
					}
					else{
						String str = (String) s;
						
						System.out.println(str);
						//for (Pipe<String> pipeOut : outPipes) {
							//pipeOut.put(s);
						//}
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