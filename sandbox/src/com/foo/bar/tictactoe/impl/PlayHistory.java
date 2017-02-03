package com.foo.bar.tictactoe.impl;

import java.util.ArrayList;
import java.util.List;

public class PlayHistory {
	private List<String> computerWinHistory = new ArrayList<>();
	private List<String> computerLoseHistory = new ArrayList<>();
	private List<String> computerDrawHistory = new ArrayList<>();
	
	public void addComputerWinGame(String XwinSequence) {
		computerWinHistory.add(XwinSequence);
	}
	
	public void addComputerLoseGame(String XloseSequence) {
		computerLoseHistory.add(XloseSequence);
	}
	
	public void addComputerDrawGame(String XloseSequence) {
		computerDrawHistory.add(XloseSequence);
	}

	public boolean isPossibleComputerWinPlay(String possibleXplay) {
		return possibleMatch(computerWinHistory, possibleXplay);
	}
	
	public boolean isPossibleComputerLosePlay(String possibleXplay) {
		return possibleMatch(computerLoseHistory, possibleXplay);
	}
	
	public boolean isPossibleComputerDrawPlay(String possibleXplay) {
		return possibleMatch(computerDrawHistory, possibleXplay);
	}
	
	private boolean possibleMatch(List<String> histPlay, String possiblePlay) {
		for (String hist: histPlay) {
			if (hist.indexOf(possiblePlay)>=0) {
				return true;
			}
		}
		return false;
	}
	
	public void printComputerLoseHistory() {
		System.out.println("---Computer Lose history---");
		for (String loseMove: computerLoseHistory) 
		    System.out.println(computerLoseHistory);
	}
}
