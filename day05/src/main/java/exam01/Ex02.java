package exam01;

import java.sql.*;

public class Ex02 {
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");

        String url = "jdbc:oracle:thin:@localhost:1521:orcl?user=scott&password=tiger";
        //쿼리 스트링 형태로 계정 정보 입력 가능
        String sql = "SELECT * FROM DEPT2";
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            ResultSet result = stmt.executeQuery(sql);
            while (result.next()){
                int deptNo = result.getInt("DEPTNO");
                String dName = result.getString("DNAME");
                String loc = result.getString("LOC");
                System.out.printf("DEPTNO=%d, DNAME=%s, LOC=%s",deptNo,dName,loc);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
