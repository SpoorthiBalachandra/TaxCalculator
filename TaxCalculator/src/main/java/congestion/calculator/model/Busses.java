package congestion.calculator.model;

import org.springframework.stereotype.Component;

@Component
public class Busses implements Vehicle {

	public String tollFree;
	
	public String getVehicleType() {
		return "Busses";
	}
}