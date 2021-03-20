package jobs;

import database.DB_Connection;
import jobs.Interface_jobs.I_Cash_payment;
import jobs.Interface_jobs.I_Payment;

public class Cash_payment extends Payment implements I_Cash_payment {

    private I_Payment payment;

    /**
     * @param conn
     */
    public Cash_payment(DB_Connection conn) {
        super(conn);
    }

    /**
     *
     * @param payment_ID
     * @param payment_amount
     * @param customer_ID
     */
    public void createCash_payment(int payment_ID, double payment_amount, int customer_ID){
        payment.createPayment(payment_ID, "Cash Payment", payment_amount, customer_ID);
    }


}
