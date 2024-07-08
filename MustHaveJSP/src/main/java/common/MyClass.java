package common;

public class MyClass {
	
	// 정수 a부터 b까지의 합을 반환하는 정적메서드
	public static int myFunction(int a, int b) {
		int sum = 0;
		for (int i = 0; i < b; i++) {
			sum += i;
		}
		return sum;
	}
}
