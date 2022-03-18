package leetcode;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;

public class BurstBalloons {
	public int maxCoins(int[] iNums) {
		return -1;
	}

	public static void main(String[] args) throws NoSuchAlgorithmException {
		String text = "Hello World";
		System.out.println(((long)Integer.MAX_VALUE  - Integer.MIN_VALUE) / 4);
	}

	int findMachine(String key) {
		int hash = key.hashCode();

		return selectMachine(hash);
	}

	private int selectMachine(int hash) {
		/**
		 * with consistent Hashing
		 *
		 * SELECT machine_id FROM machine_mapping
		 * WHERE start_token >= #{hash}
		 * AND end_token <= #{hash}
		 */

		return 0;
	}
}
