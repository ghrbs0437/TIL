import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCInsertExample {
    public static void main(String[] args) {
        // 데이터베이스 연결에 필요한 정보. DataBase 벤더에 따라 URL 형식이 다르다.
        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String user = "username";
        String password = "password";

        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            // 1. 데이터베이스 연결
            conn = DriverManager.getConnection(url, user, password);

            // 2. Statement 객체 생성
            String sql = "insert into members (userid, userpwd, username, emailid, emaildomain)";
            sql += "VALUES (?,?,?,?,?)";
            // 3. SQL parameter 바인딩
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "testuser");
            pstmt.setString(2, "password");
            pstmt.setString(3, "Test User");
            pstmt.setString(4, "test");
            pstmt.setString(5, "domain.com");

            // 4. SQL 쿼리 실행
            int rowAffected = pstmt.executeUpdate();

            // 5. 결과 처리
            System.out.println(rowAffected + " row(s) Affected");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 5. 자원 해제
            try {
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
