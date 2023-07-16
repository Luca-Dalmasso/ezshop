# Unit Testing Documentation template

Authors: <br>
Luca Dalmasso s281316 <br>
Kitou Mgbatou Osee Patrik s288425 <br>
Mistruzzi Luca Guglielmo s292623 (old: s235744)<br>
Protopapa Matteo s290151 <br>              
Date: 08/05/2021

<br>

Version:


# Black Box Unit Tests



## Method createUser

<br>
<br>

**Criteria for method $createUser$:**
	

- Validity of object username
- Length of string username
- existence of string username
- Validity of object password
- length of string password
- Validity of object role
- length of object role
- admissible value of object role

<br>
<br>

**Predicates for method $createUser$:**

| Criterion                       | Predicate                                           |
| ------------------------------- | --------------------------------------------------- |
| Validity of object username     | Valid                                               |
|                                 | null                                                |
| Length of string username       | =0                                                  |
|                                 | >0                                                  |
| existence of string username    | exists                                              |
|                                 | not exists                                          |
| Validity of object password     | Valid                                               |
|                                 | null                                                |
| Length of string password       | =0                                                  |
|                                 | >0                                                  |
| Validity of object role         | Valid                                               |
|                                 | null                                                |
| Length of string role           | =0                                                  |
|                                 | >0                                                  |
| admissible value of object role | =("Administrator", "Cashier", "ShopManager") -> yes |
|                                 | !=("Administrator", "Cashier", "ShopManager") -> No |

<br>
<br>

**Boundaries for method $createUser$**:

| Criterion | Boundary values |
| --------- | --------------- |
|           |                 |

<br>
<br>

 **Combination of predicates for method $createUser$**

| Validity of object username | Length of string username | existence of string username | Validity of object password | Length of string password | Validity of object role | Length of string role | admissible value of object role | Valid/Invalid | Description of the test case   <br> Example of usage         | JUnit test case        |
| :-------------------------- | ------------------------- | ---------------------------- | --------------------------- | ------------------------- | ----------------------- | --------------------- | ------------------------------- | ------------- | ------------------------------------------------------------ | ---------------------- |
| null                        | *                         | *                            | *                           | *                         | *                       | *                     | *                               | Invalid       | T(null,*,*)->InvalidUsernameException                        | testNullUsername       |
| valid                       | 0                         | *                            | *                           | *                         | *                       | *                     | *                               | Invalid       | T("",*,*)->InvalidUsernameException                          | testEmptyUsername      |
| valid                       | >0                        | Exists                       | *                           | *                         | *                       | *                     | *                               | Invalid       | UserList.createUser("u1",...,...)<br>UserList.createUser("u1",...,...)->throw InvalidUsernameException | testDuplicatedUsername |
| *                           | *                         | *                            | Null                        | *                         | *                       | *                     | *                               | Invalid       | T(*,null,*)->InvalidPasswordException                        | testNullPassword       |
| *                           | *                         | *                            | valid                       | 0                         | *                       | *                     | *                               | Invalid       | T(*,"",*)->InvalidPasswordException                          | testEmptyPassword      |
| *                           | *                         | *                            | *                           | *                         | null                    | *                     | *                               | Invalid       | T(*,*,null)->InvalidRoleException                            | testNullRole           |
| *                           | *                         | *                            | *                           | *                         | Valid                   | 0                     | *                               | Invalid       | T(*,*,"")->InvalidRoleException                              | testEmptyRole          |
| *                           | *                         | *                            | *                           | *                         | Valid                   | >0                    | No                              | Invalid       | T(*,*,"Customer")->InvalidRoleException                      | testInvalidRole        |
| valid                       | >0                        | NotExist                     | valid                       | >0                        | valid                   | >0                    | Yes                             | Valid         | UserList.createUser("u1","validPasswr1","Administrator")     | testCreateValidUser    |

<br>
<br>

## Method deleteUser

<br>
<br>

**Criteria for method $deleteUser$:**
	

- Validity of object userID
- Sign of object userID

<br>
<br>

**Predicates for method $deleteUser$:**

| Criterion                 | Predicate |
| ------------------------- | --------- |
| Validity of object userID | Valid     |
|                           | null      |
| Sign of object userID     | <0        |
|                           | >=0       |

<br>
<br>

**Boundaries for method $deleteUser$**:

| Criterion | Boundary values |
| --------- | --------------- |
|           |                 |

<br>
<br>

 **Combination of predicates for method $deleteUser$**

| Validity of object userID | Sign of object userID | Valid/Invalid | Description of the test case   <br> Example of usage | JUnit test case      |
| :------------------------ | --------------------- | ------------- | ---------------------------------------------------- | -------------------- |
| null                      | *                     | Invalid       | T(null)->InvalidUserIdException                      | testDeleteUserNullID |
| valid                     | <0                    | Invalid       | T("")->InvalidUserIdException                        | testDeleteUserNegID  |
| valid                     | >=0                   | valid         |                                                      | testDeleteUser       |

