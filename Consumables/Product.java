package Consumables;

public class Product {
    public String name = "";
    /**
     * Tüketme süresi
     */
    public float consumeTime = 150f;
    /**
     * Hazırlanma süresi
     */
    public float prepareTime = 200f;
    /**
     * Tüketildi mi?
     */
    public boolean isConsumed = false;

    /**
     * Ürünü oluşturur.
     * @param _name
     * @param _consumeTime
     * @param _prepareTime
     */
    public Product(String _name, float _consumeTime, float _prepareTime) {
        this.name = _name;
        this.consumeTime = _consumeTime;
        this.prepareTime = _prepareTime;
    }
}
