package com.test.basic.topscoreranking.dto;

import java.util.Date;

public class HistoryScore {
	private long score;
	private Date time;
	public long getScore() {
		return score;
	}
	public void setScore(long score) {
		this.score = score;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "HistoryScore [score=" + score + ", time=" + time + "]";
	}
	
	
}
