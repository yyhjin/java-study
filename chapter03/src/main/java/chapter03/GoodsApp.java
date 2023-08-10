package chapter03;

public class GoodsApp {

	public static void main(String[] args) {
		Goods camera = new Goods();
		
		// 정보은닉, 캡슐화 사용 X (변수 default)
/*
		camera.name = "Nikon";
		camera.price = 400000;
		camera.countStock = 30;
		camera.countSold = 50;
		
		System.out.println(
				"상품이름 : " + camera.name
				+ ", 가격: " + camera.price
				+ ", 재고개수: " + camera.countStock
				+ ", 팔린 개수: " + camera.countSold);
*/
		
		// 정보은닉, 캡슐화 (private, Getter, Setter)
		camera.setName("Nikon");
		camera.setPrice(400000);
		camera.setCountStock(30);
		camera.setCountSold(50);

		// 정보은닉(데이터 보호) - setter 함수에서 코드 짜서 막아줌
		camera.setPrice(-1);
		
		// countOfGoods 테스트
		Goods goods1 = new Goods();
		Goods goods2 = new Goods();
		Goods goods3 = new Goods();
		System.out.println(Goods.countOfGoods);
	
		camera.showInfo();
		
		camera.setPrice(500000);
		int discountPrice = camera.calcDiscountPrice(0.5);
		System.out.println(discountPrice);
		
		Goods tv = new Goods("TV", 400000, 10, 20);
		tv.showInfo();
	}

}
