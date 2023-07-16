package it.polito.ezshop.integrationtests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import it.polito.ezshop.Tests.BB.CreditCardList.TestCheckBalance;
import it.polito.ezshop.Tests.BB.CreditCardList.TestSearchCardByCode;

@RunWith(Suite.class)
@Suite.SuiteClasses({TestCheckBalance.class, 
					 TestSearchCardByCode.class, 
					})
public class TestSuiteCreditCardList {

}
