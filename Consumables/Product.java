package Consumables;

public class Product {
    public String name = "";
    public float consumeTime = 150f;
    public float prepareTime = 200f;
    public boolean isConsumed = false;

    public Product(String _name, float _consumeTime, float _prepareTime) {
        this.name = _name;
        this.consumeTime = _consumeTime;
        this.prepareTime = _prepareTime;
    }
}
