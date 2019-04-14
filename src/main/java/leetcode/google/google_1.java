/*
 * @(#)google_1.java 2019. 04. 05.
 *
 * Copyright 2012 NHN Corp. All rights Reserved.
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package leetcode.google;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 */
public class google_1 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();

		String [] emails = new String[n];

		for (int i = 0; i < n; i++) {
			emails[i] = scanner.next();
		}

		google_1 g1 = new google_1();

		int ret = g1.numUniqueEmails(emails);

		System.out.println(ret);
	}

	public int numUniqueEmails(String[] emails) {

		Set<String> set = new HashSet<>();

		for (String email : emails) {

			StringBuilder localPart = new StringBuilder();
			StringBuilder emailPart = new StringBuilder();

			boolean isLocalPart = true;
			boolean foundPlus = false;

			for (int i = 0; i < email.length(); i++) {
				char ch = email.charAt(i);

				if (ch == '@') {
					isLocalPart = false;
					continue;
				}

				if (ch == '+') {
					foundPlus = true;
					continue;
				}

				if (isLocalPart) {
					if (foundPlus == false && ch != '.') {
						localPart.append(ch);
					}
				} else {
					emailPart.append(ch);
				}
			}

			set.add(localPart.toString() + "@" + emailPart.toString());
		}

		return set.size();
	}
}
