package main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.Scanner;

import towers.SkeletonTower;

// Custom mouse input class
public class MouseHandler extends MouseAdapter {
	
	@SuppressWarnings("unused")
	private int mouseX;
	@SuppressWarnings("unused")
	private int mouseY;
	public Graphics g2;
	public Thread mouseThread;
	private boolean towerExists;
	
	public MouseHandler(Graphics g2) {
		this.g2 = g2;
	}
	
	private boolean checkBuildable(Point p) { // Checks if the tile is placeable (Not placing on path)
		GamePanel.scan = new Scanner(getClass().getResourceAsStream("/resources/map_data.txt"));
		Point textCoordinate = new Point(p.x/48, p.y/48);
		String row;
		char clickedChar = '?';
		for(int y = 0; y <= textCoordinate.y; y++) {
			row = GamePanel.scan.nextLine();
			for(int x = 0; x <= textCoordinate.x; x++) {
				if(x == textCoordinate.x) 	clickedChar = row.charAt(x);
			}
		}
		if(clickedChar != '1' && clickedChar != '2' && !Main.isPaused) {return true;}
		return false;
	}
	
	@Override
	public void mouseClicked(MouseEvent e)
	{
		//System.out.println(e.getX() + "," + e.getY()); // Prints out the coordinates of the point that was clicked
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {}
	
	@Override
	public void mouseExited(MouseEvent e) {}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}
	
	@Override
	public void mousePressed(MouseEvent e) {}
	
	@SuppressWarnings("static-access")
	@Override
	public void mouseReleased(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		
		if(checkBuildable(e.getPoint())) { // Checks if point is buildable
			int roundedX = (e.getX()/48)*48;
			int roundedY = (e.getY()/48)*48;
			SkeletonTower tow = new SkeletonTower(Main.gamePanel, (Graphics2D)Main.gamePanel.getGraphics(), roundedX, roundedY);
			towerExists = false;
			
			for(Point p : Main.gamePanel.skeletonCoordinates) {
				if(p.equals(new Point(roundedX, roundedY))) {
					towerExists = true;
				}
			}
			for(Point p2 : Main.gamePanel.mageCoordinates) {
				if(p2.equals(new Point(roundedX, roundedY))) {
					towerExists = true;
				}
			}
			for(Point p3 : Main.gamePanel.orcCoordinates) {
				if(p3.equals(new Point(roundedX, roundedY))) {
					towerExists = true;
				}
			}
			for(Point p4 : Main.gamePanel.demonCoordinates) {
				if(p4.equals(new Point(roundedX, roundedY))) {
					towerExists = true;
				}
			}
			for(Point p5 : Main.gamePanel.beholderCoordinates) {
				if(p5.equals(new Point(roundedX, roundedY))) {
					towerExists = true;
				}
			}
			for(Point p6 : Main.gamePanel.dragonCoordinates) {
				if(p6.equals(new Point(roundedX, roundedY))) {
					towerExists = true;
				}
			}
			if(!towerExists && Main.actionPanel.goldLeft >=350 && Main.actionPanel.getTowerSelection() == 1) {
				Main.gamePanel.skeletonCoordinates.add(new Point(roundedX, roundedY));
				Main.actionPanel.buySkeletonTower();
				Main.gamePanel.skeletonCopy.add(tow);
			}
			if(!towerExists && Main.actionPanel.goldLeft >=550 && Main.actionPanel.getTowerSelection() == 2) {
				Main.gamePanel.mageCoordinates.add(new Point(roundedX, roundedY));
				Main.actionPanel.buyMageTower();
				Main.gamePanel.mageCopy.add(tow);
			}
			if(!towerExists && Main.actionPanel.goldLeft >=725 && Main.actionPanel.getTowerSelection() == 3) {
				Main.gamePanel.orcCoordinates.add(new Point(roundedX, roundedY));
				Main.actionPanel.buyOrcTower();
				Main.gamePanel.orcCopy.add(tow);
			}
			if(!towerExists && Main.actionPanel.goldLeft >=825 && Main.actionPanel.getTowerSelection() == 4) {
				Main.gamePanel.demonCoordinates.add(new Point(roundedX, roundedY));
				Main.actionPanel.buyDemonTower();
				Main.gamePanel.demonCopy.add(tow);
			}
			if(!towerExists && Main.actionPanel.goldLeft >=900 && Main.actionPanel.getTowerSelection() == 5) {
				Main.gamePanel.beholderCoordinates.add(new Point(roundedX, roundedY));
				Main.actionPanel.buyBeholderTower();
				Main.gamePanel.beholderCopy.add(tow);
			}
			if(!towerExists && Main.actionPanel.goldLeft >=1800 && Main.actionPanel.getTowerSelection() == 6) {
				Main.gamePanel.dragonCoordinates.add(new Point(roundedX, roundedY));
				Main.actionPanel.buyDragonTower();
				Main.gamePanel.dragonCopy.add(tow);
			}
		}
	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {}

}
