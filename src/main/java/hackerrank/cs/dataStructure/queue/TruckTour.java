package hackerrank.cs.dataStructure.queue;

import java.io.IOException;
import java.util.Scanner;

/**
 * @author neo82
 */
public class TruckTour {

	/*
	 * Complete the truckTour function below.
	 */
	static int truckTour(int[][] petrolpumps) {
		int size = petrolpumps.length;

		int i = 0;
		int smallest = 0;
		int remain = 0;

		while (i < size) {
			int petrol = petrolpumps[i][0];
			int distance = petrolpumps[i][1];

			int nextPetrol = remain + petrol - distance;

			if (nextPetrol < 0) {
				smallest = i + 1;
				remain = 0;
			} else {
				remain = nextPetrol;
			}

			i++;
		}

		return smallest;
	}


	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		int n = Integer.parseInt(scanner.nextLine().trim());

		int[][] petrolpumps = new int[n][2];

		for (int petrolpumpsRowItr = 0; petrolpumpsRowItr < n; petrolpumpsRowItr++) {
			String[] petrolpumpsRowItems = scanner.nextLine().split(" ");

			for (int petrolpumpsColumnItr = 0; petrolpumpsColumnItr < 2; petrolpumpsColumnItr++) {
				int petrolpumpsItem = Integer.parseInt(petrolpumpsRowItems[petrolpumpsColumnItr].trim());
				petrolpumps[petrolpumpsRowItr][petrolpumpsColumnItr] = petrolpumpsItem;
			}
		}

		int result = truckTour(petrolpumps);

		System.out.println(result);
	}
}
