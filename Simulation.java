import java.util.List;
import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import Consumables.*;

public class Simulation {
    private static Simulation instance = new Simulation();

    public static Simulation GetInstance() {
        return instance;
    }

    public static ProductList products = new ProductList();
    public static List<Table> tables = new ArrayList<>();

    public static Queue<Customer> waitingCustomers = new LinkedList<>();

    // Constants

    public static final int MAX_TABLES = 10;
    public static final float PROB_CUSTOMER_ARRIVAL = 0.8f;

    public static Food GetRandomFood() {
        Random rand = new Random();
        return products.foods.get(rand.nextInt(products.foods.size()));
    }

    public static Drink GetRandomDrink() {
        Random rand = new Random();
        return products.drinks.get(rand.nextInt(products.drinks.size()));
    }

    public static boolean TrySitTable(Customer _customer) {
        for (Table table : tables) {
            if (table.IsEmpty()) {
                table.Sit(_customer);
                return true;
            }
        }
        return false;
    }

    public static void LeaveTable(Customer _customer) {
        for (Table table : tables) {
            if (table.customer == _customer) {
                table.Leave();
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < MAX_TABLES; i++) {
            tables.add(new Table());
        }

        while (true) {
            try {
                if (Math.random() < PROB_CUSTOMER_ARRIVAL) {
                    Customer customer = new Customer();
                    waitingCustomers.offer(customer);
                    customer.start();
                }
                System.out.println("Waiting customers: " + waitingCustomers.size());
                // for (Table table : tables) {
                //     System.out.println("[" + table.id + "] " + table.GetStatus());
                // }
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}