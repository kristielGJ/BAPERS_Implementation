package jobs.Interface_jobs;

public interface I_Card_payment {

    void makeCard_payment(int card_ID, int payment_ID, double payment_amount, String card_type, String expiry_date, String last_digits, int customer_ID);
}
