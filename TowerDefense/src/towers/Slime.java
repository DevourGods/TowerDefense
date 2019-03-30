package towers;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Slime extends Tower
{
	public Slime() throws IOException
	{
		
		image = ImageIO.read(getClass().getResource("/resources/Slime.png"));
		range = 2;
		speed = 1;
	}
}
