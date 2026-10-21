package com.leslie.demo.concurrency;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;


interface HtmlParser {
    public List<String> getUrls(String url);
}

/**
 * // This is the HtmlParser's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface HtmlParser {
 *     public List<String> getUrls(String url) {}
 * }
 */

// All I can say is that I can do it without concurrency

class Solution {
    public List<String> crawl(String startUrl, HtmlParser htmlParser) {
        Set<String> visited = ConcurrentHashMap.newKeySet();
        Queue<String> queue = new ConcurrentLinkedQueue<>();
        List<String> res = Collections.synchronizedList(new ArrayList<>());

        queue.offer(startUrl);
        visited.add(startUrl);

        ExecutorService pool = Executors.newFixedThreadPool(4);
        AtomicInteger pending = new AtomicInteger(1); // failed, need to track how many urls are pending for processing

        Runnable worker = () -> {
            while (true) { // failed, need to process a lot of urls
                // process todo tasks ->  yield the cpu for the running tasks
                String url = queue.poll();

                if (url == null) {
                    // failed, cannot think of the fact the processing jobs is important!! instead of the jobs in the queue
                    if (pending.get() == 0) {
                        return;
                    }
                    // failed, wait for other running threads
                    Thread.yield();
                    continue;
                }

                res.add(url);
                List<String> urls = htmlParser.getUrls(url);

                for (String u : urls) {
                    if (isSameHost(startUrl, u) && visited.add(u)) {
                        pending.incrementAndGet();
                        queue.offer(u);
                    }
                }
                pending.decrementAndGet();
            }
        };

        for (int i = 0; i < 4; i++) {
            pool.submit(worker);
        }

        pool.shutdown(); // cannot exit directly
        try {
            if (!pool.awaitTermination(1, TimeUnit.HOURS)) {
                pool.shutdownNow();
            }
        } catch (InterruptedException e) {
            pool.shutdownNow();
            Thread.currentThread().interrupt();
        }

        return res;
    }
    private boolean isSameHost(String url1, String url2){
        String host1 = url1.split("/")[2];
        String host2 = url2.split("/")[2];
        return host1.equals(host2);
    }
}



class Solution1 {
    Queue<String> queue = new LinkedList<>();
    HashSet<String> visited = new HashSet<>();
    List<String> res = new ArrayList<>();
    public List<String> crawl(String startUrl, HtmlParser htmlParser) {
        queue.add(startUrl);
        visited.add(startUrl);
        while(!queue.isEmpty()){
            String url = queue.poll();
            res.add(url);
            List<String> urls = htmlParser.getUrls(url);
            for(String u:urls){
                if(!visited.contains(u) && isSameHost(startUrl, u)){
                    visited.add(u);
                    queue.add(u);
                }
            }
        }
        return res;
    }

    private boolean isSameHost(String url1, String url2){
        String host1 = url1.split("/")[2];
        String host2 = url2.split("/")[2];
        return host1.equals(host2);
    }

    public static void main(String[] args) {
//        System.out.println(isSameHost("https://leetcode.com/problems/", "https://leetcode.com/contest/"));
    }
}