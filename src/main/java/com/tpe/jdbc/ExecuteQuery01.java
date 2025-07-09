package com.tpe.jdbc;

import java.sql.*;

public class ExecuteQuery01 {
    public static void main(String[] args) throws SQLException {
        //STEP 2: Veritabani adresi, login bilgileri ekleme
        Connection con = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/jdbc_nt",
                "techpront",
                "125322"
        );

        //STEP 3: Statement olusturma - Query execute etmek icin kullanilir
        Statement st = con.createStatement();
        System.out.println("Statement olusturuldu");

        //TASK 1: ID'leri 5-10 arasinda olan ulkelerin isimlerini listeleyin.
        System.out.println("------------ TASK 1 ------------");
        String sql1 = "SELECT id,name FROM countries WHERE id BETWEEN 5 AND 10";

        boolean query1 = st.execute(sql1); //true mu false mu?? CEVAP: true
        System.out.println(query1);
        ResultSet rs = st.executeQuery(sql1);
        while (rs.next()){
            System.out.print("Country name: " + rs.getString("name"));
            System.out.println("ID:" + rs.getInt("id"));
        }


        st.close();
        con.close();

    }
}
