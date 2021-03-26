package model.discount;

import model.Model;

public abstract class Discount extends Model {
    private int account_no;

    public int getAccount_no() {
        return account_no;
    }

    protected void setAccount_no(int account_no) {
        this.account_no = account_no;
    }
}
