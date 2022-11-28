package congestion.calculator.model;

import org.springframework.stereotype.Component;

@Component
public class Military implements Vehicle {

	public String getVehicleType() {
		return "Military";
	}
}