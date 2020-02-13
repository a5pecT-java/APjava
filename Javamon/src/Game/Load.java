package Game;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;
import java.util.Scanner;

public class Load
{
	public static void loadPlayer(Scanner scanner, Player userPlayer, String filePath)
	{
		Player[] players = listSavedPlayers(filePath);
		// list out players
		for (int i = 0; i < players.length; i++)
		{
			int currentPlayerNumber = i + 1;
			// wont work if .name isn't commented out
			System.out.println(currentPlayerNumber + ". " + players[i]/* .name */);
		}
		// choose by integer
		int playerChoice = scanner.nextInt();

		// turn integer into a player
		int playerIndex = playerChoice - 1;

		Player chosenPlayer = getPlayer(players[playerIndex].name, filePath);
		userPlayer = chosenPlayer;

	}

	public static Player getPlayer(String name, String filePath)
	{
		Player[] players = listSavedPlayers(filePath);
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

	public static Player[] listSavedPlayers(String filePath)
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
		}
		return new Player[4];
	}
}
