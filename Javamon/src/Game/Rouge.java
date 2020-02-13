package Game;

import java.io.Serializable;

class Rouge extends Player implements Serializable
{
	private static final long serialVersionUID = -9219641156098824973L;
	public double stamina;

	Rouge(String name, int health, int attack, int defense, int agility, double exp, double stamina)
	{
		this.name = name;
		this.health = health;
		this.attack = attack;
		this.defense = defense;
		this.agility = agility;
		this.exp = exp;
		this.stamina = stamina;
		classType = ClassTypes.Rouge;
	}

	@Override
	public String getStats()
	{
		return name + "'s Stats:\n" + "Health: " + health + "/80\n" + "Attack: " + attack + "\nDefense: " + defense
				+ "\nAgility: " + agility;
	}

	@Override
	public int getHealth()
	{
		return health;
	}

	@Override
	public int getAttack()
	{
		return attack;
	}

	@Override
	public int getDefense()
	{
		return defense;
	}

	@Override
	public int getAgility()
	{
		return agility;
	}

	@Override
	public void giveExp(int amount)
	{
		exp += amount;
	}

	@Override
	public double getExp()
	{
		return exp;
	}

}