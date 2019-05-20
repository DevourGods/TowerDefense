package main;

import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.*;
import java.io.IOException;

public class AnimatedSprite {
	
	//instance variables
	protected JPanel frame;
	protected Graphics2D g2;
	public BufferedImage image;
	public boolean alive;
	public Point position;
	public Point velocity;
	public double rotationRate;
	public int currentState;
	public int currentFrame;
	public int totalFrames;
	public int animationDirection;
	public int frameCount;
	public int frameDelay;
	public int frameWidth;
	public int frameHeight;
	public int columns;
	public double moveAngle;
	public double faceAngle;
	public boolean once;
	public boolean walking = true;
	public int health;
	
	// Constructor
	public AnimatedSprite(JPanel _frame, Graphics2D _g2) {
		frame= _frame;
		g2 = _g2;
		image = null;
		alive = true;
		position = new Point(960,0);
		velocity = new Point(0,0);
		rotationRate = 0.0;
		currentState = 0;
		currentFrame = 0;
		totalFrames = 1;
		animationDirection = 1;
		frameCount = 0;
		frameDelay = 0;
		frameWidth = 0;
		frameHeight = 0;
		columns = 0;
		moveAngle = 0.0;
		faceAngle = 0.0;
		once = false;
		
	}
	
	// Accessor methods
	public JPanel getJFrame() {return frame;}
	public Graphics2D getGraphics() {return g2;}
	
	// Mutator methods
	public void setGraphics(Graphics2D _g2) {g2 = _g2;}
	public void setImage(BufferedImage _image) {image = _image;}
	
	public int getHeight() {
		if(image != null)
			return image.getHeight();
		return 0;
	}
	
	public int getWidth() {
		if(image != null)
			return image.getWidth();
		return 0;
	}
	
	public double getCenterX() {
		return position.x + getWidth()/2;
	}
	
	public double getCenterY() {
		return position.y + getHeight()/2;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)position.x, (int) position.y,
				frameWidth, frameHeight);
	}
	
	public void load(String filename, int _columns, int _totalFrames, int _frameWidth, int _frameHeight) {
		try{
			image = ImageIO.read(getClass().getResourceAsStream(filename));
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
		columns = _columns;
		totalFrames = _totalFrames;
		frameWidth = _frameWidth;
		frameHeight = _frameHeight;
	}
	
	public synchronized void update() {
		//update position
		position.x += velocity.x;
		position.y += velocity.y;
		
		//update rotation
		if(rotationRate > 0.0) {
			faceAngle += rotationRate;
			if(faceAngle < 0)
				faceAngle = 360-rotationRate;
			if(faceAngle > 360)
				faceAngle = rotationRate;
		}
		
		//update animation
		if(totalFrames > 1) {
			frameCount++;
			if(frameCount > frameDelay) {
				frameCount = 0;
				currentFrame += animationDirection;
				if(currentFrame > totalFrames -1) {
					currentFrame = 0;
					if(once)
						alive = false;
				} else if(currentFrame < 0) {
					currentFrame = totalFrames -1;
					if(once)
						alive = false;
				}
			}		
		}
	}
	
	// draw bounding rectangle around sprite
	public void drawBounds(Color c) {
		g2.setColor(c);
		g2.draw(getBounds());
	}
	
	public synchronized void draw() {
		if(alive) {
			update();
			// Get current frame
			int frameX = (currentFrame % columns) * frameWidth;
			int frameY = (currentFrame / columns) * frameHeight;
			// Draw the frame
			g2.drawImage(image, position.x, position.y,position.x+frameWidth, position.y+frameHeight,
					frameX, frameY, frameX+frameWidth, frameY+frameHeight,getJFrame());
		}
	}
	
	// Check for collision with a rectangular shape
	public boolean collidesWith(Rectangle rect) {
		return (rect.intersects(getBounds()));
	}
	
	// Check for collision with another sprite
	public boolean collidesWith(AnimatedSprite other) {
		return (getBounds().intersects(other.getBounds()));
	}
	
	// Check for collision with a point
	public boolean collidesWith(Point p) {
		return (getBounds().contains(p));
	}
}
