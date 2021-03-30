package model.customers.transaction;

import model.Transaction;
import model.customers.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface I_CustomersTransaction extends Transaction {

    Customer addCustomer(String name, String Address, String Phone);

    Customer updateCustomer(String name, int Acc_no, String Address, String Phone, String valued);
    public ArrayList<Customer> getAll();

    ArrayList<String[]> getAllCust();

    String[][] getData(ArrayList<String[]> customers);

    int getLastAccNo();
}
