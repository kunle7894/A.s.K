package world;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Utils 
{
	public static String loadFilesAsString(String path)
	{
		StringBuilder builder = new StringBuilder();
		try
		{
			InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
			//copyFileUsingStream(new File(path), new File(newPath));
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line;
			while ((line=br.readLine()) != null)
			{
				builder.append(line + "\n");
			}
			br.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return builder.toString();
	}
	
	public static int parseInt(String number)
	{
		try
		{
			return Integer.parseInt(number);
		}
		catch (NumberFormatException e)
		{
			e.printStackTrace();
			return 0;
		}
	}
}
