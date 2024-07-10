package common;

import java.sql.Statement;

import jakarta.servlet.ServletContext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JDBConnect {
	public Connection con;
	
	public Statement stmt;
	// 인파라미터가 있는 동적 쿼리문을 실행할 때 사용
	public PreparedStatement psmt;
	// SELECT 쿼리문의 결과를 저장할 때 사용
	public ResultSet rs;
	public JDBConnect() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String id = "MUSTHAVE";
			String pwd = "1234";
			con = DriverManager.getConnection(url, id, pwd);
			
			System.out.println("DB 연결 성공(기본 생성자)");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		try {
			if (rs != null) rs.close();
			if (stmt != null) stmt.close();
			if (psmt != null) psmt.close();
			if (con != null) con.close();
			
			System.out.println("JDBC 자원 해제");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 
	
	public JDBConnect(String driver, String url, String id, String pwd) {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, id, pwd);
			System.out.println("DB 연결 성공(인수 생성자 1)");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public JDBConnect(ServletContext application) {
		/*
		  	JSP의 내장객체는 메서드에서는 즉시 사용할 수 없고 반드시 매개변수로
		  	참조값을 받은 후 사용해야한다.
		 */
		
		
		try {
			// web.xml에 정의된 컨테스트 초기화 파라미터를 직접 얻어와서
			// 드라이버 로드 및 연결을 진행한다.
			String driver = application.getInitParameter("OracleDriver");
			Class.forName(driver);
			String url = application.getInitParameter("OracleURL");
			String id = application.getInitParameter("OracleId");
			String pwd = application.getInitParameter("OraclePwd");
			con = DriverManager.getConnection(url, id, pwd);
			System.out.println("DB 연결 성공(인수 생성자 2)");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
