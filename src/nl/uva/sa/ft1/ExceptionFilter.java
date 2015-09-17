package nl.uva.sa.ft1;

import java.util.List;

public class ExceptionFilter implements Filter<String, String>{
	private Pipe<String> pipe = null;

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
			} catch (InterruptedException iex) { }

			if (line==null){
				break;
			}
			else if (line.startsWith("log:exception")){
				System.out.println(line + " - end");
			}
		}
	}


	public boolean setPipeIn(Pipe<String> pipe) {
		this.pipe = pipe;
		return true;
	}

	public boolean setPipeOut(Pipe<String> pipe) {
		return false;
	}


	@Override
	public boolean setPipesIn(List<Pipe<String>> pipes) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean setPipesOut(List<Pipe<String>> pipes) {
		// TODO Auto-generated method stub
		return false;
	}
}
