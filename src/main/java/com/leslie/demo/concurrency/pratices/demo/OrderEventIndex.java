package com.leslie.demo.concurrency.pratices.demo;


import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

public class OrderEventIndex {

    // 一条订单状态变更事件
    record OrderEvent(
            long sequence,
            String orderId,
            String status
    ) {}

    // 多个线程共享同一个事件索引
    private final ConcurrentSkipListMap<Long, OrderEvent> events =
            new ConcurrentSkipListMap<>();

    // 为并发产生的事件分配唯一序号
    private final AtomicLong sequenceGenerator = new AtomicLong();

    // 订单处理线程调用：发布一条事件
    public void publish(String orderId, String status) {

        long sequence = sequenceGenerator.incrementAndGet();

        OrderEvent event =
                new OrderEvent(sequence, orderId, status);

        events.put(sequence, event);

        System.out.println(
                Thread.currentThread().getName()
                        + " published: " + event
        );
    }

    // 查询最新一条事件
    public OrderEvent getLatestEvent() {
        Map.Entry<Long, OrderEvent> entry = events.lastEntry();

        return entry == null ? null : entry.getValue();
    }

    // 查询指定序号范围内的事件
    public List<OrderEvent> getEvents(long from, long to) {

        return new ArrayList<>(
                events.subMap(from, true, to, true).values()
        );
    }

    public static void main(String[] args)
            throws InterruptedException {

        OrderEventIndex index = new OrderEventIndex();

        ExecutorService pool = Executors.newFixedThreadPool(4);

        CountDownLatch done = new CountDownLatch(3);

        // 三个线程同时处理不同订单
        for (int i = 1; i <= 3; i++) {

            final int workerId = i;

            pool.submit(() -> {
                try {
                    for (int j = 1; j <= 5; j++) {

                        String orderId =
                                "ORDER-" + workerId + "-" + j;

                        index.publish(orderId, "PAID");

                        Thread.sleep(100);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    done.countDown();
                }
            });
        }

        // 第四个线程同时查询事件
        pool.submit(() -> {
            while (done.getCount() > 0) {

                OrderEvent latest = index.getLatestEvent();

                if (latest != null) {
                    System.out.println(
                            "Monitor sees latest: " + latest
                    );
                }

                try {
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });

        done.await();

        // 所有生产线程结束后，查询完整事件范围
        System.out.println("\nAll events:");

        index.getEvents(1, Long.MAX_VALUE)
                .forEach(System.out::println);

        pool.shutdown();
    }
}
