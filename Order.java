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
    public final int MAX_FOOD = 2;
    public final int MAX_DRINK = 1;

    public OrderStages stage = OrderStages.NotGiving;

    public List<Food> foods = new ArrayList<>();
    public List<Drink> drinks = new ArrayList<>();

    public Order(List<Food> _foods, List<Drink> _drinks) throws OutOfOrderLimitsException {
        if (_foods.size() > MAX_FOOD || _drinks.size() > MAX_DRINK) {
            throw new OutOfOrderLimitsException(
                    "Order exceeds the order limit [food:" + _foods.size() + " drink:" + _drinks.size() + "]");
        }
        foods = _foods;
        drinks = _drinks;
    }

    public void GiveOrder() {

    }

    public void PrepareOrder() {

    }

    public void CompleteOrder() {

    }

    public void SetStage(OrderStages _stage) {
        stage = _stage;
    }

    public boolean IsComplete() {
        return stage == OrderStages.OrderComplete;
    }

    public boolean IsPreparing() {
        return stage == OrderStages.AtChef || stage == OrderStages.AtWaiter;
    }

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
}
