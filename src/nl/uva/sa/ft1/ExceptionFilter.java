package nl.uva.sa.ft1;

public class ExceptionFilter implements Filter<String>{
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
				System.out.println(line);
			}
		}
	}


	@Override
	public boolean setPipeIn(Pipe<String> pipe) {
		this.pipe = pipe;
		return true;
	}

	@Override
	public boolean setPipeOut(Pipe<String> pipe) {
		return false;
	}
}
