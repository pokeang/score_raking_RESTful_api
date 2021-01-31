package com.test.basic.topscoreranking.dto;

import java.util.List;

public class HistoryScoreResponse {
	private HistoryScore top;
	private HistoryScore low;
	private float avarage;
	private List<HistoryScore> scores;

	public HistoryScore getTop() {
		return top;
	}

	public void setTop(HistoryScore top) {
		this.top = top;
	}

	public HistoryScore getLow() {
		return low;
	}

	public void setLow(HistoryScore low) {
		this.low = low;
	}

	public float getAvarage() {
		return avarage;
	}

	public void setAvarage(float avarage) {
		this.avarage = avarage;
	}

	public List<HistoryScore> getScores() {
		return scores;
	}

	public void setScores(List<HistoryScore> scores) {
		this.scores = scores;
	}

	@Override
	public String toString() {
		return "HistoryScoreResponse [top=" + top + ", low=" + low + ", avarage=" + avarage + ", scores=" + scores
				+ "]";
	}
	
}
