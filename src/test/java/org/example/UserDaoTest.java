package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

// TDD 방식
public class UserDaoTest {

    // 테스트코드를 실행하기에 앞서 수행하기 위해서
    // 즉, 테스트 코드를 수행하기에 앞서서 테이블을 생성하기 위해서 테이블 생성 또는 초기 데이터를 쿼리를 수행하기 위해서 ...
    @BeforeEach
    void setUp() {
        // ResourceDatabasePopulator 는 Spring Framework JDBC 에 있는 클래스
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("db_schema.sql"));
        DatabasePopulatorUtils.execute(populator,ConnectionManager.getDataSource());
    }

    @Test
    void createTest() throws SQLException {
        // Dao : 데이터 엑세스 오브젝트 , DB 작업 수행할때 USER DAO에게 위임할 예정
        UserDao userDao = new UserDao();

        userDao.create(new org.example.User("wizard","password","name","email"));


        org.example.User user = userDao.findByUserId("JUNG");

        assertThat(user).isEqualTo(new org.example.User("wizard","password","name","email"));

    }
}
