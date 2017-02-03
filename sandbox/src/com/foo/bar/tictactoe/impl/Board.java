package com.foo.bar.tictactoe.impl;
import static com.foo.bar.tictactoe.impl.XO.EMPTY;

import java.util.ArrayList;
import java.util.List;

public class Board {
	public static final int GAME_TIE=-1;
	public static final int GAME_WON= 0;
	
	private static final int BOARD_DIM=10; // first element not used
	private XO[] board = new XO[BOARD_DIM];
	private String recordedMoves="";
	
	public Board() {
		reset();
	}
	public void reset() {
		for (int i=0;i<BOARD_DIM;i++) {
			board[i]=EMPTY;
		}
		recordedMoves="";
	}
	
	public void makeAMove(XO letter, int location) {
		board[location]=letter;
		recordMove(letter, location);
	}
	
	public String getMoves(XO letter, int location) {
		return recordedMoves + "-->"+letter+"_"+location;
	}
	
	public String getFullGameMoves() {
		return recordedMoves;
	}
	
	private void recordMove(XO letter, int location) {
		recordedMoves = getMoves(letter, location);
	}
	public boolean isWinnerMove(XO letter, int location) {
		XO orig=board[location];
		board[location] = letter;
		boolean isWinning = (board[1]==letter && board[2]==letter && board[3]==letter) ||
			   (board[4]==letter && board[5]==letter && board[6]==letter) ||
			   (board[7]==letter && board[8]==letter && board[9]==letter) ||
			   (board[1]==letter && board[4]==letter && board[7]==letter) ||
			   (board[2]==letter && board[5]==letter && board[8]==letter) ||
			   (board[3]==letter && board[6]==letter && board[9]==letter) ||
			   (board[1]==letter && board[5]==letter && board[9]==letter) ||
			   (board[7]==letter && board[5]==letter && board[3]==letter);
		board[location]=orig;
		return isWinning;
	}
	
	public void printBoard() {
		System.out.println(board[1]+"--"+board[2]+"--"+board[3]);
		System.out.println(board[4]+"--"+board[5]+"--"+board[6]);
		System.out.println(board[7]+"--"+board[8]+"--"+board[9]);
		System.out.println("\n");
	}
	
	public Integer[] getEmptyCells() {
		List<Integer> emptyIdx = new ArrayList<>();
		for (int i=1;i<BOARD_DIM;i++) {
			if (board[i]==EMPTY) {
				emptyIdx.add(i);
			}
		}
		return (emptyIdx.isEmpty())? new Integer[0] : emptyIdx.toArray(new Integer[emptyIdx.size()]);
	}
}
