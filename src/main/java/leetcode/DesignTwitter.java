package leetcode;

import java.util.*;


public class DesignTwitter {

    static class Tweet {
        int id;
        int counter;

        public Tweet(int id, int counter) {
            this.id = id;
            this.counter = counter;
        }
    }

    static class Twitter {
        int counter = 0;
        Map<Integer, List<Tweet>> tweetMap = new HashMap<>();
        Map<Integer, Set<Integer>> followerMap = new HashMap<>();

        /** Initialize your data structure here. */
        public Twitter() {

        }

        /** Compose a new tweet. */
        public void postTweet(int userId, int tweetId) {
            tweetMap.computeIfAbsent(userId, t -> new ArrayList<>()).add(new Tweet(tweetId, ++counter));
            follow(userId, userId);
        }

        /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
        public List<Integer> getNewsFeed(int userId) {
            PriorityQueue<Tweet> pq = new PriorityQueue<>((o1, o2) -> o2.counter - o1.counter);

            if (followerMap.containsKey(userId)) {
                for (int followerId : followerMap.get(userId)) {
                    List<Tweet> tweets = tweetMap.get(followerId);

                    if (tweets == null || tweets.isEmpty()) {
                        continue;
                    }

                    for (int i = tweets.size() - 1; i >= 0 && i >= tweets.size() - 11; i--) {
                        pq.add(tweets.get(i));
                    }
                }
            }

            List<Integer> res = new ArrayList<>();

            for (int i = 0; i < 10 && !pq.isEmpty(); i++) {
                res.add(pq.poll().id);
            }

            return res;
        }

        /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
        public void follow(int followerId, int followeeId) {
            followerMap.computeIfAbsent(followerId, t -> new HashSet<>()).add(followeeId);
        }

        /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
        public void unfollow(int followerId, int followeeId) {
            followerMap.computeIfAbsent(followerId, t -> new HashSet<>()).remove(followeeId);
        }
    }
}
