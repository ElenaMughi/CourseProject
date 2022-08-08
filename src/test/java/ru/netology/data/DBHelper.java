package ru.netology.data;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DBHelper {

    private static Connection conn;

    static Connection connects() {
        try (FileInputStream baseConnectionFile = new FileInputStream("./application.properties")) {
            Properties appConnectProp = new Properties();
            appConnectProp.load(baseConnectionFile);

            conn = DriverManager.getConnection(appConnectProp.getProperty("spring.datasource.url"),
                    appConnectProp.getProperty("spring.datasource.username"),
                    appConnectProp.getProperty("spring.datasource.password")
            );
        } catch (SQLException | IOException error) {
            error.printStackTrace();
        }
        return conn;
    }

    public static int[] countLastTransaction() {
        int count[] = {0, 0};
        String dataSQL = "select count(id) from order_entity;";
        conn = connects();
        try (Statement usersStmt = conn.createStatement();
             ResultSet rs = usersStmt.executeQuery(dataSQL)
        ) {
            rs.next();
            count[0] = rs.getInt("count");
        } catch (SQLException err) {
            err.printStackTrace();
        }
        dataSQL = "select count(id) from credit_request_entity;";
        try (Statement usersStmt1 = conn.createStatement();
             ResultSet rs = usersStmt1.executeQuery(dataSQL)
        ) {
            rs.next();
            count[1] = rs.getInt("count");
        } catch (SQLException err) {
            err.printStackTrace();
        }
        return count;
    }

    public static boolean isPaymentSave(int count[], String cardSuccess) {
        boolean paymentSave = false;
        String status = "";
        int[] result = countLastTransaction();
        if ((result[0] == count[0] + 1) & (result[1] == count[1] + 1)) {
            String dataSQL = "select * from order_entity oe, credit_request_entity cre where oe.payment_id = cre.bank_id ORDER by oe.created desc;";
            conn = connects();
            try (Statement usersStmt = conn.createStatement();
                 ResultSet rs = usersStmt.executeQuery(dataSQL)) {
                rs.next();
                status = rs.getString("status");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (cardSuccess.equals(status)) {
            paymentSave = true;
        }
        return paymentSave;
    }
}
