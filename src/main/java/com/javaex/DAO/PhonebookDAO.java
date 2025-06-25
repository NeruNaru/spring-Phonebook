package com.javaex.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.VO.PhonebookVO;

@Repository
public class PhonebookDAO {

	// 필드
	@Autowired
	public SqlSession sqlsession;
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/phonebook_db";
	private String id = "phonebook";
	private String pw = "phonebook";

	// 생성자
	public PhonebookDAO(){}

	// 메소드gs
	
	//메소드일반
	// DB연결 메소드-공통
	private void connect() { // 메인에서는 사용하지 못함

		try {
			// 1. JDBC 드라이버 (MySQL) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기
			this.conn = DriverManager.getConnection(url, id, pw);

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

	}
	
	// 자원정리 메소드-공통
	private void close() {
		// 5. 자원정리
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
			System.out.println("error:" + e);
		}
	}
	
	// 전체리스트 가져오기
	public List<PhonebookVO> personSelect() {
		System.out.println("PhonebookDAO.personSelect()");
		
		List<PhonebookVO> phonebookList = sqlsession.selectList("phonebook.selectList");
		
		return phonebookList;
		
	}
	
	
	
	// 1명 정보 가져오기
	public PhonebookVO personSelectOne(int no) {
		
		//VO준비 (1명정보만 담아야 하니 리스트 필요없음)
		PhonebookVO PhonebookVO = null;
		
		this.connect();
		
		try {
			//3. SQL문준비 / 바인딩 / 실행
			// SQL문준비
			String query= "";
			query +=" select  person_id, ";
			query +="		  name, ";
			query +="         hp, ";
			query +="         company ";
			query +=" from person ";
			query +=" where person_id = ? ";
			
			// 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			
			// 실행
			rs = pstmt.executeQuery();
			
			//4. 결과처리
			rs.next();
			
			//ResultSet에서 각각의 값을 꺼내서 자바 변수에 담는다
			int personId = rs.getInt("person_id");
			String name = rs.getString("name");
			String hp = rs.getString("hp");
			String company = rs.getString("company");
			
			//VO로 묶어준다
			PhonebookVO = new PhonebookVO(personId, name, hp, company);
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
		this.close();
		
		return PhonebookVO;
		
	}
	
	
	
	//사람(주소) 등록
	public int personInsert(PhonebookVO PhonebookVO) {
		System.out.println("personInsert()");
		int count = -1;
		
		this.connect();
		
		try {
			//3. SQL문준비 / 바인딩 / 실행
			// - SQL문준비
			String query ="";
			query += " insert into person ";
			query += " values(null, ?, ?, ?) ";
			
			// - 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, PhonebookVO.getName());
			pstmt.setString(2, PhonebookVO.getHp());
			pstmt.setString(3, PhonebookVO.getCompany());
			
			// - 실행
			count = pstmt.executeUpdate();
			
			//4. 결과처리
			System.out.println(count + "건이 저장되었습니다.");
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
		this.close();
		
		return count;
	}
	
	//사람(주소) 삭제
	public int personDelete(int no) {
		System.out.println("personDelete");
		
		int count = -1;
		
		this.connect();
		
		try {
			//3. SQL문준비 / 바인딩 / 실행
			// - SQL문준비
			String query="";
			query += " delete from person ";
			query += " where person_id = ? ";
			
			// - 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			
			// - 실행
			count = pstmt.executeUpdate();
			
			//4. 결과처리
			System.out.println(count + "건 삭제되었습니다.");
			
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
		this.close();
		
		return count;
	}
	
	
	//사람(주소) 수정
	public int personUpdate(PhonebookVO PhonebookVO) {
		System.out.println("personUpdate");
		
		int count = -1;
		
		this.connect();
		
		try {
			//3. SQL문준비 / 바인딩 / 실행
			// - SQL문준비
			String query="";
			query += " update person ";
			query += " set name = ?, ";
			query += " 	   hp = ?, ";
			query += " 	   company = ? ";
			query += " where person_id = ? ";
			
			// - 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, PhonebookVO.getName());
			pstmt.setString(2, PhonebookVO.getHp());
			pstmt.setString(3, PhonebookVO.getCompany());
			pstmt.setInt(4, PhonebookVO.getPersonId());
			
			// - 실행
			count = pstmt.executeUpdate();
			
			//4. 결과처리
			System.out.println(count + "건 수정되었습니다.");
			
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		
		this.close();
		
		return count;
	}
	
	
}