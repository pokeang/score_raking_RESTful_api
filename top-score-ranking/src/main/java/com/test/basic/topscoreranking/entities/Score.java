package com.test.basic.topscoreranking.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
@Table(name = "tbl_score")
public class Score {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
//	@ManyToOne(fetch = FetchType.LAZY, optional = false)
//	@JoinColumn(player = "player_id", nullable = false)
//	@OnDelete(action = OnDeleteAction.CASCADE)
//    // @JsonIgnore
//	private Player player;
	
	// name of player
	@NotNull
	private String player;

	@Min(0)
	private int score;

	private Date time;

	public Score() {
		
	}
	
	public Score(@NotNull String player, @Min(0) int score, Date time) {
		super();
		this.player = player;
		this.score = score;
		this.time = time;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	@Override
	public String toString() {
		return "Score [id=" + id + ", player=" + player + ", score=" + score + ", time=" + time + "]";
	}
	
}
