package prob02;

import java.util.Scanner;

public class GoodsApp {
	private static final int COUNT_GOODS = 3;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		Goods[] goods = new Goods[COUNT_GOODS];
		
		// 상품 입력
		for (int i = 0; i < COUNT_GOODS; i++) {
			String line = scanner.nextLine();
			String[] infos = line.split(" ");
		
			Goods g = new Goods();
			g.name = infos[0];
			g.price = Integer.parseInt(infos[1]);
			g.count = Integer.parseInt(infos[2]);
			
			goods[i] = g;
		}
		
		// 상품 출력
		for (int i = 0; i < COUNT_GOODS; i++) {
			System.out.println(goods[i].name + "(가격:" + goods[i].price + "원)이 " + goods[i].count + "개 입고 되었습니다.");			
		}
		
		// 자원정리
		scanner.close();
	}
}
