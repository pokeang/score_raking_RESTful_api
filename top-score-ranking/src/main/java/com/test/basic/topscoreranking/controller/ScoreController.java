package com.test.basic.topscoreranking.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.basic.topscoreranking.dao.IHistoryScores;
import com.test.basic.topscoreranking.dto.HistoryScore;
import com.test.basic.topscoreranking.dto.HistoryScoreResponse;
import com.test.basic.topscoreranking.dto.ScoreSearchDTO;
import com.test.basic.topscoreranking.entities.Score;
import com.test.basic.topscoreranking.service.impl.ScoreServiceImpl;

@RestController
@RequestMapping({"/api/v1/score"})
public class ScoreController {
	@Autowired
	private ScoreServiceImpl scoreService;
	
	@PostMapping
	public ResponseEntity<Score> createScore(@Valid @RequestBody Score score) {
		score.setPlayer(score.getPlayer().toLowerCase());
		return ResponseEntity.ok(scoreService.add(score));
	}
	
	@GetMapping("/{scoreId}")
	public ResponseEntity<Score> getScoreById(@PathVariable int scoreId){
		Optional<Score> scores = scoreService.findById(scoreId);
		if(!scores.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Score score = scores.get();
		return ResponseEntity.ok(score);
	}
	
	@DeleteMapping("/{scoreId}")
	public String deleteScore(@PathVariable int scoreId) {
		return scoreService.findById(scoreId).map(score -> {
			scoreService.deleteScoreById(scoreId);
			return "Score id " + scoreId + " was delete";
		}).orElse("Score id " + scoreId + " is not found");
	}
	
	@GetMapping()
	public Page<Score> getAllScore(Pageable pageable){
		return scoreService.getAll(pageable);
	}
	
	@PostMapping("/search")
	public Page<Score> searchScore(@RequestBody ScoreSearchDTO param, Pageable pageable) {
		if (param.getPlayer() != null) {
			return scoreService.getScoreListByPlayer(param.getPlayer(), pageable);
		}
		if(param.getPlayers() != null && param.getPlayers().size() > 0) {
			return scoreService.getScoreListByPlayers(param.getPlayers(), pageable);
		}
		if(param.getAfterDate() != null && param.getBeforeDate() != null) {
			return scoreService.getScoreListByBetweenDate(param.getBeforeDate(), param.getAfterDate(), pageable);
		}
		if(param.getAfterDate() != null) {
			return scoreService.getScoreListByAfterDate(param.getAfterDate(), pageable);
		}
		return null;
	}
	
	
	@GetMapping("/history/{name}")
	public ResponseEntity<HistoryScoreResponse> getHistory(@PathVariable String name){
		try {
			HistoryScore scoreHistory;
			IHistoryScores minMaxScore = scoreService.getMinMaxScoreHistoryByPlayer(name);
			HistoryScoreResponse historyScoreResponse = new HistoryScoreResponse();

			scoreHistory = new HistoryScore();
			scoreHistory.setScore(minMaxScore.getminScore());
			scoreHistory.setTime(minMaxScore.getminDate());
			historyScoreResponse.setLow(scoreHistory);

			scoreHistory = new HistoryScore();
			scoreHistory.setScore(minMaxScore.getmaxScore());
			scoreHistory.setTime(minMaxScore.getmaxDate());
			historyScoreResponse.setTop(scoreHistory);
			
			historyScoreResponse.setAvarage(minMaxScore.getAverage());
			
			List<Score> scores = scoreService.getScoreListByPlayer(name);
			List<HistoryScore> scoresRespone = new ArrayList<>();
			scores.forEach(score -> {
				HistoryScore scoreHistorys = new HistoryScore();
				scoreHistorys.setScore(score.getScore());
				scoreHistorys.setTime(score.getTime());
				scoresRespone.add(scoreHistorys);
			});
			
			historyScoreResponse.setScores(scoresRespone);
			
			return ResponseEntity.ok(historyScoreResponse);
		}catch(Exception e) {
			ResponseEntity.noContent();
		}
		return null;
	}
}
