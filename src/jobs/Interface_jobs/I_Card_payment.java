package jobs.Interface_jobs;

public interface I_Card_payment {

    void createCard_payment(int card_ID, int payment_ID, String card_type, String expiry_date, String last_digits, double payment_amount, int customer_ID);

}
