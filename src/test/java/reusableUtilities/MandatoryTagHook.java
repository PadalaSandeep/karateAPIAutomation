package reusableUtilities;

import com.intuit.karate.Results;
import com.intuit.karate.core.ExecutionContext;
import com.intuit.karate.core.ExecutionHook;
import com.intuit.karate.core.Feature;
import com.intuit.karate.core.FeatureResult;
import com.intuit.karate.core.PerfEvent;
import com.intuit.karate.core.Scenario;
import com.intuit.karate.core.ScenarioContext;
import com.intuit.karate.core.ScenarioResult;
import com.intuit.karate.core.Step;
import com.intuit.karate.core.StepResult;
import com.intuit.karate.http.HttpRequestBuilder;

/**
 *
 * @author pthomas3
 */
public class MandatoryTagHook implements ExecutionHook {
	String Status, Error, Tags;
	public static InfluxDBCall influxDB = new InfluxDBCall();
    @Override
    public boolean beforeScenario(Scenario scenario, ScenarioContext context) {
      
        return true;
    }

    @Override
    public void afterScenario(ScenarioResult result, ScenarioContext context) {
    	
    	if(result.isFailed()) {
    		Status = "Failed";
    	}
    	else {
    		Status = "Passed";
    	}
    	
    	if(result.getError()== null) {
    		Error = "No Error";
    	}
    	else {
    		Error = result.getError().toString();
    	}
    	
    	Tags="";
    	if(result.getScenario().getTags()== null) {
    		Tags = "No Tags";
    	}
    	else {
    		for(int z=0; z<result.getScenario().getTags().size(); z++) {
        		
        		Tags = Tags+result.getScenario().getTags().get(z)+",";
        	}
    		Tags = Tags.substring(0,Tags.length()-1);
    	}
    	
    	influxDB.DBwrite(result.getScenario().getFeature().getName().trim(),
    			Tags,
    			result.getScenario().getName().trim(),
    			context.getRequestBuilder().getUrlAndPath().trim(),
    			Status, Error);
    	
    	System.out.println("This is into Hook, Feature Name is "+ result.getScenario().getFeature().getName());
    	System.out.println("This is into Hook, scenario tags is "+ Tags);
    	System.out.println("This is into Hook, scenario name is "+ result.getScenario().getName());
    	System.out.println("This is into Hook, scenario EndPoint is "+ context.getRequestBuilder().getUrlAndPath());
    	System.out.println("This is into Hook, scenario Method type is "+ context.getPrevRequest().getMethod());
     	System.out.println("This is into Hook, scenario status is "+ Status);
     	System.out.println("This is into Hook, scenario Error is "+ Error);
    }    

    @Override
    public boolean beforeFeature(Feature feature, ExecutionContext context) {
        return true;
    }

    @Override
    public void afterFeature(FeatureResult result, ExecutionContext context) {
        
    }    

    @Override
    public void beforeAll(Results results) {
    	
    	influxDB.DBConnection("http://localhost:8086", "root", "root");
    }

    @Override
    public void afterAll(Results results) {
    	
    	influxDB.connectionClose();

    }        

    @Override
    public boolean beforeStep(Step step, ScenarioContext context) {
        return true;
    }

    @Override
    public void afterStep(StepResult result, ScenarioContext context) {

    }        
        
    @Override
    public String getPerfEventName(HttpRequestBuilder req, ScenarioContext context) {
        return null;
    }    
    
    @Override
    public void reportPerfEvent(PerfEvent event) {
    	
        
    }

}