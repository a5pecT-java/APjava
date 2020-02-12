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
}
