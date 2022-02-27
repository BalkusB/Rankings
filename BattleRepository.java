package com.rankings.players;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface BattleRepository extends CrudRepository<Battle, String>
{
	public List<Battle> findByVictor(String victor);
	public List<Battle> findByLoser(String loser);
}
