package Game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import Game.Player.ClassTypes;

public class Game
{
	Scanner scanner = new Scanner(System.in);
	static Player userPlayer;
	boolean playing = true;

	final String filePath = "C:\\Users\\DanDa\\Documents\\GitHub\\APjava\\Javamon\\src\\Game\\Players.txt";

	public void Run()
	{
		printTitle();

		while (playing)
		{
			int menuChoice = printMainMenu();
			checkMenuChoice(menuChoice);
		}
	}

	private void printTitle()
	{
		System.out.println("Welcome to the Infinity Dungeon!");
	}

	private int printMainMenu()
	{
		System.out.println("1. Create a Character!");
		System.out.println("2. Load a Character!");
		System.out.println("3. Check your Stats!");
		System.out.println("4. Goto Battle!");
		System.out.println("5. Save");
		System.out.println("6. Quit");
		System.out.print("Choice: ");
		int menuChoice = scanner.nextInt();
		return menuChoice;
	}

	private void checkMenuChoice(int menuChoice)
	{
		switch (menuChoice)
		{
		case 1: // 1. Create a Character!
			{
				createPlayer();
				break;
			}
		case 2: // 2. Load a Character!"
			{
				Load.loadPlayer(scanner, userPlayer, filePath);
				break;
			}
		case 3: // 3. Check your Stats!
			{
				checkStats();
				break;
			}
		case 4: // 4. Go and Battle!
			{
				userPlayer.getExp();
				battle(callCreateMonster(), userPlayer);
				break;
			}
		case 5: // 5. Save and Quit
			{
				Save.savePlayer(userPlayer, filePath);
				break;
			}
		case 6: // 6. Quit
			{
				playing = false;
				break;
			}
		}
	}

	private static void battle(String monster, Player userPlayer)
	{

		// Amount of XP you get from each monster.
		int lvl1 = (int) (Math.random() * 10 + 15);
		int lvl2 = (int) (Math.random() * 20 + 15);
		int lvl3 = (int) (Math.random() * 30 + 20);
		int lvl4 = (int) (Math.random() * 40 + 25);
		int lvl5 = (int) (Math.random() * 50 + 30);
		double XP = userPlayer.getExp();
		System.out.println(XP);
		int level = 1;
		if (XP < 100)
		{
			System.out.println(userPlayer.getName() + "'s Level: " + level);
		} else if (XP > 100 && XP < 200)
		{
			level++;
			System.out.println(userPlayer.getName() + "'s Level: " + level);
		} else if (XP > 200 && XP < 275)
		{
			level++;
			System.out.println(userPlayer.getName() + "'s Level: " + level);
		} else if (XP > 275 && XP < 325)
		{
			level++;
			System.out.println(userPlayer.getName() + "'s Level: " + level);
		} else
		{
			level++;
			System.out.println(userPlayer.getName() + "'s Level: " + level);
		}
		int xpBar1 = 100;
		int xpBar2 = 200;
		int xpBar3 = 300;
		int xpBar4 = 400;
		int xpBar5 = 500;
		Boolean battling = true;
		System.out.println(monster);
		int monsterLevel = 0;
		while (battling)
		{
			while (level <= monsterLevel)
			{
				switch (monster)
				{
				// Level Ones
				case "Undead Corpse":
					{
						monsterLevel = 1;
						break;
					}
				case "Rat":
					{

						break;
					}
				case "Slime":
					{
						break;
					}
				case "Giant Leech":
					{
						break;
					}
				case "Skeleton":
					{
						break;
					}
				// Level Twos
				case "Lich":
					{
						break;
					}
				case "Skeleton Archer":
					{
						break;
					}
				case "Rust Monster":
					{
						break;
					}
				case "Ghoul":
					{
						break;
					}
				case "Blood Hound":
					{
						break;
					}
				// Level Threes
				case "Orc":
					{
						break;
					}
				case "Skeleton Warrior":
					{
						break;
					}
				case "Giant":
					{
						break;
					}
				case "Dark Mage":
					{
						break;
					}
				case "Stone Giant":
					{
						break;
					}
				case "Vampire":
					{
						break;
					}
				case "Corrupted Hero":
					{
						break;
					}
				case "Elemental":
					{
						break;
					}
				case "Mimic":
					{
						break;
					}
				// Level Fives
				case "Demon":
					{
						break;
					}
				case "Dragon":
					{
						break;
					}
				}
			}
		}

	}

