package com.test.basic.topscoreranking.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.test.basic.topscoreranking.dao.IHistoryScores;
import com.test.basic.topscoreranking.entities.Score;

public interface ScoreService {
	
	Score add(Score score);
	Optional<Score> findById(int id);
	void deleteScoreById(int id);

	Page<Score> getAll(Pageable pageable);
	
	// get all scores by player name
	Page<Score> getScoreListByPlayer(String name, Pageable pageable);
	
	// get all score by before date
	Page<Score> getScoreListByAfterDate(Date afterDate, Pageable pageable);
	
	// get all score by many player name
	Page<Score> getScoreListByPlayers(List<String> name, Pageable pageable);
	
	// get all score by before date and after date
	Page<Score> getScoreListByBetweenDate(Date beforeDate, Date afterDate, Pageable pageable);
	
	// for history score by name
	List<Score> getScoreListByPlayer(String name);
	
	IHistoryScores getMinMaxScoreHistoryByPlayer(String name);
	
	

}
