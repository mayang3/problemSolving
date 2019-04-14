package baekjoon.sort;

import java.io.*;

public class 수정렬하기3 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int [] arr = new int[N];

		for (int i=0 ; i<arr.length ; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		br.close();

		countingSort(arr);
	}

	static void countingSort(int[] arr) throws IOException {
		int [] output = new int[arr.length];
		int [] counts = new int[10001];

		// 1. 카운팅
		for (int i=0 ; i<arr.length ; i++) {
			counts[arr[i]]++;
		}

		// 2. 구간합으로 변경
		for (int i=1 ; i<counts.length ; i++) {
			counts[i] += counts[i-1];
		}

		// 3. output 에 입력
		for (int i=0 ; i<arr.length ; i++) {
			output[counts[arr[i]] - 1] = arr[i];
			counts[arr[i]]--;
		}

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		for (int v : output) {
			// string 으로 변환하지 않으면 int -> char 처리되서 엉뚱한 값 찍힘
			bw.write(Integer.toString(v) + "\n");
		}

		bw.close();
	}
}
