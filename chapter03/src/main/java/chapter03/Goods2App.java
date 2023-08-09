package chapter03;

import mypackage.Goods2;

public class Goods2App {

	public static void main(String[] args) {
		Goods2 goods = new Goods2();
		
		// public: 접근 제한 없음
		goods.name = "Nikon";
		
		// protected: 같은 패키지, *자식클래스에서 접근 가능 (오류)
		// goods.price = 400000;
		
		// default: 같은 패키지에서 접근 가능 (오류)
		// goods.countStock = 30;
		
		// private: 같은 클래스에서만 접근 가능 (오류)
		// goods.countSold = 50;
		
	}

}
