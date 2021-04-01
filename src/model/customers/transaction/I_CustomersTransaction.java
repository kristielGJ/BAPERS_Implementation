package model.customers.transaction;

import model.Transaction;
import model.customers.Customer;
import java.util.ArrayList;

/**
 *interface for CustomersTransaction class that interacts with customer table in database
 */
public interface I_CustomersTransaction extends Transaction {

    Customer addCustomer(String name, String Company, String Address, String Phone);

    Customer updateCustomer(String name, String Company, int Acc_no, String Address, String Phone, String valued);

    ArrayList<Customer> getAll();

    ArrayList<String[]> getAllCust();

    String[][] getData(ArrayList<String[]> customers);

    int getLastAccNo();

    String getDiscountType(int accNo);
}
