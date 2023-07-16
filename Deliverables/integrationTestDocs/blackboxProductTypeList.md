
# Unit Testing Documentation template

Authors: <br>
Luca Dalmasso s281316 <br>
Kitou Mgbatou Osee Patrik s288425 <br>
Mistruzzi Luca Guglielmo s292623 (old: s235744)<br>
Protopapa Matteo s290151 <br>              
Date: 23/05/2021

<br>

Version:


# Black Box Unit Tests

**Criteria for method $addProductType$:**

- validity of object description
- length of object description
- validity of object pricePerUnit
- sign of object procePerUnit
- Validity of object position 
- uniqueness of object productCode
- uniqueness of object description

<br>
<br>

**Predicates for method $addProductType$:**

| Criterion                        | Predicate  |
| -------------------------------- | ---------- |
| validity of object description   | valid      |
|                                  | null       |
| length of object description     | =0         |
|                                  | >0         |
| validity of object pricePerUnit  | valid      |
|                                  | null       |
| sign of object procePerUnit      | >0         |
|                                  | <=0        |
| Validity of object position      | valid      |
|                                  | Null       |
| uniqueness of object productCode | unique     |
|                                  | not unique |

<br>
<br>

**Boundaries for method $addProductType$**:

| Criterion | Boundary values |
| --------- | --------------- |
|           |                 |
|           |                 |

<br>
<br>

 **Combination of predicates for method $addProductType$**

