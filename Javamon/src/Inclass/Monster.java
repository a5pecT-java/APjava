package Inclass;
public class Monster 
{
	private String name;
	private int health, attackStat, defenseStat, speedStat;
	private String special;
	
	Monster(String n, int a, int d, int s)
	{
		name = n;
		attackStat = a;
		defenseStat = d;
		speedStat = s;
		health = 20;
		pickSpecial();
	}
	public String getSpecial()
	{
		return special;
	}
	public void pickSpecial() 
	{
		// determine which is largest: attack, defense, speed
		if(attackStat >= defenseStat && attackStat >= speedStat)
		{
			special = "slash";
		}
		else if(defenseStat >= speedStat)
		{
			special = "absorb";
		}
		else
		{
			special = "quick";
		}
	}

	public int standardAttack()
	{
		// can deal up to attackStat / 2
		int damage = (int)(Math.random() * attackStat / 2 + 1);
		System.out.println(name + " does a standard attack for " + damage + "damage!");
		return damage;
	}
	
	public int specialAttack()
	{
		int damage = 0;
		if (special.equals("slash"))
		{
			// deals from 3 to (attackStat / 2 + 3)
			// lose a HP
			damage = (int)(Math.random() * attackStat / 2 + 3);
			health--;
			System.out.println(name + " does a slash attack for " + damage + " damage.");
			System.out.println(name + " loses 1 HP.");
		}
		else if (special.equals("absorb"))
		{
			damage = (int)(Math.random() * attackStat) / 2;
			health++;
			System.out.println(name + " does an absorb attack for " + damage + " damage.");
			System.out.println(name + " gains 1 HP.");
		}
		else if (special.equals("quick"))
		{
			damage = 2;
			System.out.println(name + " does a quick attack for 2 damage.");
		}
		return damage;
	}
	
	public void printAttackMenu()
	{
		System.out.println("1. Standard Attack");
		if(special.equals("slash"))
		{
			System.out.println("2. Slash");
		}
		else if(special.equals("absorb"))
		{
			System.out.println("2. Absorb");
		}
		else
		{
			System.out.println("2. Quick Attack");
		}
	}
	
	public boolean isHit(int enemyAttack)
	{
		boolean hit = false;
		int attack = (int)(Math.random() * enemyAttack + 1);
		int defense = (int)(Math.random() * defenseStat + 1);
		if (attack > defense)
		{
			hit = true;
		}
		return hit;
	}
	
	public void takeDamage(int damage)
	{
		health -= damage;
		if (health <= 0)
		{
			health = 0;
			System.out.println(name + " has fainted!");
		}
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getAttackStat()
	{
		return attackStat;
	}
	
	public int getDefenseStat()
	{
		return defenseStat;
	}
	
	public int getSpeedStat()
	{
		return speedStat;
	}
	
	public int getHealth()
	{
		return health;
	}

	public String toString()
	{
		return name + " [Health: " + health + ", Attack: " +
				attackStat + ", Defense: " + defenseStat +
				", Speed: " + speedStat + "]";
	}
}
