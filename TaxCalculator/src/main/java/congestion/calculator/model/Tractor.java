package congestion.calculator.model;

import org.springframework.stereotype.Component;

@Component
public class Tractor implements Vehicle {
	@Override
	public String getVehicleType() {
		return "Tractor";
	}
}