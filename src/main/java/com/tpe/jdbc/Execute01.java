package com.tpe.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Execute01 {                    //throws clause
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        //STEP 1: Driver ekleme - JDK7'den sonra redundant
        Class.forName("org.postgresql.Driver");

        //STEP 2: Veritabani adresi, login bilgileri ekleme
        Connection con = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/jdbc_nt",
                "techpront",
                "125322"
        );

        //STEP 3: Statement olusturma - Query execute etmek icin kullanilir
        Statement st = con.createStatement();
        System.out.println("Statement olusturuldu");

        //STEP 4: Query olusturma ve execute etme
        //TASK 1: Tablo olustur: workers
        //  -worker_id
        //  -worker_name
        //  -worker_salary

        boolean bool1 = st.execute("CREATE TABLE IF NOT EXISTS " +
                "workers(worker_id INT, worker_name VARCHAR(48), worker_salary REAL)");

        System.out.println("Table olusturuldu: " + bool1);

        //TASK 2: workers tablosuna yeni bir sutun ekle.
        //  -city VARCHAR(32)
        st.execute("ALTER TABLE workers ADD COLUMN city VARCHAR(32)");
//        DO $$
//        BEGIN
//        IF NOT EXISTS (
//                SELECT 1
//                FROM information_schema.columns
//                WHERE table_name = 'workers' AND column_name = 'city'
//        ) THEN
//        ALTER TABLE workers ADD COLUMN city VARCHAR(32);
//        END IF;
//        END$$;

        //STEP 5: Kaynaklari salmak
        st.close();
        con.close();
    }
}