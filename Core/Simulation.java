package Core;

import java.util.List;
import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import Consumables.*;
import Entities.*;

public class Simulation {
    private static Simulation instance = new Simulation();

    public static Simulation GetInstance() {
        return instance;
    }

    // TODO Move this into the customer class
    public static ProductList products = new ProductList();

    public static List<Table> tables = new ArrayList<>();
    public static List<Waiter> waiters = new ArrayList<>();
    public static List<Chef> chefs = new ArrayList<>();

    public static List<Customer> waitingCustomers = new ArrayList<>();

    // Queues
    public static Queue<Order> orderQueue = new LinkedList<>();

    public static Queue<Waiter> availableWaiter = new LinkedList<>();
    public static Queue<Waiter> busyWaiter = new LinkedList<>();

    public static Queue<Chef> availableChef = new LinkedList<>();

    // Constants

    /**
     * Masa sayısı
     */
    public static final int TABLE_COUNT = 10;
    /**
     * Garson sayısı
     */
    public static final int WAITER_COUNT = 6;
    /**
     * Şef sayısı
     */
    public static final int CHEF_COUNT = 2;
    /**
     * 1 saniye içerisinde müşterinin gelme olasılığı
     */
    public static final float PROB_CUSTOMER_ARRIVAL = 0.9f;

    /**
     * Statik product listesinden rastgele bir yiyecek ürünü döndürür.
     * @return Food
     */
    public static Food GetRandomFood() {
        Random rand = new Random();
        return products.foods.get(rand.nextInt(products.foods.size()));
    }

    /**
     * Statik product listesinden rastgele bir içecek ürünü döndürür.
     * @return
     */
    public static Drink GetRandomDrink() {
        Random rand = new Random();
        return products.drinks.get(rand.nextInt(products.drinks.size()));
    }

    /**
     * Boş bir masa bulur ve müşteriyi oturtur.
     * @param _customer Müşteri
     * @return başarı durumu
     */
    public static boolean TrySitTable(Customer _customer) {
        for (Table table : tables) {
            if (table.IsEmpty()) {
                table.Sit(_customer);
                return true;
            }
        }
        return false;
    }

    /**
     * Müşteri masadan ayrılır.
     * @param _customer Müşteri
     */
    public static void LeaveTable(Customer _customer) {
        for (Table table : tables) {
            if (table.customer == _customer) {
                table.Leave();
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < TABLE_COUNT; i++) {
            tables.add(new Table());
        }

        for (int i = 0; i < WAITER_COUNT; i++) {
            try {
                Waiter waiter = new Waiter();
                waiters.add(waiter);
                availableWaiter.add(waiter);
                waiter.start();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        for (int i = 0; i < CHEF_COUNT; i++) {
            Chef chef = new Chef();
            chefs.add(chef);
            availableChef.add(chef);
            chef.start();
        }

        while (true) {
            try {
                if (Math.random() < PROB_CUSTOMER_ARRIVAL) {
                    Customer customer = new Customer();
                    waitingCustomers.add(customer);
                    customer.start();
                }
                StringBuilder sb = new StringBuilder();
                sb.append("**********************\nWaiting customers: " + waitingCustomers.size());
                for (Table table : tables) {
                    sb.append("\nTable [" + table.id + "] " + table.GetStatus());
                }
                System.out.println(sb.toString());
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}