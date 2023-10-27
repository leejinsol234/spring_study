package exam01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Ex01 {
    public static void main(String[] args) throws ClassNotFoundException {

        //동적 로딩 : Class.forName("패키지명을 포함한 클래스명");-> 실행 과정 중에 로딩이 되게 하기 위해
        Class.forName("oracle.jdbc.driver.OracleDriver");

        //데이터베이스 접속을 위한 Connection객체 생성
        String url = "jdbc:oracle:thin:@localhost:1521:orcl"; //데이터베이스 저장소
        String username= "scott";
        String password= "tiger";
        try (Connection conn = DriverManager.getConnection(url, username, password); //자원 해제가 필요함

             Statement stmt = conn.createStatement()) {
                int deptNo = 50;
                String dName = "DEPT1";
                String loc = "LOC1";

                String sql = "INSERT INTO DEPT2 VALUES (" + deptNo + ", '" + dName +"','" + loc +"')";
                int affectedRows = stmt.executeUpdate(sql); //executeUpdate: 반환값 -> 반영된 레코드 개수(추가, 수정 및 삭제된 레코드의 개수)
                System.out.println(affectedRows);
        } catch (SQLException e){
            e.printStackTrace();
        }

    }
}
