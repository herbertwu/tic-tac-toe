package com.foo.bar.tictactoe.impl;
import static com.foo.bar.tictactoe.impl.XO.*;

import com.foo.bar.tictactoe.def.ComputerPlayStrategy;

public class BruteForceComputerPlayStrategy implements ComputerPlayStrategy {
	private Board board;
	private PlayHistory history;
	
	public BruteForceComputerPlayStrategy(Board board, PlayHistory history)  {
		this.board = board;
		this.history = history;
	}
	/**
	 * 
	 * @return -1:tie, 0:win , >0 next move
	 */
	public Integer nextMove() {
		Integer[] emptyCells = board.getEmptyCells();
		if (emptyCells.length==0) {
			return Board.GAME_TIE;
		}
		int defaultMove = emptyCells[emptyCells.length-1]; // ensure inspecting all possible plays
		for (int i=0;i<emptyCells.length;i++) {
			int nextPossibleMove = emptyCells[i];
			if (board.isWinnerMove(X, nextPossibleMove)) {
			   board.makeAMove(X, nextPossibleMove);
			   return Board.GAME_WON;
			} else {
				String currentPlay = board.getMoves(X, nextPossibleMove);
				 if (history.isPossibleComputerWinPlay(currentPlay) || history.isPossibleComputerDrawPlay(currentPlay)) {
					   board.makeAMove(X, nextPossibleMove); 
					   return nextPossibleMove;
				 } else if (history.isPossibleComputerLosePlay(currentPlay)) {
					 continue; // avoid Losing-ONLY play
				 } else {
					 board.makeAMove(X, nextPossibleMove);
					 return nextPossibleMove;
				 }
			}
		} 
		
		return defaultMove; //maybe neither win or lose found
	}

}
