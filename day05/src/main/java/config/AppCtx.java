package config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class AppCtx {
    @Bean(destroyMethod = "close") //외부 라이브러리이므로 (destroyMethod = "close")
    public DataSource dataSource(){
        DataSource ds = new DataSource();
        //데이터 베이스 연결 설정 S
        ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        ds.setUrl("jdbc:oracle:thin:@localhost:1521:orcl");
        ds.setUsername("spring6");
        ds.setPassword("_aA123456");
        //데이터 베이스 연결 설정 E

        //connection pool 설정 S
        ds.setInitialSize(2); //커넥션 풀을 초기화할 때 생성할 초기 커넥션 개수
        ds.setMaxActive(10); //커넥션 풀에서 가져올 수 있는 최대 커넥션 개수
        ds.setTestWhileIdle(true); //커넥션이 풀에 유휴 상태로 있는 동안 검사할지 여부를 지정한다. 기본값은 false이다.
        ds.setTimeBetweenEvictionRunsMillis(3000); //3초마다 연결 상태 확인(커넥션 풀의 유효 커넥션을 검사할 주기, 밀리초 단위)
        ds.setMinEvictableIdleTimeMillis(30 * 1000); //커넥션 풀에 유효 상태를 유지할 최초 시간(30초)
        //connection pool 설정 E
        return ds;
    }

    //쿼리 수행
    @Bean
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(dataSource());
    }
}
