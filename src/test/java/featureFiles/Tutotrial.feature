Feature: Get Customer Account Details

Background: 
		* def expectedOutput1 = read('../dataResources/responseFiles/SingleUserResult.json')

@golden
Scenario: user 2 details
		
		Given url 'https://reqres.in/api/users/2'
		When method Get
		Then status 200
		* print 'Response is : '
		#Then print response
		And match response == expectedOutput1[0]