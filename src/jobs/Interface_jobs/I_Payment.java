package jobs.Interface_jobs;

public interface I_Payment {

    void createPayment(int payment_ID, String payment_type, Double payment_amount, int customer_ID);

    String[] generateInvoice(int payment_ID);


}
