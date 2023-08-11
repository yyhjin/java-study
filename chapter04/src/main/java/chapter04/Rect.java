package chapter04;

import java.util.Objects;

public class Rect {
	private int width;
	private int height;
	
	public Rect(int width, int height) {
		this.width = width;
		this.height = height;
	}

	@Override
	public int hashCode() {
//		return Objects.hash(height, width);
		
		// 비즈니스 로직 바뀌었을 때 (넓이 같으면 같은 애로)
		return Objects.hash(height * width);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rect other = (Rect) obj;
//		return height == other.height && width == other.width;
		
		// 비즈니스 로직이 바뀐다면 hashCode도 같이 바꿔줘야 함
		return height*width == other.height*other.width;
	}

	@Override
	public String toString() {
		return "Rect [width=" + width + ", height=" + height + "]";
	}
	
	
}
