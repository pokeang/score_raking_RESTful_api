package com.test.basic.topscoreranking.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;

import com.test.basic.topscoreranking.dao.IHistoryScores;
import com.test.basic.topscoreranking.dao.ScoreDao;
import com.test.basic.topscoreranking.entities.Score;
import com.test.basic.topscoreranking.service.impl.ScoreServiceImpl;
import com.test.basic.topscoreranking.util.DateFormatUtil;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ScoreServiceTest {
	
	private static Score s1;
	private static Score s2;
	

	@Mock
	private ScoreDao scoreDao;
	
	@Mock
	private IHistoryScores IHistoryScore;
	
	@Mock
    private Pageable pageableMock;
	
	@Mock
	private Page<Score> scorePage;
	
	@Mock
	private Query query;
	
	@InjectMocks
	private ScoreServiceImpl scoreService;
	
	@BeforeAll
	public static void init() {
		s1 = new Score("player1", 10, new Date());
		s2 = new Score("player2", 20, new Date());
	}
	
	@Test
	void createScore() {
		Mockito.when(scoreDao.save(s1)).thenReturn(s1);
		assertThat(scoreService.add(s1), is(s1));
		Mockito.verify(scoreDao, Mockito.times(1)).save(s1);
	}
	
	@Test
	public void getScoreById() {
		Mockito.when(scoreDao.findById(1)).thenReturn(Optional.of(s1));
		assertThat(scoreService.findById(1), is(Optional.of(s1)));
		Mockito.verify(scoreDao, Mockito.times(1)).findById(1);
	}
	
	@Test
	void deleteById() {
		scoreService.deleteScoreById(1);
		Mockito.verify(scoreDao, Mockito.times(1)).deleteById(1);
	}
	
	@Test
	public void getScoreListByPlayer() {
		Mockito.when(scoreDao.getScoreListByPlayer(s1.getPlayer(), PageRequest.of(0, 3))).thenReturn(scorePage);
		assertThat(scoreService.getScoreListByPlayer(s1.getPlayer(), PageRequest.of(0, 3)), is(scorePage));
		Mockito.verify(scoreDao, Mockito.times(1)).getScoreListByPlayer(s1.getPlayer(), PageRequest.of(0, 3));
	}
	
	@Test
	public void getScoreListByAfterDate() {
		Mockito.when(scoreDao.getScoreListByAfterDate(s1.getTime(), PageRequest.of(0, 3))).thenReturn(scorePage);
		assertThat(scoreService.getScoreListByAfterDate(s1.getTime(), PageRequest.of(0, 3)), is(scorePage));
		Mockito.verify(scoreDao, Mockito.times(1)).getScoreListByAfterDate(s1.getTime(), PageRequest.of(0, 3));
	}
	
	@Test
	public void getPlayerByPlayers() {
		List<String> playersName = Arrays.asList(s1.getPlayer(), s2.getPlayer());
		Mockito.when(scoreDao.getScoreListByPlayers(playersName, PageRequest.of(0, 3))).thenReturn(scorePage);
		assertThat(scoreService.getScoreListByPlayers(playersName, PageRequest.of(0, 3)), is(scorePage));
		Mockito.verify(scoreDao, Mockito.times(1)).getScoreListByPlayers(playersName, PageRequest.of(0, 3));
	}
	
	@Test 
	public void getScoreListByBetweenDateTime() {
		Mockito.when(scoreDao.getScoreListByBetweenDate(s1.getTime(), DateFormatUtil.addMoreDay(2), PageRequest.of(0, 3))).thenReturn(scorePage);
		assertThat(scoreService.getScoreListByBetweenDate(s1.getTime(), DateFormatUtil.addMoreDay(2), PageRequest.of(0, 3)), is(scorePage));
		Mockito.verify(scoreDao, Mockito.times(1)).getScoreListByBetweenDate(s1.getTime(), DateFormatUtil.addMoreDay(2), PageRequest.of(0, 3));
	}
	
	@Test
	void getScoreListByPlayerName() {
		Mockito.when(scoreDao.getScoreListByPlayer(s1.getPlayer())).thenReturn(Arrays.asList(s1, s2));
		assertThat(scoreService.getScoreListByPlayer(s1.getPlayer()).size(), is(2));
		Mockito.verify(scoreDao, Mockito.times(1)).getScoreListByPlayer(s1.getPlayer());
	}
	
	@Test
	void getMinMaxScoreHistoryByPlayer() {
		Mockito.when(scoreDao.getMinMaxScoreHistoryByPlayer(s1.getPlayer())).thenReturn(IHistoryScore);
		assertThat(scoreService.getMinMaxScoreHistoryByPlayer(s1.getPlayer()), is(IHistoryScore));
		Mockito.verify(scoreDao, Mockito.times(1)).getMinMaxScoreHistoryByPlayer(s1.getPlayer());
	}

}
