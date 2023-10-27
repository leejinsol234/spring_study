package exam01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Ex03 {
    public static void main(String[] args) throws ClassNotFoundException{
        Class.forName("oracle.jdbc.driver.OracleDriver");

        String url = "jdbc:oracle:thin:@localhost:1521:orcl";
        String user = "scott";
        String pass = "tiger";

        String sql = "INSERT INTO DEPT2 VALUES (?,?,?)"; //미리 쿼리문을 준비
        try(Connection conn = DriverManager.getConnection(url,user,pass);
            PreparedStatement pstmt = conn.prepareStatement(sql)){

            //set자료형() 메서드 -> 물음표로 미리 준비해둔 쿼리문에 들어갈 값을 입력할 때(단,물음표 순서는 1부터)
            pstmt.setInt(1,50);
            pstmt.setString(2, "DEPT2");
            pstmt.setString(3,"LOC");

            //int executeUpdate(); -> 미리 sql문이 준비되어 있으므로 매개변수가 없음.
            int affectedRows = pstmt.executeUpdate();
            System.out.println(affectedRows);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
