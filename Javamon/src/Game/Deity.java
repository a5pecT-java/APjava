package Game;
import java.io.Serializable;
class Deity extends Player implements Serializable
{
	private static final long serialVersionUID = 7270741692631815548L;
	Deity(String name, int health, int attack, int defense, int agility, double exp) 
	{
		this.name = name;
		this.health = health;
		this.attack = attack;
		this.defense = defense;
		this.agility = agility;
		this.exp = exp;
		classType = ClassTypes.Deity;
	}
	
	@Override
	public String getStats()
	{
		return name + "'s Stats:\n" + "Health: " + health + "/100\n" + "Attack: " + attack + "\nDefense: " + defense + "\n";
	}
}