	private static String callCreateMonster()
	{
		try
		{
			return monsterSelector();
		} catch (IOException e)
		{
			System.out.println("didn't reach it");
			e.printStackTrace();
			return null;
		}
	}

	private static String monsterSelector() throws IOException
	{
		String monster = "";
		FileReader fr = new FileReader("C:\\Users\\DanDa\\Documents\\GitHub\\APjava\\Javamon\\src\\Game\\Monsters");
		BufferedReader ReadFileBuffer = new BufferedReader(fr);
//		File monsterSelect = new File("Monsters");
//		BufferedReader c1 = new BufferedReader(new FileReader(monsterSelect));
		int random = (int) (Math.random() * 21 + 1);
		for (int i = 0; i < random; i++)
			monster = ReadFileBuffer.readLine();
		fr.close();
		return monster;
	}

	private void createPlayer()
	{
		scanner.nextLine();
		System.out.print("Enter your name: ");
		String name = scanner.nextLine();
		System.out.println();

		ClassTypes selectedClassType = selectClass(name);
		pickStats(name, selectedClassType);
	}

	private ClassTypes selectClass(String name)
	{
		System.out.println("What class of character do you want " + name + "?");
		System.out.println("1. Knight");
		System.out.println("2. Rouge");
		System.out.println("3. Wizard");
		System.out.println("4. Deity");
		System.out.print("Choice: ");

		int choice = scanner.nextInt();

		while (choice < 1 || choice > 4)
		{
			System.err.print("Invalid Range: \"Try again\"");
			choice = scanner.nextInt();
		}

		ClassTypes classType = getClassTypeFromInt(choice);
		System.out.println();

		return classType;
	}

	private ClassTypes getClassTypeFromInt(int choice)
	{
		switch (choice)
		{
		case 1:
			return ClassTypes.Knight;
		case 2:
			return ClassTypes.Rouge;
		case 3:
			return ClassTypes.Wizard;
		case 4:
			return ClassTypes.Deity;
		default:
			return ClassTypes.Knight;
		}
	}

	private void pickStats(String name, ClassTypes selectedClassType)
	{
		System.out.println("Pick attack, defense, and agility stats.");
		System.out.println("You get 6 points to distribute among the three.");
		int attack, defense, agility;
		do
		{
			System.out.print("Enter Attack stat: ");
			attack = scanner.nextInt();
			System.out.print("Enter Defense stat: ");
			defense = scanner.nextInt();
			System.out.print("Enter Agility stat: ");
			agility = scanner.nextInt();
		} while (attack + defense + agility > 6); // check for negatives
		System.out.println();

		Player newPlayer = null;

		switch (selectedClassType)
		{
		case Knight:
			{
				newPlayer = new Knight(name, 100, 15 + attack, 30 + defense, 8 + agility, 0.0, 0.0);
				System.out.println("You Picked Knight, you have more armor and a special attribute called Pride!");
				break;
			}
		case Rouge:
			{
				newPlayer = new Rouge(name, 80, 25 + attack, 10 + defense, 30 + agility, 0.0, 100.0);
				System.out.println(
						"You Picked Rouge, you have more agility and attack and a special attribute called Stamina!");
				break;
			}
		case Wizard:
			{
				newPlayer = new Wizard(name, 60, 40 + attack, 5 + defense, 15 + agility, 0.0, 100.0);
				System.out.println("You Picked Wizard, you have the most attack and a special attribute called Mana!");
				break;
			}
		case Deity:
			{
				newPlayer = new Deity("Lord " + name, 0.0);
				System.out.println("You're literally a God, unleash your wrath amongst the infinity tower!");
				break;
			}
		}

		userPlayer = newPlayer;
		System.out.println();
	}

	private void checkStats()
	{
		System.out.println(userPlayer.getStats());
		System.out.println();
	}
}
