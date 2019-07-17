package ca.utoronto.utm.paint;

import java.awt.BasicStroke;
import java.awt.Color;

public class Point {
	int x, y,size;
	private Color colour = Color.BLACK; //starter colour of the point
	
	
	/**
	 * Initializes and constructs a point
	 * 
	 * @param x; the x position of the pixel
	 * @param y; the y poisition of the pixel
	 * @param size; the size of the font
	 * @param c; the color of the point
	 */
	Point(int x, int y, int size,Color c){
		this.x=x; this.y=y; this.size = size;
		this.colour = c;
	}
	
	/**
	 * Returns the x position of the point
	 * @return x
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Returns the size of the point
	 * @return
	 */
	public int getStroke(){
		return size;
	}
	
	/**
	 * Set's the position of the point to be drawn
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Returns the y position of hte point
	 * @return y
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Set's the y poisition of the point to be drawn
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * Set's the color of the point
	 * @param c
	 */
	public void setColour(Color c){
		this.colour = c;
	}


	/**
	 * Returns the color of the point
	 * @return
	 */
	public Color getColour(){
		return this.colour;
	}
	
}
