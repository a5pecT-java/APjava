package Inclass;
import java.util.Scanner;

public class Game 
{
	static boolean playing = true;
	static Scanner input = new Scanner(System.in);
	static Scanner inputNum = new Scanner(System.in);
	static Monster playerM;
	static Monster compM;
	
	public static void main(String[] args) 
	{
		printTitle();
		
		while(playing)
		{
			printMenu();
			int choice = inputNum.nextInt();
			if (choice == 1)
			{
				// create monster
				createMonster();
			}
			else if (choice == 2)
			{
				// check stats
				System.out.println(playerM);
				playerM.printAttackMenu();
			}
			else if (choice == 3)
			{
				// battle
				createCompMonster();
				battle();
			}
			else if (choice == 4)
			{
				// quit
				playing = false;
			}
			else
			{
				System.out.println("Enter a valid choice!");
			}
		}
	}
	
	private static void battle() 
	{
		boolean battling = true;
		boolean playerFirst = false;
		if (playerM.getSpeedStat() >= compM.getSpeedStat())
		{
			playerFirst = true;
		}
		while(battling)
		{
			if(playerFirst)
			{
				playerTurn();
				input.nextLine();
				if(compM.getHealth() > 0)
				{
					compTurn();
					input.nextLine();
				}
				
			}
			else
			{
				compTurn();
				input.nextLine();
				if(playerM.getHealth() > 0)
				{
					compTurn();
					input.nextLine();
				}
			}
			
			if(playerM.getHealth() <= 0)
			{
				battling = false;
				System.out.println(playerM.getName() + " has fainted! The battle is over.");
				System.out.println("You can create a new monster, or try again.");
			}
			else if(compM.getHealth() <= 0)
			{
				battling = false;
				System.out.println(compM.getName() + " has fainted! The battle is over!");
				System.out.println("You can create a new monster, or battle again.");
			}
			System.out.println();
			System.out.println(playerM.getName() + " has " + playerM.getHealth() + " HP");
			System.out.println(compM.getName() + " has " + compM.getHealth() + " HP");
			System.out.println();
		}
	}

	private static void compTurn() {
		System.out.println("Computer's Turn");
		System.out.println("===============");
		
		compM.printAttackMenu();
		int choice = (int)(Math.random() * 2 + 1);
		int damage = 0;
		if ((playerM.isHit(compM.getAttackStat()) || (choice == 2 && compM.getSpecial().equals("quick"))))
		{
			if (choice == 1)
			{
				damage = compM.standardAttack();
			}
			else if (choice == 2)
			{
				damage = compM.specialAttack();
			}
		}
		else
		{
			System.out.println("Miss!");
		}
		playerM.takeDamage(damage);
		
	}

	private static void playerTurn() 
	{
		System.out.println("\nPlayer's Turn");
		System.out.println("=============");
		
		playerM.printAttackMenu();
		int choice = inputNum.nextInt();
		int damage = 0;
		if ((compM.isHit(playerM.getAttackStat()) || (choice == 2 && playerM.getSpecial().equals("quick"))))
		{
			if (choice == 1)
			{
				damage = playerM.standardAttack();
			}
			else if (choice == 2)
			{
				damage = playerM.specialAttack();
			}
		}
		else
		{
			System.out.println("Miss!");
		}
		compM.takeDamage(damage);
	}

	private static void createCompMonster() 
	{
		int as = (int)(Math.random() * 5 + 5);
		int ds = (int)(Math.random() * 5 + 5);
		int ss = (int)(Math.random() * 5 + 2);
		compM = new Monster("Freddy", as, ds, ss);
		System.out.println("You are battling Freddy!");
		System.out.println(compM);
	}

	private static void printTitle()
	{
		System.out.println("Welcome to Javamon: Java Monster Battle Game!");
		System.out.println("~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~");
		System.out.println("");
	}
	
	private static void printMenu() 
	{
		System.out.println("1. Create a monster");
		System.out.println("2. Check monster stats");
		System.out.println("3. Battle!");
		System.out.println("4. Quit");
		System.out.print("Enter your choice: ");
	}
	
	private static void createMonster()
	{
		System.out.println();
		System.out.print("Enter a name: ");
		String name = input.nextLine();
		
		System.out.println("Pick attack, defense, and speed stats.");
		System.out.println("You get 20 points to distribute among the three.");
		int as, ds, ss;
		do
		{
			System.out.print("Enter attack stat: ");
			as = inputNum.nextInt();
			System.out.print("Enter defense stat: ");
			ds = inputNum.nextInt();
			System.out.print("Enter speed stat: ");
			ss = inputNum.nextInt();
		} while(as + ds + ss > 20);
			
		playerM = new Monster(name, as, ds, ss);
		System.out.println(playerM);
		input.nextLine();
	}
}