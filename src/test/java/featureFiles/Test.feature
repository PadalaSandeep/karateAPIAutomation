Feature: Get Customer Account Details


Scenario: user 5 details
		* def expectedFormat = read('../requestFiles/format.json')
		* def waitUntil = 
		"""
		function(x) {
		    if (x == "Wong") {
		      karate.log('Pass');
		      return;
		    }
		    karate.log('Fail');
		    // uncomment / modify the sleep time as per your wish
		    // java.lang.Thread.sleep(1000);
		}
		"""
		
		Given url 'https://reqres.in/api/users'
		And params ({id: '2'})
		When method Get
		Then print 'Response is : '
		And print 'result ------',response
		* call waitUntil response.data.last_name