package nl.uva.sa.ft1.pipe;

import static org.junit.Assert.*;

import org.junit.Test;

public class SynchronizedArrayListPipeTest {
	@Test
	public void test() {
		SynchronizedArrayListPipe<String> pipe = new SynchronizedArrayListPipe<>();
		try {
			pipe.put("foo");
			pipe.put("bar");
			assertEquals(false, pipe.isClosed());
			assertEquals(false, pipe.isEmpty());
			assertEquals("foo", pipe.get());
			assertEquals("bar", pipe.get());
		} catch (PipeClosedException e) {
			e.printStackTrace();
			fail("Pipe closed prematurely");		
		} catch (OperationFailedException e) {
			e.printStackTrace();
			fail("Pipe operation failed");
		}

		assertEquals(true, pipe.isEmpty());
		assertEquals(false, pipe.isClosed());
	}
}
