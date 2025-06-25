package com.javaex.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.VO.PhonebookVO;
import com.javaex.service.PhonebookService;


@Controller
public class PhonebookController {
	
	// list  / delete / iform / insert / update / uForm
	
	//field
	@Autowired
	private PhonebookService phonebookservice;
	//editor
	
	//method g/s
	
	//method normal
	
	// test
	@RequestMapping(value="test", method= {RequestMethod.GET, RequestMethod.POST})
	public String test() {
		
		System.out.println("phonebook.test()");
		
		return "/test";
	}
	
	//list
	@RequestMapping(value="list", method= {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		System.out.println("PhonebookController.list()");
		
		List<PhonebookVO> phonebookList = phonebookservice.exePhonebookList();
		
		model.addAttribute("pList", phonebookList);
		
		return "list";
	}
	
	//iForm
	@RequestMapping(value="iForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String iForm() {
		System.out.println("PhonebookController.iForm()");
		
		return "insertform";
	}
	
	//insert
	@RequestMapping(value="insert", method= {RequestMethod.GET, RequestMethod.POST})
	public String insert(@ModelAttribute PhonebookVO phonebookvo) {
		System.out.println("PhonebookController.insert()");
		
		phonebookservice.exePhonebookAdd(phonebookvo);
		
		return "redirect:/list";
	}
	
	//delete
	@RequestMapping(value="delete", method= {RequestMethod.GET, RequestMethod.POST})
	public String delete(@ModelAttribute int phonebookvo) {
		System.out.println("PhonebookController.delete()");
		
		phonebookservice.exePhonebookDelete(phonebookvo);
		
		return "redirect:/list";
	}
	
	//uForm
	@RequestMapping(value="uForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String uForm() {
		System.out.println("PhonebookController.uForm()");
		
		return "updateform";
	}
	//update
	@RequestMapping(value="update", method= {RequestMethod.GET, RequestMethod.POST})
	public String update(@ModelAttribute PhonebookVO phonebookvo) {
		System.out.println("PhonebookController.update()");
		
		phonebookservice.exePhonebookUpdate(phonebookvo);
		
		return "redirect:/list";
	}
	
}
