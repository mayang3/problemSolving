package leetcode;

import java.util.*;

public class AccountMerge {

	public static void main(String[] args) {
		String [][] input = {{"John", "a@mail.com", "b@mail.com", "c@mail.com"},
			{"John", "d@mail.com", "e@mail.com", "f@mail.com"},
			{"John", "g@mail.com", "b@mail.com", "c@mail.com"},
			{"Mary", "mary@mail.com"}};

		List<List<String>> listInput = new ArrayList<>();

		for (String[] ii : input) {
			listInput.add(Arrays.asList(ii));
		}

		AccountMerge accountMerge = new AccountMerge();
		System.out.println(accountMerge.accountsMerge(listInput));
	}

	public List<List<String>> accountsMerge(List<List<String>> accounts) {
		Map<String, String> owner = new HashMap<>(); // name
		Map<String, String> parents = new HashMap<>(); // parent by email
		Map<String, TreeSet<String>> unions = new HashMap<>();

		// initiate
		for (List<String> account : accounts) {
			for (int i = 1; i < account.size(); i++) {
				parents.put(account.get(i), account.get(i));
				owner.put(account.get(i), account.get(0));
			}
		}

		// union -> 여기가 핵심이다!
		for (List<String> account : accounts) {
			String parent = find(account.get(1), parents);

			for (int i = 2; i < account.size(); i++) {
				parents.put(find(account.get(i), parents), parent);
			}
		}

		// merge
		for (List<String> account : accounts) {
			String p = find(account.get(1), parents);

			if (!unions.containsKey(p)) {
				unions.put(p, new TreeSet<>());
			}

			for (int i = 1; i < account.size(); i++) {
				unions.get(p).add(account.get(i));
			}
		}

		List<List<String>> res = new ArrayList<>();

		// for print
		for (String p : unions.keySet()) {
			LinkedList<String> emails = new LinkedList<>(unions.get(p));
			emails.addFirst(owner.get(p));
			res.add(emails);
		}

		return res;
	}

	private String find(String s, Map<String, String> parents) {
		if (parents.get(s) == s) {
			return s;
		}
		parents.put(s, find(parents.get(s), parents));

		return parents.get(s);
	}
}
