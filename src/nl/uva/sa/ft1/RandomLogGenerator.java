package nl.uva.sa.ft1;

import java.util.Random;

import nl.uva.sa.ft1.pipe.OperationFailedException;
import nl.uva.sa.ft1.pipe.Pipe;
import nl.uva.sa.ft1.pipe.PipeClosedException;

//A random log generator thread that takes a list of predefined log messages and outputs 100 radomly selected ones 
//to a pipe output pipe
public class RandomLogGenerator extends Thread {
	private Pipe<String> pipe = null;
	private String [] logs = {"log:exception", "log:verbose", "log:warning", "vital:pulse", "vital:pressure"};

	public RandomLogGenerator(Pipe<String> _pipe) {
		pipe = _pipe;
	}

	public void run() {
		Random randomGenerator = new Random();
		
		try {
			//Randomly pick one of the predefined logs and send it to the pipe
			for (int i = 1; i<= 100; i++){
				int rndNum = randomGenerator.nextInt(5);
				String logLine = logs[rndNum];
				pipe.put(logLine);
			}
			//Close the pipe
			pipe.close();
		} catch (PipeClosedException e) {
			e.printStackTrace();
		} catch (OperationFailedException e) {
			e.printStackTrace();
		}
	}
}