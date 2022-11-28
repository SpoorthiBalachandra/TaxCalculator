package congestion.calculator.model;

import org.springframework.stereotype.Component;

@Component
public class Motorcycle implements Vehicle {
    @Override
    public String getVehicleType() {
        return "Motorbike";
    }
}
