package Game;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Save
{
	public static void savePlayer(Player player, String filePath)
	{
		try
		{
			FileOutputStream fileOut = new FileOutputStream(filePath);
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
