package chapter03;

import mypackage.Goods2;;

public class DiscountGoods2 extends Goods2 {
	private float discountRate = 0.5f;
	
	public int getDiscountPrice() {
		// protected는 자식클래스에서 접근할 수 있음. price
		int discountPrice = (int)(discountRate * price);
		return discountPrice;
	}
}
