package nl.uva.sa.ft1;

import java.util.ArrayList;
import java.util.List;

public class PipeFilterDemo {

    public static void main(String[] args) {
    	//Create shared pipes
    	Pipe<String> pipe1 = new PipeImpl<String>();
    	Pipe<String> pipe2 = new PipeImpl<String>();
    	Pipe<String> pipe3 = new PipeImpl<String>();

    	List<Pipe<String>> logInPipes = new ArrayList<Pipe<String>>();
    	logInPipes.add(pipe1);

    	List<Pipe<String>> logOutPipes = new ArrayList<Pipe<String>>();
    	logOutPipes.add(pipe2);
    	logOutPipes.add(pipe3);

    	List<Pipe<String>> extInPipes = new ArrayList<Pipe<String>>();
    	extInPipes.add(pipe2);
    	
    	List<Pipe<String>> verInPipes = new ArrayList<Pipe<String>>();
    	verInPipes.add(pipe3);
    	
    	//Create generator and pass pipe 1 to it
    	Thread logGenerator = new RandomLogGenerator(pipe1);

    	//Create log filter and set output pipes
    	Filter<String, String> logingFilter = new LogingFilter();
    	logingFilter.setPipesIn(logInPipes);
    	logingFilter.setPipesOut(logOutPipes);

    	//Create exception filter and set input pipes
    	Filter<String, Integer> exceptionFilter = new ExceptionCountFilter();
    	exceptionFilter.setPipesIn(extInPipes);
    	//Create verbose filter and set input pipes
    	Filter<String, Integer> verboseFilter = new VeboseCountFilter();
    	verboseFilter.setPipesIn(verInPipes);

    	//Create threads of the filters, and run them
    	Thread logFilter = new Thread(logingFilter);
    	Thread expFilter = new Thread(exceptionFilter);
    	Thread vebFilter = new Thread(verboseFilter);
    	
    	logGenerator.start();
    	logFilter.start();
    	expFilter.start();
    	vebFilter.start();
     }
}