| validity of object<br> description | length of object <br>description | validity of object <br>pricePerUnit | sign of object <br>procePerUnit | uniqueness <br>of object productCode | uniqueness<br> of object description | Valid/Invalid | Description of the test case   <br> Example of usage         | JUnit test case      |
| :--------------------------------- | -------------------------------- | ----------------------------------- | ------------------------------- | ------------------------------------ | ------------------------------------ | ------------- | ------------------------------------------------------------ | -------------------- |
| null                               | *                                | *                                   | *                               | *                                    | *                                    | invalid       | T(null,*,*,*,*)->InvalidProductDescriptionException          | testNullDescription  |
| *                                  | 0                                | *                                   | *                               | *                                    | *                                    | invalid       | T("",*,*,*,*)->InvalidProductDescriptionException            | testEmptyDescription |
| *                                  | *                                | null                                | *                               | *                                    | *                                    | invalid       | T(*,*,null,*,*)->InvalidPricePerUnitException                | testNullPrice        |
| *                                  | *                                | *                                   | <=0                             | *                                    | *                                    | invalid       | T(*,*,0,*,*)->InvalidPricePerUnitException<br>T(*,*,-1,*,*)->InvalidPricePerUnitException | testNegPrice         |
| valid                              | >0                               | valid                               | >0                              | unique                               | unique                               | Valid         | T("whatever","whatever1",1.00,"whatever","")->return >0<br>T("whatever1","whatever2",1.00,","whatever")->return >0<br>T("whatever2","whatever3",1.00,"","")->return >0<br>T("whatever","whatever4",1.00,null,"")->return>0 && product.description == "" | testValidProductType |
| *                                  | *                                | *                                   | *                               | not unique                           | *                                    | invalid       | T("description1","code",1.00,"whatever","")<br/>T("description2","code",1.00,"whatever","")->return -1 | testDuplicatedCode   |

<br>

<br>

**Criteria for method $searchProductTypeByID$:**

- validity of object productID
- sign of object productID

<br>
<br>

**Predicates for method $searchProductTypeByID$:**

| Criterion                    | Predicate |
| ---------------------------- | --------- |
| validity of object productID | valid     |
|                              | null      |
| sign of object productID     | <=0       |
|                              | >0        |

<br>
<br>

**Boundaries for method $searchProductTypeByID$**:

| Criterion | Boundary values |
| --------- | --------------- |
|           |                 |
|           |                 |

<br>
<br>

 **Combination of predicates for method $searchProductTypeByID$**

| validity of object productID | sign of object productID | Valid/Invalid | Description of the test case   <br> Example of usage         | JUnit test case |
| :--------------------------- | ------------------------ | ------------- | ------------------------------------------------------------ | --------------- |
| null                         | *                        | invalid       | T(null)->InvalidProductIdException                           | testNullID      |
| *                            | <=0                      | invalid       | T(0)->InvalidProductIdException<br>T(-1)->InvalidProductIdException | testNegID       |
| valid                        | >0                       | valid         | T(1)->return null<br>ADD ->("whatever","whatever1",1.00,"whatever","") T(1)->return != null | testValidSearch |

<br><br>

**Criteria for method $searchProductTypeByBarCode$:**

- validity of object barcode
- length of object barcode

<br>
<br>

**Predicates for method $searchProductTypeByBarCode$:**

| validity of object productID | sign of object productID |
| ---------------------------- | ------------------------ |
| validity of object barcode   | valid                    |
|                              | null                     |
| length of object barcode     | =0                       |
|                              | >0                       |

<br>
<br>

**Boundaries for method $searchProductTypeByBarCode$**:

| Criterion | Boundary values |
| --------- | --------------- |
|           |                 |
|           |                 |

<br>
<br>

 **Combination of predicates for method $searchProductTypeByBarCode$**

| validity of object barcode | length of object barcode | Valid/Invalid | Description of the test case   <br> Example of usage         | JUnit test case |
| :------------------------- | ------------------------ | ------------- | ------------------------------------------------------------ | --------------- |
| null                       | *                        | invalid       | T(null)->InvalidProductCodeException                         | testNullCode    |
| *                          | =0                       | invalid       | T("")->InvalidProductCodeException<br>                       | testEmptyCode   |
| valid                      | >0                       | valid         | T("whatever1")->return null<br>ADD ->("whatever","whatever1",1.00,"whatever","") T("whatever1")->return != null | testValidSearch |

<br><br>

**Criteria for method $searchProductTypeByDescription$:**

- validity of object description
- length of object description

<br>
<br>

**Predicates for method $searchProductTypeByDescription$:**

| validity of object productID   | sign of object productID |
| ------------------------------ | ------------------------ |
| validity of object description | valid                    |
|                                | null                     |
| length of object description   | =0                       |
|                                | >0                       |

<br>
<br>

**Boundaries for method $searchProductTypeByDescription$**:

| Criterion | Boundary values |
| --------- | --------------- |
|           |                 |
|           |                 |

<br>
<br>

 **Combination of predicates for method $searchProductTypeByDescription$**

| validity of object description | length of object description | Valid/Invalid | Description of the test case   <br> Example of usage         | JUnit test case      |
| :----------------------------- | ---------------------------- | ------------- | ------------------------------------------------------------ | -------------------- |
| null                           | *                            | Valid         | T(null)->no exception should be thrown                       | testNullDescription  |
| *                              | =0                           | Valid         | 1) create a product with object note == null, so that a new product with empty description is created: addProductType("whatever","whatever4",1.00,null,"")<br>T("")->return list with length > 0 | testEmptyDescription |
| valid                          | >0                           | valid         | T("a")->return list with length == 0<br>ADD ->("a","x",1.00,"whatever","") T("whatever1")->return list with length > 0 | testValidSearch      |



**Criteria for method $updateProduct$:**

- validity of object productID
- sign of object productID
- validity of object description
- length of object description
- sign of object pricePerUnit

<br>
<br>

**Predicates for method $updateProduct$:**

| validity of object productID   | sign of object productID |
| ------------------------------ | ------------------------ |
| validity of object productID   | valid                    |
|                                | null                     |
| sign of object productID       | <=0                      |
|                                | >0                       |
| validity of object description | valid                    |
|                                | null                     |
| length of object description   | =0                       |
|                                | >0                       |
| sign of object pricePerUnit    | <=0                      |
|                                | >0                       |

<br>
<br>

**Boundaries for method $updateProduct$**:

| Criterion | Boundary values |
| --------- | --------------- |
|           |                 |
|           |                 |

<br>
<br>

 **Combination of predicates for method $updateProduct$**

