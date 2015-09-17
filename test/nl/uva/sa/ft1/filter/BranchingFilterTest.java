package nl.uva.sa.ft1.filter;

import org.junit.Test;
import static org.junit.Assert.*;

import nl.uva.sa.ft1.pipe.OperationFailedException;
import nl.uva.sa.ft1.pipe.Pipe;
import nl.uva.sa.ft1.pipe.SynchronizedArrayListPipe;

import java.util.ArrayList;

public class BranchingFilterTest {
	@Test
	public void test() {
		SynchronizedArrayListPipe<String> inPipe = new SynchronizedArrayListPipe<>();
		SynchronizedArrayListPipe<String> outPipe1 = new SynchronizedArrayListPipe<>();
		SynchronizedArrayListPipe<String> outPipe2 = new SynchronizedArrayListPipe<>();
		ArrayList<Pipe<String>> outPipes = new ArrayList<>();
		outPipes.add(outPipe1);
		outPipes.add(outPipe2);
		
		inPipe.put("foo");
		inPipe.put("bar");
		inPipe.close();
		BranchingFilter<String> filter = new BranchingFilter<>(inPipe, outPipes);
		filter.run();
		try {
			assertEquals("foo", outPipe1.get());
			assertEquals("bar", outPipe1.get());
			assertEquals("foo", outPipe2.get());
			assertEquals("bar", outPipe2.get());
			assertTrue(outPipe1.isEmpty());
			assertTrue(outPipe2.isClosed());
			assertTrue(outPipe2.isEmpty());
			assertTrue(outPipe2.isClosed());
		} catch (OperationFailedException e) {
			e.printStackTrace();
			fail("Operation failed");
		}		
	}
}
