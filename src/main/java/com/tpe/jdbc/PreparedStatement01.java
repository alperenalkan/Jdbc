package com.tpe.jdbc;

import java.sql.*;

public class PreparedStatement01 {
    public static void main(String[] args) throws SQLException {
        Connection con = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/jdbc_nt",
                "techpront",
                "125322"
        );

        Statement st = con.createStatement();

        //TASK 1: 'Mathematics' dersinin minimum puanini 80 olarak degistirin.
        //ILIKE: Case insensitive: Mathematics = mathematics = MATHEMATICS

        String sql1 = "UPDATE university_programs SET min_score = 80 WHERE name ILIKE 'Mathematics'";

        int updateCount = st.executeUpdate(sql1);

        System.out.println(updateCount);

        //===============================================================================================

        //Prepared Statement yontemi
        //TASK 2: PreparedStatement kullanarak 'Physics' ismindeki dersin puanini 60 olarak guncelleyin.
        String sql2 = "UPDATE university_programs SET min_score = ? WHERE name ILIKE ?";

        PreparedStatement prst = con.prepareStatement(sql2);

        prst.setDouble(1, 60.0);
        prst.setString(2, "Physics");
        //artik query hazir

        int updateCount2 = prst.executeUpdate();
        System.out.println(updateCount2);

        //TASK 3: PreparedStatement kullanarak 'Economics' ismindeki dersin puanini 70 olarak guncelleyin
        //IKINCI BIR PREPARED STATEMENT OLUSTURMAYIN.
        prst.setDouble(1, 70.0);
        prst.setString(2, "Economics");

        int updateCount3 = prst.executeUpdate();

        //TASK 4: PreparedStatement kullanarak, programlar tablosuna yeni bir ders ekleyin.
        //Ders ismi 'Software Engineering' ve minimum score'u 83.5

        String sql3 = "INSERT INTO university_programs (name, min_score) VALUES (?,?)";
        PreparedStatement prst2 = con.prepareStatement(sql3);

        prst2.setString(1, "Software Engineering");
        prst2.setDouble(2, 83.5);

        int updateCount4 = prst2.executeUpdate();
        System.out.println(updateCount4);




        st.close();
        prst.close();
        con.close();

    }
}
