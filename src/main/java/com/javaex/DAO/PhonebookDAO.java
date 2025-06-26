package com.javaex.DAO;

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
	
	
	// 전체리스트 가져오기
	public List<PhonebookVO> personSelect() {
		System.out.println("PhonebookDAO.personSelect()");
		
		List<PhonebookVO> phonebookList = sqlsession.selectList("phonebook.selectList");
		
		return phonebookList;
		
	}
	
	
	
	// 1명 정보 가져오기
	public void personSelectOne(int personId) {
		System.out.println("PhonebookDAO.personSelectOne()");
		
		sqlsession.selectOne("phonebook.selectOne",personId);
	}
	
	
	
	//사람(주소) 등록
	public int personInsert(PhonebookVO phonebookVO) {
		System.out.println("PhonebookDAO.personInsert()");
		
		int count = sqlsession.insert("phonebook.insert", phonebookVO);
		
		return count;
	}
	
	//사람(주소) 삭제
	public int personDelete(int personId) {
		System.out.println("PhonebookDAO.personDelete()");
		
		int count = sqlsession.delete("phonebook.delete", personId);
		
		return count;
	}
	
	
	//사람(주소) 수정
	public int personUpdate(PhonebookVO PhonebookVO) {
		System.out.println("PhonebookDAO.personUpdate()");
		
		int count = sqlsession.update("phonebook.update", PhonebookVO);
		
		return count;
	}
	
	
}