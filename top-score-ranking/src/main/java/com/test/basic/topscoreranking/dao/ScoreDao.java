package com.test.basic.topscoreranking.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.test.basic.topscoreranking.entities.Score;

@Repository
public interface ScoreDao extends JpaRepository<Score, Integer>{
	
	@Query("SELECT s FROM Score s WHERE s.player = UPPER(?1) OR s.player = LOWER(?1)")
	Page<Score> getScoreListByPlayer(String name, Pageable pageable);
    
    @Query("SELECT s FROM Score s WHERE s.time > DATE(:afterDate)")
    Page<Score> getScoreListByAfterDate(@Param("afterDate") Date afterDate, Pageable pageable);
    
    @Query("SELECT s FROM Score s WHERE s.player IN (?1)")
    Page<Score> getScoreListByPlayers(List<String> names, Pageable pageable);
    
	@Query("SELECT s FROM Score s WHERE s.time BETWEEN DATE(:afterDate) AND DATE(:beforeDate)")
	Page<Score> getScoreListByBetweenDate(@Param("beforeDate") Date beforeDate, @Param("afterDate") Date afterDate, Pageable pageable);
	
	@Query("SELECT s FROM Score s WHERE s.player = UPPER(?1) OR s.player = LOWER(?1)")
	List<Score> getScoreListByPlayer(String name);
	
	@Query(value ="SELECT MIN(score) AS minScore,"
			+ "(SELECT time FROM tbl_score WHERE player = UPPER(?1) OR player = LOWER(?1) ORDER BY score ASC LIMIT 1) AS minDate, "
			+ "MAX(score) AS maxScore, "
			+ "(SELECT time FROM tbl_score WHERE player = UPPER(?1) OR player = LOWER(?1) ORDER BY score DESC LIMIT 1) AS maxDate, "
			+ "AVG(score) AS average "
			+ "FROM tbl_score WHERE player = UPPER(?1) OR player = LOWER(?1)", nativeQuery = true)
	IHistoryScores getMinMaxScoreHistoryByPlayer(String name);

}
