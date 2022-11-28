package congestion.calculator.model;

import org.springframework.stereotype.Component;

@Component
public class Emergency implements Vehicle {

	public String getVehicleType() {
		return "Emergency";
	}
}
