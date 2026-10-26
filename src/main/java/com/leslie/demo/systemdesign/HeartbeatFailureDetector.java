package com.leslie.demo.systemdesign;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// failed, not understanding the question in the beginning
class HeartbeatFailureDetector {
    public HeartbeatFailureDetector() {}

    public int[] failedNodes(int[] lastSeen, int now, int timeout) {
        List<Integer> failed = new ArrayList<>(); // I used to use array,but the size is wrong
        for (int i = 0; i < lastSeen.length; i++) {
            if (now - lastSeen[i] > timeout)
                failed.add(i);
        }
        int[] result = new int[failed.size()];
        for (int i = 0; i < failed.size(); i++)
            result[i] = failed.get(i);
        return result;
    }
}

/**
 * Your HeartbeatFailureDetector object will be instantiated and called as such:
 * HeartbeatFailureDetector obj = new HeartbeatFailureDetector();
 * int[] param_1 = obj.failedNodes(lastSeen, now, timeout);
 */