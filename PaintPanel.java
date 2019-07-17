package ca.utoronto.utm.paint;

import javax.swing.*;  
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

// https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics2D.html
// https://docs.oracle.com/javase/tutorial/2d/

class PaintPanel extends JPanel implements Observer, MouseMotionListener, MouseListener  {
	private int i=0;
	private PaintModel model; // slight departure from MVC, because of the way painting works
	private View view; // So we can talk to our parent or other components of the view
	private Color colour = Color.WHITE; // This will be our default colour
	private String mode; // modifies how we interpret input (could be better?)
	private Circle circle; // the circle we are building
	private Rectangle rectangle; // the rectangle we are building
	private Square square; // the square we are building
	private int size; //the size of the font
	private int line = 0; //the count of number of scribbles drawn
	private boolean filled; //to check whether or not outline is selected
	private Color backgroundColor = Color.blue;
	public PaintPanel(PaintModel model, View view){
		this.setBackground(backgroundColor);
		this.setPreferredSize(new Dimension(300,300));
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
		
		this.model = model;
		this.model.addObserver(this);
		
		this.view=view;
	}

	/**
	 *  View aspect of this
	 */
	public void paintComponent(Graphics g) {
		// Use g to draw on the JPanel, lookup java.awt.Graphics in
		// the javadoc to see more of what this can do for you!!
		
        super.paintComponent(g); //paint background
        Graphics2D g2d = (Graphics2D) g; // lets use the advanced api
		// setBackground (Color.blue); 
		// Origin is at the top left of the window 50 over, 75 down
		//g2d.setColor(colour);
        g2d.drawString ("i="+i, 50, 75);
		i=i+1;
		
	
		
		//Goes through the array of shapes, and draws them in order...fixes the overlap problem.
		for(Object c: this.model.getShapes()){
			if (c instanceof Circle){
				Circle d = (Circle)c;
				int x = d.getCentre().getX();
				int y = d.getCentre().getY();
				int radius = d.getRadius();
				g2d.setStroke(new BasicStroke(d.getStroke()));
				g2d.setColor(d.getColour());
				//Circle now draws from centre, 
				if (d.isFilled() == false){
				g2d.drawOval(x-radius, y-radius, radius*2, radius*2);
				}
				else{
					g2d.fillOval(x-radius, y-radius, radius*2, radius*2);
				}
				
			} // end circle draw
			
			if (c instanceof Rectangle){
				Rectangle r = (Rectangle)c;
				Point start = r.getStart(); 
				Point end = r.getEnd();
				int smallerX = getSmaller(start.getX(),end.getX());
				int smallerY = getSmaller(start.getY(),end.getY());
				// Rectangle now draws from the start to the end
				int width = Math.abs(end.getX() - start.getX());
				int length = Math.abs(end.getY() - start.getY());
				g2d.setStroke(new BasicStroke(r.getStroke()));
				g2d.setColor(r.getColour());
				if (r.isFilled() == false){
				g2d.drawRect(smallerX, smallerY, width, length);
				}
				else{
					g2d.fillRect(smallerX, smallerY, width, length);
				}
			}// end rectangle draw
			
			if (c instanceof Square){
				Square s = (Square)c;
				Point start = s.getStart(); 
				Point end = s.getEnd();
				g2d.setStroke(new BasicStroke(s.getStroke()));
				g2d.setColor(s.getColour());
				if((end.getY() > start.getY()) & (end.getX() > start.getX())){
					int length = Math.abs(end.getX() - start.getX());
					//There are 4 possible directions in which you can draw a square, NE,NW,SW,SE. The following if statements take care of all those conditions
					if (s.isFilled() == false){
						g2d.drawRect(start.getX(), start.getY(), length, length);
					}
					else{
						g2d.fillRect(start.getX(), start.getY(), length, length);
					}
				}
				
				else if((end.getY() > start.getY()) & (end.getX() < start.getX())){
					int length = Math.abs(end.getX() - start.getX());				
					if (s.isFilled() == false){
						g2d.drawRect(end.getX(), start.getY(), length, length);
					}
					else{
						g2d.fillRect(end.getX(), start.getY(), length, length);
					}
				}
				
				else if((end.getY() < start.getY()) & (end.getX() > start.getX())){
					int length = Math.abs(end.getY() - start.getY());
					g2d.drawRect(start.getX(), end.getY(), length, length);
					if (s.isFilled() == false){
						g2d.drawRect(start.getX(), end.getY(), length, length);
					}
					else{
						g2d.fillRect(start.getX(), end.getY(), length, length);
					}
				}
				else{
					
				int lengthx = Math.abs(end.getX() - start.getX());
				int lengthy = Math.abs(end.getY() - start.getY());
				int smallerL = getSmaller(lengthx,lengthy);
				
				if (s.isFilled() == false){
					g2d.drawRect(start.getX()-smallerL, start.getY()-smallerL, smallerL, smallerL);
				}
				else{
					g2d.fillRect(start.getX()-smallerL, start.getY()-smallerL, smallerL, smallerL);
				}
			
				} 
			} //end square
			
			if (c instanceof ArrayList<?>){
				ArrayList<Point> points = (ArrayList<Point>)c;
				for(int i=0; i<points.size()-1;i++){
					Point p1=points.get(i);
					Point p2=points.get(i+1);
					g2d.setStroke(new BasicStroke(points.get(i).getStroke()));
					g2d.setColor(points.get(i).getColour());
					//draws the line between 2 points with the initial color of white
					//idea check if distance between lines is more than one, mouse points not being read fast enough
					//if((Math.abs(p1.getX()-p2.getX()) <= 1 && Math.abs(p1.getY()-p2.getY()) <= 1) || pressed == true){
					g2d.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
					}
				
			}//ends points draw
			
		}

		g2d.dispose();
	}
	

