package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcTemplate {
    // 라이브러리 임...


    // executeUpdate 메소드 내부에서 setter 를 호출하게끔하고 자신이 생성한 prepared statement 를 전달하게 하여
    // 이 prepared statement 이용해서 setting 하도록 하는 부분.
    public void executeUpdate(User user, String sql, PreparedStatementSetter pss) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;

        try{
            conn  = ConnectionManager.getConnection(); // 커넥션을 받아옴
            //String sql = "INSERT INTO USERS VALUES (?,?,?,?)";

            //setter 를 하여 setting 을 원하는 preparedStatment 만 전달하면 된다!
            ps = conn.prepareStatement(sql); // 일단 Obseesion은 method signature에 붙여준다.(catch로 잡는것이 아니라)
            pss.setter(ps);
//            ps.setString(1,user.getUserId());
//            ps.setString(2,user.getPassword());
//            ps.setString(3,user.getName());
//            ps.setString(4,user.getEmail());



            ps.executeUpdate();

        }finally {
            // 자원 해제 ==> try with resource 라는 메소드 존재
            if(ps != null) ps.close();
            // 자원 해제
            if(conn != null) conn.close();

        }
    }

    public Object executeQuery( String sql,PreparedStatementSetter pss, RowMapper rowMapper) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null; // 조회이기 때문에

        try {
            conn = ConnectionManager.getConnection();
//            String sql = "SELECT USERID,PASSWORD,NAME,EMAIL FROM USERS WHERE USERID = ?";

            // 생성한 ps 를 setter 에 전달
            ps = conn.prepareStatement(sql);
            // 몇개가 생성 될지 모르기 때문에 setter
            pss.setter(ps);
//            ps.setString(1, userId);


            rs = ps.executeQuery();

            Object obj = null;
            if(rs.next()) {
                return rowMapper.map(rs);
            }
            return obj;
        }finally {
            // 만나는 순서대로 자원 해제
            if(rs != null) rs.close();
            if(ps != null) ps.close();
            if(conn != null) conn.close();
        }

    }
}
