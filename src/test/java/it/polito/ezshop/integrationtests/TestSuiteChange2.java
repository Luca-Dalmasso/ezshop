package it.polito.ezshop.integrationtests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import it.polito.ezshop.change2Tests.*;

@RunWith(Suite.class)
@SuiteClasses({
			   WBTestProduct.class,
			   EZShopTestAddProductToSaleRFID.class,
			   EZShopTestDeleteProductFromSaleRFID.class,
			   EZShopTestRecordOrderArrivalRFID.class,
			   EZShopTestReturnProductRFID.class,
			   EZShopTestEndSaleTransactionNew.class,
			   EZShopTestEndReturnTransactionNew.class
})
public class TestSuiteChange2 {

}
