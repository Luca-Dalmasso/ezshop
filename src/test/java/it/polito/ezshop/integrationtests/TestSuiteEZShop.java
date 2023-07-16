package it.polito.ezshop.integrationtests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import it.polito.ezshop.Tests.BB.EZShop.EZShopTestAddProductToSale;
import it.polito.ezshop.Tests.BB.EZShop.EZShopTestApplyDiscountRateToProduct;
import it.polito.ezshop.Tests.BB.EZShop.EZShopTestApplyDiscountRateToSale;
import it.polito.ezshop.Tests.BB.EZShop.EZShopTestAttachCardToCustomer;
import it.polito.ezshop.Tests.BB.EZShop.EZShopTestComputeBalance;
import it.polito.ezshop.Tests.BB.EZShop.EZShopTestComputePointsForSale;
import it.polito.ezshop.Tests.BB.EZShop.EZShopTestCreateCard;
import it.polito.ezshop.Tests.BB.EZShop.EZShopTestCreateProductType;
import it.polito.ezshop.Tests.BB.EZShop.EZShopTestCreateUser;
import it.polito.ezshop.Tests.BB.EZShop.EZShopTestDefineCustomer;
import it.polito.ezshop.Tests.BB.EZShop.EZShopTestDeleteCustomer;
import it.polito.ezshop.Tests.BB.EZShop.EZShopTestDeleteProductFromSale;
import it.polito.ezshop.Tests.BB.EZShop.EZShopTestDeleteProductType;
import it.polito.ezshop.Tests.BB.EZShop.EZShopTestDeleteReturnTransaction;
import it.polito.ezshop.Tests.BB.EZShop.EZShopTestDeleteSaleTransaction;
import it.polito.ezshop.Tests.BB.EZShop.EZShopTestDeleteUser;
import it.polito.ezshop.Tests.BB.EZShop.EZShopTestEndReturnTransaction;
import it.polito.ezshop.Tests.BB.EZShop.EZShopTestEndSaleTransaction;
import it.polito.ezshop.Tests.BB.EZShop.EZShopTestGetAllCustomers;
import it.polito.ezshop.Tests.BB.EZShop.EZShopTestGetAllOrders;
import it.polito.ezshop.Tests.BB.EZShop.EZShopTestGetAllProductTypes;
import it.polito.ezshop.Tests.BB.EZShop.EZShopTestGetAllUser;
import it.polito.ezshop.Tests.BB.EZShop.EZShopTestGetCreditsAndDebits;
import it.polito.ezshop.Tests.BB.EZShop.EZShopTestGetCustomer;
import it.polito.ezshop.Tests.BB.EZShop.EZShopTestGetProductTypeByBarCode;
import it.polito.ezshop.Tests.BB.EZShop.EZShopTestGetProductTypesByDescription;
import it.polito.ezshop.Tests.BB.EZShop.EZShopTestGetSaleTransaction;
import it.polito.ezshop.Tests.BB.EZShop.EZShopTestGetUser;
import it.polito.ezshop.Tests.BB.EZShop.EZShopTestIssueOrder;
import it.polito.ezshop.Tests.BB.EZShop.EZShopTestLogin;
import it.polito.ezshop.Tests.BB.EZShop.EZShopTestLogout;
import it.polito.ezshop.Tests.BB.EZShop.EZShopTestModifiyCustomer;
import it.polito.ezshop.Tests.BB.EZShop.EZShopTestModifyPointsOnCard;
import it.polito.ezshop.Tests.BB.EZShop.EZShopTestPayOrder;
import it.polito.ezshop.Tests.BB.EZShop.EZShopTestPayOrderFor;
import it.polito.ezshop.Tests.BB.EZShop.EZShopTestReceiveCashPayment;
import it.polito.ezshop.Tests.BB.EZShop.EZShopTestReceiveCreditCardPayement;
import it.polito.ezshop.Tests.BB.EZShop.EZShopTestRecordBalanceUpdate;
import it.polito.ezshop.Tests.BB.EZShop.EZShopTestRecordOrderArrival;
import it.polito.ezshop.Tests.BB.EZShop.EZShopTestReset;
import it.polito.ezshop.Tests.BB.EZShop.EZShopTestReturnCashPayment;
import it.polito.ezshop.Tests.BB.EZShop.EZShopTestReturnCreditCarPayment;
import it.polito.ezshop.Tests.BB.EZShop.EZShopTestReturnProduct;
import it.polito.ezshop.Tests.BB.EZShop.EZShopTestStartReturnTransaction;
import it.polito.ezshop.Tests.BB.EZShop.EZShopTestStartSaleTransaction;
import it.polito.ezshop.Tests.BB.EZShop.EZShopTestUpdatePosition;
import it.polito.ezshop.Tests.BB.EZShop.EZShopTestUpdateProduct;
import it.polito.ezshop.Tests.BB.EZShop.EZShopTestUpdateQuantity;
import it.polito.ezshop.Tests.BB.EZShop.EZShopTestUpdateUserRights;

@RunWith(Suite.class)
@SuiteClasses({ EZShopTestAddProductToSale.class, EZShopTestApplyDiscountRateToProduct.class,
		EZShopTestApplyDiscountRateToSale.class, EZShopTestAttachCardToCustomer.class, EZShopTestComputeBalance.class,
		EZShopTestComputePointsForSale.class, EZShopTestCreateCard.class, EZShopTestCreateProductType.class,
		EZShopTestCreateUser.class, EZShopTestDefineCustomer.class, EZShopTestDeleteCustomer.class,
		EZShopTestDeleteProductFromSale.class, EZShopTestDeleteProductType.class,
		EZShopTestDeleteReturnTransaction.class, EZShopTestDeleteSaleTransaction.class, EZShopTestDeleteUser.class,
		EZShopTestEndReturnTransaction.class, EZShopTestEndSaleTransaction.class, EZShopTestGetAllCustomers.class,
		EZShopTestGetAllOrders.class, EZShopTestGetAllProductTypes.class, EZShopTestGetAllUser.class,
		EZShopTestGetCreditsAndDebits.class, EZShopTestGetCustomer.class, EZShopTestGetProductTypeByBarCode.class,
		EZShopTestGetProductTypesByDescription.class, EZShopTestGetSaleTransaction.class, EZShopTestGetUser.class,
		EZShopTestIssueOrder.class, EZShopTestLogin.class, EZShopTestLogout.class, EZShopTestModifiyCustomer.class,
		EZShopTestModifyPointsOnCard.class, EZShopTestPayOrder.class, EZShopTestPayOrderFor.class,
		EZShopTestReceiveCashPayment.class, EZShopTestReceiveCreditCardPayement.class,
		EZShopTestRecordBalanceUpdate.class, EZShopTestRecordOrderArrival.class, EZShopTestReset.class,
		EZShopTestReturnCashPayment.class, EZShopTestReturnCreditCarPayment.class, EZShopTestReturnProduct.class,
		EZShopTestStartReturnTransaction.class, EZShopTestStartSaleTransaction.class, EZShopTestUpdatePosition.class,
		EZShopTestUpdateProduct.class, EZShopTestUpdateQuantity.class, EZShopTestUpdateUserRights.class })
public class TestSuiteEZShop {

}
