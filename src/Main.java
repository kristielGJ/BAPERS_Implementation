import database.DB_Connection;

import java.sql.ResultSet;

public class Main {

    public static void main(String[] args) {
        DB_Connection conn = new DB_Connection();
        ResultSet rs = conn.query("select * from User_account");
        DB_Connection.printQuery(rs);
    }

}
