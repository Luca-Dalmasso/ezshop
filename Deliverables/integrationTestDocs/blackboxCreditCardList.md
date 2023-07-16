
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

**Criteria for method $searchCardByCode$:**
	

- Validity of object code
- length of object code
- valid checksum of object code (Luhn Algorithm)

<br>
<br>

**Predicates for method $searchCardByCode$:**

| Criterion               | Predicate |
| ----------------------- | --------- |
| Validity of object code | valid     |
|                         | null      |
| length of object code   | =0        |
|                         | >0        |
| valid checksum          | true      |
|                         | False     |

<br>
<br>

**Boundaries for method $searchCardByCode$**:

| Criterion | Boundary values |
| --------- | --------------- |
|           |                 |

<br>
<br>

 **Combination of predicates for method $searchCardByCode$**

| Validity of object code | valid checksum | length of object code | Valid/Invalid | Description of the test case   <br> Example of usage         | JUnit test case           |
| :---------------------- | -------------- | --------------------- | ------------- | ------------------------------------------------------------ | ------------------------- |
| null                    | *              | *                     | invalid       | T(null)->InvalidCreditCardException                          | testNullCode()            |
| valid                   | *              | 0                     | invalid       | T("")->InvalidCreditCardException                            | testEmptyCode()           |
| valid                   | false          | >0                    | invalid       | T("79927398710")->InvalidCreditCardException<br>T("79927398711")->InvalidCreditCardException<br>T("79927398719")->InvalidCreditCardException | testInvalidChecksumCode() |
| valid                   | true           | >0                    | valid         | T("5100293991053009")<br>T("4716258050958645")<br>T("4485370086510891") | testValidCode()           |

<br>
<br>

**Criteria for method $checkBalance$:**
	

- Validity of object creditCardCode
- length of object creditCardCode
- valid checksum of object creditCardCode (Luhn Algorithm)
- validity of object cost
- sign of cost

<br>
<br>

**Predicates for method $checkBalance$:**

| Criterion                                                    | Predicate |
| ------------------------------------------------------------ | --------- |
| Validity of object <br>creditCardCode                        | valid     |
|                                                              | null      |
| length of object <br>creditCardCode                          | =0        |
|                                                              | >0        |
| valid checksum of object <br>creditCardCode (Luhn Algorithm) | true      |
|                                                              | false     |
| validity of object cost                                      | valid     |
|                                                              | null      |
|                                                              |           |
|                                                              |           |

<br>
<br>

**Boundaries for method $checkBalance$**:

| Criterion | Boundary values |
| --------- | --------------- |
|           |                 |

<br>
<br>

 **Combination of predicates for method $checkBalance$**

| Validity of object <br/>creditCardCode | length of object <br/>creditCardCode | valid checksum of object <br/>creditCardCode (Luhn Algorithm) | validity of object cost | Valid/Invalid | Description of the test case   <br> Example of usage         | JUnit test case       |
| :------------------------------------- | ------------------------------------ | ------------------------------------------------------------ | ----------------------- | ------------- | ------------------------------------------------------------ | --------------------- |
| null                                   | *                                    | *                                                            | *                       | invalid       | T(null,*)->InvalidCreditCardException                        | testNullCode          |
| *                                      | 0                                    | *                                                            | *                       | invalid       | T("",*)->InvalidCreditCardException                          | testEmptyCode         |
| *                                      | *                                    | false                                                        | *                       | invalid       | T("79927398710",*)->InvalidCreditCardException<br/>T("79927398711",*)->InvalidCreditCardException<br/>T("79927398719",*)->InvalidCreditCardException | testInvalidChecksum   |
| *                                      | *                                    | *                                                            | null                    | invalid       | T(*,null)->return false                                      | testNullCost          |
| valid                                  | >0                                   | true                                                         | valid                   | valid         | T("5100293991053009",10)->return true<br/>T("4716258050958645",1)->return false<br/>T("4485370086510891",150)->return true<br>T("4485370086510891",151)->return false | testValidBalanceCheck |

<br>
<br>