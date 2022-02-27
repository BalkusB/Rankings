package com.rankings.players;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "players")
@Entity
public class Player 
{
	@Id
	private String name;
	private int elo;
	
	@Transient
	private List<Battle> battlesWon;
	@Transient
	private List<Battle> battlesLost;
	
	public Player() {};
	
	public Player(String name, int elo)
	{
		this.name = name;
		this.elo = elo;
	}
	
	public void modElo(int mod)
	{
		elo += mod;
	}
	
	public void setBattles(List<Battle> battlesWon, List<Battle> battlesLost)
	{
		this.battlesWon = battlesWon;
		this.battlesLost = battlesLost;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getElo()
	{
		return elo;
	}

//	public List<Battle> getBattlesWon() 
//	{
//		return battlesWon;
//	}
//
//	public List<Battle> getBattlesLost() 
//	{
//		return battlesLost;
//	}
	
}
