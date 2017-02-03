package com.foo.bar.tictactoe.impl;

import static com.foo.bar.tictactoe.impl.XO.*;

import java.util.Scanner;

public class HumanPlay {
	private Board board;
	private Scanner in;
	
	public HumanPlay(Board board,Scanner in) {
		this.board = board;
		this.in = in;
	}

	public Integer nextMove() {	
		System.out.print("Enter move(1-9):");
		int nextMove =  in.nextInt();
		if (board.isWinnerMove(O, nextMove)) {
			 board.makeAMove(O, nextMove);
			 return Board.GAME_WON;
		} else {
			 board.makeAMove(O, nextMove);	
			 return nextMove;
		}
	}
}
