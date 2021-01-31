package com.test.basic.topscoreranking.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.test.basic.topscoreranking.dao.IHistoryScores;
import com.test.basic.topscoreranking.dto.HistoryScore;
import com.test.basic.topscoreranking.dto.HistoryScoreResponse;
import com.test.basic.topscoreranking.dto.ScoreSearchDTO;
import com.test.basic.topscoreranking.entities.Score;
import com.test.basic.topscoreranking.service.impl.ScoreServiceImpl;
import com.test.basic.topscoreranking.util.DateFormatUtil;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ScoreControllerTest {
	private static Score s1;
	private static Score s2;

//	@Autowired
//    private MockMvc mvc;

	@Mock
    private Pageable pageableMock;
	
	@Mock
	private Page<Score> scorePage;
	
	@InjectMocks
	private ScoreController scoreController;
	
	@Mock
	private ScoreServiceImpl scoreService;

	@Mock
	private IHistoryScores IHistoryScore;

	
	@Mock
	private List<Score> scores;
	
	
	@BeforeAll
	public static void init() {
		s1 = new Score("player1", 10, new Date());
		s2 = new Score("player2", 20, new Date());
	}

	
	@Test
	void createScore() {
		scoreController.createScore(s1);
		Mockito.verify(scoreService, Mockito.times(1)).add(s1);
	}
	
	@Test
	void getScoreById_WhenMatch() {
		Mockito.when(scoreService.findById(1)).thenReturn(Optional.of(s1));
		ResponseEntity<Score> s = scoreController.getScoreById(1);
		assertThat(s.getBody(), is(s1));
	}
	
	@Test
	void getScoreById_WhenNoMatch() {
		Mockito.when(scoreService.findById(1)).thenReturn(Optional.empty());
		ResponseEntity<Score> s = scoreController.getScoreById(1);
		assertThat(s.getStatusCode(), is(HttpStatus.NOT_FOUND));
	}
	
	@Test
	void deleteScore_WhenFound() {
		Mockito.when(scoreService.findById(1)).thenReturn(Optional.of(s1));
		scoreController.deleteScore(1);
		Mockito.verify(scoreService, Mockito.times(1)).deleteScoreById(1);
	}
	
	@Test
	void searchScoreByPlayerOnlyOne(){
		ScoreSearchDTO param = new ScoreSearchDTO();
		Mockito.when(scoreService.getScoreListByPlayer(s1.getPlayer(), PageRequest.of(0, 3))).thenReturn(scorePage);
		param.setPlayer(s1.getPlayer());
		scoreController.searchScore(param, PageRequest.of(0, 3));
		Mockito.verify(scoreService, Mockito.times(1)).getScoreListByPlayer(s1.getPlayer(), PageRequest.of(0, 3));
	}
	
	@Test
	void searchScoreByMultipleName(){
		ScoreSearchDTO param = new ScoreSearchDTO();
		List<String> playersName = Arrays.asList(s1.getPlayer(), s2.getPlayer());
		param.setPlayers(playersName);
		Mockito.when(scoreService.getScoreListByPlayers(playersName, PageRequest.of(0, 3))).thenReturn(scorePage);
		scoreController.searchScore(param, PageRequest.of(0, 3));
		Mockito.verify(scoreService, Mockito.times(1)).getScoreListByPlayers(playersName, PageRequest.of(0, 3));
	}
	
	@Test
	void getScoreListByBetweenDate() {
		ScoreSearchDTO param = new ScoreSearchDTO();
		param.setBeforeDate(s1.getTime());
		param.setAfterDate(DateFormatUtil.addMoreDay(2));
		Mockito.when(scoreService.getScoreListByBetweenDate(param.getBeforeDate(), param.getAfterDate(), PageRequest.of(0, 3))).thenReturn(scorePage);
		scoreController.searchScore(param, PageRequest.of(0, 3));
		Mockito.verify(scoreService, Mockito.times(1)).getScoreListByBetweenDate(param.getBeforeDate(), param.getAfterDate(), PageRequest.of(0, 3));
	}
	
	@Test
	void getScoreListByAfterDate() {
		ScoreSearchDTO param = new ScoreSearchDTO();
		param.setAfterDate(DateFormatUtil.addMoreDay(2));
		Mockito.when(scoreService.getScoreListByAfterDate(param.getAfterDate(), PageRequest.of(0, 3))).thenReturn(scorePage);
		scoreController.searchScore(param, PageRequest.of(0, 3));
		Mockito.verify(scoreService, Mockito.times(1)).getScoreListByAfterDate(param.getAfterDate(), PageRequest.of(0, 3));
	}
	
	@Test
	void getHistory() {
		Mockito.when(scoreService.getMinMaxScoreHistoryByPlayer(s1.getPlayer())).thenReturn(IHistoryScore);
		Mockito.when(scoreService.getScoreListByPlayer(s1.getPlayer())).thenReturn(scores);
//		mvc.perform(get("/api/v1/score/history/" + s1.getPlayer()))
//		   .andExpect(status().isOk())
//		   .andExpect(jsonPath("$.top.score", is(0)))
//		   .andExpect(jsonPath("$.top.time", is(null)))
//		   .andExpect(jsonPath("$.low.score", is(0)))
//		   .andExpect(jsonPath("$.low.time", is(null)))
//		   .andExpect(jsonPath("$.average", is(0.0)))
//		   .andExpect(jsonPath("$.scores", is(scores)));
		
		HistoryScoreResponse historyScoreResponse = new HistoryScoreResponse();
		HistoryScore scoreHistory = new HistoryScore();
		scoreHistory.setScore(IHistoryScore.getminScore());
		scoreHistory.setTime(IHistoryScore.getminDate());
		historyScoreResponse.setLow(scoreHistory);
		
		scoreHistory = new HistoryScore();
		scoreHistory.setScore(IHistoryScore.getmaxScore());
		scoreHistory.setTime(IHistoryScore.getmaxDate());
		historyScoreResponse.setTop(scoreHistory);
		
		historyScoreResponse.setAvarage(IHistoryScore.getAverage());
		
		List<HistoryScore> scoresRespone = new ArrayList<>();
		scores.forEach(score -> {
			HistoryScore scoreHistorys = new HistoryScore();
			scoreHistorys.setScore(score.getScore());
			scoreHistorys.setTime(score.getTime());
			scoresRespone.add(scoreHistorys);
		});
		historyScoreResponse.setScores(scoresRespone);
		
		ResponseEntity<HistoryScoreResponse> s = scoreController.getHistory(s1.getPlayer());
		assertThat(s.getStatusCode(), is(HttpStatus.OK));
		assertThat(s.getBody().getLow().getScore(), is(historyScoreResponse.getLow().getScore()));
		assertThat(s.getBody().getLow().getTime(), is(historyScoreResponse.getLow().getTime()));
	}
}