| validity of <br>object productID | sign of <br>object productID | validity of <br>object description | length of <br>object description | sign of <br>object pricePerUnit | Valid/Invalid | Description of the test case   <br> Example of usage         | JUnit test case      |
| :------------------------------- | ---------------------------- | ---------------------------------- | -------------------------------- | ------------------------------- | ------------- | ------------------------------------------------------------ | -------------------- |
| null                             | *                            | *                                  | *                                | *                               | invalid       | T(null,*,*,*,*)->InvalidProductIdException                   | testNullProductID    |
| *                                | <=0                          | *                                  | *                                | *                               | invalid       | T(0,*,*,*)->InvalidProductIdException                        | testNegProductID     |
| *                                | *                            | null                               | *                                | *                               | invalid       | T(*,null,*,*,*)->InvalidProductDescriptionException          | testNullDescription  |
| *                                | *                            | *                                  | 0                                | *                               | invalid       | T(*,"",*,*,*)->InvalidProductDescriptionException            | testEmptyDescription |
| *                                | *                            | *                                  | *                                | *                               | invalid       | T(*,*,*,0,*)-> InvalidPricePerUnitException                  | testNegPricePerUnit  |
| valid                            | >0                           | valid                              | >0                               | >0                              | valid         | T(1,"a","1",1.00,null)->return false<br>addProduct("a","1",1.00,null,null)<br>T(1,"a","1",1.00,null)-> return true | testValidUpdate      |

<br>

<br>

**Criteria for method $updatePosition$:**

- validity of object productID
- Sign of object productID
- validity of object position
- length of object position
- correct format of object position
- position is unique

<br>
<br>

**Predicates for method $updatePosition$:**

| validity of object productID      | valid      |
| --------------------------------- | ---------- |
|                                   | null       |
| Sign of object productID          | =0         |
|                                   | >0         |
| validity of object position       | valid      |
|                                   | null       |
| length of object position         | =0         |
|                                   | >0         |
| correct format of object position | true       |
|                                   | False      |
| Position is unique                | unique     |
|                                   | not unique |

<br>
<br>

**Boundaries for method $updatePosition$**:

| Criterion | Boundary values |
| --------- | --------------- |
|           |                 |
|           |                 |

<br>
<br>String description, String productCode, Double pricePerUnit, String note, String position

 **Combination of predicates for method $updatePosition$**

| validity of <br>object productID | sign of <br>object productID | validity of <br>object position | length of <br>object position | correct format <br>of object position | position<br>is unique | Valid/Invalid | Description of the test case   <br> Example of usage         | JUnit test case               |
| :------------------------------- | ---------------------------- | ------------------------------- | ----------------------------- | ------------------------------------- | --------------------- | ------------- | ------------------------------------------------------------ | ----------------------------- |
| null                             | *                            | *                               | *                             | *                                     | *                     | invalid       | T(null,*)->InvalidProductIdException                         | testNullID                    |
| *                                | <=0                          | *                               | *                             | *                                     | *                     | invalid       | T(0,*)->InvalidProductIdException                            | testNegID                     |
| *                                | *                            | valid                           | >0                            | false                                 | *                     | invalid       | T(*,"ax14")->InvalidLocationException                        | testValidButIncorrectPosition |
| *                                | *                            | valid                           | >0                            | true                                  | not unique            | invalid       | addProduct("description","code",1.00,"note","00-AA-00")->return id=1<br>addProduct("description2","code2",1.00,"note2","01-AA-00")->return id=2<br>T(2,"00-AA-00")->InvalidLocationException | testDuplicatedPosition        |
| valid                            | >0                           | null                            | *                             | *                                     | *                     | valid         | addProduct("description3","code3",1.00,"note3","02-AA-00")->return id=1<br/>T(3,null)->return true | testNullPosition              |
| valid                            | >0                           | valid                           | =0                            | *                                     | *                     | valid         | addProduct("description4","code4",1.00,"note4","02-AA-00")->return id=1<br/>T(4,"")->return true | testEmptyPosition             |
| valid                            | >0                           | valid                           | >0                            | true                                  | unique                | valid         | addProduct("description5","code5",1.00,"note5","02-AA-00")->return id=1<br/>T(5,"03-AA-00")->return true | testValidFormatUpdate         |