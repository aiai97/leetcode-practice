package com.leslie.demo.lld;

//  passed
public class Car {
    private String brand;
    private String model;
    private int speed;
    public Car(String brand, String model) {
        this.brand = brand;
        this.model = model;
        this.speed = 0;
    }

    public int accelerate(int amount) {
        this.speed += amount;
        return speed;
    }

    public int brake(int amount) {
        this.speed -= amount;
        if(speed < 0) speed = 0;
        return speed;
    }

    public int getSpeed() {
        return speed;
    }

    public String describe() {
        return this.brand + " " + this.model + " at " + this.speed + " km/h";
    }

}

/**
 * Your Car object will be instantiated and called as such:
 * Car obj = new Car(brand, model);
 * int param_1 = obj.accelerate(amount);
 * int param_2 = obj.brake(amount);
 * int param_3 = obj.getSpeed();
 * String param_4 = obj.describe();
 */