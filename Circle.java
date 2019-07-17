package ca.utoronto.utm.paint;

import java.awt.Color;

public class Circle { 
	private Point centre; //centre point of the circle
	private int radius; //radius of the circle
	private int strokeSize; //font size of the circle
	private Color colourOutline = Color.BLACK; //initial colour of circle
	private boolean filled = false; //denotes whether or not the shape is filled or outlined

	
	/**
	 * Initializes and constructs a Circle, to be drawn.
	 * 
	 * @param centre; object point type pointing to the starting location where the object was created to denote the centre of the circle
	 * @param radius; the radius of the circle
	 * @param strokeSize; the size of the font used to draw the shape
	 * @param cO; the color of the shape
	 * @param filled; boolean denotes whether or not the shape is outlined or filled
	 */
	public Circle(Point centre, int radius, int strokeSize,Color cO,boolean filled){
		this.centre = centre;
		this.radius = radius;
		this.strokeSize = strokeSize;
		this.colourOutline = cO;
		this.filled = filled;
		
	}

	/**
	 * Returns the centre point of the circle
	 * @return Point centre
	 */
	public Point getCentre() {
		return centre;
	}
	
	/**
	 * Returns the size of the font
	 * @return int strokeSize
	 */
	public int getStroke(){
		return strokeSize;
	}

	/**
	 * Gives the circle a new centre if needed, relocates the circle
	 * @param Point centre
	 */
	public void setCentre(Point centre) {
		this.centre = centre;
	}

	/**
	 * Returns the radius of the circle
	 * @return int radius;
	 */
	public int getRadius() {
		return radius;
	}

	/**
	 * increases circle size by setting radius
	 * @param int radius
	 */
	public void setRadius(int radius) {
		this.radius = radius;
	}
	/**
	 * Sets the color of the shape to color c
	 * @param Color c
	 */
	public void setColourOutline(Color c){
		this.colourOutline = c;
	}


	/**
	 * Returns the color of the shape, returns a color object
	 * @return Color colourOutline
	 */
	public Color getColour (){
		return this.colourOutline;
	}
	
	/**
	 * Returns whether or not this shape is to be filled
	 * @return boolean filled
	 */
	public boolean isFilled(){
		return filled;
	}

}
