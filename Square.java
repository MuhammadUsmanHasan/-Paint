package ca.utoronto.utm.paint;

import java.awt.Color;

public class Square {
	private Point end; //finishing corner in which the shape is drawn
	private Point start; //starting corner in which the shape is drawn
	private int size; //size of font
	private boolean filled = false; //whether or not the shape it to be filled or outlined
	private Color colourOutline = Color.BLACK; //starting initial colour

	
		/**
		 * Initializes and constructs a Square, to be drawn.
		 * 
		 * @param start
		 * @param end
		 * @param size
		 * @param cO
		 * @param filled
		 */
		public Square(Point start, Point end, int size,Color cO,boolean filled){
			this.start = start;
			this.end = end;
			this.size = size;
			this.colourOutline = cO;
			this.filled = filled;
		}

		
		/**
		 * Returns a boolean filled which denotes whether or not this shape is to be filled
		 * @return filled
		 */
		public boolean isFilled(){
			return filled;
		}

		
		/**
		 * Sets the ending corner of the shape square
		 * @param e
		 */
		public void setEnd(Point e) {
			this.end = e;
		}
		
		/**
		 * Returns the int fontsize of the shape
		 * @return size
		 */
		public int getStroke(){
			return size;
		}


		/**
		 * Set's the corner in which the shape is drawn from
		 * @param s
		 */
		public void setStart(Point s) {
			this.start = s;
		}
		
		/**
		 * returns the corner in which the square is finished drawing
		 * @return end
		 */
		public Point getEnd(){
			return this.end;
		}
		
		/**
		 * returns the corner in which the square is started drawing
		 * @return start
		 */
		public Point getStart(){
			return this.start;
		}
		

		/**
		 * Set's the colour of the shape
		 * @param c
		 */
		public void setColourOutline(Color c){
			this.colourOutline = c;
		}

	
		/**
		 * Returns the color of the shape
		 * @return colourOutline
		 */
		public Color getColour (){
			return this.colourOutline;
		}


	

}
