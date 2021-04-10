package com.game.player;

public class Player {
	
	private String name;
	
	private int score;
	
	public Player(String name, int score) {
		this.name = name;
		this.score = score;
	}
	
	public Player(String name) {
		this(name, 0);
	}
	
	public Player() {
		this("", 0);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public String toString() {
		return name + "'s Score: " + score;
	}
}
