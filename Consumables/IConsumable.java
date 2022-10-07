package Consumables;
public interface IConsumable {
    /**
     * Tüketilme logunu yazdırır.
     */
    public void Consume();
    /**
     * Tüketildi mi?
     * @return
     */
    public boolean IsConsumed();
}
