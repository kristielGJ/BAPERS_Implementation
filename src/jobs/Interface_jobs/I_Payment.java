package jobs.Interface_jobs;

public interface I_Payment {

    void makePayment(int payment_ID, double payment_amount, String payment_type, int customer_ID);

    String[] generateInvoice(int payment_ID);


}
