package nl.uva.sa.ft1;

public class LogingFilter implements Filter<String>{
	private Pipe<String> pipe = null;
	private Pipe<String> pipe2 = null;

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
				pipe2.put(null);
				break;
			}
			else if (line.startsWith("log:")){
				pipe2.put(new String(line));
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
		this.pipe2 = pipe;
		return true;
	}

}