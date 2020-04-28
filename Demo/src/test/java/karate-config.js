function fn() {    
  
  var env = karate.env; // variable to define environment
  
  karate.log('karate.env system property was:', env);
  
  if (!env) {
    env = 'dev';
  }
  
  var config = {
			    env: env,
				SearchAccountUrl: '',
				GetAccountUrl: '',
  				};
  
  if (env == 'dev') 
  {
	config.SearchAccountUrl = '';
	config.GetAccountUrl = '';
  } 
  else if (env == 'sit') 
  {
    config.SearchAccountUrl = '';
	config.GetAccountUrl = '';
  }
  else if (env == 'uat') 
  {
    config.SearchAccountUrl = '';
	config.GetAccountUrl = '';
  }
  
  karate.log('Envrionment URL:', env);
  
  karate.configure('connectTimeout','500000')
  karate.configure('readTimeout','500000')	
	
  return config;
}
