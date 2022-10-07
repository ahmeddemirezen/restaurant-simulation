package Entities;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import Core.*;

public class Chef extends Thread {
    public enum ChefState {
        IDLE, COOKING
    }

    public ChefState state = ChefState.IDLE;
    public int id;

    /**
     * Şefin hazırlamakta olduğu siparişlerin tutulduğu kuyruk
     */
    public Queue<Order> orders = new LinkedList<>();

    /**
     * Şefin hazırlamakta olduğu sipariş
     */
    public Order order;

    private static int chefCount;

    public Chef() {
        this.id = ++chefCount;
        System.out.println(this);
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            if (order == null) {
                order = orders.poll();
                if (order != null) {
                    try {
                        SetState(ChefState.COOKING);
                        System.out.println(this);
                        order.SetStage(Order.OrderStages.AtChef);
                        TimeUnit.MILLISECONDS.sleep((long) order.GetTotalPrepareTime());
                        order.SetStage(Order.OrderStages.OrderComplete);
                        SetState(ChefState.IDLE);
                        System.out.println("Chef [id=" + id + ", state= PREPARED ORDER " + order + "]");
                        order = null;
                        Simulation.availableChef.add(this);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * Şefin sipariş kuyruğuna yeni bir sipariş eklemesini sağlar
     * @param _order Sipariş
     */
    public void AddOrder(Order _order) {
        orders.add(_order);
    }

    /**
     * Şefin durumunu değiştirir
     * @param _state Yeni durum
     */
    public void SetState(ChefState _state) {
        state = _state;
    }

    /**
     * Şefin hazırda beklemekte olduğunu kontrol eder
     * @return Şefin hazırda beklemekte olduğu durumu
     */
    public boolean IsIdle() {
        return state == ChefState.IDLE;
    }

    @Override
    public String toString() {
        return "Chef [id=" + id + ", state=" + state + "]";
    }
}
