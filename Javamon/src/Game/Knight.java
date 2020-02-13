package Game;

import java.io.Serializable;

class Knight extends Player implements Serializable
{
	private static final long serialVersionUID = 7296141938765941548L;
	protected double pride;

	Knight(String name, int health, int attack, int defense, int agility, double exp, double pride)
	{
		this.name = name;
		this.health = health;
		this.attack = attack;
		this.defense = defense;
		this.agility = agility;
		this.exp = exp;
		classType = ClassTypes.Knight;
	}

	@Override
	public String getStats() // had getStats() didnt work.
	{
		return name + "'s Stats:\n" + "Health: " + health + "/100\n" + "Attack: " + attack + "\nDefense: " + defense
				+ "\nAgility: " + agility + "\nPride: " + pride;
	}

	@Override
	public String toString()
	{
		return "a";
	}

	public int getHealth()
	{
		return health;
	}

	public int getAttack()
	{
		return attack;
	}

	public int getDefense()
	{
		return defense;
	}

	public int getAgility()
	{
		return agility;
	}
}