package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Utils {
    public static int getGeneratedKey(Statement st) throws SQLException {

        try (ResultSet rs = st.getGeneratedKeys()) {
            if (rs.next()) {
                int key = rs.getInt(1);
                rs.close();
                return key;
            }else{
                throw new SQLException("Error getting generated key.. no key was found");
            }
        }
    }
}
