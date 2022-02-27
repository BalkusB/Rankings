package com.rankings.players;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService 
{
	@Autowired
	private PlayerRepository playerRepo;
	@Autowired
	private BattleRepository battleRepo;
	
	public List<Player> getRankings()
	{
		List<Player> players = new ArrayList<Player>();
		playerRepo.findAll().forEach(players::add);
		for(Player player : players)
			player.setBattles(getBattlesWon(player.getName()),getBattlesLost(player.getName()));
		players.sort((o1, o2) -> Integer.compare(o2.getElo(), o1.getElo()));
		return players;
	}
	
	public Optional<Player> getPlayer(String name)
	{
		Optional<Player> temp = playerRepo.findById(name);
		Player player = temp.get();
		player.setBattles(getBattlesWon(name),getBattlesLost(name));
		return Optional.ofNullable(player);
	}
	
	public List<Battle> getBattlesWon(String name)
	{
		List<Battle> battles = new ArrayList<Battle>();
		battleRepo.findByVictor(name).forEach(battles::add);
		return battles;
	}
	
	public List<Battle> getBattlesLost(String name)
	{
		List<Battle> battles = new ArrayList<Battle>();
		battleRepo.findByLoser(name).forEach(battles::add);
		return battles;
	}
	
	public void addPlayer(String name)
	{
		playerRepo.save(new Player(name, 1000));
	}
	
	public void addBattle(Battle battle)
	{
		Optional<Player> temp = playerRepo.findById(battle.getVictor());
		Player victor = temp.get();
		temp = playerRepo.findById(battle.getLoser());
		Player loser = temp.get();
		int eloChange = getEloChange(victor.getElo(), loser.getElo());
		victor.modElo(eloChange);
		loser.modElo(-eloChange);
		battleRepo.save(battle);
		playerRepo.save(victor);
		playerRepo.save(loser);
	}
	
	public int getEloChange(int victorElo, int loserElo)
	{
		Float toRet = 100 * (1 - 1.0f * 1.0f / (1 + 1.0f * 
                (float)(Math.pow(10, 1.0f * 
               (loserElo - victorElo) / 400))));
		return (int) Math.round(toRet);
	}
}
