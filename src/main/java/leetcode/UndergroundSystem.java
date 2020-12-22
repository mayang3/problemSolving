package leetcode;

import java.util.HashMap;
import java.util.Map;

public class UndergroundSystem {

	Map<Integer, Pair> checkInMap;
	Map<String, Counter> averageTimeMap;

	public UndergroundSystem() {
		this.checkInMap = new HashMap<>();
		this.averageTimeMap = new HashMap<>();
	}

	public void checkIn(int id, String stationName, int t) {
		checkInMap.put(id, new Pair(stationName, t));
	}

	public void checkOut(int id, String stationName, int t) {
		Pair pair = checkInMap.get(id);
		String fromTo = pair.stationName + "_" + stationName;

		Counter counter = averageTimeMap.getOrDefault(fromTo, new Counter());

		counter.sum += t - pair.t;
		counter.traveler++;

		averageTimeMap.put(fromTo,  counter);
	}

	public double getAverageTime(String startStation, String endStation) {
		Counter counter = averageTimeMap.get(startStation + "_" + endStation);

		return (double)counter.sum / (double)counter.traveler;
	}

	static class Counter {
		int sum;
		int traveler;
	}

	static class Pair {
		String stationName;
		int t;

		public Pair(String stationName, int t) {
			this.stationName = stationName;
			this.t = t;
		}
	}
}
