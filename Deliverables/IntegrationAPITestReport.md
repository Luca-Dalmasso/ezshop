# Integration and API Test Documentation

Authors:
Dalmasso Luca s281316
Kitou Mgbatou Osee Patrik s288425
Mistruzzi Luca Guglielmo s292623 (old: s235744)
Protopapa Matteo s290151

Date:
26/05/21

Version:
1.01

# Contents

- [Dependency graph](#dependency graph)

- [Integration approach](#integration)

- [Tests](#tests)

- [Scenarios](#scenarios)

- [Coverage of scenarios and FR](#scenario-coverage)
- [Coverage of non-functional requirements](#nfr-coverage)



# Dependency graph 

[GraphImage](./ezshop.png)

# Integration approach

    Bottom up
    1) UnitTest: WhiteBox (also on DB) full coverage
    2) Integration: WhiteBox on SaleTransactionLIst
    3) Integration: BlackBox on all other lists
    4) Integration: BlackBox on API



#  Tests

   <define below a table for each integration step. For each integration step report the group of classes under test, and the names of
     JUnit test cases applied to them> JUnit test classes should be here src/test/java/it/polito/ezshop

## Step 1
| Classes  | JUnit test cases |
|--|--|
| TestSuiteUnitTest | BalanceOperationTest  |
|                   | CreditCardTest        |
|                   | CustomerTest          |
|                   | LoyaltyCardTest       |
|                   | OrderTest             |
|                   | ProductTypeTest       |
|                   | ReturnTransactionTest |
|                   | TicketEntryTest       |
|                   | UserTest              |
|                   | SQLiteJDBCTestDelete  |
|                   | SQLiteJDBCTestInsert  |
|                   | SQLiteJDBCTestMaxID   |
|                   | SQLiteJDBCTestUpdate  |


## Step 2
| Classes  | JUnit test cases |
|--|--|
| TestSuiteSaleTransaction | SaleTransactionTestAddProduct                 |
|                          | SaleTransactionTestDeleteProduct              |
|                          | SaleTransactionTestGettersSettersConstructors |
|                          | SaleTransactionTestSetDiscountToProduct       |


## Step 3 

   

| Classes  | JUnit test cases |
|--|--|
|TestSuiteCreditCardList|TestCheckBalance|
||TestSearchCardByCode|
|TestSuiteCustomerList|CustomerListTestAddCustomer|
||CustomerListTestAttachCustomerCard|
||CustomerListTestDeleteCustomer|
||CustomerListTestGetCustomer|
||CustomerListTestGetCustomerList|
||CustomerListTestUpdateCustomer|
|TestSuiteLoyaltyCardList|LoyaltyCardListTestAddLoyaltyCard|
||LoyaltyCardListTestUpdatePointsOnCard|
||LoyaltyCardListTestAddLoyaltyCardIdAsArgoument|
||LoyaltyCardListTestDeleteLoyaltyCard|
||LoyaltyCardListTestSearchLoyaltyCardById|
|TestSuiteOrderList|OrderListTestAddOrder|
||OrderListTestChangeOrderStatus|
||OrderListTestGetAllOrders|
||OrderListTestSearchOrderByFields|
||OrderListTestSearchOrderByID|
|TestSuiteProductTypeList|TestAddProductType|
||TestSearchProductByBarcode|
||TestSearchProductByDescription|
||TestSearchProductByID|
||TestUpdatePosition|
||TestUpdateProductType|
|TestSuiteReturnTransactionList|ReturnTransactionListTestAddReturn|
||ReturnTransactionListTestCloseReturnTransaction|
||ReturnTransactionListTestDeleteReturnTransaction|
||ReturnTransactionListTestSearchReturnTransaction|
|TestSuiteSaleTransaction|SaleTransactionTestAddProduct|
||SaleTransactionTestDeleteProduct|
||SaleTransactionTestGettersSettersConstructors|
||SaleTransactionTestSetDiscountToProduct|
|TestSuiteSaleTransactionList|SaleTransactionListTestAddSale|
||SaleTransactionListTestCloseSale|
||SaleTransactionListTestDeleteSale|
||SaleTransactionListTestGetClosedSale|
||SaleTransactionListTestSearchSale|
|TestSuiteUserList|TestUpdateUserRights|
||TestUserListAddUser|
||TestUserListDeleteUser|
||TestUserListGetAllUsers|
||TestUserListSetAuthenticatedUser|
|TestSuiteAccountBook|AccountBookAddBalanceOperation|
||AccountBookDeleteBalanceOperation|
||AccountBookSetBalance|
||AccountBookTestGetAccountingList|
||AccountBookTestGetBalance|

## Step 4

| Classes         | JUnit test cases                       |
| --------------- | -------------------------------------- |
| TestSuiteEZShop | EZShopTestAddProductToSale             |
|                 | EZShopTestApplyDiscountRateToProduct   |
|                 | EZShopTestApplyDiscountRateToSale      |
|                 | EZShopTestAttachCardToCustomer         |
|                 | EZShopTestComputeBalance               |
|                 | EZShopTestComputePointsForSale         |
|                 | EZShopTestCreateCard                   |
|                 | EZShopTestCreateProductType            |
|                 | EZShopTestCreateUser                   |
|                 | EZShopTestDefineCustomer               |
|                 | EZShopTestDeleteCustomer               |
|                 | EZShopTestDeleteProductFromSale        |
|                 | EZShopTestDeleteProductType            |
|                 | EZShopTestDeleteReturnTransaction      |
|                 | EZShopTestDeleteSaleTransaction        |
|                 | EZShopTestDeleteUser                   |
|                 | EZShopTestEndReturnTransaction         |
|                 | EZShopTestEndSaleTransaction           |
|                 | EZShopTestGetAllCustomers              |
|                 | EZShopTestGetAllOrders                 |
|                 | EZShopTestGetAllProductTypes           |
|                 | EZShopTestGetAllUser                   |
|                 | EZShopTestGetCreditsAndDebits          |
|                 | EZShopTestGetCustomer                  |
|                 | EZShopTestGetProductTypeByBarCode      |
|                 | EZShopTestGetProductTypesByDescription |
|                 | EZShopTestGetSaleTransaction           |
|                 | EZShopTestGetUser                      |
|                 | EZShopTestIssueOrder                   |
|                 | EZShopTestLogin                        |
|                 | EZShopTestLogout                       |
|                 | EZShopTestModifiyCustomer              |
|                 | EZShopTestModifyPointsOnCard           |
|                 | EZShopTestPayOrder                     |
|                 | EZShopTestPayOrderFor                  |
|                 | EZShopTestReceiveCashPayment           |
|                 | EZShopTestReceiveCreditCardPayement    |
|                 | EZShopTestRecordBalanceUpdate          |
|                 | EZShopTestRecordOrderArrival           |
|                 | EZShopTestReset                        |
|                 | EZShopTestReturnCashPayment            |
|                 | EZShopTestReturnCreditCarPayment       |
|                 | EZShopTestReturnProduct                |
|                 | EZShopTestStartReturnTransaction       |
|                 | EZShopTestStartSaleTransaction         |
|                 | EZShopTestUpdatePosition               |
|                 | EZShopTestUpdateProduct                |
|                 | EZShopTestUpdateQuantity               |
|                 | EZShopTestUpdateUserRights             |

# Scenarios


<If needed, define here additional scenarios for the application. Scenarios should be named
 referring the UC in the OfficialRequirements that they detail>

## Scenario UCx.y

| Scenario |  name |
| ------------- |:-------------:|
|  Precondition     |  |
|  Post condition     |   |
| Step#        | Description  |
|  1     |  ... |
|  2     |  ... |



# Coverage of Scenarios and FR


<Report in the following table the coverage of  scenarios (from official requirements and from above) vs FR. 
Report also for each of the scenarios the (one or more) API JUnit tests that cover it. >




| Scenario ID | Functional Requirements covered | JUnit  Test(s) |
| ----------- | ------------------------------- | ----------- |
|  ..         | FR1                           | TestUpdateUserRights |
|  ..         |                          | TestUserListAddUser |
| ...         |                                 | EZShopTestCreateUser |
| ...         |                                 | EZShopTestDeleteUser |
| ...         |                                 | EZShopTestUpdateUserRights |
| ...         |                                 | EZShopTestGetAllUser |
|  | | EZShopTestGetUser |
|  | | TestUserListSetAuthenticatedUser |
|  | FR3+FR4(partial) | EZShopTestUpdateQuantity |
|  | | EZShopTestUpdateProduct |
|  | | EZShopTestUpdatePosition |
|  | | EZShopTestReturnProduct |
|  | | EZShopTestGetProductTypesByDescription |
|  | | EZShopTestGetProductTypeByBarCode |
|  | | EZShopTestGetAllProductTypes |
|  | | EZShopTestDeleteProductType |
|  | | EZShopTestCreateProductType |
|  | | TestAddProductType |
|  | | TestSearchProductByBarcode |
|  | | TestSearchProductByDescription |
|  | | TestSearchProductByID |
|  | | TestUpdatePosition |
|  | | TestUpdateProductType |
|  | FR4 (remaining (orders)) | EZShopTestRecordOrderArrival |
|  |  | EZShopTestPayOrderFor |
|  |  | EZShopTestPayOrder |
|  |  | EZShopTestIssueOrder |
|  |  | EZShopTestGetAllOrders |
|  |  | OrderListTestAddOrder |
|  |  | OrderListTestChangeOrderStatus |
|  |  | OrderListTestGetAllOrders |
|  |  | OrderListTestSearchOrderByFields |
|  |  | OrderListTestSearchOrderByID |
|  | FR5 | EZShopTestModifiyCustomer |
|  |  | EZShopTestDefineCustomer |
|  |  | EZShopTestAttachCardToCustomer |
|  |  | EZShopTestCreateCard |
|  |  | EZShopTestDeleteCustomer |
|  |  | EZShopTestGetAllCustomers |
|  |  | EZShopTestGetCustomer |
|  |  | EZShopTestModifiyCustomer |
|  |  | EZShopTestModifyPointsOnCard |
|  |  | CustomerListTestAddCustomer |
|  |  | CustomerListTestAttachCustomerCard |
|  |  | CustomerListTestDeleteCustomer |
|  |  | CustomerListTestGetCustomer |
|  |  | CustomerListTestGetCustomerList |
|  |  | CustomerListTestUpdateCustomer |
|  |  | LoyaltyCardListTestAddLoyaltyCard |
|  |  | LoyaltyCardListTestUpdatePointsOnCard |
|  |  | LoyaltyCardListTestAddLoyaltyCardIdAsArgoument |
|  |  | LoyaltyCardListTestDeleteLoyaltyCard |
|  |  | LoyaltyCardListTestSearchLoyaltyCardById |
|  | FR6 | EZShopTestStartSaleTransaction |
|  |  | EZShopTestStartReturnTransaction |
|  |  | EZShopTestGetSaleTransaction |
|  |  | EZShopTestEndSaleTransaction |
|  |  | EZShopTestDeleteSaleTransaction |
|  |  | EZShopTestApplyDiscountRateToSale |
|  |  | EZShopTestComputePointsForSale |
|  |  | EZShopTestAddProductToSale |
|  |  | EZShopTestApplyDiscountRateToProduct |
|  |  | EZShopTestDeleteReturnTransaction |
|  |  | EZShopTestEndReturnTransaction |
|  |  | EZShopTestStartReturnTransaction |
|  |  | ReturnTransactionListTestAddReturn |
|  |  | ReturnTransactionListTestCloseReturnTransaction |
|  |  | ReturnTransactionListTestDeleteReturnTransaction |
|  |  | SaleTransactionTestSetDiscountToProduct |
|  |  | ReturnTransactionListTestSearchReturnTransaction |
|  |  | SaleTransactionTestAddProduct |
|  |  | SaleTransactionTestDeleteProduct |
|  |  | SaleTransactionTestGettersSettersConstructors |
|  |  | SaleTransactionTestSetDiscountToProduct |
|  |  | SaleTransactionListTestAddSale |
|  |  | SaleTransactionListTestDeleteSale |
|  |  | SaleTransactionListTestGetClosedSale |
|  |  | SaleTransactionListTestSearchSale |
|  | FR7 | EZShopTestGetCreditsAndDebits |
|  |  | EZShopTestReceiveCashPayment |
|  |  | EZShopTestReceiveCreditCardPayement |
|  |  | EZShopTestReturnCashPayment |
|  |  | EZShopTestReturnCreditCarPayment |
|  | FR8 | AccountBookAddBalanceOperation |
|  |  | AccountBookDeleteBalanceOperation |
|  |  | AccountBookSetBalance |
|  |  | AccountBookTestGetAccountingList |
|  |  | AccountBookTestGetBalance |
|  |  | EZShopTestRecordBalanceUpdate |
|  |  | EZShopTestReceiveCreditCardPayement |
|  |  | EZShopTestComputeBalance |



# Coverage of Non Functional Requirements





### 

| Non Functional Requirement | Test name                           |
| -------------------------- | ----------------------------------- |
| NFR4                       | EZShopTestGetAllCustomers           |
|                            | EZShopTestCreateProductType         |
| NFR5                       | EZShopTestReceiveCreditCardPayement |
|                            | EZShopTestReturnCreditCarPayment    |
| NFR6                       | EZShopTestCreateCard                |
|                            |                                     |

