package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tictacto.TicTacTo;

public class TicTacToTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test_winning_player1() {
		TicTacTo tictacto = new TicTacTo();
		tictacto.play(0, 0);
		tictacto.play(0, 1);
		tictacto.play(1, 0);
		tictacto.play(1, 1);
		assertEquals(0, tictacto.checkWin());
		tictacto.play(2, 0);
		assertEquals(1, tictacto.checkWin());
	}

	@Test
	public void test_winning_player2() {
		TicTacTo tictacto = new TicTacTo();
		tictacto.play(0, 0);
		tictacto.play(0, 1);
		tictacto.play(1, 0);
		tictacto.play(1, 1);
		tictacto.play(0, 2);
		assertEquals(0, tictacto.checkWin());
		tictacto.play(2, 1);
		assertEquals(2, tictacto.checkWin());
	}
	
	@Test
	public void test_double_play(){
		TicTacTo tictacto = new TicTacTo();
		assertTrue(tictacto.play(0, 0));
		assertFalse(tictacto.play(0, 0));
		assertTrue(tictacto.play(2, 2));
		assertFalse(tictacto.play(2, 2));
	}
	
	@Test
	public void test_diagonal_win(){
		TicTacTo tictacto = new TicTacTo();
		tictacto.play(0, 0);
		tictacto.play(0, 1);
		tictacto.play(1, 1);
		tictacto.play(0, 2);
		tictacto.play(2, 2);
		assertEquals(1, tictacto.checkWin());
	}
}
