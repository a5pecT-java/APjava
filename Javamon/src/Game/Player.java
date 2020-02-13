package Game;

import java.io.Serializable;

public class Player implements Serializable
{
	public enum ClassTypes
	{
		Knight, Rouge, Wizard, Deity
	}

	private static final long serialVersionUID = -7241225752812173282L;

	public String name;

	public int health;
	public int attack;
	public int defense;
	public int agility;
	public double exp;

	public ClassTypes classType;

	public String getStats()
	{
		return "player stats";
	}

	public String getName()
	{
		return name;
	}

	public void setName(String n)
	{
		name = n;
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

	public int toText(Player userPlayer)
	{
		return 0;
	}

	public void giveExp(int lvl1)
	{

	}

	public double getExp()
	{
		return exp;
	}
}
