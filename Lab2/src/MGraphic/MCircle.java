package MGraphic;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class MCircle {
	
	private int x;
	private int y;
	private int r;
	private String name;
	
	private Color color = Color.BLUE;
	
	private static int id = 0;
	private static Random rand = new Random();
	
	public MCircle() {
		this.x = rand.nextInt(100) + 1;
		this.y = rand.nextInt(100) + 1;
		this.r = rand.nextInt(100) + 1;
		
		this.name = "Circle" + id;
		id++;
	}
	
	public MCircle(int x, int y, int r, Color color) {
		this.x = x;
		this.y = y;
		this.r = r;
		this.color = color;
		
		this.name = "Circle" + id;
		id++;
	}
	
	public void show(Graphics2D g2D) {
		g2D.setColor(color);
		g2D.fillOval(x, y, 2 * r, 2 * r);
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
	
	public void setR(int r) {
		this.r = r;
	}
	
	public int getR() {
		return r;
	}
	
	public Color getColor() {
		return color;
	}
	
}
