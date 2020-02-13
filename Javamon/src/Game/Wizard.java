package Game;

import java.io.Serializable;

class Wizard extends Player implements Serializable
{
	private static final long serialVersionUID = -7322711679959362790L;
	public double mana;

	Wizard(String name, int health, int attack, int defense, int agility, double exp, double mana)
	{
		this.name = name;
		this.health = health;
		this.attack = attack;
		this.defense = defense;
		this.agility = agility;
		this.exp = exp;
		classType = ClassTypes.Wizard;
	}

	@Override
	public String getStats()
	{
		return name + "'s Stats:\n" + "Health: " + health + "/60\n" + "Attack: " + attack + "\nDefense: " + defense
				+ "\nMana: " + mana;
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