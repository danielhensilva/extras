package com.hanselnpetal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hanselnpetal.domain.CustomerContact;
import com.hanselnpetal.service.ContactsManagementService;

@Controller
@RequestMapping("/addContact")
public class ContactsManagementController {

	@Autowired
	private ContactsManagementService contactsManagementService;
	
	
	@RequestMapping(method=RequestMethod.POST)
	public String processAddContactSubmit(@RequestBody CustomerContact aContact) {
		
		CustomerContact newContact = contactsManagementService.add(aContact);
		
		if (newContact != null) {
			return "success";
		}
		
		return "failure";
	}
}
