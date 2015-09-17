package nl.uva.sa.ft1.filter;

import org.junit.Test;
import static org.junit.Assert.*;

import nl.uva.sa.ft1.pipe.OperationFailedException;
import nl.uva.sa.ft1.pipe.Pipe;
import nl.uva.sa.ft1.pipe.SynchronizedArrayListPipe;

import java.util.ArrayList;

public class MergingFilterTest {
	@Test
	public void test() {
		SynchronizedArrayListPipe<String> inPipe1 = new SynchronizedArrayListPipe<>();
		SynchronizedArrayListPipe<String> inPipe2 = new SynchronizedArrayListPipe<>();
		SynchronizedArrayListPipe<String> outPipe = new SynchronizedArrayListPipe<>();
		ArrayList<Pipe<String>> inPipes = new ArrayList<>();
		inPipes.add(inPipe1);
		inPipes.add(inPipe2);
		
		inPipe1.put("foo");
		inPipe1.close();
		inPipe2.put("bar");
		inPipe2.close();
		MergingFilter<String> filter = new MergingFilter<>(inPipes, outPipe);
		filter.run();
		try {
			assertEquals("foo", outPipe.get());
			assertEquals("bar", outPipe.get());
			assertTrue(outPipe.isEmpty());
			assertTrue(outPipe.isClosed());
		} catch (OperationFailedException e) {
			e.printStackTrace();
			fail("Operation failed");
		}		
	}
}
