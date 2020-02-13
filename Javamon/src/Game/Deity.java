package Game;

import java.io.Serializable;

class Deity extends Player implements Serializable
{
	private static final long serialVersionUID = 7270741692631815548L;

	Deity(String name, double exp)
	{
		this.name = name;
		this.health = Integer.MAX_VALUE;
		this.attack = Integer.MAX_VALUE;
		this.defense = Integer.MAX_VALUE;
		this.agility = Integer.MAX_VALUE;
		this.exp = exp;
		classType = ClassTypes.Deity;
	}

	@Override
	public String getStats()
	{
		return name + "'s Stats:\n" + "Health: " + health + "/Inf\n" + "Attack: " + attack + "\nDefense: " + defense
				+ "\n";
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