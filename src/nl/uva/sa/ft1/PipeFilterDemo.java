package nl.uva.sa.ft1;

public class PipeFilterDemo {

    public static void main(String[] args) {
    	//Create shared pipes
    	Pipe<String> pipe1 = new PipeImpl<String>();
    	Pipe<String> pipe2 = new PipeImpl<String>();

    	//Create generator and pass pipe 1 to it
    	Thread logGenerator = new RandomLogGenerator(pipe1);

    	//Create log filter and set pipe1 as input and pipe2 as output
    	Filter<String> logingFilter = new LogingFilter();
    	logingFilter.setPipeIn(pipe1);
    	logingFilter.setPipeOut(pipe2);
    	
    	//Create exception filter and set pipe1 as input (no pipe2 needed)
    	Filter<String> exceptionFilter = new ExceptionFilter();
    	exceptionFilter.setPipeIn(pipe2);

    	//Create threads of the filters, and run them
    	Thread logFilter = new Thread(logingFilter);
    	Thread expFilter = new Thread(exceptionFilter);
    	logGenerator.start();
    	logFilter.start();
    	expFilter.start();
     }
}
