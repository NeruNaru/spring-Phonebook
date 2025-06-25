package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.DAO.PhonebookDAO;
import com.javaex.VO.PhonebookVO;

@Service
public class PhonebookService {
	//field
	@Autowired
	private PhonebookDAO phonebookdao;
	//editor
	
	//method g/s
	
	//method normal
	
	//list
		public List<PhonebookVO> exePhonebookList(){
			System.out.println("PhonebookService.exePhonebookList()");
			
			// work with DAO
			List<PhonebookVO> phonebookList = phonebookdao.personSelect();
			
			return phonebookList;
		}
	//insert
		public int exePhonebookAdd(PhonebookVO phonebookvo) {
			System.out.println("PhonebookService.exePhonebookAdd()");
			
			// work with DAO
			int count = phonebookdao.personInsert(phonebookvo);
			
			return count;
		}
		
	//delete
		public int exePhonebookDelete(int phonebookvo) {
			System.out.println("PhonebookService.exePhonebookDelete()");
			
			//work with DAO
			int count = phonebookdao.personDelete(phonebookvo);
			
			return count;
		}
	//update
		public int exePhonebookUpdate(PhonebookVO phonebookvo) {
			System.out.println("PhonebookService.exePhondbookUpdate()");
			
			int count = phonebookdao.personUpdate(phonebookvo);
			
			return count;
		}
}
