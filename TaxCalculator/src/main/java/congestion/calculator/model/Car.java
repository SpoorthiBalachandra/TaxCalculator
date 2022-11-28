package congestion.calculator.model;

import org.springframework.stereotype.Component;

@Component
public class Car implements Vehicle {

	public String getVehicleType() {
		return "Car";
	}
}