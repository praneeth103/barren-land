package com.test;

import java.util.List;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.main.BarrenSolution;

public class UnitTests {

	@Test
	public void testGetFertileLand() {
		List<Integer> fertileLands = BarrenSolution.getFertileLand(
				new String[] { "48 192 351 207", "48 392 351 407", "120 52 135 547", "260 52 275 547" });
		fertileLands.forEach(item->System.out.print(item + " "));
		assertEquals(new Integer(22816),fertileLands.get(0));
		assertEquals(new Integer(192608),fertileLands.get(1));

	}

}
