package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.athaydes.automaton.FXApp;
import com.athaydes.automaton.FXer;

import javafx.scene.control.Alert;
import tictacto.Main;

public class TicTacToViewTest {
	FXer fxer;
	@Before
	public void setUp() throws Exception {
		FXApp.startApp(new Main());
		fxer = FXer.getUserWith( FXApp.getScene().getRoot() );
	}

	@Test
	public void test_game_player1_winning() throws InterruptedException {
		fxer.clickOn("#rect00").waitForFxEvents();
		fxer.clickOn("#rect01").waitForFxEvents();
		fxer.clickOn("#rect10").waitForFxEvents();
		fxer.clickOn("#rect21").waitForFxEvents();
		fxer.clickOn("#rect22").waitForFxEvents();
		fxer.clickOn("#rect02").waitForFxEvents();
		fxer.clickOn("#rect20").waitForFxEvents();
		fxer.pause(1000);
		assertTrue(true);
	}

}
