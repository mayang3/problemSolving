package leetcode.contest.weekly_283;

import java.util.ArrayList;
import java.util.List;

public class CellsInARangeOnAnExcelSheet {

	public List<String> cellsInRange(String s) {
		String [] splits = s.split(":");

		String start = splits[0];
		String end = splits[1];

		List<String> res = new ArrayList<>();

		for (int i = start.charAt(0); i <= end.charAt(0); i++) {
			for (int j = Character.getNumericValue(start.charAt(1)); j <= Character.getNumericValue(end.charAt(1)) ; j++) {
				res.add("" + ((char)i) + j);
			}
		}

		return res;
	}

	public static void main(String[] args) {
		CellsInARangeOnAnExcelSheet cellsInARangeOnAnExcelSheet = new CellsInARangeOnAnExcelSheet();

	}
}
