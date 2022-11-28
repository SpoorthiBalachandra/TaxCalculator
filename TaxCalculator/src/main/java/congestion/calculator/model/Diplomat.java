package congestion.calculator.model;

import org.springframework.stereotype.Component;

@Component
public class Diplomat implements Vehicle {

	public String getVehicleType() {
		return "Diplomat";
	}
}