package com.leslie.demo.designpatterns;


import java.util.Map;
//
//enum OrderState {
//    PENDING_PAYMENT,
//    PENDING_SHIPMENT,
//    IN_TRANSIT,
//    COMPLETED,
//    CANCELLED
//}
//
//enum OrderEvent {
//    PAY,
//    CANCEL,
//    SHIP,
//    CONFIRM_RECEIPT
//}
//
//class Order {
//    private OrderState state = OrderState.PENDING_PAYMENT;
//
//    // Current state + event -> next state
//    private static final Map<OrderState, Map<OrderEvent, OrderState>>
//            TRANSITIONS = Map.of(
//            OrderState.PENDING_PAYMENT, Map.of(
//                    OrderEvent.PAY, OrderState.PENDING_SHIPMENT,
//                    OrderEvent.CANCEL, OrderState.CANCELLED
//            ),
//            OrderState.PENDING_SHIPMENT, Map.of(
//                    OrderEvent.SHIP, OrderState.IN_TRANSIT
//            ),
//            OrderState.IN_TRANSIT, Map.of(
//                    OrderEvent.CONFIRM_RECEIPT, OrderState.COMPLETED
//            )
//    );
//
//    public synchronized boolean handle(OrderEvent event) {
//        OrderState next = TRANSITIONS
//                .getOrDefault(state, Map.of())
//                .get(event);
//
//        if (next == null) {
//            return false; // Illegal transition
//        }
//
//        state = next;
//        return true;
//    }
//
//    public synchronized OrderState getState() {
//        return state;
//    }
//
//    public static void main(String[] args) {
//        Order order = new Order();
//
//        System.out.println(order.handle(OrderEvent.SHIP));
//
//        System.out.println(order.handle(OrderEvent.PAY));
//
//        System.out.println(order.handle(OrderEvent.SHIP));
//
//        System.out.println(order.handle(OrderEvent.CONFIRM_RECEIPT));
//
//        System.out.println(order.handle(OrderEvent.CANCEL));
//    }
//}
//
//
