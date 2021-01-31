package com.test.basic.topscoreranking.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.test.basic.topscoreranking.dao.IHistoryScores;
import com.test.basic.topscoreranking.dao.ScoreDao;
import com.test.basic.topscoreranking.entities.Score;
import com.test.basic.topscoreranking.service.ScoreService;

@Service
public class ScoreServiceImpl implements ScoreService {

	@Autowired
	private ScoreDao scoreDao;

	public Score add(Score score) {
		return scoreDao.save(score);
	}

	public Optional<Score> findById(int id) {
		return scoreDao.findById(id);
	}
	
	public void deleteScoreById(int id) {
		scoreDao.deleteById(id);
	}

	public Page<Score> getAll(Pageable pageable) {
		return scoreDao.findAll(pageable);
	}

	public Page<Score> getScoreListByPlayer(String name, Pageable pageable) {
		return scoreDao.getScoreListByPlayer(name, pageable);
	}

	public Page<Score> getScoreListByAfterDate(Date afterDate, Pageable pageable) {
		return scoreDao.getScoreListByAfterDate(afterDate, pageable);
	}

	public Page<Score> getScoreListByPlayers(List<String> names, Pageable pageable) {
		return scoreDao.getScoreListByPlayers(names, pageable);
	}

	public Page<Score> getScoreListByBetweenDate(Date beforeDate, Date afterDate, Pageable pageable) {
		return scoreDao.getScoreListByBetweenDate(beforeDate, afterDate, pageable);
	}

	
	public List<Score> getScoreListByPlayer(String name) {
		return scoreDao.getScoreListByPlayer(name);
	}

	public IHistoryScores getMinMaxScoreHistoryByPlayer(String name) {
		return scoreDao.getMinMaxScoreHistoryByPlayer(name);
	}

}
