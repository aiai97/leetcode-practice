package com.leslie.demo.design;

import java.util.List;


import java.util.*;

class Twitter {

    private static class Tweet {
        int userId;
        int tweetId;
        int time;
        int index;

        Tweet(int userId, int tweetId, int time, int index) {
            this.userId = userId;
            this.tweetId = tweetId;
            this.time = time;
            this.index = index;
        }
    }

    private Map<Integer, List<Tweet>> tweets = new HashMap<>();
    private Map<Integer, Set<Integer>> follows = new HashMap<>(); // followee -> followers, I used to think it is key-a single value, but maybe when writing it, I will adjsut
    private int time = 0;

    public Twitter() {
    }

    public void postTweet(int userId, int tweetId) {
        tweets.putIfAbsent(userId, new ArrayList<>());

        List<Tweet> list = tweets.get(userId);
        list.add(new Tweet(userId, tweetId, time++, list.size())); //failed,  always append new tweet to the end, I used to think I should order the list
    }

    public List<Integer> getNewsFeed(int userId) {
        List<Integer> feed = new ArrayList<>();

        PriorityQueue<Tweet> pq = new PriorityQueue<>(
                (a, b) -> Integer.compare(b.time, a.time)
        );

        Set<Integer> users = new HashSet<>();
        users.add(userId);
        users.addAll(follows.getOrDefault(userId, Collections.emptySet()));

        for (int id : users) {
            List<Tweet> list = tweets.get(id);

            if (list != null && !list.isEmpty()) {
                pq.offer(list.get(list.size() - 1));
            }
        }

        while (!pq.isEmpty() && feed.size() < 10) {
            Tweet tweet = pq.poll();
            feed.add(tweet.tweetId);

            //failed, get the tweet list of the user
            List<Tweet> list = tweets.get(tweet.userId);
            // failed,if there are more tweets from the user
            if (tweet.index > 0) {
                pq.offer(list.get(tweet.index - 1));
            }
        }

        return feed;
    }

    public void follow(int followerId, int followeeId) {
        follows.putIfAbsent(followerId, new HashSet<>());
        follows.get(followerId).add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        Set<Integer> set = follows.get(followerId);

        if (set != null) {
            set.remove(followeeId);
        }
    }
}
