package reusableUtilities;

import java.util.concurrent.TimeUnit;

import org.influxdb.BatchOptions;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Point;

public class InfluxDBCall {
	private static InfluxDB influxDB;

	public void DBConnection(String serverURL, String username, String password) {
		influxDB = InfluxDBFactory.connect(serverURL, username, password);
		//Mention DataBase Name of your influxDB
		influxDB.setDatabase("KarateAPIAutomation");
		influxDB.enableBatch(BatchOptions.DEFAULTS);
		
		System.out.println("Connection Created");
	}

	public void DBwrite(String featureName, String tags, String scenarioName, String endPoint, String status, String error) {
		System.out.println("into Write");
		//Mention your Measurement Name
		influxDB.write(Point.measurement("KarateAPIAutomationResults")
				//Adding all tags and Fields as per requirement
				.tag("projectName","KarateDemo")
				.tag("featureName", featureName)
				.tag("endPoint", endPoint)
				.tag("status", status)
				.addField("scenarioName", scenarioName)
				.addField("tags", tags)
				.addField("status", status)
				.addField("Error", error)
				.build());
	}

	public void connectionClose() {
		influxDB.close();
		System.out.println("Connection closed");
	}

}
