package nl.uva.sa.ft1.pipe;

import static org.junit.Assert.*;

import org.junit.Test;

public class SynchronizedArrayListPipeTest {
	@Test
	public void test() {
		SynchronizedArrayListPipe<String> pipe = new SynchronizedArrayListPipe<>();
		pipe.put("foo");
		pipe.put("bar");
		assertEquals(false, pipe.isClosed());
		assertEquals(false, pipe.isEmpty());
		try {
			assertEquals("foo", pipe.get());
			assertEquals("bar", pipe.get());
		} catch (OperationFailedException e) {
			fail("Pipe operation failed");
		}

		assertEquals(true, pipe.isEmpty());
		assertEquals(false, pipe.isClosed());
	}
}
