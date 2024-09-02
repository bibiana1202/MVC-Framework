package org.example;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    // 커넥션 관련된부분은 ConnectionManager 가 하도록

    // C+A+c : 상수 만들기
    public static final String DB_DRIVER = "org.h2.Driver";
    public static final String DB_URL = "jdbc:h2:mem://localhost/~/jdbc-practice;MODE=MySQL;DB_CLOSE_DELAY=-1";
    public static final int MAX_POOL_SIZE = 40;
    // 데이터 소스
    private static final DataSource ds;
    // 만든 Hikari 데이터 소스로 초기화 해준다.
    // 커넥션풀 하나만 가지도록 설정
    // 커넥션의 사용 주체는 WAS(Web Application Server) 스레드 이므로 커넥션 개수는 WAS 스레드 수와 함께 고려해야함
    static{
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setDriverClassName(DB_DRIVER);
        hikariDataSource.setJdbcUrl(DB_URL);
        hikariDataSource.setUsername("sa");
        hikariDataSource.setPassword("");
        hikariDataSource.setMaximumPoolSize(MAX_POOL_SIZE); // 커넥션을 멕시멈 몇까지 설정할것인가?
        hikariDataSource.setMinimumIdle(MAX_POOL_SIZE); // 커넥션 풀 적용시 어떤식으로 동작하는지 학습할것

        ds = hikariDataSource;
    }
    // 커넥션을 받아오는 메소드
    // 커넥션 풀 적용 = 미리 일정량의 데이터 베이스 커넥션을 생성해두고 , 필요할때 가져가 쓰는 기법
    // Hikari CP 사용 하여 커넥션풀 적용
    public static Connection getConnection()  //throws SQLException
    {
//        String url = "jdbc:h2:mem://localhost/~/jdbc-practice;MODE=MySQL;DB_CLOSE_DELAY=-1";
//        String id = "sa";
//        String pw = "" ;
//        try{
//            Class.forName("org.h2.Driver");
//            // DriverManager 의 getConnection을 받아올때 url,id,password를 전달해서 받아온다.
//            return DriverManager.getConnection(url,id,pw);
//        }catch(Exception e){
//            return null;
//        }

//        return getDataSource().getConnection();


        try{
            //hikari 데이터 소스에서 커넥션을 하나 받아온다.
            return ds.getConnection();
        }catch(SQLException e) {
            throw new IllegalStateException(e);

        }



    }

//    public static DataSource getDataSource() {
//        // Hikari CP 는 데이터 소스를 가져올때 Hikari 데이터 소스를 사용하기 위해서...
//        HikariDataSource hikariDataSource = new HikariDataSource();
//        hikariDataSource.setDriverClassName(DB_DRIVER);
//        hikariDataSource.setJdbcUrl(DB_URL);
//        hikariDataSource.setUsername("sa");
//        hikariDataSource.setPassword("");
//        hikariDataSource.setMaximumPoolSize(MAX_POOL_SIZE); // 커넥션을 멕시멈 몇까지 설정할것인가?
//        hikariDataSource.setMinimumIdle(MAX_POOL_SIZE); // 커넥션 풀 적용시 어떤식으로 동작하는지 학습할것
//        return hikariDataSource;
//    }


    // 데이터 소스를 받아오는
    public static DataSource getDataSource()
    {
        return ds;
    }
}
