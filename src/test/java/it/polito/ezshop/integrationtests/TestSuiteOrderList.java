package it.polito.ezshop.integrationtests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import it.polito.ezshop.Tests.BB.OrderList.*;

@RunWith(Suite.class)
@Suite.SuiteClasses({OrderListTestAddOrder.class, 
					 OrderListTestChangeOrderStatus.class, 
					 OrderListTestGetAllOrders.class,
					 OrderListTestSearchOrderByFields.class,
					 OrderListTestSearchOrderByID.class, 
					})
public class TestSuiteOrderList {

}
