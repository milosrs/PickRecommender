package com.lol.util;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lol.model.champions.Champion;
import com.lol.model.champions.Spell;
import com.lol.model.champions.Stats;

@Service
public class Utils {

	public static boolean isAdcEligible(Champion champ, Champion enemy) {
		System.out.println("Champion name: "+ champ.getName());
		Stats stats = champ.getStats();
		Stats enemyStats = enemy.getStats();
		
		List<Spell> spells = champ.getSpells();
		List<Spell> enemySpells = enemy.getSpells();
		
		double totalAd = stats.getAttackdamage() + stats.getAttackdamageperlevel() * 18;
		double range = stats.getAttackrange();
		double attackSpeed = stats.getAttackspeedoffset() + stats.getAttackspeedperlevel() * 10;
		
		double totalAdEnemy = enemyStats.getAttackdamage() + enemyStats.getAttackdamageperlevel() * 18;
		double rangeEnemy = enemyStats.getAttackrange();
		double enemyAS = enemyStats.getAttackspeedoffset() + enemyStats.getAttackspeedperlevel() * 10;
		
		return ((totalAd*attackSpeed > totalAdEnemy*enemyAS + 20 && range > rangeEnemy + 50) || isCounteringEnemySpellsADC(spells, enemySpells, attackSpeed, enemyAS, stats, enemyStats));
	}

	//DMG: 1, BonusPhysicalAd in %: 3
	private static boolean isCounteringEnemySpellsADC(List<Spell> spells, List<Spell> enemySpells, Double as, Double enemyAs, Stats stats, Stats enemyStats) {
		Double midGameImportant[][] = createDamageMatrix(spells, as, stats);
		Double midGameImportantEnemy[][] = createDamageMatrix(enemySpells, as, enemyStats);
		
		int enemyWins = 0;
		int champWins = 0;
		int spellCount = 4;
		
		for(int i = 0; i < spellCount; i++) {
			if(midGameImportant[i][0] == 0 || midGameImportantEnemy[i][0] == 0) {
				continue;
			} else {
				if(midGameImportant[i][0] > midGameImportantEnemy[i][0] + 500) {
					champWins++;
				} else if(midGameImportant[i][0] < midGameImportantEnemy[i][0] + 300) {
					enemyWins++;
				}
				
				if(midGameImportant[i][1] > midGameImportantEnemy[i][1]) {
					champWins++;
				} else if(midGameImportant[i][1] < midGameImportantEnemy[i][1]) {
					enemyWins++;
				}
			}
		}
		System.out.println("Champion votes: "+ champWins +"\nEnemy votes: " +enemyWins);
		return champWins > enemyWins;
	}
	
	private static Double[][] createDamageMatrix(List<Spell> spells, Double as, Stats stats) {
		Double midGame[][] = new Double[4][2];
		int range = 0;
		int dmg = 0;
		
		for(Spell spell : spells) {
			int midGameRank = Math.floorDiv(spell.getMaxrank(), 2) + 1;
			
			if(spell.getRange() instanceof String) {
				midGame[range][dmg++] = 0.0;
				midGame[range][dmg++] = 0.0;
			} else if(spell.getRange() instanceof List) {
				List<Integer> spellRange = (List<Integer>) spell.getRange();
				Double bonusPercentPhysicalScaled = getImportantSpellData(spell.getEffect(), midGameRank);
				Double physicalDmg = getImportantSpellData(spell.getEffect(), midGameRank);
				
				midGame[range][dmg++] = spellRange.get(midGameRank).doubleValue();
				midGame[range][dmg++] = physicalDmg + physicalDmg*bonusPercentPhysicalScaled;
			}
			
			range++;
			dmg = 0;
		}
		
		return midGame;
	}
	
	private static Double getImportantSpellData(List<Object> effect, int index) {
		try {
			return (Double) effect.get(index);
		} catch(Exception e) {
			return 0.0;
		}
	}
}
