package com.isge;

import org.junit.Assert;
import org.junit.Test;

public class HelloTest {

		@Test
		public void should_get_2_when_adding_1_and_1() {
			int result = 1 + 1;
			Assert.assertEquals(result, 2);
		}
}
