package chapter04;

import java.util.Calendar;

public class CalendarTest {

	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		
		// 메소드 앞 final - 오버라이드 금지
		// 객체 앞 final - 다른 객체 할당 금지

		printDate(cal);

		cal.set(Calendar.YEAR, 2023);
		cal.set(Calendar.MONTH, 11);  // 12월(month-1)
		cal.set(Calendar.DATE, 25);
		printDate(cal);
		
		cal.set(2017, 9, 25);
		cal.add(Calendar.DATE, 10000);
		printDate(cal);
	}

	private static void printDate(Calendar cal) {
		// 변수 앞 final - 값 변경 금지(상수)
		final String[] DAYS = {"일", "월", "화", "수", "목", "금", "토"};
		
		
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONDAY);  // 0 ~ 11, +1
		int date = cal.get(Calendar.DATE);
		int day = cal.get(Calendar.DAY_OF_WEEK);  // 1(일) ~ 7(월)
		int hour = cal.get(Calendar.HOUR);
		int minute = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);
		
		System.out.println(
				year + "/" 
				+ (month + 1) + "/"
				+ date + " "
				+ DAYS[day-1] + "요일 "
				+ hour + ":"
				+ minute + ":"
				+ second
				);
	}

}
