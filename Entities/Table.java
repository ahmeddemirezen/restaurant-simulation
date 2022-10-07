package Entities;
public class Table {
    public enum TableStatus {
        Free,
        Occupied
    }

    /**
     * Masa durumu
     */
    private TableStatus status = TableStatus.Free;

    public int id;

    private static int tableCount;

    /**
     * Masada oturan müşteri
     */
    public Customer customer;

    public Table() {
        this.id = ++tableCount;
    }

    /**
     * Masaya oturmayı deneyen müşteriyi oturtur.
     * @param _customer
     */
    public void Sit(Customer _customer) {
        status = TableStatus.Occupied;
        this.customer = _customer;
    }

    /**
     * Müşteri masadan ayrılır.
     */
    public void Leave() {
        status = TableStatus.Free;
        this.customer = null;
    }

    /**
     * Masa boş mu?
     * @return boolean
     */
    public boolean IsEmpty() {
        return this.status == TableStatus.Free;
    }

    /**
     * Masa durumunu değiştirir.
     * @param _status
     */
    public void SetStatus(TableStatus _status) {
        this.status = _status;
    }

    /**
     * Masa durumunu döndürür.
     * @return
     */
    public TableStatus GetStatus() {
        return this.status;
    }

}
