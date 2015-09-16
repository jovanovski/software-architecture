package nl.uva.sa.ft1;

import static org.junit.Assert.*;

import org.junit.Test;

import nl.uva.sa.ft1.Hello;


public class HelloTest {

	@Test
	public void test() {
		Hello h = new Hello();
		assertEquals("Hello!", h.say());
	}

}
