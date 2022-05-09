/**
 * This is a shape class that is meant to be inherited by 
 * sub classes to become other shapes
 * @author Tianhan Wang
 *
 */
public abstract class Shape implements Comparable<Shape> {
	
	//let the sub-class to calculates its own area
	abstract double area();
	
	@Override
	public int compareTo(Shape s) {
		double d=this.area()-s.area();
		int i=(int)d;
		return i;
	}
}

/**
 * Cirlce class extends shape
 * @author TIanhan Wang
 *
 */
class Circle extends Shape{
	double radius;
	public Circle(double radius) {
		this.radius=radius;
	}
	@Override
	public double area() {
		return Math.PI*radius*radius;
	}
	
}
/**
 * Rectangle has width and height to its attributes
 * @author TIanhan Wang
 *
 */
class Rectangle extends Shape{
	double width;
	double height;
	public Rectangle(double width,double height) {
		this.width=width;
		this.height=height;
	}
	@Override
	public double area() {
		return this.width*this.height;
	}
}

/**
 * Square only has side to its attribute
 * @author TIanhan Wang
 *
 */
class Square extends Shape{
	double side;
	public Square(double side) {
		this.side=side;
	}
	@Override
	public double area() {
		return side*side;
	}
}




