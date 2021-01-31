package com.test.basic.topscoreranking.dao;

import java.util.Date;

public interface IHistoryScores {
	Long getminScore();
	Date getminDate();
	Long getmaxScore();
	Date getmaxDate();
	float getAverage();
}
