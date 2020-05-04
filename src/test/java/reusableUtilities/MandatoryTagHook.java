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

    @Override
    public boolean beforeScenario(Scenario scenario, ScenarioContext context) {
      
        return true;
    }

    @Override
    public void afterScenario(ScenarioResult result, ScenarioContext context) {
    	System.out.println("This is into Hook, Feature Name is "+ result.getScenario().getFeature().getName());
    	System.out.println("This is into Hook, scenario tags is "+ result.getScenario().getTags());
    	System.out.println("This is into Hook, scenario name is "+ result.getScenario().getName());
    	System.out.println("This is into Hook, scenario EndPoint is "+ context.getRequestBuilder().getUrlAndPath());
    	System.out.println("This is into Hook, scenario Method type is "+ context.getPrevRequest().getMethod());
     	System.out.println("This is into Hook, scenario status is "+ result.isFailed());
     	System.out.println("This is into Hook, scenario Error is "+ result.getError());
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
    	

    }

    @Override
    public void afterAll(Results results) {
    	System.out.println("After All Results "+results.getScenarioResults());
    	for(int z=0; z<results.getScenarioResults().size(); z++) {
    		
    		results.getScenarioResults().get(z).getScenario().getName();
    		results.getScenarioResults().get(z).getScenario().getFeature().getName();
    		results.getScenarioResults().get(z).getLastStepResult().getResult();
    	}

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