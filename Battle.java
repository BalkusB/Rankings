package com.rankings.players;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "battles")
@Entity
public class Battle 
{
	@Id
	private String id;
	private String victor;
	private String loser;
	
	public Battle() {};
	
	public Battle(String id, String victor, String loser)
	{
		this.id = id;
		this.victor = victor;
		this.loser = loser;
	}

	public String getId() 
	{
		return id;
	}

	public String getVictor() 
	{
		return victor;
	}
	
	public String getLoser() 
	{
		return loser;
	}
	
}
