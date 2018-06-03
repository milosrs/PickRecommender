package com.lol.model.champions;

public class Info {
	private int difficulty;
	private int attack;
	private int defense;
	private int magic;
	
	public Info() {
		super();
	}
	public Info(int difficulty, int attack, int defense, int magic) {
		super();
		this.difficulty = difficulty;
		this.attack = attack;
		this.defense = defense;
		this.magic = magic;
	}
	public int getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
	public int getAttack() {
		return attack;
	}
	public void setAttack(int attack) {
		this.attack = attack;
	}
	public int getDefense() {
		return defense;
	}
	public void setDefense(int defense) {
		this.defense = defense;
	}
	public int getMagic() {
		return magic;
	}
	public void setMagic(int magic) {
		this.magic = magic;
	}
	
	
}