	@Override
	public void update(Observable o, Object arg) {
		// Not exactly how MVC works, but similar.
		this.repaint(); // Schedule a call to paintComponent
	}
	
	/**
	 *  Controller aspect of this, sets the shape to be drawn
	 *  @param mode
	 */
	public void setMode(String mode){
		
		this.mode=mode;
	}
	

	/**
	 * Set's the size of th efont
	 * @param size
	 */
	public void setSize(int size){
		
		this.size = size;
		
	}
	
	/**
	 * Set's the color to be used to draw the shape, rgb values
	 * @param r
	 * @param g
	 * @param b
	 */
	public void setNewColour(int r, int g , int b){
		
		this.colour = new Color(r,g,b);

	}	
	
	/**
	 * Set's whether or not the shape is to be filled or colored in
	 * @param a
	 */
	public void setColourArea(String a){
		
		if(a == "Inside"){
			filled = true;
		}
		if(a =="Outline"){
			filled = false;
		}
	}
	
	// MouseMotionListener below
	@Override
	public void mouseMoved(MouseEvent e) {
		if(this.mode=="Squiggle"){

		} 
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		if(this.mode=="Squiggle"){
			this.model.addPoint(new Point(e.getX(), e.getY(), size,colour), line);
			
			
		} else if(this.mode=="Circle"){
			//Continuously deletes the shape and redrawws it as it gets dragged to show feedback
			if(this.circle!=null){
				int radius = Math.abs(this.circle.getCentre().getX()-e.getX());
				Point centre = this.circle.getCentre();
				this.circle=null;
				this.model.removeCircle();
				
				this.circle=new Circle(centre, radius,size,colour,filled);
				this.model.addCircle(this.circle);
			}
		
		}
		else if (this.mode == "Rectangle"){
			//Continuously deletes the shape and redrawws it as it gets dragged to show feedback
			if(this.rectangle!=null){
				
				int endX = e.getX();
				int endY = e.getY();
				
				Point endPoint = new Point(endX,endY,size,colour);
				Point start = this.rectangle.getStart();
				this.rectangle = null;
				
				this.model.removeRectangle();
				this.rectangle = new Rectangle(start,endPoint,size,colour,filled);
				this.model.addRectangle(this.rectangle);
			
			}
		}
		else if (this.mode == "Square"){
			//Continuously deletes the shape and redrawws it as it gets dragged to show feedback
			if(this.square!=null){				
				int endX = e.getX();
				int endY = e.getY();			
				Point endPoint = new Point(endX,endY,size,colour);
				Point start = this.square.getStart();
				this.square=null;
				this.model.removeSquare();
				this.square = new Square(start,endPoint,size,colour,filled);
				this.model.addSquare(this.square);
			
			}
		}
	}

	// MouseListener below
	@Override
	public void mouseClicked(MouseEvent e) {
		if(this.mode=="Squiggle"){

		} else if(this.mode=="Circle"){
			
		}
	}
	
	
	
	@Override
	public void mousePressed(MouseEvent e) {
		if(this.mode=="Squiggle"){
			

		} else if(this.mode=="Circle"){
			//finds the starting point of the shape to be drawn at
			int radius = 0;
			Point centre = new Point(e.getX(), e.getY(),size,colour);
			

			this.circle=new Circle(centre, radius,size,colour,filled);
			this.model.addCircle(this.circle);
		}
		else if(this.mode=="Rectangle"){
			//finds the starting point of the shape to be drawn at
			Point start = new Point(e.getX(), e.getY(),size,colour);
			// if the user didn't move their mouse, then the end point is the same
			Point end = new Point(e.getX(),e.getY(),size,colour);

			this.rectangle=new Rectangle(start, end,size,colour,filled);
			this.model.addRectangle(this.rectangle);
		}
		else if(this.mode=="Square"){
			//finds the starting point of the shape to be drawn at
			Point start = new Point(e.getX(),e.getY(),size,colour);
			Point end = new Point(e.getX(), e.getY(),size,colour);
			this.square=new Square(start, end,size,colour,filled);
			this.model.addSquare(this.square);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(this.mode=="Squiggle"){
			this.model.addLines(line); 
			line++;
			
		} else if(this.mode=="Circle"){
			//Finalizes the shape to be drawn
			if(this.circle!=null){

				int radius = Math.abs(this.circle.getCentre().getX()-e.getX());
				this.circle.setRadius(radius);
				this.model.addCircle(this.circle);
				this.circle=null;
			
			}
			
			
		}
		else if(this.mode=="Rectangle"){
			if(this.rectangle!=null){
				//Finalizes the shape to be drawn
				int endX = e.getX();
				int endY = e.getY();
				Point endPoint = new Point(endX,endY,size,colour);
				this.rectangle.setEnd(endPoint);
				this.model.addRectangle(this.rectangle);
				this.rectangle=null;
			
			}
		}
		else if(this.mode=="Square"){
			if(this.square!=null){
				//Finalizes the shape to be drawn
				int endX = e.getX();
				int endY = e.getY();
				Point endPoint = new Point(endX,endY,size,colour);
				this.square.setEnd(endPoint);
				this.model.addSquare(this.square);
				this.square=null;
				
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if(this.mode=="Squiggle"){
			
		} else if(this.mode=="Circle"){
			
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if(this.mode=="Squiggle"){
			
		} else if(this.mode=="Circle"){
			
		}
	}
	
	/**
	 * Checks whether x is smaller than y
	 * @param x
	 * @param y
	 * @return smaller int
	 */
	private int getSmaller(int x, int y){
		
		//Small helper method to make rectangle code easier
		if (x<y){
			return x;
		}
		else
		{
			return y;
		}
	
}
}