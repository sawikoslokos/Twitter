Hello, 

This project ontain 2 Tests: 

Test 1 - Test 1 is posting message on a twitter accoun. 

To perform Test please change an information in :

src\test\resources\tests_suite.xml File

Set up an username to a twitter account, password and message You would like to be posted.
(Please remember that twitter will not allow to post message of the same text one by one)

Exaple: 
<parameter name="user" value="ValueForUserName"/>


Test 2 - Tes Add to observe account proposed by twitter and verify if it was added correctly

Test will not need any value. 


Test could be runned via command line just by using: mvn clean test command in test directory. 
After run html raport will be generated in test-output directory. 
 