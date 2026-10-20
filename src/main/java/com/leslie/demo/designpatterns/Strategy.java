package com.leslie.demo.designpatterns;

interface Algorithm {
    void execute();
}

class AlgorithmA implements Algorithm {
    public void execute() { /* 算法 A */ }
}

class AlgorithmB implements Algorithm {
    public void execute() { /* 算法 B */ }
}

class Context {
    private Algorithm algorithm;

    void setAlgorithm(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    void run() {
        algorithm.execute();
    }
}