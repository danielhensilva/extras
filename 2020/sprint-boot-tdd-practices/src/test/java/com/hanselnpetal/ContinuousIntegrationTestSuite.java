package com.hanselnpetal;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.hanselnpetal.controller.ContactsManagementControllerIntegrationTest;
import com.hanselnpetal.data.repos.CustomerContactRepositoryIntegrationTest;
import com.hanselnpetal.service.ContactsManagementServiceIntegrationTest;

@RunWith(Suite.class)
@Suite.SuiteClasses ({ DatastoreSystemsHealthTest.class, 
	ContactsManagementControllerIntegrationTest.class })

public class ContinuousIntegrationTestSuite {

	// intentionally empty - Test Suite Setup (annotations) is sufficient
}
