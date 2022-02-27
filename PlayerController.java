package com.rankings.players;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerController
{
	@Autowired
	private PlayerService playerService;
	
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/rankings")
	public List<Player> displayRankings()
	{
		return playerService.getRankings();
	}
	
	@GetMapping("/rankings/{name}")
	public Optional<Player> displayPlayer(@PathVariable String name)
	{
		return playerService.getPlayer(name);
	}
	
	@PostMapping("/rankings/{name}")
	public void addPlayer(@PathVariable String name)
	{
		playerService.addPlayer(name);
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@PutMapping("/rankings")
	public Battle addBattle(@RequestBody Battle battle)
	{
		playerService.addBattle(battle);
		return battle;
	}
}
