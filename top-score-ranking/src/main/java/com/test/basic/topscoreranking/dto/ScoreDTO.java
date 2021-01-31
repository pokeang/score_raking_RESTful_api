package com.test.basic.topscoreranking.dto;

import java.util.Date;


public class ScoreDTO {
	private String player;
	private int score;
	private Date time;

	public String getPlayer() {
		return player;
	}

	public void setPlayer(String player) {
		this.player = player;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	
}
