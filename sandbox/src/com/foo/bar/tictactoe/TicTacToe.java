package com.foo.bar.tictactoe;
import static com.foo.bar.tictactoe.impl.XO.O;
import static com.foo.bar.tictactoe.impl.XO.X;
import static com.foo.bar.tictactoe.impl.XO.EMPTY;

import java.util.Random;
import java.util.Scanner;

import com.foo.bar.tictactoe.def.ComputerPlayStrategy;
import com.foo.bar.tictactoe.impl.Board;
import com.foo.bar.tictactoe.impl.BruteForceComputerPlayStrategy;
import com.foo.bar.tictactoe.impl.HumanPlay;
import com.foo.bar.tictactoe.impl.PlayHistory;
import com.foo.bar.tictactoe.impl.XO;
/**
 * 1. For simplicity - Computer: X, Human: O
 * 
 * @author Herbert
 *
 */

public class TicTacToe {
	private boolean gameOn = true;
	private XO currentPlayer;
	private Board board = new Board();
	private PlayHistory history = loadHistory();
	private ComputerPlayStrategy computerStrategy;
	private HumanPlay humanPlay;
	private Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		System.out.println("Starting game!");
		TicTacToe ttt = new TicTacToe();
		ttt.play();
	}
	
	private PlayHistory loadHistory() {
		// TODO load from file etc
		return new PlayHistory();
	}

	public void play() {
		while (true) {
			currentPlayer = randomSelectStarter();
			if (currentPlayer==X) {
				System.out.println("Computer plays first\n");
			} else {
				System.out.println("You play first\n");
			}
			board.reset();
			gameOn=true;
			while (gameOn) {
				if (currentPlayer == X) {
					computerMakeAMove();
				} else {
					humanMakeAMove();
				}
			}
			
			if (!continuePlay()) {
				System.out.println("\n\nBye Bye!");
				scanner.close();
				break;
			}
		}
	}
	

	private void humanMakeAMove() {
		humanPlay = new HumanPlay(board,scanner);
		int moveResult = humanPlay.nextMove();
		if (moveResult == Board.GAME_TIE) {
			history.addComputerDrawGame(board.getFullGameMoves());
			gameOver("It is a draw!", EMPTY);
		} else if (moveResult == Board.GAME_WON) {
			history.addComputerLoseGame(board.getFullGameMoves());
			gameOver("You won!",O);
		} else {
			board.printBoard();
			turnToPlayer(X);
		}
		
	}

	private void computerMakeAMove() {
		computerStrategy = fetchPlayStrategy();
		int moveResult = computerStrategy.nextMove();
		if (moveResult == Board.GAME_TIE) {
			history.addComputerDrawGame(board.getFullGameMoves());
			gameOver("It is a draw!",EMPTY);
		} else if (moveResult == Board.GAME_WON) {
			history.addComputerWinGame(board.getFullGameMoves());
			gameOver("Computer won!",X);
		} else {
			board.printBoard();
			turnToPlayer(O);
		}
	}
	
	private void turnToPlayer(XO nextPlayer) {
		currentPlayer=nextPlayer;
	}

	ComputerPlayStrategy fetchPlayStrategy() {
		return new BruteForceComputerPlayStrategy(board, history);
	}

	private boolean continuePlay() {
		System.out.print("Continue play(yes/no)?:");
		String yes = scanner.next();
		return "y".equals(yes);
	}
	
	private void gameOver(String msgs, XO winner) {
		gameOn=false;
		board.printBoard();
		System.out.println(msgs);
		saveGameHistory(winner);
		
	}

	private void saveGameHistory(XO winner) {
		if (winner == X) {
			history.addComputerWinGame(board.getFullGameMoves());
		} else if (winner == O) {
			history.addComputerLoseGame(board.getFullGameMoves());
		} else {
			history.addComputerDrawGame(board.getFullGameMoves());
		}
		
		history.printComputerLoseHistory();
	}

	private XO randomSelectStarter() {
		XO[] players = {X,O};
		Random rand = new Random();
		return players[rand.nextInt(2)];
	}
}
