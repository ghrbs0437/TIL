import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCSelectExample {
    public static void main(String[] args) {
        // 데이터베이스 연결에 필요한 정보. DataBase 벤더에 따라 URL 형식이 다르다.
        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String user = "username";
        String password = "password";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            // 1. 데이터베이스 연결
            conn = DriverManager.getConnection(url, user, password);

            // 2. Statement 객체 생성
            String sql = "select * from members";
            pstmt = conn.prepareStatement(sql);
            // 3. SQL 쿼리 실행
            rs = pstmt.executeQuery();

            // 4. 결과 처리
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                System.out.println("ID: " + id + ", Name: " + name);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 5. 자원 해제
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
