package it.polito.ezshop.integrationtests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import it.polito.ezshop.Tests.BB.CustomerList.CustomerListTestAddCustomer;
import it.polito.ezshop.Tests.BB.CustomerList.CustomerListTestAttachCustomerCard;
import it.polito.ezshop.Tests.BB.CustomerList.CustomerListTestDeleteCustomer;
import it.polito.ezshop.Tests.BB.CustomerList.CustomerListTestGetCustomer;
import it.polito.ezshop.Tests.BB.CustomerList.CustomerListTestGetCustomerList;
import it.polito.ezshop.Tests.BB.CustomerList.CustomerListTestUpdateCustomer;

@RunWith(Suite.class)
@Suite.SuiteClasses({CustomerListTestAddCustomer.class,
					 CustomerListTestAttachCustomerCard.class,
					 CustomerListTestDeleteCustomer.class,
					 CustomerListTestGetCustomer.class,
					 CustomerListTestGetCustomerList.class,
					 CustomerListTestUpdateCustomer.class
					})

public class TestSuiteCustomerList {

}
