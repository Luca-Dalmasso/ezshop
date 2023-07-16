# Easy Shop
**Final project for the Software Engineering I course, MSc Computer Engineering, Politecnico di Torino**

**Authors** 
- 		 Dalmasso Luca s281316
 		 Kitou Mgbatou Osee Patrik s288425
 		 Mistruzzi Luca Guglielmo s292623
 		 Protopapa Matteo s290151

**Date: 21/04/2021**

**Version: 1.0**

Below is reported the content of the followind documents:

- [Requirements](./Deliverables/RequirementsDocument.md)
- [Design Document](./Deliverables/DesignDocument.md)
- [GUI Prototype](./Deliverables/GUIPrototype.md)
- [Estimation](./Deliverables/Estimation.md)
- Test Documents
  - [Unit Testing](./Deliverables/unitTestDocs/UnitTestReport.md)
  - [API Integration testing](./Deliverables/IntegrationAPITestReport.md)
  - [Acceptance Tests](./src/test/java/it/polito/ezshop/acceptanceTests/Testezshop20210528.java)

## Requirements

### Contents

- [Essential description](#essential-description)
- [Stakeholders](#stakeholders)
- [Context Diagram and interfaces](#context-diagram-and-interfaces)
	+ [Context Diagram](#context-diagram)
	+ [Interfaces](#interfaces) 
- [Essential description](#essential-description)
- [Stakeholders](#stakeholders)
- [Context Diagram and interfaces](#context-diagram-and-interfaces)
  - [Context Diagram](#context-diagram)
  - [Interfaces](#interfaces)
- [Stories and personas](#stories-and-personas)
    - [**John**](#john)
        - [Story 1](#story-1)
        - [Story 2](#story-2)
        - [Story 3](#story-3)
        - [Story 4](#story-4)
    - [**Marta**](#marta)
        - [Story 1](#story-1-1)
        - [Story 2](#story-2-1)
        - [Story 3](#story-3-1)
        - [Story 4](#story-4-1)
        - [Story 5](#story-5)
- [Functional and non functional requirements](#functional-and-non-functional-requirements)
  - [Functional Requirements](#functional-requirements)
  - [Non Functional Requirements](#non-functional-requirements)
- [Use case diagram and use cases](#use-case-diagram-and-use-cases)
  - [Use case diagram](#use-case-diagram)
    - [Manage a transaction, UC1](#manage-a-transaction-uc1)
      - [Scenario 1.1](#scenario-11)
      - [Scenario 1.2](#scenario-12)
      - [Scenario 1.3](#scenario-13)
      - [Scenario 1.4](#scenario-14)
      - [Scenario 1.5](#scenario-15)
      - [Scenario 1.6 variant](#scenario-16-variant)
    - [Manage a new fidelity card, UC2](#manage-a-new-fidelity-card-uc2)
      - [Scenario 1.1](#scenario-11-1)
    - [Manage product orders, UC3](#manage-product-orders-uc3)
      - [Scenario 1.1](#scenario-11-2)
      - [Scenario 1.2](#scenario-12-1)
      - [Scenario 1.3](#scenario-13-1)
      - [Scenario 1.4](#scenario-14-1)
    - [User Authentication, UC4](#user-authentication-uc4)
      - [Scenario 1.1](#scenario-11-3)
    - [Accounting status, UC5](#accounting-status-uc5)
      - [Scenario 1.1](#scenario-11-4)
      - [Scenario 1.2 - variant](#scenario-12---variant)
    - [System update, UC6](#system-update-uc6)
      - [Scenario 1.1](#scenario-11-5)
- [Glossary](#glossary)
- [System Design](#system-design)
- [Deployment Diagram](#deployment-diagram)

### Essential description

Small shops require a simple application to support the owner or manager. A small shop (ex a food shop) occupies 50-200 square meters, sells 500-2000 different item types, has one or a few cash registers 
EZShop is a software application to:
- manage sales
- manage inventory
- manage customers
- support accounting


### Stakeholders

| Stakeholder name    | Description |
| ---- | ---- |
| Company | the company that produces and sells the application   |
| Developer           |                               Who develops the application, and all the future relises                               |
| Maintainer          |                             Who updates the application whenever a new release is ready                              |
| Administrator       |                                                Who manages the users                                                 |
| Store Manager       |                                    Buyers of the application, owners of the store                                    |
| Cashier             |                         An employee who totals purchases and collects payment from customers                         |
| Warehouse worker    | employee responsible of receiving and processing incoming stock and materials, picking and filling orders from stock |
| Customer            |                                                  Buyers of products                                                  |
| Supplier            |                                          Who delivers products to the store                                          |
| Product             |                                                   Item to be sold                                                    |
| Cash                |                                       money in the form of coins or banknotes                                        |
| Payment card        |                                   a card that entitles a person to make purchases                                    |
| Payment terminal    |                                   the system to manage payment with a credit card                                    |
| Fidelity Card       |                              A card which rewards customers that make regular purchases                              |
| Internet Service Provider |       organization that provides internet service         |



### Context Diagram and interfaces

#### Context Diagram

```plantuml
@startuml context diagram

:Cashier: -d-> (System)
:Store Manager: -d-> (System)
:Administrator: -l-> (System)
:Payment terminal: -r-> (System)
:Product: -u-> (System)
:Fidelity Card: -u-> (System)
:Mainteiner: -u-> (System)

@enduml
```

#### Interfaces

| Actor            | Logical Interface | Physical Interface  |
| ---------------- | :---------------: | :-----------------: |
| Cashier          |        GUI        |     touchscreen     |
| Store Manager    |        GUI        | keyboard, mouse, PC |
| Administrator    |        GUI        | keyboard, mouse, PC |
| Mainteiner       |        GUI        | keyboard, mouse, PC |
| Product          |      barcode      |   barcode reader    |
| Payment terminal |   web services    |   ethernet cable    |
| Fidelity Card    |      barcode      |   barcode reader    |
| internet         |   web protocols   |    modem/router     |


#### Stories and personas

### **John**
John is the store manager of a small shop. He works 5 days a week (from Tuesday to Saturday), from 8.00 Am to 6.00 Pm. 

##### Story 1
Every morning John opens the shop, and goes to his office. Since he needs to check the inventory and the catalogue, he logs into the application to keep track of the products of the store, so that he can contact suppliers for delivering orders.

##### Story 2
Once a week, on Friday afternoon, John needs to manage the store's accounting to keep track of the incoming, using the PC.

##### Story 3
On wednesday a young lady appeared in the shop. She, after asking to cashier that wants to be registered with a fidelity card, is sent to John's office. Here John inserts the customer data into the application and gives the card to the customer so she can proceed to purchase some goods.

##### Story 4
On saturday usually a new stock of products is delivered to John's shops. John before leaving the office would like to update the previous order in the inventory.

### **Marta**
Marta is a young 22 yold software engineering student, *very good in math*, working part-time as cashier in the store.
During the morning she follows university lectures, after that, in the afternoon, she works at the store form 2.00 Pm to 7.30 Pm. At the end of the working day, since usually is the last employee in the shop, she closes the store.

##### Story 1
Marta as always is sitting in her working place at POS, while reading the customer's products for the checkout. After some products have been read correctly, one is not read by the barcode because it's scratched. So Marta has to insert it in the cash register manually using the digits on the product, as fast as possible, because others customers are waiting in queue.

##### Story 2
Marta is interrupted by a customer while she was doing the checkout for him. The customer says that he forgotten the wallet at home, apologizing for that he helps Marta to put aside the products then he leaves the store. Marta after moving the products to put them back on the shelves, deletes the shopping list from the checkout and starts a new one for the next customers.

##### Story 3
A clumsy customer, after counting the change for a few moments, realize that doesn't have enough money to pay his bill in cash so he tells it to cashier Marta. She proceeds to change the payment method and enables the credit card terminal to complete the purchase. After successful payment the customer greets her and leaves the store.

##### Story 4
Marta, tired for the long day at university, makes a mistake and scans a product two times in a row. She realize it watching the display of the POS indicating the total quantity of the last scanned product. To solve the inconvinient the cashier has to remove from the checkout by deleting the duplicate so she can keep scanning the barcode of others products.

##### Story 5
Mark, a loyal customer and a close friend of Marta, shows up at the POS with few products to pay. Marta that knows well Mark ask him immediatly his fidelity card and scan it with the barcode reader. Then Mark ask for a discout. Marta shows him his fidelity points balance and tells him that can have a 10€ discount. Mark acept it and proceeds to pay the store bill.



### Functional and non functional requirements

#### Functional Requirements


| ID        |                 Description                 |
| --------- | :-----------------------------------------: |
| **FR1**   |         **handle sale transaction**         |
| FR1.1     |            open sale transaction            |
| FR1.2     |     close sale transaction with success     |
| FR1.3     |           cancel sale transaction           |
| FR1.4     |         modify current transaction          |
| FR1.5     |               handle payment                |
| FR1.5.1   |             handle cash payment             |
| FR1.5.2   |        handle payment terminal data         |
| FR1.5.2.1 |                receive data                 |
| FR1.5.2.2 |                  send data                  |
| **FR2**   |          **read product barcode**           |
| FR2.1     |            retrieve product name            |
| FR2.2     |           retrieve product price            |
| **FR3**   |       **read fidelity card barcode**        |
| FR3.1     |          retrive customer account           |
| FR3.1.1   |                 read points                 |
| FR3.1.2   |               apply discount                |
| FR3.1.3   |                update points                |
| FR3.2     |      link barcode to customer account       |
| **FR4**   |       **manage user authentication**        |
| FR4.1     |               create account                |
| FR4.2     |               delete account                |
| FR4.3     |               modify account                |
| FR4.4     |                   log in                    |
| FR4.5     |                   log out                   |
| **FR5**   | **manage products inventory and catalogue** |
| FR5.1     |                 add product                 |
| FR5.1.1   |                add quantity                 |
| FR5.1.2   |       add purchase and selling price        |
| FR5.2     |               delete product                |
| FR5.3     |               update quantity               |
| FR5.4     |            update product price             |
| FR5.5     |               check quantity                |
| FR5.6     |               search product                |
| FR5.7     |            create products list             |
| FR5.8     |            delete products list             |
| FR5.9     |           add product to the list           |
| FR5.10    |        remove product from the list         |
| **FR6**   |         **handle suppliers order**          |
| FR6.1     |               insert an order               |
| FR6.2     |               delete an order               |
| FR6.3     |                 show orders                 |
| FR6.4     |               search an order               |
| FR6.5     |             change order status             |
| **FR7**   |           **support accounting**            |
| FR7.1     |             add a sold product              |
| FR7.1.1   |              add a date and id              |
| FR7.2     |           add a purchased product           |
| FR7.2.1   |              add a date and id              |
| FR7.3     |            search in accounting             |
| FR7.3.1   |        show income for a time period        |
| FR7.3.2   |      show outgoings for a time period       |
| FR7.3.3   |        show profit for a time period        |
| FR7.3.4   |       show income for a given product       |
| FR7.3.5   |     show outgoings for a given product      |
| FR7.3.6   |       show profit for a given product       |
| **FR8**   |             **manage updates**              |

#### Non Functional Requirements


| ID    | Type (efficiency, reliability, ..) |                                   Description                                   | Refers to |
| ----- | :--------------------------------: | :-----------------------------------------------------------------------------: | :-------: |
| NFR1  |             usability              |       a cashier should be capable to use the POS GUI in less than 2 hours       |    FR1    |
| NFR2  |             usability              |        a manager should be able to manage accounts in less than 2 hours         |    FR4    |
| NFR3  |             usability              | a manager should be able to manage inventory and catalogue in less than 2 hours |    FR5    |
| NFR4  |             usability              |        a manager should be able to manage orders GUI in less than 1 hour        |    FR6    |
| NFR5  |             usability              |      a manager should be able to manage accounting GUI in less than 1 hour      |    FR7    |
| NFR6  |             usability              |    a manager should be able to learn to update system in less than 10 minute    |    FR8    |
| NFR7  |             efficency              |                   a transaction manages at most 300 products                    |    FR1    |
| NFR8  |             efficency              |             a barcode and his information must be read in <= 200ms              |    FR2    |
| NFR9  |             efficency              |                    a fidelity card must be read in <= 200ms                     |    FR3    |
| NFR10 |             efficency              |                     a user must be able to login in <= 10s                      |   FR4.4   |
| NFR11 |             efficency              |                     a user must be able to logout in <= 3s                      |   FR4.5   |
| NFR12 |             efficency              |                operations on inventory must be done in <= 100ms                 |    FR5    |
| NFR13 |             efficency              |  the inventory and the catalogue must contains a range of 500 to 2000 products  |    FR5    |
| NFR14 |             efficency              |               operations on orders table must be done in <= 100ms               |    FR6    |
| NFR15 |             efficency              |                      accounting must be computed in <= 1s                       |    FR7    |
| NFR16 |             efficency              |                   system update must be computed in <= 1 hour                   |    FR8    |
| NFR17 |             efficency              |                         system update must be <= 50 MB                          |    FR8    |
| NFR18 |              Security              |                    server stores salted hashes of passwords                     |    FR4    |
| NFR19 |              Security              |             cashier passwords must be 8 alphanumeric caracters long             |    FR4    |
| NFR20 |              Security              |            manager passwords must be 16 alphanumeric caracters long             |    FR4    |
| NFR21 |            reliability             |            operations on the inventory fails at most 1 time per year            |    FR5    |
| NFR22 |            reliability             |                profits and outcomes errors must be <= 1 per year                |    FR7    |
| NFR23 |            reliability             |                       update errors must be <= 1 per year                       |    FR8    |
| DOM1  |               domain               |                                currency is Euro                                 |    FR1    |


### Use case diagram and use cases


#### Use case diagram


```plantuml

@startuml
top to bottom direction
rectangle System {
  (FR4.4 login) as (CASHERLOGIN)
  (FR4.4 login) as (SMLOGIN)
  (FR4.4 login) as (ADMINLOGIN)
  (FR4.4 login) as (MAINTEINERLOGIN)
  (FR1 hanlde sale transaction) as (FR1)
  (FR1.2 close sale transaction with success) as (FR1.2)
  (FR1.3 cancel sale transaction) as (FR1.3)
  (FR1.4 modify current transaction) as (FR1.4)
  (FR1.5 handle payment) as (FR1.5)
  (FR1.5.1 handle cash payment) as (FR1.5.1)
  (FR1.5.2 handle payment terminal data) as (FR1.5.2)
  (FR2 Read product barcode) as (FR2)
  (FR3 read fidelity card barcode) as (FR3)
  (FR4 manage user authentication) as (FR4)
  (FR5 manage inventory and catalogue) as (FR5)
  (FR5.3 update quantity) as (FR5.3)
  (FR6 handle orders) as (FR6)
  (FR7 support accounting) as (FR7)
  (FR7.1 add a sold product) as (FR7.1)
  (FR7.2 add a purchased product) as (FR7.2)
  (FR7.3 search in accounting) as (FR7.3)
  (FR8 manage updates) as (FR8)

  (CASHERLOGIN) ..> (FR1) :<<include>>
  (ADMINLOGIN) ..> (FR4) :<<include>>
  (MAINTEINERLOGIN) ..> (FR8) :<<include>>
  (SMLOGIN) ..> (FR5) :<<include>>
  (SMLOGIN) ..> (FR6) :<<include>>
  (SMLOGIN) ..> (FR7.3) :<<include>>
  (FR1) ..> (FR1.2) :<<include>>
    (FR1.2) ..> (FR1.5) :<<include>>
      (FR1.5) ..> (FR1.5.1) :<<include>>
      (FR1.5) ..> (FR1.5.2) :<<include>>
    (FR1.2) ..> (FR5.3) :<<include>>
      (FR5.3) <.. (FR5) :<<include>>
    (FR1.2) ..> (FR7.1) :<<include>>
      (FR7.1) <.. (FR7) :<<include>>
  (FR1) ..> (FR1.3) :<<include>>
  (FR1) ..> (FR1.4) :<<include>>
    (FR1.4) ..> (FR2) :<<include>>
  (FR1) ..> (FR3) :<<include>>
  (FR4) ..> (FR3) :<<include>>
  (FR6) ..> (FR2) :<<include>>
  (FR6) ..> (FR5.3) :<<include>>
  (FR6) ..> (FR7.2) :<<include>>
  (FR7) ..> (FR7.2) :<<include>>
  (FR7) ..> (FR7.3) :<<include>>

}

actor "Fidelity Card" as FCARD
actor "Payment terminal" as PT
actor "Store Manager" as GOD

Administrator <|-- GOD
Maintainer <|-- GOD
Cashier <|-- GOD
Cashier -d-> (CASHERLOGIN)
Administrator -d-> (ADMINLOGIN)
Maintainer -d-> (MAINTEINERLOGIN)
GOD -d-> (SMLOGIN)
Product <-- (FR2)
Product <-- (FR5)
FCARD <-- (FR3)
PT <-- (FR1.5.2)

@enduml

```


### Manage a transaction, UC1
| Actors Involved  |                                             Cashier, Product, Payment Card, Fidelity Card                                             |
| ---------------- | :-----------------------------------------------------------------------------------------------------------------------------------: |
| Precondition     |                                                the cashier should be logged in the POS                                                |
| Post condition   | the purchased products are added to the sold products table, the quantity of the products is updated, transaction concluded correctly |
| Nominal Scenario |                             the cashier scans products chosen by customer and closes current transaction                              |
| Variants         |              since the customer doesn't have enough credit on Payment Card, cashier needs to cancel current transaction               |

#### Scenario 1.1 


| Scenario 1.1   |                                                            transaction with cash payment                                                             |
| -------------- | :--------------------------------------------------------------------------------------------------------------------------------------------------: |
| Precondition   |                                                       the cashier should be logged in the POS                                                        |
| Post condition |        the purchased products are added to the sold products table, the quantity of the products is updated, transaction concluded correctly         |
| Step#          |                                                                     Description                                                                      |
| 1              |                                                  the cashier scan a product with the barcode reader                                                  |
| 2              |                                                           a new sale transaction is opened                                                           |
| 3              |                                                                a new list is created                                                                 |
| 4              |                                        the barcode reader retrive the name of the product from the catalogue                                         |
| 5              |                                        the barcode reader retrive the price of the product from the catalogue                                        |
| 6              |                                  the retrived information are added to the list and displayed it in the POS screen                                   |
| 7              |                                            steps 1 and 4 to 6 are repeated until all products are scanned                                            |
| 8              |                                   the POS computes the total amount of the list and displayed it in the POS screen                                   |
| 9              |                                            the chashier selects the cash payment method with the POS GUI                                             |
| 10             |                                      the cashier insert the amount of money gived by the customer with the GUI                                       |
| 11             |                                           the system evaluate the change and show it it on the POS screen                                            |
| 12             |                                                        the transaction is closed with success                                                        |
| 13             | for each product the quantity in the inventory is updated, a new record is inserted in the sold products table with the current date and a unique id |
| 14             |                                                                 the list is deleted                                                                  |

#### Scenario 1.2

| Scenario 1.2   |                                                          transaction with Payment Terminal                                                           |
| -------------- | :--------------------------------------------------------------------------------------------------------------------------------------------------: |
| Precondition   | the cashier should be logged in the POS , web services must be operative, credit card accepted by terminal (enough credit and pin digited correctly) |
| Post condition |        the purchased products are added to the sold products table, the quantity of the products is updated, transaction concluded correctly         |
| Step#          |                                                                     Description                                                                      |
| 1              |                                                  the cashier scan a product with the barcode reader                                                  |
| 2              |                                                           a new sale transaction is opened                                                           |
| 3              |                                                                a new list is created                                                                 |
| 4              |                                        the barcode reader retrive the name of the product from the catalogue                                         |
| 5              |                                        the barcode reader retrive the price of the product from the catalogue                                        |
| 6              |                                  the retrived information are added to the list and displayed it in the POS screen                                   |
| 7              |                                            steps 1 and 4 to 6 are repeated until all products are scanned                                            |
| 8              |                                   the POS computes the total amount of the list and displays it in the POS screen                                    |
| 9              |                                                         POS sends amount at payment terminal                                                         |
| 10             |                                         the chashier selects the credit card payment method with the POS GUI                                         |
| 11             |                                              the system receives a positive reply from payment terminal                                              |
| 12             |                                                        the transaction is closed with success                                                        |

#### Scenario 1.3

| Scenario 1.3   |                                             The cashier changes payment method from credit card to cash                                              |
| -------------- | :--------------------------------------------------------------------------------------------------------------------------------------------------: |
| Precondition   |                                       the cashier should be logged in the POS, web services must be operative                                        |
| Post condition |        the purchased products are added to the sold products table, the quantity of the products is updated, transaction concluded correctly         |
| Step#          |                                                                     Description                                                                      |
| 1              |                                                  the cashier scan a product with the barcode reader                                                  |
| 2              |                                                           a new sale transaction is opened                                                           |
| 3              |                                                                a new list is created                                                                 |
| 4              |                                        the barcode reader retrive the name of the product from the catalogue                                         |
| 5              |                                        the barcode reader retrive the price of the product from the catalogue                                        |
| 6              |                                  the retrived information are added to the list and displayed it in the POS screen                                   |
| 7              |                                            steps 1 and 4 to 6 are repeated until all products are scanned                                            |
| 8              |                                   the POS computes the total amount of the list and displayed it in the POS screen                                   |
| 9              |                                         the chashier selects the credit card payment method with the POS GUI                                         |
| 10             |                             the payment is refused by the terminal because there is not enough money in the credit card                              |
| 11             |                                               the cashier cancel payment card method with the POS GUI                                                |
| 12             |                                              the cashier selects cash payment method   with the POS GUI                                              |
| 13             |                                      the cashier insert the amount of money gived by the customer with the GUI                                       |
| 14             |                                           the system evaluate the change and show it it on the POS screen                                            |
| 15             |                                                        the transaction is closed with success                                                        |
| 16             | for each product the quantity in the inventory is updated, a new record is inserted in the sold products table with the current date and a unique id |
| 17             |                                                                 the list is deleted                                                                  |

#### Scenario 1.4 


| Scenario 1.4   |                                                     transaction with product inserted by mistake                                                     |
| -------------- | :--------------------------------------------------------------------------------------------------------------------------------------------------: |
| Precondition   |                   the cashier should be logged in the POS, a transaction should be opened, a product should be scanned by mistake                    |
| Post condition |        the purchased products are added to the sold products table, the quantity of the products is updated, transaction concluded correctly         |
| Step#          |                                                                     Description                                                                      |
| 1              |                                                   the cashier realizes that he/she made a mistake                                                    |
| 2              |                                            the cashier select the cancel product button from the POS GUI                                             |
| 3              |                                                     the cashier scans again the product barcode                                                      |
| 4              |                                                one occurence of the product is removed from the list                                                 |
| 5              |                                                  the cashier continues with the remaining products                                                   |
| 6              |                                   the POS computes the total amount of the list and displayed it in the POS screen                                   |
| 7              |                                            the chashier selects the cash payment method with the POS GUI                                             |
| 8              |                                      the cashier insert the amount of money gived by the customer with the GUI                                       |
| 9              |                                           the system evaluate the change and show it it on the POS screen                                            |
| 10             |                                                        the transaction is closed with success                                                        |
| 11             | for each product the quantity in the inventory is updated, a new record is inserted in the sold products table with the current date and a unique id |
|12             |                                                                 the list is deleted                                                                  |

#### Scenario 1.5

| Scenario 1.5   |                                                transaction with fidelity card discount                                                |
| -------------- | :-----------------------------------------------------------------------------------------------------------------------------------: |
| Precondition   |               the cashier should be logged in the POS , web services must be operative, enough points on fidelity card                |
| Post condition | the purchased products are added to the sold products table, the quantity of the products is updated, transaction concluded correctly |
| Step#          |                                                              Description                                                              |
| 1              |                                          the cashier scan a product with the barcode reader                                           |
| 2              |                                                   a new sale transaction is opened                                                    |
| 3              |                                                         a new list is created                                                         |
| 4              |                                 the barcode reader retrive the name of the product from the catalogue                                 |
| 5              |                                the barcode reader retrive the price of the product from the catalogue                                 |
| 6              |                           the retrived information are added to the list and displayed it in the POS screen                           |
| 7              |                                    steps 1 and 4 to 6 are repeated until all products are scanned                                     |
| 8              |                           the POS computes the total amount of the list and displayed it in the POS screen                            |
| 9              |                                 the customer asks how many points are currently on the fidelity card                                  |
| 10             |                              the chashier checks the points amount and the discount that can be applied                               |
| 11             |                      if discounts are possible, the chashier selects the fidelity card discount with the POS GUI                      |
| 12             |                                            the points are deducted from the fidelity card                                             |
| 13             |                                       the chashier selects the payment method with the POS GUI                                        |
| 14             |                                            the payment is managed as in previous scenarios                                            |

#### Scenario 1.6 variant

| Scenario 1.6   |                      deleted transaction for not enough money                       |
| -------------- | :---------------------------------------------------------------------------------: |
| Precondition   |  the cashier should be logged in the POS, the list of scanned products is correct   |
| Post condition | the transaction is deleted, the inventory and the sold products table do not change |
| Step#          |                                     Description                                     |
| 1              |   the POS computes the total amount of the list and displays it in the POS screen   |
| 2              |            the chashier selcts the cash payment method with the POS GUI             |
| 3              |      the transaction is deleted because the customer doesn't have enough money      |
| 4              |                                 the list is deleted                                 |


### Manage a new fidelity card, UC2
| Actors Involved  |                  Store Manager, Fidelity Card                   |
| ---------------- | :-------------------------------------------------------------: |
| Precondition     |   the store manager should be logged in the management system   |
| Post condition   |          a new fidelity card is assigned to a customer          |
| Nominal Scenario | the store manager collects customer data and gives him the card |


#### Scenario 1.1 

| Scenario 1.1   |                    new fidelity card for a customer                    |
| -------------- | :--------------------------------------------------------------------: |
| Precondition   |              the store manager already log in the system               |
| Post condition | a new customer account is created, the customer recive a fidelity card |
| Step#          |                              Description                               |
| 1              |                 the store manager create a new account                 |
| 2              |             the customer data are inserted in the account              |
| 3              |   the store manager scans a brand new fidelity card with the barcode   |
| 4              |             the barcode is associated with the new accout              |
| 5              |                 the customer recives the fidelity card                 |

### Manage product orders, UC3
| Actors Involved  |                                     Store Manager                                      |
| ---------------- | :------------------------------------------------------------------------------------: |
| Precondition     |              the store manager should be logged in the management system               |
| Post condition   |                   products with small quantity  have been reordered                    |
| Nominal Scenario | the store manager consults the list of products that are ending and places their order |
| Variants         |                       The store Manager wants to cancel an order                       |

#### Scenario 1.1

| Scenario 1.1  |                         the store manager wants to place an order                          |
| ------------- | :----------------------------------------------------------------------------------------: |
| Precondition  |                  the store manager should be log in the management system                  |
| Post conditon |                       A new order should be created for the supplier                       |
| Step#         |                                        Description                                         |
| 1             |         the store manager checks products quantity using the management system GUI         |
| 2             |             the store manager place an order  using the management system GUI              |
| 3             | the store manager repeats the step 1-2 untill all the finishing products have been ordered |

#### Scenario 1.2

| Scenario 1.1  |                           the store manager wants consults an order status                           |
| ------------- | :--------------------------------------------------------------------------------------------------: |
| Precondition  |                       the store manager should be log in the management system                       |
| Post conditon |                      The manager should know the date he will receive his order                      |
| Step#         |                                             Description                                              |
| 1             |                  the store manager search an order using the management system GUI                   |
| 2             | the store manager select among the results the order of his interest using the management System GUI |
| 3             |                       the store manage can have access to details of the order                       |

#### Scenario 1.3
| Scenario 1.1  |                   the store manager wants to cancel an order                   |
| ------------- | :----------------------------------------------------------------------------: |
| Precondition  |            the store manager should be log in the management system            |
| Post conditon |       An order should be remove from the orders' tables in the Database        |
| Step#         |                                  Description                                   |
| 1             | the store manager checks if an order is active using the management system GUI |
| 2             |       the store manager delete an order  using the management system GUI       |

#### Scenario 1.4

| Scenario 1.4  |                         the manager receives a product delivery from the  supplier                         |
| ------------- | :--------------------------------------------------------------------------------------------------------: |
| Precondition  |                         the store manager, should be log in the management system                          |
| Post conditon | The Order relative to the products received must change his status to completed from the System order list |
| Step#         |                                                Description                                                 |
| 1             |                     The store manager read a product barcode using the barcode reader                      |
| 2             |              the store Manager add the product in the Inventory using add quantity System GUI              |
| 3             |        the store manager  repeats steps 1-2 untill all the products are registered in the Inventory        |
| 4             |     the store manager search the order he just received from supplier using the management system GUI      |
| 5             |       the store manager changes the status of the order to completed using the management system GUI       |


### User Authentication, UC4

| Actors Involved  |               Administrator, Store Manager, Cashier               |
| ---------------- | :---------------------------------------------------------------: |
| Precondition     |                    User knows his credentials                     |
| Post condition   | User has access to the System  and the associated functionalities |
| Nominal Scenario |                     User uses his credentials                     |


#### Scenario 1.1

| Scenario 1.1   |          Administrator logs in, add new user          |
| -------------- | :---------------------------------------------------: |
| Precondition   |     Admin already logged in with his credentials      |
| Post condition |         New profile is added into the system          |
| Step#          |                      Description                      |
| 1              | Admin inserts User's datas and associated credentials |
| 2              |           Admin choses Users's  priviledges           |
| 3              |                  Admin adds new User                  |


### Accounting status, UC5

| Actors Involved  |                                                 Store Manager                                                 |
| ---------------- | :-----------------------------------------------------------------------------------------------------------: |
| Precondition     | the tables of purchased and sold products contain up to date information, the Store Manager already logged in |
| Post condition   |                        the system shows to the Store Manager the requested information                        |
| Nominal Scenario | the Store Manager applies some filters, the requested information are displayed on the management system GUI  |
| Variants         |       the filters applied don't retrive any information, an advice appears on the management system GUI       |


#### Scenario 1.1

| Scenario 1.1   |                                  the Store Manager wants to know today income for a specific product                                   |
| -------------- | :------------------------------------------------------------------------------------------------------------------------------------: |
| Precondition   |                      Store Manager already logged in, the tables of sold products contains up to date information                      |
| Post condition |                                          today's income for the selected product is displayed                                          |
| Step#          |                                                              Description                                                               |
| 1              |                                    the Store Manager select from the GUI menu the accounting option                                    |
| 2              |                                                        the search form appears                                                         |
| 3              | the Store Manager insert product name in the search field select the filter 'by date', insert today's date and press the search button |
| 4              |                                a list of possible product is displayed in a dropdown field of the form                                 |
| 5              |                                                   the Store Manager select a product                                                   |
| 6              |                           the Store Manager select the filter to be applied in a dropdown filed of the form                            |
| 7              |                                                 the Store Manager insert today's date                                                  |
| 8              |                                             the Store Manager select the income check box                                              |
| 9              |                                               the Store Manager press the search button                                                |
| 10             |                                        today's income is displayed in the system management GUI                                        |


#### Scenario 1.2 - variant

| Scenario 1.2   |            the Store Manager wants to know today income for a specific product, the product is not being sold today             |
| -------------- | :-----------------------------------------------------------------------------------------------------------------------------: |
| Precondition   | Store Manager already logged in, the tables of sold products contains up to date information, the form search already completed |
| Post condition |                              the system doesn't find valid informations and shows a warning advice                              |
| Step#          |                                                           Description                                                           |
| 1              |                                            the Store Manager press the search button                                            |
| 2              |                                      a warning advice appears on the system management GUI                                      |


### System update, UC6

| Actors Involved  |                                                    Maintainer                                                    |
| ---------------- | :--------------------------------------------------------------------------------------------------------------: |
| Precondition     | the system is not updated, the Maintainer is logged in the system, the system have an active internet connection |
| Post condition   |                     the system is updated to the last version and ready to use in login page                     |
| Nominal Scenario |                                  the Maintainer of the system checks for update                                  |


#### Scenario 1.1 

| Scenario 1.1   |                                 system update                                 |
| -------------- | :---------------------------------------------------------------------------: |
| Precondition   | the Maintainer already log in the system, the internet conncection is working |
| Post condition |                               system is updated                               |
| Step#          |                                  Description                                  |
| 1              |  the Maintainer open the update window and clicks on check for update button  |
| 2              |   the system contact the company's server to download the update installer    |
| 3              |        the system install automatically the updtate and restart itself        |


### Glossary

```plantuml
@startuml
left to right direction

class Store {
    name 
    address
    telephone number
}



class System {
    version
}

class Inventory {}

class Catalogue {}

class Product {
    name
}

class ProductDescriptor {
    barcode
    description
    price
}

class Supplier {
    name
    address
    telephone number
    email address
}

class Order {
    orderID 
    status: Cancelled, Pending, Delivered
}

class Person {
    name
    surname
}

class User {
    username 
    password
}

class Role {
    priviledges
}

class Cashier

class "Store Manager" as SM

class Administrator

class Maintainer

class Customer

class "Fidelity Card" as FC {
    card barcode
    points wallet

}

class Transaction {
    saleID
}

class "Payment Card" as PCARD {
    card number
    PIN
    balance
    expire date 
}

class Accounting {
    income 
    outgoings
    profit
}

class Update {
    dimension
    size
}

Store -- System
System -- Inventory
System -- Catalogue
System --"*" Update
Inventory -- "*" Product
Catalogue -- "*" ProductDescriptor
ProductDescriptor -- Product
System -- "*" User
User "0..1" -- "*" Role
Role <|-- Administrator 
Role <|-- Cashier
Role <|-- SM
Role <|-- Customer
Role <|-- Maintainer
Role -- "*" Transaction
Transaction "*" -- "0..1" PCARD

Transaction "*" -- "0..1" FC
Transaction "*" -- Person
User -- FC :if registered >
User -- "*" Transaction : Handle if Cashier >
User -- "*" Order :handle if Manager >
User -- Person :Profiled as >
Order "*" -- Supplier
Order -- "*" ProductDescriptor
User -- Inventory : handle if Store Manager >
User -- Accounting : handle if Store Manager >
Accounting -- "*" Transaction
User -- Update : handle if Administrator >
@enduml
```

### System Design


```plantuml
@startuml
object "System" as SYS
object "Point of sale" as POS
object "Barcode Reader" as BCODE
object "Printer" as PRINT
object "Computer" as PC 
Object "Application" as APP
Object "Web service" as WEB

SYS o-- POS
POS o-- BCODE
POS o-- PRINT
SYS o-- PC
PC -- WEB
PC -- APP
@enduml
```

### Deployment Diagram 


```plantuml
@startuml

scale 3/4
left to right direction

node "store server"as server {
    artifact "system management" as SYS{
        database database [
          inventory
          ----
          catalogue
          ====
          orders
          ====
          purchased product accounting
          ----
          sold product accounting
          ====
          accounts 
        ]
    }
}

node PC {
    artifact "system management GUI" as SGUI {
        artifact "accounting GUI"
        artifact "catalogue and inventory GUI"
        artifact "oreders GUI"
        artifact "account management GUI"
        artifact "update GUI"
    }
}

node POS {
    artifact GUI as PGUI1 {
        artifact "cashier GUI" as CA1
        artifact "customer GUI" as CU1
    }
}


server --- POS : "internet connection"
server --- PC : "internet connection"
PC -[hidden]r-- POS

@enduml

```

## Design Document

## Contents

- [Design Document](#design-document)
- [Contents](#contents)
- [Instructions](#instructions)
- [High level design](#high-level-design)
- [Low level design](#low-level-design)
  - [Package EZshopModel](#package-ezshopmodel)
- [Verification traceability matrix](#verification-traceability-matrix)
- [Verification sequence diagrams](#verification-sequence-diagrams)
  - [Sequence diagrams 1](#sequence-diagrams-1)
  - [Sequence diagrams 2](#sequence-diagrams-2)
    - [Sequence diagram 2.1](#sequence-diagram-21)
    - [Sequence diagram 2.2](#sequence-diagram-22)
  - [Sequence diagrams 3](#sequence-diagrams-3)
  - [Sequence diagrams 4](#sequence-diagrams-4)
    - [Sequence diagram 4.1](#sequence-diagram-41)
    - [Sequence diagram 4.2](#sequence-diagram-42)
  - [Sequence diagrams 5](#sequence-diagrams-5)
    - [Sequence diagram 5.1](#sequence-diagram-51)
  - [Sequence diagrams 6](#sequence-diagrams-6)
    - [Sequence diagram 6.2](#sequence-diagram-62)
  - [Sequence diagrams 7](#sequence-diagrams-7)
    - [Sequence diagram 7.1](#sequence-diagram-71)
  - [Sequence diagrams 8](#sequence-diagrams-8)
    - [Sequence diagram 8.2](#sequence-diagram-82)
  - [Sequence diagrams 9](#sequence-diagrams-9)
    - [Sequence diagram 9.1](#sequence-diagram-91)
  - [Sequence diagrams 10](#sequence-diagrams-10)
    - [Sequence diagram 10.2: return  cash payment](#sequence-diagram-102-return--cash-payment)

## Instructions

The design must satisfy the Official Requirements document, notably functional and non functional requirements

## High level design 

Two tiers architecture: Presentation and Logic/Data layer tied togheter

```plantuml
@startuml

top to bottom direction

package EZshopModel
package EZshopData
package EZshopExceptions
package EZshopGUI

EZshopData -up- EZshopGUI
EZshopData -- EZshopModel
EZshopModel <|-r- EZshopExceptions
EZshopData <|-- EZshopExceptions


@enduml
```

## Low level design

### Package EZshopModel

```plantuml
@startuml

scale 1.0
left to right direction

class User {
    -id : integer
    -name : String
    -password : String
    -role : String
    +User(integer id, String name, String password, String role)
    +int getID()
    +void setID(int id)
    +String getPassword()
    +void setPassword(String psw)
    +String getRole()
    +void setRole(String role)   
}

class UserList {
    -usersList : List<User>
    -authenticatedUser : User
    +UserList()
    +List<Users> getAllUsers()
    +Integer addUser(String username, String password, String role)
    +boolean deleteUser(Integer id)
    +Integer getAuthenticatedUser()
    +Boolean emptyAuthenticatedUser()
    +boolean setAuthenticatedUser(User use)
    +boolean updateUserRights (Integer id, String role)
}

class ProductTypeList {
    -productsList : List<ProductType>
    +ProductTypeList()
    +List<ProductType> getProductTypeList()
    +Integer addProductType(String description, String productCode, Double pricePerUnit, Double discountRate, String note, String position)
    +public boolean updateProduct(Integer id, String newDescription, String newCode, double newPrice, String newNote)
    +ProductType searchProductTypeByID(Integer productID)
    +ProductType searchProductTypeByBarCode(String barCode)
    +List<ProductType> searchProductTypeByDescription(String description)
    +boolean updatePosition(Integer productID, String position)
}

class CustomerList {
    -customerList : List<Customer>
    +CustomerList()
    +Customer getCustomer(Integer id)
    +List<Customer> getCustomerList()
    +Integer addCustomer(String customerName)
    +boolean updateCustomer(Integer id, String newCustomerName, String newCustomerCard)
    +boolean deleteCustomer(Integer customerID)
    +boolean attachCustomerCard((Integer id, String newCustomerCard)

}

class CardList {
    -cardList : List<LoyaltyCard>
    +CardList()
    +String addCard()
    +boolean deleteCard()
    +LoyaltyCard searchCardByID(String cardID)
    +boolean modifyPoints(String cardID, int pointsToBeAdded)

}

class CreditCardList {
    -cardList: List<CreditCard>
    +CreditCard searchCardByCode(String creditCardCode)
    +boolean checkCodeValidity(String creditCardCode)
    +boolean checkBalance(String creditCardCode, Double cost)
}

class CreditCard {
    -creditCardCode : String
    -balance : Double
    +CreditCard(String CreditCardCode, Double Balance)
    +String getCreditCardCode()
    +Double getBalance()
    +void setBalance(Double balance)
}

class SaleTransactionList {
    -saleTList : List<SaleTransaction>
    +SaleTransactionList()
    +Integer addSale()
    +SaleTransaction searchSale(Integer saleID)
    +SaleTransaction getClosedSale(Integer saleID)
    +boolean closeSale(Integer saleID)
    +boolean deleteSale(Integer saleID)
}

class ReturnTransactionList {
    -returnTList : List<ReturnTransaction>
    +ReturnTransactionList()
    +Integer addReturn(Integer transactionID)
    +ReturnTransaction searchReturn(Integer returnID)
    +boolean closeReturn(Integer returnID)
    +boolean deleteReturn(Integer returnID)
}

CreditCardList -- Shop
CreditCard "*" -- CreditCardList
ReturnTransactionList -- Shop
ReturnTransaction "*" -- ReturnTransactionList
SaleTransactionList -- Shop
SaleTransaction "*" -- SaleTransactionList
CardList -- Shop
LoyaltyCard "*" -- CardList
CustomerList -- Shop
Customer "*" -- CustomerList
ProductTypeList -- Shop
ProductType "*" -- ProductTypeList
User "*" -- UserList
UserList -- Shop


class Shop {
    -users : UserList
    -accountbook : AccountBook
    -inventory : ProductTypeList
    -orders : OrderList
    -customers : CustomerList
    -loyaltyCards : CardList
    -saleTransactions : SaleTransactionList
    -returnTransactions : ReturnTransactionList
    -creditCards : CreditCardList
    -positions : HashMap<String,boolean>
    -cardToCustomer : HashMap<CardID,CustomerID>


    +void reset()
    +Integer createUser(String username, String password, String role)
    +boolean deleteUser(Integer id)
    +List<User> getAllUsers()
    +User getUser(Integer id)
    +boolean updateUserRights(Integer id, String role)
    +User login(String username, String password)
    +boolean logout()
    +Integer createProductType(String description, String productCode, double pricePerUnit, String note)
    +boolean updateProduct(Integer id, String newDescription, String newCode, double newPrice, String newNote)
    +boolean deleteProductType(Integer id)
    +List<ProductType> getAllProductTypes()
    +ProductType getProductTypeByBarCode(String barCode)
    +List<ProductType> getProductTypesByDescription(String description)
    +boolean updateQuantity(Integer productId, int toBeAdded)
    +boolean updatePosition(Integer productId, String newPos)
    +Integer issueOrder(String productCode, int quantity, double pricePerUnit)
    +Integer payOrderFor(String productCode, int quantity, double pricePerUnit)
    +boolean payOrder(Integer transactionID)
    +boolean recordOrderArrival(Integer transactionID)
    +List<Order> getAllOrders()
    +Integer defineCustomer(String customerName)
    +boolean modifyCustomer(Integer id, String newCustomerName, String newCustomerCard)
    +boolean deleteCustomer(Integer id)
    +Customer getCustomer(Integer id)
    +List<Customer> getAllCustomers()
    +String createCard()
    +boolean attachCardToCustomer(String customerCard, Integer customerId)
    +boolean modifyPointsOnCard(String customerCard, int pointsToBeAdded)
    +Integer startSaleTransaction()
    +boolean addProductToSale(Integer transactionId, String productCode, int amount)
    +boolean deleteProductFromSale(Integer transactionId, String productCode, int amount)
    +boolean applyDiscountRateToProduct(Integer transactionId, String productCode, double discountRate)
    +boolean applyDiscountRateToSale(Integer transactionId, double discountRate)
    +int computePointsForSale(Integer transactionId)
    +boolean endSaleTransaction(Integer transactionId)
    +boolean deleteSaleTransaction(Integer transactionId)
    +SaleTransaction getSaleTransaction(Integer transactionId)
    +Integer startReturnTransaction(Integer transactionId)
    +boolean returnProduct(Integer returnId, String productCode, int amount)
    +boolean endReturnTransaction(Integer returnId, boolean commit)
    +boolean deleteReturnTransaction(Integer returnId)
    +double receiveCashPayment(Integer transactionId, double cash)
    +boolean receiveCreditCardPayment(Integer transactionId, String creditCard)
    +double returnCashPayment(Integer returnId)
    +double returnCreditCardPayment(Integer returnId, String creditCard)
    +boolean recordBalanceUpdate(double toBeAdded)
    +List<BalanceOperation> getCreditsAndDebits(LocalDate from, LocalDate to)
    +double computeBalance()
}

class AccountBook {
    -Accountinglist : List<BalanceOperation>
    -balance : Double
    +AccountBook()
    +List<BalanceOperation> getAccountinglist()
    +Double getBalance()
    +void setBalance(Double newBalance)
    +void addBalanceOperation(BalanceOperation transaction, String type, Double amount, Date date)
    +bool deleteBalanceOperation(Integer TransactionID)
}
AccountBook - Shop


class BalanceOperation {
    -transactionID : Integer
    -type : String
    -amount : Double
    -date : Date
    +BalanceOperation(Integer transactionID, String type, Double amount)
    +Integer getTransactionID()
    +String getType()
    +Double getAmount()
    +Date getDate()

}


AccountBook -- "*" BalanceOperation


class OrderList {
    -orders : List<Order>
    +OrderList()
    +List<Order> getOrders()
    +Integer addOrder(String productCode, int quantity, double pricePerUnit)
    +Order searchOrderByID(Intger transactionID)
    +Order searchOrderByFields(String productCode, int quantity, double pricePerUnit)
    +Boolean changeOrderStatus(String status)
}
OrderList -- Shop

class Order {
    -productCode : String
    -quantity : Integer
    -pricePerUnit : Double
    -state : String
    -orderId : Integer
    +Order(Integer transactionID, String productCode, Integer quantity, Double pricePerUnit)
    +String getProductCode()
    +Integer getQuantity()
    +Double getPricePerUnit()
    +String getState()
    +void setState(String state)
    +Integer getOrderId()
    +void setOrderId()
}
Order "*" -- OrderList

Order --|> BalanceOperation
SaleTransaction --|> BalanceOperation
ReturnTransaction --|> BalanceOperation


class ProductType{
    -productID : Integer
    -barCode : String
    -description : String
    -sellPrice : Double
    -quantity : Integer
    -discountRate : Double
    -notes : String
    -position : Position
    +ProductType(Integer, productID String description, String productCode, double pricePerUnit, String note)
    +Integer getProductID()
    +void setBarCode(String barCode)
    +String getBarCode()
    +void setDescription(String desc)
    +String getDescription()
    +void setSellPrice(Double price)
    +String getSellPrice()
    +void setQuantity(Integer qty)
    +String getQuantity()
    +void setDiscountRate(Double dRate)
    +String getDiscountRate()
    +void setNotes(Double dRate)
    +String getNotes()
    +void setPosition(Position position)
    +Position getPosition()
    +void updatePosition(String newPos)
}

class SaleTransaction {
    -entries : Map<productCode, (sellPrice, quantity)>
    -status : String
    -paymentType : String
    -discountRate : Double 
    +SaleTransaction(Integer saleID)
    +Integer getsaleID() 
    +String getStatus()
    +void setStatus(String status) 
    +Double getDiscountRate() 
    +void setDiscountRate(Double discount)   
    +void addProduct(String productCode, Double sellPrice, Integer quantity)
    +boolean deleteProduct(String productCode, Integer quantity)
    +Map<productCode, (sellPrice, cost)> getSaleList()
    +boolean setDiscountToProduct(String productCode, Double discount)
    +boolean modifyProductQuantity((String productCode, Integer quantity)   
}

SaleTransaction - "*" ProductType


class LoyaltyCard {
    -cardID : String
    -points : Integer
    +String getCardID()
    +void setCardID(String cardID)
    +Integer getPoints()
    +void setPoints(Integer points)
}

class Customer {
    -customerID : Integer
    -name : String
    -customerCard : String
    +Customer(Integer customerID, String name, String card)
    +Integer getCustomerID()
    +void setCustomerID(Integer customerID)
    +String getCustomerName()
    +void setCustomerName(String name)
    +String getCustomerCard()
    +void setCustomerCard(String card)
    +Integer getPoints()
    +void setPoints(Integer points)

}

LoyaltyCard "0..1" - Customer

SaleTransaction "*" -- "0..1" LoyaltyCard

Order "*" - ProductType

class ReturnTransaction {
    -saleTransactionID : Integer
    -status : String
    -productCode : String
    -quantity : Integer
    +ReturnTransaction(Integer transactionID, Integer saleTransactionID,Integer transactionID, String type, Double amount)
    +String getProductCode()
    +void setProductCode(String productCode)
    +Integer getSaleTransactionID()
    +void setSaleTransactionID(Integer saleID)
    +Integer getQuantity()
    +void getQuantity()
    +String getStatus()
    +void setStatus(String status)


}

ReturnTransaction "*" - SaleTransaction
ReturnTransaction "*" - ProductType

note "type as : CREDIT, ORDER, SALE, RETURN, DEBIT" as n1 
note "Facade class that implements\nthe EZshopInterface" as n2
note "inheritance of methods and\nattributes from BalanceOperation" as N3
N3 .. SaleTransaction
N3 .. BalanceOperation
N3 .. ReturnTransaction
N3 .. Order
n1 .. BalanceOperation
Shop .. n2

@enduml
```

## Verification traceability matrix

|       | Shop  | UserList  | User | ProductTypeList | ProductType | CustomerList | Customer | CardList | LoyaltyCard | CreditCardList | CreditCard | SaleTransactionList | SaleTransaction | ReturnTransactionList | ReturnTransaction | AccountBook | BalanceOperation | OrderList | Order |
| :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: | :-: |
| FR1    |x|x|x| | | | | | | | | | | | | | | | |
| FR3    |x|x|x|x|x| | | | | | | | | | | | | | |
| FR4    |x|x|x|x|x| | | | | | | | | | |x|x|x|x|
| FR5    |x|x|x| | |x|x|x|x| | | | | | | | | | |
| FR6    |x|x|x|x|x| | |x|x| | |x|x|x|x| | | | |
| FR7    |x|x|x| | | | | | |x|x| | | | | | | | |
| FR8    |x|x|x| | | | | | | | |x|x|x|x|x|x|x|x|

## Verification sequence diagrams 


### Sequence diagrams 1

```plantuml
@startuml

title Scenario 1.1: Create Product type X

actor ShopManager

ShopManager -> EZshopGUI : add a new product
EZshopGUI -> Shop: createProductType()
Shop -> ProductTypeList : addProductType()
ProductTypeList -> ProductType : ProductType()
ProductTypeList <- ProductType : return ProductType
Shop <- ProductTypeList : return ProductID
EZshopGUI <- Shop: return ProductID
ShopManager <- EZshopGUI : product successfully added

@enduml
```

### Sequence diagrams 2
#### Sequence diagram 2.1

```plantuml
@startuml

title Scenario 2.1: Create user and define rights

actor Administrator

Administrator -> EZshopGUI : define a new user\nand his rights
EZshopGUI -> Shop: createUser()
Shop -> UserList : addUser()
UserList -> User : User()
User -> UserList : return User
UserList -> Shop : return userID
EZshopGUI <- Shop : return userID
Administrator <- EZshopGUI : user successfully added

@enduml
```

#### Sequence diagram 2.2

```plantuml
@startuml

title Scenario 2.2: Delete user

actor Administrator

Administrator -> EZshopGUI : delete a user
EZshopGUI -> Shop: deleteUser()
Shop -> UserList : deleteUser()
UserList -> UserList : User removed\nfrom user list
UserList -> Shop : return true
EZshopGUI <- Shop : return true
Administrator <- EZshopGUI : user successfully deleted

@enduml
```

### Sequence diagrams 3

```plantuml
@startuml

title Scenario 3.1: Order of product type X issued

actor ShopManager

ShopManager -> EZshopGUI : define a new order in state ISSUED
EZshopGUI -> Shop: issueOrder()
Shop -> OrderList : addOrder()
OrderList -> Order : Order()
Order -> Order : setState(ISSUED)
Order -> OrderList : return Order
OrderList -> Shop : return orderID
EZshopGUI <- Shop : return orderID
ShopManager <- EZshopGUI : order successfully added

@enduml
```

```plantuml
@startuml

title Scenario 3.2: Order of product type X payed

actor ShopManager

ShopManager -> EZshopGUI : pay a previously issued order
EZshopGUI -> Shop: payOrder()
Shop -> OrderList : getOrderByID()
OrderList -> Shop :return Order
Shop -> Order : getState()
Order -> Shop : return "ISSUED"
Shop -> Order : getQuantity()
Order -> Shop : return quantity
Shop -> Order : getPricePerUnit()
Order -> Shop : return pricePerUnit
Shop -> AccountBook : getBalance()
AccountBook -> Shop : return balance
Shop -> Shop : check if pricePerUnit*quantity <= balance
Shop -> AccountBook : addBalanceOperation()
AccountBook -> BalanceOperation : BalanceOperation()
BalanceOperation -> AccountBook : return BalanceOperation
AccountBook -> Shop : return
Shop -> OrderList : changeOrderStatus()
OrderList -> Order : setStatus("PAYED")
Order -> OrderList : return
OrderList -> Shop : return true
EZshopGUI <- Shop : return true
ShopManager <- EZshopGUI : order successfully payed

@enduml
```

### Sequence diagrams 4

#### Sequence diagram 4.1

```plantuml
@startuml

title Scenario 4.1: Create customer record

actor ShopManager

Cashier -> EZshopGUI : define a new customer
EZshopGUI -> Shop: defineCustomer()
Shop -> CustomerList : addCustomer()
CustomerList -> Customer : Customer()
Customer -> CustomerList : return Customer
CustomerList -> Shop : return customerID
EZshopGUI <- Shop : return customerID
Cashier <- EZshopGUI : customer successfully added

@enduml
```

#### Sequence diagram 4.2

```plantuml
@startuml

title Scenario 4.2: Attach Loyalty card to customer record

actor Cashier

Cashier -> EZshopGUI : gives a new loyalty card\nto the customer
EZshopGUI -> Shop: attachCardToCustomer()
Shop -> Shop : check if card is not\nassociated to a customer\nwith cardToCustomer map
Shop -> CustomerList : attachCustomerCard()
CustomerList -> Customer: setCustomerCard()
Customer -> CustomerList: return
CustomerList -> Shop : return true
EZshopGUI <- Shop : return true
Cashier <- EZshopGUI : card successfully attached\nto a customer

@enduml
```

### Sequence diagrams 5

#### Sequence diagram 5.1

```plantuml
@startuml

title Scenario 5.1: Login

actor ShopManager

ShopManager -> EZshopGUI : signs in the shop application 
EZshopGUI -> Shop: login()
Shop -> UserList : getAllUsers()
UserList -> Shop : return List<User>
Shop -> Shop : check credentials\nand retrive user
Shop -> UserList : setAuthenticatedUser()
UserList -> Shop : return true
EZshopGUI <- Shop : return User
ShopManager <- EZshopGUI : successfully signed in

@enduml
```

### Sequence diagrams 6

#### Sequence diagram 6.2

```plantuml
@startuml

title Scenario 6.2: Sale of product type X with product discount

actor Cashier

Cashier -> EZshopGUI : wants to manage a sale transaction 
EZshopGUI -> Shop: startSaleTransaction()
Shop -> SaleTransactionList: addSale()
SaleTransactionList -> SaleTransaction: SaleTransaction()
SaleTransaction -> SaleTransactionList: return a SaleTransaction
SaleTransactionList -> Shop: return TransactionID
Shop -> EZshopGUI: return TransactionID
Cashier <- EZshopGUI : the sale transaction is opened
Cashier -> EZshopGUI : the cashier read the product barcode
EZshopGUI -> Shop: getProductTypeByBarCode()
Shop -> ProductTypeList: searchProductTypeByBarCode()
ProductTypeList -> Shop: return ProductType
Shop -> ProductType: getProductID()
ProductType -> Shop: return ProductID
Shop -> EZshopGUI: return ProductID
EZshopGUI -> Cashier: product found
Cashier -> EZshopGUI : inserts the product quantity
EZshopGUI -> Shop: addProductToSale()
Shop -> SaleTransactionList: searchSale()
SaleTransactionList -> Shop: return a SaleTransaction
Shop -> SaleTransaction: addProduct()
SaleTransaction -> Shop: return
Shop -> ProductType: setQuantity()
ProductType -> Shop: return
Shop -> EZshopGUI: return true
Cashier <- EZshopGUI : product successfully added\nto the sale transaction
Cashier -> EZshopGUI : applies a discount to product 
EZshopGUI -> Shop: applyDiscountRateToProduct()
Shop -> SaleTransaction: setDiscountToProduct()
SaleTransaction -> Shop: return true
Shop -> EZshopGUI: return true
Cashier <- EZshopGUI : discount successfully added to product
Cashier -> EZshopGUI : closes the sale transaction
EZshopGUI -> Shop: endSaleTransaction()
Shop -> SaleTransactionList: closeSale()
SaleTransactionList -> Shop: return true
Shop -> EZshopGUI: return true
Cashier <- EZshopGUI : transaction successfully closed
note right: payment and balance\nmanaged on sequence\ndiagram 7.1

@enduml
```

### Sequence diagrams 7

#### Sequence diagram 7.1

```plantuml
@startuml

title Scenario 7.1: Manage payment by valid credit card

actor Cashier

Cashier -> EZshopGUI : read a credit card\nto pay a sale transaction
EZshopGUI -> Shop: receiveCreditCardPayment()
Shop -> SaleTransactionList : getClosedSale()
Shop <- SaleTransactionList : return SaleTransaction
Shop -> SaleTransaction : getAmount()
Shop <- SaleTransaction : return amount
Shop -> CreditCardList : checkCodeValidity()
Shop <- CreditCardList : return true
Shop -> CreditCardList : checkBalance()
Shop <- CreditCardList : return true
Shop -> AccountBook : addBalanceOperation()
AccountBook -> AccountBook : update the balance\nand add the\n balance operation
Shop <- AccountBook : return
EZshopGUI <- Shop: return true
Cashier <- EZshopGUI : successful payment

@enduml
```

### Sequence diagrams 8

#### Sequence diagram 8.2

```plantuml
@startuml

title Scenario 8.2: Return transaction of product type X completed, cash

actor Cashier

Cashier -> EZshopGUI : inserts a transactionID
EZshopGUI -> Shop: startReturnTransaction()
Shop -> ReturnTransactionList: addReturn()
ReturnTransactionList -> ReturnTransaction: ReturnTransaction()
ReturnTransaction -> ReturnTransactionList: return a ReturnTransaction
ReturnTransactionList -> Shop: return returnID
Shop -> EZshopGUI: return returnID
EZshopGUI -> Cashier: return transaction\nsuccessfully opened
Cashier -> EZshopGUI : the cashier read the product barcode
EZshopGUI -> Shop: getProductTypeByBarCode()
Shop -> ProductTypeList: searchProductTypeByBarCode()
ProductTypeList -> Shop: return ProductType
Shop -> ProductType: getProductID()
ProductType -> Shop: return ProductID
Shop -> EZshopGUI: return ProductID
EZshopGUI -> Cashier: product found
Cashier -> EZshopGUI : inserts the product quantity
EZshopGUI -> Shop: returnProduct()
Shop -> ReturnSaleTransactionList: searchReturn()
ReturnSaleTransactionList -> Shop: return a ReturnTransaction
Shop -> ReturnTransaction: addProduct()
ReturnTransaction -> Shop: return
Shop -> ProductType: setQuantity()
ProductType -> Shop: return
Shop -> EZshopGUI: return true
Cashier <- EZshopGUI : product successfully returned
note right: cash return managed\non sequence diagram 10.1
Cashier -> EZshopGUI : cashier close the return transaction
EZshopGUI -> Shop: endReturnTransaction()
Shop -> ReturnSaleTransactionList: closeReturn()
ReturnSaleTransactionList -> Shop: return true
Shop -> SaleTransactionList: searchSale()
SaleTransactionList -> Shop: return a SaleTransaction
Shop -> SaleTransaction: modifyProductQuantity()
SaleTransaction -> Shop: return true
Shop -> EZshopGUI: return true
Cashier <- EZshopGUI : sale transaction and balance updatated

@enduml
```

### Sequence diagrams 9

#### Sequence diagram 9.1

```plantuml
@startuml
title Scenario 9.1: List credits and debits
actor ShopManager
ShopManager -> EZshopGUI : retrieve list of BalanceOperation
EZshopGUI -> Shop : getCreditsAndDebits()
Shop -> AccountBook : getAccountinglist()
AccountBook -> Shop : return List<BalanceOperation>
Shop -> Shop : filter BalanceOperation List by dates
Shop -> EZshopGUI : return filtered BalanceOperation List
EZshopGUI -> ShopManager : print list
@enduml
```

### Sequence diagrams 10

#### Sequence diagram 10.2: return  cash payment

```plantuml
@startuml
actor ShopManager
ShopManager -> EZshopGUI : record cash return
EZshopGUI -> Shop: returnCashPayment()
Shop -> ReturnTransactionList : searchReturn()
ReturnTransactionList -> Shop: return a ReturnTransaction
Shop -> ReturnTransaction: getAmount()
ReturnTransaction -> Shop: return amount
Shop -> AccountBook : addBalanceOperation()
AccountBook -> AccountBook : update the balance\nand add the\n balance operation
Shop <- AccountBook : return
Shop -> EZshopGUI: return amount
ShopManager <- EZshopGUI : return the cash to the customer
@enduml
```

## GUI Prototype

This is the GUI of the POS: [cashierGUI.pdf](./Deliverables/cashierGUI.pdf)

This is the GUI of the application: [ezshopGUI.pdf](./Deliverables/ezshopGUI.pdf)

## Estimation

## Contents
- [Project Estimation](#project-estimation)
- [Contents](#contents)
- [Estimation approach](#estimation-approach)
  - [Estimate by product decomposition](#estimate-by-product-decomposition)
    - [PBS table](#pbs-table)
  - [Estimate by activity decomposition](#estimate-by-activity-decomposition)
    - [WBS table](#wbs-table)
    - [Gantt chart](#gantt-chart)
## Estimation approach

### Estimate by product decomposition

### PBS table
|                                                                                                         | Estimate       |
| ------------------------------------------------------------------------------------------------------- | -------------- |
| NC =  Estimated number of classes to be developed                                                       | 40             |
| A = Estimated average size per class, in LOC                                                            | 50             |
| S = Estimated size of project, in LOC (= NC * A)                                                        | 2000           |
| E = Estimated effort, in person hours (here use productivity 10 LOC per person hour)                    | 200            |
| C = Estimated cost, in euro (here use 1 person hour cost = 30 euro)                                     | 3000           |
| Estimated calendar time, in calendar weeks (Assume team of 4 people, 8 hours per day, 5 days per week ) | 6 days, 2 hour |

## Estimate by activity decomposition

### WBS table
| Activity name        | Estimated effort (person hours) |
| -------------------- | ------------------------------- |
| Requiriment document | 80                              |
| GUI prototype design | 20                              |
| Design document      | 50                              |
| Coding               | 50                              |
| Testing              | 20                              |

### Gantt chart

```plantuml

@startgantt
Project starts 2021-04-01
saturday are close
sunday are close
then [Requiriment document] lasts 6 days
then [GUI prototype design] lasts 2 days
then [Design document] lasts 4 days
then [Coding                   ] lasts 4 days
then [Testing] lasts 4 days

[Requiriment document] ends 2021-04-08
[GUI prototype design] ends 2021-04-12
[Design document] ends 2021-04-16
[Coding                   ] ends 2021-04-22
[Testing] ends 2021-04-23
@endgantt

```

## TimeSheet

Report in this table the effort (in person hours) spent per week, per activity, per team. 
[One person hour is the effort of one person working one hour.
Two person hours is the effort of one person working 2 hours, or 2 persons woring one hour. And so on.]
You have to sum the working hours of each component of the team.
Count only effort spent on EZShop (so do not count effort spent in virtual labs, studying, lessons)

These figures will not be used to evaluate neither the project nor the team, they are meant as practical application of basic project management.

| Week | requirement engineering | design | coding | unit testing | integration testing | acceptance testing | management | git maven |
|:-----------:|:--------:|:-----------:|:-----------:|:----------:|:------------:|:---------------:|:-------------:|:--------------:|
| apr 5 - 11 | 12h | | | | | | | |
| apr 12 - 18| 46.5h | | | | | | | |
| apr 19 - 25| 39h | | | | | | | |
| apr 26 - 2 | | 57h | | | | | | |
| may 3 - 9  | | | 21h | | 3h | | | |
| may 10 - 16| | | 50h | 4h | 2h | | | |
| may 17 - 23| | | 10h | 4.5h | | | | |
| may 24 - 30| | | | | 57.5h | | | |
| jun 31 - 6 | | | | | | 3h | | |
| jun 7 - 13 | | | | | | | | |
| jun 14 -20 | | | | | | | | |
| jun 21 -27 | | | | | | | | |
| jun 28 - 4 | | | | | | | | |
| jul 5 - 11 | | | | | | | | |
| jul 12 -18 | | | | | | | | |
| jul 19 -25 | | | | | | | | |

## Change - RFID

Each product has an RFID. RFID is a positive integer (received as a 10 characters string). The RFID is an identifier, as such is
unique (no two products can have the same RFID). The RFID is defined outside EzShop (EzShop application receives the RFID and is not supposed to produce them).

```plantuml
class ProductType{
    barCode
    description
    sellPrice
    quantity
    discountRate
    notes
}

class Product{
     RFID
}
ProductType -- "*" Product : describes
```

### Sale of a product
When a product is sold an RFID reader reads the RFID, no bar code reader is used. From the RFID 
the application retrieves the product type of the product, and all related information (like the price).
The sale transaction records each product sold. 
    see addProductToSaleRFID()   on API

### Reception of an order
When an order is received, each product received must be recorded, along with its RFID. 
    see recordOrderArrivalRFID()   on API

### Return of a product
When a product is returned, its RFID is read, the product is re inserted in the inventory. 
    see functions deleteProductFromSaleRFID(), returnProductRFID()   on API


## Tests

### Unit Testing Documentation

#### Contents

- [Black Box Unit Tests](#black-box-unit-tests)

- [White Box Unit Tests](#white-box-unit-tests)


#### Black Box Unit Tests

 #### **Class *SQLiteJDBC* - method *maxID***



**Criteria for method *maxID*:**
	

 - existance of tuples in DB tables(Accounting/SaleTransactions/ReturnTransactions/Orders)
 - 





**Predicates for method *maxID*:**

| Criteria | Predicate |
| -------- | --------- |
|     existance of tuples in DB tables     |      exists     |
|          |      not exists     |





**Boundaries**:

| Criteria | Boundary values |
| -------- | --------------- |
|     existance of tuples in DB tables     |        0, +inf         |




**Combination of predicates**:


| Criteria 1 | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|
|not exists| Valid |T() -> true| testMaxIDWithEmptyDB() |
|exists| Valid |T() -> true|testMaxIDWithDataOnDB()|


 #### **Class *SQLiteJDBC* - method *insert***



**Criteria for method *insert*:**
	

 - existance of row in DB with given id
 - existance of table name in DB
 - correctness of args number for given table name 





**Predicates for method *insert*:**

| Criteria | Predicate |
| -------- | --------- |
|     existance of row in DB with given id     |     exists      |
|          |     not exists       |
|    existance of table name in DB      |      exists     |
|          |    not exists        |
|     correctness of args number for given table name     |     {2, 3, 4, 7, 8} -> valid      |
|          |      invalid     |





**Boundaries**:

| Criteria | Boundary values |
| -------- | --------------- |
|    existance of row in DB with given id      |        0, 1         |
|    existance of table name in DB      |         0, 1        |
|    correctness of args number for given table name  |   0, 2, 3, 4, 7, 8 +inf    |


**Combination of predicates**:


| Criteria 1 | Criteria 2 | Criteria 3 | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|-------|-------|
| not exists | exists | valid|Valid|test for each table the insert -> true|testInsertRow()|
| exists | exists | valid |Invalid|test for each table the insert -> false|testRowAlreadyExist()|
| not exists | exists | invalid |Invalid|test for each table the insert -> false|testInsertWrongNumberOfParams()|
| * | not exists | * |Invalid|T("FakeTableName", 9999, "Name") -> false| testTableNameNotExist() |


 #### **Class *SQLiteJDBC* - method *delete***



**Criteria for method *delete*:**
	

 - existance of row in DB with given id
 - existance of table name in DB 





**Predicates for method *name*:**

| Criteria | Predicate |
| -------- | --------- |
|     existance of row in DB with given id     |     exists      |
|          |     not exists       |
|    existance of table name in DB      |      exists     |
|          |    not exists        |





**Boundaries**:

| Criteria | Boundary values |
| -------- | --------------- |
|    existance of row in DB with given id      |        0, 1         |
|    existance of table name in DB      |         0, 1        |



**Combination of predicates**:


| Criteria 1 | Criteria 2 | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|-------|
|exists|exists|Valid|test for each table the delete -> true|testDeleteRow()|
|not exists|exists|Valid|test for each table the delete -> false|testDeleteRowDoesntExist()|
|*|not exists|Invalid|T("FakeTableName", 9999) -> false|testTableNameNotExist()|


 #### **Class *SQLiteJDBC* - method *update***



**Criteria for method *update*:**
	

 - existance of table name in DB
 - correctness of args number for given table name 





**Predicates for method *name*:**

| Criteria | Predicate |
| -------- | --------- |
|    existance of table name in DB      |      exists     |
|          |    not exists        |
|     correctness of args number for given table name     |     {2, 3, 4, 7, 8} -> valid      |
|          |      invalid     |





**Boundaries**:

| Criteria | Boundary values |
| -------- | --------------- |
|    existance of table name in DB      |         0, 1        |
|    correctness of args number for given table name  |   0, 2, 3, 4, 7, 8 +inf    |



**Combination of predicates**:


| Criteria 1 | Criteria 2 | Valid / Invalid | Description of the test case | JUnit test case |
|-------|-------|-------|-------|-------|
| exists | valid |Valid|test for each table the update -> true|testUpdateRow()|
| exists | invalid |Invalid|test for each table the update -> false|testUpdateWrongNumberOfParams()|
| not exists | * |Invalid|T("FakeTableName", 9999, "Name") -> false| testTableNameNotExist() |


### White Box Unit Tests

#### Test cases definition


| Unit name | JUnit test case |
|--|--|
|BalanceOperation|BalanceOperationTest|
|CreditCard|CreditCardTest|
|Customer|CustomerTest|
|LoyaltyCard|LoyaltyCardTest|
|Order|OrderTest|
|ProductType|ProductTypeTest|
|ReturnTransaction|ReturnTransactionTest|
|SaleTransaction|SaleTransactionTest|
|TicketEntry|TicketEntryTest|
|User|UserTest|

#### Code coverage report

![coverage report](./Deliverables/unitTestDocs/unitTestReport.png)

### Integration and API Test Documentation

#### Contents

- [Dependency graph](#dependency graph)

- [Integration approach](#integration)

- [Tests](#tests)

- [Scenarios](#scenarios)

- [Coverage of scenarios and FR](#scenario-coverage)
- [Coverage of non-functional requirements](#nfr-coverage)



#### Dependency graph 

[GraphImage](./Deliverables/ezshop.png)

#### Integration approach

    Bottom up
    1) UnitTest: WhiteBox (also on DB) full coverage
    2) Integration: WhiteBox on SaleTransactionLIst
    3) Integration: BlackBox on all other lists
    4) Integration: BlackBox on API



####  Tests

   <define below a table for each integration step. For each integration step report the group of classes under test, and the names of
     JUnit test cases applied to them> JUnit test classes should be here src/test/java/it/polito/ezshop

##### Step 1
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


##### Step 2
| Classes  | JUnit test cases |
|--|--|
| TestSuiteSaleTransaction | SaleTransactionTestAddProduct                 |
|                          | SaleTransactionTestDeleteProduct              |
|                          | SaleTransactionTestGettersSettersConstructors |
|                          | SaleTransactionTestSetDiscountToProduct       |


##### Step 3 

   

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

##### Step 4

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

### Scenarios


<If needed, define here additional scenarios for the application. Scenarios should be named
 referring the UC in the OfficialRequirements that they detail>

#### Scenario UCx.y

| Scenario |  name |
| ------------- |:-------------:|
|  Precondition     |  |
|  Post condition     |   |
| Step#        | Description  |
|  1     |  ... |
|  2     |  ... |



### Coverage of Scenarios and FR


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



### Coverage of Non Functional Requirements
 

| Non Functional Requirement | Test name                           |
| -------------------------- | ----------------------------------- |
| NFR4                       | EZShopTestGetAllCustomers           |
|                            | EZShopTestCreateProductType         |
| NFR5                       | EZShopTestReceiveCreditCardPayement |
|                            | EZShopTestReturnCreditCarPayment    |
| NFR6                       | EZShopTestCreateCard                |
|                            |                                     |

