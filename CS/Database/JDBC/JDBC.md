### Update History

> 2024.09.20 최초작성

# JDBC

> JAVA 어플리케이션이 데이터베이스에 `연결`하고 쿼리를 실행할 수 있도록 하는 API

DriverManager -> Connection -> statement -> (resultset)

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
