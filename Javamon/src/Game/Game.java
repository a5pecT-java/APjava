package Game;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.util.Scanner;

import Game.Player.ClassTypes;

public class Game
{
	Scanner scanner = new Scanner(System.in);
	Player userPlayer;
	boolean playing = true;

	final String filePath = "Players.txt";

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
		System.out.println("5. Quit and Save");
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
			loadPlayer();
			break;
		}
		case 3: // 3. Check your Stats!
		{
			checkStats();
			break;
		}
//			case 4: // 4. Go and Battle!
//			{
//				battle();
//				break;
//			}
		case 5: // 5. Save and Quit
		{
			playing = false;
			Save.savePlayer(userPlayer);
			break;
		}
		case 6: // 6. Quit
		{
			playing = false;
			break;
		}
		}
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
		int at, de, ag;
		do
		{
			System.out.print("Enter Attack stat: ");
			at = scanner.nextInt();
			System.out.print("Enter Defense stat: ");
			de = scanner.nextInt();
			System.out.print("Enter Agility stat: ");
			ag = scanner.nextInt();
		} while (at + de + ag > 6); // check for negatives
		System.out.println();

		Player newPlayer = null;

		switch (selectedClassType)
		{
		case Knight:
		{
			newPlayer = new Knight(name, 100, 15 + at, 30 + de, 8 + ag, 0.0, 0.0);
			System.out.println("You Picked Knight, you have more armor and a special attribute called Pride!");
			break;
		}
		case Rouge:
		{
			newPlayer = new Rouge(name, 80, 25 + at, 10 + de, 30 + ag, 0.0, 100.0);
			System.out.println(
					"You Picked Rouge, you have more agility and attack and a special attribute called Stamina!");
			break;
		}
		case Wizard:
		{
			newPlayer = new Wizard(name, 60, 40 + at, 5 + de, 15 + ag, 0.0, 100.0);
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

	private void loadPlayer()
	{
		Player[] players = listSavedPlayers();
		// list out players
		for (int i = 0; i < players.length; i++)
		{
			int currentPlayerNumber = i + 1;
			System.out.println(currentPlayerNumber + ". " + players[i].name);
		}
		// choose by integer
		int playerChoice = scanner.nextInt();

		// turn integer into a player
		int playerIndex = playerChoice - 1;
		Player chosenPlayer = getPlayer(players[playerIndex].name);

		userPlayer = chosenPlayer;
	}

	private Player getPlayer(String name)
	{
		Player[] players = listSavedPlayers();
		for (int i = 0; i < players.length; i++)
		{
			Player currentPlayer = players[i];
			String currentPlayerName = currentPlayer.name;
			if (currentPlayerName == name)
			{
				return currentPlayer;
			}
		}

		return null;
	}

	private Player[] listSavedPlayers()
	{
		try
		{
			FileInputStream fileIn = new FileInputStream(filePath);
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);

			Player[] players = new Player[100];

			int currentIndex = 0;
			boolean hasObjects = true;

			while (hasObjects)
			{
				Object objectThatWasRead = objectIn.readObject();
				if (objectThatWasRead != null)
				{
					players[currentIndex] = (Player) objectThatWasRead;
					currentIndex++;
				} else
				{
					hasObjects = false;
				}
			}

			fileIn.close();
			objectIn.close();

			return players;
		} catch (FileNotFoundException e)
		{
			System.out.println("File was not found.");
		} catch (ClassNotFoundException e)
		{
			System.out.println("Couldn't find class.");
		} catch (Exception e)
		{
			System.out.println("There are no players");
			Run();
		}

		return new Player[4];
	}
}
