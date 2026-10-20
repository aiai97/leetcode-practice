package com.leslie.demo.designpatterns;

public class DecoratorDemo {
    public static void main(String[] args) {
        LoggingDecorator service = new LoggingDecorator(new RealService());
        service.execute();
    }
}

class RealService  {
    public void execute() {
        System.out.println("执行原功能");
    }
}

class LoggingDecorator{
    private final RealService target;

    LoggingDecorator(RealService target) {
        this.target = target;
    }

    public void execute() {
        System.out.println("记录日志");
        target.execute();
    }
}

//interface Service {
//    void execute();
//}
//
//class RealService implements Service {
//    public void execute() {
//        System.out.println("执行原功能");
//    }
//}
//
//class LoggingDecorator implements Service {
//    private final Service target;
//
//    LoggingDecorator(Service target) {
//        this.target = target;
//    }
//
//    public void execute() {
//        System.out.println("记录日志");
//        target.execute();
//    }
//}