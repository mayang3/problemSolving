package hackerrank.cs.dataStructure.array;

import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/crush/problem
 * https://wcipeg.com/wiki/Prefix_sum_array_and_difference_array#Example:_Diamonds_.28BOI.29
 *
 * 예를 들어, constant = 0 이라고 가정하고, prefix array 의 결과 값이 [100, 100, 100, 0, 0] 이 되게 하려면
 * original array 의 값은 [100, 0, 0, -100, 0] 이 되어야 한다.
 * @author baejunbeom
 */
public class ArrayManipulationSolution {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int m = scan.nextInt();

		long[] arr = new long[n];

		int lower;
		int upper;
		long sum;

		for (int i=0 ; i<n ; i++) arr[i]=0;

		// 여기서 원본 array 로 만들어준다.
		for (int i=0 ; i<m ; i++) {
			lower = scan.nextInt();
			upper = scan.nextInt();
			sum = scan.nextInt();
			arr[lower-1] += sum;
			if(upper<n) arr[upper] -= sum;
		}

		long max=0;
		long temp=0;

		// c = 0 으로 하는 prefix array 로 변환한다.
		for(int i=0;i<n;i++){
			temp += arr[i];
			// 그 중 최댓값을 찾으면 그것이 정답이 된다.
			if(temp> max) max=temp;
		}

		System.out.println(max);
	}
}