<br>
<br>

## Method getAllUsers

<br>
<br>

**Criteria for method $getAllUsers$:**

<br>
<br>

**Predicates for method $getAllUsers$:**

| Criterion | Predicate |
| --------- | --------- |
|           | No        |

<br>
<br>

**Boundaries for method $getAllUsers$**:

| Criterion | Boundary values |
| --------- | --------------- |
|           |                 |

<br>
<br>

 **Combination of predicates for method $getAllUsers$**

| Valid/Invalid | Description of the test case   <br> Example of usage | JUnit test case |
| ------------- | ---------------------------------------------------- | --------------- |
| valid         |                                                      | testGetAllUsers |

<br>
<br>

## Method setAuthenticatedUser

<br>
<br>

**Criteria for method $setAuthenticatedUser$:**

- valid username 
- length username
- valid password
- length password

<br>
<br>

**Predicates for method $setAuthenticatedUser$:**

| Criterion       | Predicate |
| --------------- | --------- |
| valid username  | valid     |
|                 | Null      |
| length username | >0        |
|                 | =0        |
| valid password  | valid     |
|                 | Null      |
| length password | >0        |
|                 | =0        |

<br>
<br>

**Boundaries for method $setAuthenticatedUser$**:

| Criterion | Boundary values |
| --------- | --------------- |
|           |                 |

<br>
<br>

 **Combination of predicates for method $setAuthenticatedUser$**

| valid username | valid password | Length username | length password | Valid/Invalid | Description of the test case   <br> Example of usage | JUnit test case          |
| :------------- | -------------- | --------------- | --------------- | ------------- | ---------------------------------------------------- | ------------------------ |
| null           | *              | *               | *               | invalid       | T(null,*)->InvalidUsernameException                  | testNullUsername         |
| *              | null           | *               | *               | Invalid       | T(*,null)->InvalidPasswordException                  | testNullPassword         |
| valid          | *              | 0               | *               | invalid       | T("",*)->InvalidUsernameException                    | testEmptyUsername        |
| *              | valid          | *               | 0               | invalid       | T(*,"")->InvalidPasswordException                    | testEmptyPassword        |
| valid          | valid          | >0              | >0              | valid         | T("paolo12","lakdjuai")                              | TestSetAuthenticatedUser |

<br>
<br>



## Method updateUserRights

<br>
<br>

**Criteria for method $updateUserRights$:**

- valid userID 
- sign of userID
- Validity of object role
- length of object role
- admissible value of object role

<br>
<br>

**Predicates for method $updateUserRights$:**

| Criterion                       | Predicate                                           |
| ------------------------------- | --------------------------------------------------- |
| valid userID                    | valid                                               |
|                                 | null                                                |
| sign of userID                  | >0                                                  |
|                                 | <=0                                                 |
| Validity of object role         | valid                                               |
|                                 | null                                                |
| length of object role           | >0                                                  |
|                                 | =0                                                  |
| admissible value of object role | =("Administrator", "Cashier", "ShopManager") -> yes |
|                                 | !=("Administrator", "Cashier", "ShopManager") -> No |

<br>
<br>

**Boundaries for method $updateUserRights$**:

| Criterion | Boundary values |
| --------- | --------------- |
|           |                 |

<br>
<br>

 **Combination of predicates for method $updateUserRights$**

| valid userID | sign of userID | Validity of <br>object role | length <br>of object role | admissible<br> value of object role | Valid/Invalid | Description of the test case   <br> Example of usage | JUnit test case      |
| :----------- | -------------- | --------------------------- | ------------------------- | ----------------------------------- | ------------- | ---------------------------------------------------- | -------------------- |
| null         | *              | *                           | *                         | *                                   | invalid       | T(null,*)->InvalidUserIdException                    | testNullUserID       |
| *            | <=0            | *                           | *                         | *                                   | invalid       | T(-1,*)->InvalidUserIdException                      | testNegUserID        |
| *            | *              | null                        | *                         | *                                   | invalid       | T(*,null)->InvalidRoleException                      | testNullRole         |
| *            | *              | *                           | 0                         | *                                   | invalid       | T(*,"")->InvalidRoleException                        | testEmptyRole        |
| *            | *              | *                           | *                         | No                                  | invalid       | T(*,"")->InvalidRoleException                        | testInvalidRole      |
| valid        | >0             | valid                       | >0                        | Yes                                 | valid         | T("whatever","Cashier")                              | testUpdateUserRights |
|              |                |                             |                           |                                     |               |                                                      |                      |

<br>
<br>