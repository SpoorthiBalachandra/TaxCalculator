package congestion.calculator.model;

import org.springframework.stereotype.Component;

@Component
public class Foreign implements Vehicle {

	public String getVehicleType() {
		return "Foreign";
	}
}
