package Game;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Save
{
	public static void savePlayer(Player player)
	{
		try
		{
			FileOutputStream fileOut = new FileOutputStream("Players.txt");
			ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
			objectOut.writeObject(player);
			objectOut.close();
			System.out.println("The Object  was succesfully written to a file");

		} catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
