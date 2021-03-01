package leetcode;

import java.util.*;

public class TweetCountsPerFrequency {
	

	public static void main(String[] args) {

		TweetCounts tweetCounts = new TweetCounts();
		tweetCounts.recordTweet("tweet3", 0);
		tweetCounts.recordTweet("tweet3", 60);
		tweetCounts.recordTweet("tweet3", 10);                             // All tweets correspond to "tweet3" with recorded times at 0, 10 and 60.
		tweetCounts.getTweetCountsPerFrequency("minute", "tweet3", 0, 59); // return [2]. The frequency is per minute (60 seconds), so there is one interval of time: 1) [0, 60> - > 2 tweets.
		tweetCounts.getTweetCountsPerFrequency("minute", "tweet3", 0, 60); // return [2, 1]. The frequency is per minute (60 seconds), so there are two intervals of time: 1) [0, 60> - > 2 tweets, and 2) [60,61> - > 1 tweet.
		tweetCounts.recordTweet("tweet3", 120);                            // All tweets correspond to "tweet3" with recorded times at 0, 10, 60 and 120.
		tweetCounts.getTweetCountsPerFrequency("hour", "tweet3", 0, 210);  // return [4]. The frequency is per hour (3600 seconds), so there is one interval of time: 1) [0, 211> - > 4 tweets.urn [2]. The frequency is per minute (60 seconds), so there is one interval of time: 1) [0, 60> - > 2 tweets.
	}

	static class TweetCounts {

		enum Freq {
			MINUTES("minute", 60),
			HOUR("hour", 3600),
			DAY("day", 86400);

			String freq;
			int delta;

			Freq(String freq, int delta) {
				this.freq = freq;
				this.delta = delta;
			}

			static int getTime(String freq) {
				for (Freq f : values()) {
					if (f.freq.equals(freq)) {
						return f.delta;
					}
				}
				return -1;
			}
		}

		Map<String, TreeSet<Integer>> tweetMap;

		public TweetCounts() {
			this.tweetMap = new HashMap<>();
		}

		public void recordTweet(String tweetName, int time) {
			tweetMap.computeIfAbsent(tweetName, t -> new TreeSet<>()).add(time);
		}

		public List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) {
			List<Integer> resList = new ArrayList<>();

			int delta = Freq.getTime(freq);

			for (int start = startTime, j = 1; start <= endTime ; start = (startTime + delta * j), j++) {
				int end = Math.min(start + delta, endTime+1);

				int count = tweetMap.get(tweetName).subSet(start, end).size();

				resList.add(count);
			}

			return resList;
		}
	}
}
