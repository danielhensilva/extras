package com.hanselnpetal.service;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.hanselnpetal.data.repos.CustomerContactRepository;
import com.hanselnpetal.domain.CustomerContact;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class ContactsManagementServiceUnitTest {
	
	@Mock
	private CustomerContactRepository customerContactRepository;
	
	@InjectMocks
	private ContactsManagementService contactsManagementService;
	
	
	@Before
    public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testAddContactHappyPath() {
		
		// Create a contact
		CustomerContact aMockContact = new CustomerContact();
		aMockContact.setFirstName("Jenny");
		aMockContact.setLastName("Johnson");
		
		when(customerContactRepository.save(any(CustomerContact.class))).thenReturn(aMockContact);
				
		// Save the contact
		CustomerContact newContact = contactsManagementService.add(null);
		
		
		// Verify the save
		assertEquals("Jenny", newContact.getFirstName());
	}
}
