package Entities;

import java.util.ArrayList;
import java.util.List;

import Consumables.*;
import Exceptions.*;

public class Order {
    public enum OrderStages {
        NotGiving,
        AtWaiter,
        AtChef,
        OrderComplete
    }

    // Order Constraints
    /**
     * Siparişlerin maksimum yemek sayısı
     */
    public final int MAX_FOOD = 2;
    /**
     * Siparişlerin maksimum içecek sayısı
     */
    public final int MAX_DRINK = 1;

    /**
     * Sipariş sahibi
     */
    public Customer owner;

    /**
     * Siparişin aşaması
     */
    public OrderStages stage = OrderStages.NotGiving;

    public List<Food> foods = new ArrayList<>();
    public List<Drink> drinks = new ArrayList<>();

    public Order(List<Food> _foods, List<Drink> _drinks, Customer _owner) throws OutOfOrderLimitsException {
        if (_foods.size() > MAX_FOOD || _drinks.size() > MAX_DRINK) {
            throw new OutOfOrderLimitsException(
                    "Order exceeds the order limit [food:" + _foods.size() + " drink:" + _drinks.size() + "]");
        }
        foods = _foods;
        drinks = _drinks;
        owner = _owner;
    }

    /**
     * Siparişin aşamasını değiştirir.
     * @param _stage
     */
    public void SetStage(OrderStages _stage) {
        stage = _stage;
    }

    /**
     * Sipraişin tamamlanıp tamamlanmadığını döndürür.
     * @return
     */
    public boolean IsComplete() {
        return stage == OrderStages.OrderComplete;
    }

    /**
     * Siparişin hazırlanmakta olup olmadığını döndürür.
     * @return
     */
    public boolean IsPreparing() {
        return stage == OrderStages.AtChef || stage == OrderStages.AtWaiter;
    }

    /**
     * Toplam tüketme süresini hesaplar.
     * @return tüketme süresi
     */
    public float GetTotalConsumeTime() {
        float total = 0;
        for (Food food : foods) {
            total += food.consumeTime;
        }
        for (Drink drink : drinks) {
            total += drink.consumeTime;
        }
        return total;
    }

    /**
     * Siparişin hazırlanma süresini hesaplar.
     * @return hazırlanma süresi
     */
    public float GetTotalPrepareTime() {
        float total = 0;
        for (Food food : foods) {
            total += food.prepareTime;
        }
        for (Drink drink : drinks) {
            total += drink.prepareTime;
        }
        return total;
    }

    @Override
    public String toString() {
        return "Order [foods=" + foods + ", drinks=" + drinks + ", stage=" + stage + "]";
    }
}
