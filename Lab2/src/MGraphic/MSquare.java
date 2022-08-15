package MGraphic;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class MSquare {
	
	private int x;
	private int y;	
	private int side;
	private String name;
	
	private Color color = Color.BLUE;
	
	private static int id = 0;
	private static Random rand = new Random();
	
	public MSquare() {
		this.x = rand.nextInt(100) + 1;
		this.y = rand.nextInt(100) + 1;
		this.side = rand.nextInt(100) + 1;
		
		this.name = "Square" + id;
		id++;
	}
	
	public MSquare(int x, int y, int side, Color color) {
		this.x = x;
		this.y = y;
		this.side = side;
		this.color = color;
		
		this.name = "Square" + id;
		id++;
	}
	
	public String getName() {
		return name;
	}
	
	public void show(Graphics2D g2D) {
		g2D.setColor(color);
		g2D.fillRect(x, y, side, side);
	}

	public void move(int x, int y) {
		this.x += x;
		this.y += y;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public void setSide(int side) {
		this.side = side;
	}
	
	public int getSide() {
		return side;
	}

	public void setX(int x) {
		this.x = x;
	}
	
	public int getX() {
		return x;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public int getY() {
		return y;
	}	

}
