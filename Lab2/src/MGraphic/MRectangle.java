package MGraphic;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class MRectangle {
	
	private int x;
	private int y;	
	private int width;
	private int height;
	private String name;
	
	private Color color = Color.BLUE;
	
	private static int id = 0;
	private static Random rand = new Random();
	
	public MRectangle() {
		this.x = rand.nextInt(100) + 1;
		this.y = rand.nextInt(100) + 1;
		this.width = rand.nextInt(100) + 1;
		this.height = rand.nextInt(100) + 1;
		
		this.name = "Rectangle" + id;
		id++;
	}
	
	public MRectangle(int x, int y, int width, int height, Color color) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
		
		this.name = "Rectangle" + id;
		id++;
	}
	
	public void show(Graphics2D g2D) {
		g2D.setColor(color);
		g2D.fillRect(x, y, width, height);
	}

	public void move(int x, int y) {
		this.x += x;
		this.y += y;
	}
	
	public String getName() {
		return name;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public int getHeight() {
		return height;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getWidth() {
		return width;
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
