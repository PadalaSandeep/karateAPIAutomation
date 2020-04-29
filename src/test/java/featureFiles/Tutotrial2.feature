Feature: Get Customer Account Details

Background: 
		* def expectedOutput1 = read('../dataResources/responseFiles/SingleUserResult.json')
		* def expectedFormat = read('../dataResources/requestFiles/format.json')
		* def featureVar = read('Tutotrial.feature')
		* def result = call featureVar


Scenario: user 3 details
		
		Given url 'https://reqres.in/api/users/3'
		When method Get
		Then status 200
		* print 'Response is : '
		#Then print response
		#And match response == expectedOutput1[1]
		Then print 'result ------',result
		And print 'First name : ',result.response.data.first_name
		And match response == expectedFormat

@golden
Scenario: user 4 details
		* def data = { name: 'Jagruti', job: 'Consultant' }
		
		Given url 'https://reqres.in/api/users'
		And request ({ name: data.name, job: data.job })
		When method Post
		Then status 201
		Then print 'result ------',response
		And match response.name == data.name
		And match response.job == data.job
		

		