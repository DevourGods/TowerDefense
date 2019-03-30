package towers;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Skeleton extends Tower
{
	public Skeleton() throws IOException
	{	
		image = ImageIO.read(getClass().getResource("/resources/Skeleton.png"));
		imagePlaced = ImageIO.read(getClass().getResource("/resources/Skeleton-Grass.png"));
		range = 2;
		speed = 1;
	}
}
