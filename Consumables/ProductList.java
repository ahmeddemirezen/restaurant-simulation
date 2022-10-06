package Consumables;

import java.util.*;

public class ProductList {
    public List<Drink> drinks = new ArrayList<>();
    public List<Food> foods = new ArrayList<>();

    public ProductList() {
        drinks.add(new Drink("Kola", 150f, 200f));
        drinks.add(new Drink("Ayran", 140f, 200f));
        drinks.add(new Drink("Şalgam", 180f, 200f));
        drinks.add(new Drink("Soğuk Çay", 90f, 200f));
        drinks.add(new Drink("Çay", 120f, 200f));
        foods.add(new Food("Kebap", 1400f, 200f));
        foods.add(new Food("Salata", 1000f, 200f));
    }
}
