package org.example;

import java.sql.*;

public class UserDao {

//    // 커넥션을 받아오는 메소드
//    private Connection getConnection() {
//        String url = "jdbc:h2:mem://localhost/~/jdbc-practice;MODE=MySQL;DB_CLOSE_DELAY=-1";
//        String id = "sa";
//        String pw = "" ;
//        try{
//
//            Class.forName("org.h2.Driver");
//            // DriverManager 의 getConnection을 받아올때 url,id,password를 전달해서 받아온다.
//            return DriverManager.getConnection(url,id,pw);
//        }catch(Exception e){
//            return null;
//        }
//    }

//    public void create(User user) throws SQLException {
//        Connection conn = null;
//        PreparedStatement ps = null;
//
//        try{
//            conn  = ConnectionManager.getConnection(); // 커넥션을 받아옴
//            String sql = "INSERT INTO USERS VALUES (?,?,?,?)";
//            ps = conn.prepareStatement(sql); // 일단 Obseesion은 method signature에 붙여준다.(catch로 잡는것이 아니라)
//            ps.setString(1,user.getUserId());
//            ps.setString(2,user.getPassword());
//            ps.setString(3,user.getName());
//            ps.setString(4,user.getEmail());
//
//            ps.executeUpdate();
//
//        }finally {
//            // 자원 해제 ==> try with resource 라는 메소드 존재
//            if(ps != null) ps.close();
//            // 자원 해제
//            if(conn != null) conn.close();
//
//        }
//    }

    // create 는 복잡 했으나, 라이브러리로 뺄수있는 부분 sql 쿼리 부분을 파라미터로 받도록 하고
    // preparedStatement 에서 세팅하는 부분을 별도의 인터페이스를 맞들어 호출하는 쪽에서 자신의 맞게 setting 을 하여 전달하면,
    // executeUpdate 메소드 내부에서 setter 를 호출하게끔하고 자신이 생성한 prepared statement 를 전달하게 하여
    // 이 prepared statement 이용해서 setting 하도록 하는 부분.
    public void create(User user)throws SQLException{
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql ="INSERT INTO USERS VALUES (?,?,?,?)";
//        jdbcTemplate.executeUpdate(user, sql, new PreparedStatementSetter() {
//            @Override
//            public void setter(PreparedStatement ps) throws SQLException {
//                ps.setString(1,user.getUserId());
//                ps.setString(2,user.getPassword());
//                ps.setString(3,user.getName());
//                ps.setString(4,user.getEmail());
//            }
//        });
        // alt+enter 로 람다로 수행하도록 함.
        jdbcTemplate.executeUpdate(user, sql, ps -> {
            ps.setString(1,user.getUserId());
            ps.setString(2,user.getPassword());
            ps.setString(3,user.getName());
            ps.setString(4,user.getEmail());
        });
    }

//    public User findByUserId(String userId) throws SQLException {
//        Connection conn = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null; // 조회이기 때문에
//
//        try {
//            conn = ConnectionManager.getConnection();
//            String sql = "SELECT USERID,PASSWORD,NAME,EMAIL FROM USERS WHERE USERID = ?";
//            ps = conn.prepareStatement(sql);
//            ps.setString(1, userId);
//
//
//            rs = ps.executeQuery();
//
//            User user = null;
//            if(rs.next()) {
//                user = new User(rs.getString("userId"),
//                                rs.getString("password"),
//                                rs.getString("name"),
//                                rs.getString("email")
//                );
//            }
//            return user;
//        }finally {
//            // 만나는 순서대로 자원 해제
//            if(rs != null) rs.close();
//            if(ps != null) ps.close();
//            if(conn != null) conn.close();
//        }
//
//    }


    public User findByUserId(String userId) throws SQLException {

        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "SELECT USERID,PASSWORD,NAME,EMAIL FROM USERS WHERE USERID = ?";
        return (User) jdbcTemplate.executeQuery(sql
                , ps -> ps.setString(1, userId)
                , resultSet -> new User(
                        resultSet.getString("userId"),
                        resultSet.getString("password"),
                        resultSet.getString("name"),
                        resultSet.getString("email")));

    }
}


