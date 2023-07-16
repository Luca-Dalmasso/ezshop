package it.polito.ezshop.integrationtests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import it.polito.ezshop.Tests.BB.AccountBook.*;

@RunWith(Suite.class)
@Suite.SuiteClasses({AccountBookAddBalanceOperation.class, 
					 AccountBookDeleteBalanceOperation.class, 
					 AccountBookSetBalance.class,
					 AccountBookTestGetAccountingList.class,
					 AccountBookTestGetBalance.class, 
					})
public class TestSuiteAccountBook {

}
