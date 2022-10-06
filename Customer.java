import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import Consumables.*;
import Exceptions.OutOfOrderLimitsException;

public class Customer extends Thread {
    public enum CustomerState {
        WAITING, ORDERING, EATING, LEAVING
    }

    public Order order;
    public int id;
    public CustomerState state = CustomerState.WAITING;

    private static int customerCount;

    // Customer Constants

    public static final float PROB_WAITING = 0.5f;

    public Customer() {
        this.id = ++customerCount;
        System.out.println(this);
        SetOrder();
    }

    public void SetOrder() {
        List<Food> foods = new ArrayList<>();
        List<Drink> drinks = new ArrayList<>();

        foods.add(Simulation.GetRandomFood());
        foods.add(Simulation.GetRandomFood());

        drinks.add(Simulation.GetRandomDrink());

        try {
            order = new Order(foods, drinks);
        } catch (OutOfOrderLimitsException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (state != CustomerState.LEAVING) {
                switch (state) {
                    case WAITING:
                        if (Simulation.TrySitTable(this)) {
                            state = CustomerState.ORDERING;
                            System.out.println(this);
                        } else {
                            if (Math.random() > PROB_WAITING) {
                                state = CustomerState.LEAVING;
                                Simulation.waitingCustomers.remove(this);
                                System.out.println(this);
                            }
                        }
                        break;
                    case ORDERING:
                        if (!order.IsComplete()) {
                            state = CustomerState.EATING;
                            System.out.println(this);
                        }
                        break;
                    case EATING:
                        TimeUnit.MILLISECONDS.sleep((long) order.GetTotalConsumeTime());
                        state = CustomerState.LEAVING;
                        System.out.println(this);
                        break;
                    case LEAVING:
                        Simulation.LeaveTable(this);
                        System.out.println(this);
                        break;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Customer " + this.id + " (" + this.state + ")";
    }
}
