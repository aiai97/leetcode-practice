package com.leslie.demo.concurrency.pratices.demo;


import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.CountDownLatch;

public class TransactionQueueDemo {

    record Transaction(String id, double amount) {}

    // 多个接收线程和处理线程共享这个队列
    private final ConcurrentLinkedQueue<Transaction> queue =
            new ConcurrentLinkedQueue<>();

    // 接收交易
    public void submit(Transaction transaction) {
        queue.offer(transaction);

        System.out.println(
                Thread.currentThread().getName()
                        + " submitted " + transaction.id()
        );
    }

    // 处理交易
    public void process() {

        // poll()：取出并移除队头元素
        Transaction transaction = queue.poll();

        if (transaction == null) {
            return;
        }

        System.out.println(
                Thread.currentThread().getName()
                        + " processing " + transaction.id()
        );

        // 模拟交易处理
        System.out.println(
                "Processed amount: " + transaction.amount()
        );
    }

    public static void main(String[] args)
            throws InterruptedException {

        TransactionQueueDemo service =
                new TransactionQueueDemo();

        ExecutorService producers =
                Executors.newFixedThreadPool(2);

        ExecutorService consumers =
                Executors.newFixedThreadPool(2);

        CountDownLatch submitted = new CountDownLatch(2);

        // 两个生产者同时提交交易
        for (int i = 1; i <= 2; i++) {

            int producerId = i;

            producers.submit(() -> {
                try {
                    for (int j = 1; j <= 5; j++) {

                        service.submit(
                                new Transaction(
                                        "TX-" + producerId + "-" + j,
                                        100 * j
                                )
                        );
                    }
                } finally {
                    submitted.countDown();
                }
            });
        }

        // 等待所有交易提交完毕
        submitted.await();

        // 两个消费者并发处理队列中的交易
        CountDownLatch processed = new CountDownLatch(2);

        for (int i = 0; i < 2; i++) {
            consumers.submit(() -> {
                try {
                    while (true) {
                        Transaction tx = service.queue.poll();

                        if (tx == null) {
                            break;
                        }

                        System.out.println(
                                Thread.currentThread().getName()
                                        + " processing " + tx.id()
                        );
                    }
                } finally {
                    processed.countDown();
                }
            });
        }

        processed.await();

        producers.shutdown();
        consumers.shutdown();

        System.out.println("Remaining: " + service.queue.size());
    }
}
