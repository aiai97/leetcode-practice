package com.leslie.demo.design;

// easy
class PhoneDirectory {
    private boolean[] used; // what phone nunmber, which status it is in

    public PhoneDirectory(int maxNumbers) {
        used = new boolean[maxNumbers];
    }

    public int get() {
        for (int i = 0; i < used.length; i++) {
            if (!used[i]) {
                used[i] = true;
                return i;
            }
        }
        return -1;
    }

    public boolean check(int number) {
        return number >= 0
                && number < used.length
                && !used[number];
    }

    public void release(int number) {
        if (number >= 0 && number < used.length) {
            used[number] = false;
        }
    }
}