# JDBC

> JAVA 어플리케이션이 데이터베이스에 `연결`하고 쿼리를 실행할 수 있도록 하는 API

DriverManager -> Connection -> statement -> resultset

순서로 객체를 사용하여 SQL 질의를 처리한다.

- DriverManager

  - JDBC 드라이버를 관리하고 연결을 설정할 수 있는 객체

- Connection

  - DB와의 연결을 나타내는 객체
  - statement 생성
  - Transaction의 출발점

- Statement

  - statement
    - sql 생성
    - `SQL injection에 취약`하다.
  - preparedStatement
    - sql 생성 , 동적 파라미터 매핑
  - callableStatement
    - stored procedure에서 사용

- ResultSet
  - select 쿼리의 결과를 저장하는 객체

```java

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCExample {
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
            sql += "values(?,?,?,?,?)";
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
            System.out.println(rowAffected+" row(s) Affected");

        } catch (Exception e) {
            e.printStackTrace();
        } finally{
             // 5. 자원 해제
            try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
        }
    }
}

```

```java

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCExample {
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
            String sql = "insert into members (userid, userpwd, username, emailid, emaildomain) \n";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "testuser");
            pstmt.setString(2, "password");
            pstmt.setString(3, "Test User");
            pstmt.setString(4, "test");
            pstmt.setString(5, "domain.com");

            // 3. SQL 쿼리 실행
            ResultSet rs = pstmt.executeUpdate(query);

            // 4. 결과 처리
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                System.out.println("ID: " + id + ", Name: " + name);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally{
             // 5. 자원 해제
            try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
                if(rs !=null){
                    rs.close();
                }
			}catch(SQLException e){
				e.printStackTrace();
			}
        }
    }
}

```
