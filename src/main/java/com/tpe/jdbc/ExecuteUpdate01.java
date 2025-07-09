package com.tpe.jdbc;

import java.sql.*;

public class ExecuteUpdate01 {
    public static void main(String[] args) throws SQLException {
        Connection con = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/jdbc_nt",
                "techpront",
                "125322"
        );

        Statement st = con.createStatement();

        //TASK 1: Developerlarin isimlerin ve maaslarini konsolda gosterin.
        System.out.println("---------------- UPDATE ONCESI ----------------");
        ResultSet rs = st.executeQuery("SELECT * FROM developers");

        while(rs.next()){
            System.out.println(rs.getString("name") + " " + rs.getDouble("salary"));
        }
        //TASK 2: Ortalama maasi hesaplayip, daha dusuk maas alan developerlarin maasini ortalamaya esitleyin.
        String sqlUpdate = "UPDATE developers SET salary = (SELECT AVG(salary) FROM developers) " +
                "WHERE salary < (SELECT AVG(salary) FROM developers)";

        st.executeUpdate(sqlUpdate); //return: affected row count (etkilenen satir sayisi)

        //TASK 3: Update sonrasinda, tekrar konsola bilgileri yaz.
        System.out.println("---------------- UPDATE SONRASI ----------------");
        ResultSet rs2 = st.executeQuery("SELECT * FROM developers");

        while(rs2.next()){
            System.out.println(rs2.getString("name") + " " + rs2.getDouble("salary"));
        }



        st.close();
        con.close();
    }
}







