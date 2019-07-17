package ca.utoronto.utm.paint;

import java.util.ArrayList;
import java.util.Observable;

public class PaintModel extends Observable {
	private ArrayList<Point> points=new ArrayList<Point>();
	private ArrayList<Circle> circles=new ArrayList<Circle>();
	private ArrayList<ArrayList<Point>> lines = new ArrayList<ArrayList<Point>>();  
	private ArrayList<Rectangle> rectangles = new ArrayList<Rectangle>();
	private ArrayList<Square> square = new ArrayList<Square>();
	private ArrayList<Object> shapes = new ArrayList<Object>();
	
	/**
	 * Adds a point to the arraylist of points, gets the number of lines, to add to the appropriate element and shapes
	 * @param p
	 * @param line
	 */
	public void addPoint(Point p, int line){
		this.points.add(p);
		this.lines.add(line, points);
		this.shapes.add(points);
		this.setChanged();
		this.notifyObservers();
		
	}
	/**
	 * Adds the arraylist of points to the arraylist of lines
	 * @param line
	 */
	public void addLines(int line){
		lines.add(new ArrayList<>(points));
		points=new ArrayList<Point>();
		
	
	}
	/**
	 * Returns the points array
	 * @return points
	 */
	public ArrayList<Point> getPoints(){
		return points;
	}
	
	/**
	 * Returns the array of shapes
	 * @return shapes
	 */
	public ArrayList<Object> getShapes(){
		
		return shapes;
	}
	
	/**
	 * Returns the arraylist of arraylists lines.
	 * @return lines
	 */
	public ArrayList<ArrayList<Point>> getLines(){
		return lines;
	}
	
	/**
	 * Add's a circle to the circles arraylist and shapes
	 * @param c
	 */
	public void addCircle(Circle c){
		this.circles.add(c);
		this.shapes.add(c);
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Removes the circle from the arrayList of circles and shapes
	 */
	public void removeCircle(){
		this.circles.remove(circles.size()-1);
		this.shapes.remove(shapes.size()-1);
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Returns the arraylist of circles
	 * @return circles
	 */
	public ArrayList<Circle> getCircles(){
		return circles;
	}
	
	
	/**
	 * Add's a rectangle to the arraylist of rectangles and shapes
	 * @param r
	 */
	public void addRectangle(Rectangle r){
		this.rectangles.add(r);
		this.shapes.add(r);
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Removes the rectangle from the arraylist of rectangles and shapes
	 */
	public void removeRectangle(){
		this.rectangles.remove(rectangles.size()-1);
		this.shapes.remove(shapes.size()-1);
		this.setChanged();
		this.notifyObservers();
	}
	/**
	 * Returns the rectangles array
	 * @return rectangles
	 */
	public ArrayList<Rectangle> getRectangles(){
		return rectangles;
	}
	
	/**
	 * add a square to the arraylist of squares and shapes
	 * @param r
	 */
	public void addSquare(Square r){
		this.square.add(r);
		this.shapes.add(r);
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Removes teh square from the arraylist of shapes and squares
	 * 
	 */
	public void removeSquare(){
		this.square.remove(square.size()-1);
		this.shapes.remove(shapes.size()-1);
		this.setChanged();
		this.notifyObservers();
	}
	
	/**
	 * Returns the arraylist of squares
	 * @return
	 */
	public ArrayList<Square> getSquare(){
		return square;
	}
}
