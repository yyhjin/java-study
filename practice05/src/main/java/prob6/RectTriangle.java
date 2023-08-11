package prob6;

public class RectTriangle extends Shape {
	private double width;
	private double height;
	
	public RectTriangle(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	@Override
	double getArea() {
		return (width*height)/2;
	}

	@Override
	double getPerimeter() {
		return width + height + Math.sqrt(Math.pow(width, 2) + Math.pow(height, 2));
	}

}
