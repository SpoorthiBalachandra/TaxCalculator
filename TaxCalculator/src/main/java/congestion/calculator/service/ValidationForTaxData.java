package congestion.calculator.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import congestion.calculator.custom.exception.Messages;
import congestion.calculator.custom.exception.ValidationException;
import congestion.calculator.model.Vehicle;

@Service
@Component
public class ValidationForTaxData {

	@Autowired
	ValidationException validationException;

	@Autowired
	Messages message;

	@Autowired
	CongestionTaxCalculator congestionTaxCalculator;

	/*Changes made my spoorthi
	 * IsTollFree : checks tollFree vehicle
	 */
	public boolean isTollFree(String vehicleName) {
		
		Vehicle vehicle = congestionTaxCalculator.getVehicleObj(vehicleName);
		return congestionTaxCalculator.isTollFreeVehicle(vehicle);

	}
	
	/*Changes made my spoorthi
	 * Date validation for single day tax calculation
	 */
	public Date[] getFormatedDate(String strDate) {

		String[] strDateArr = strDate.split(",");
		Date dateTime[] = new Date[strDateArr.length];
		try {
			for (int i = 0; i < strDateArr.length; i++) {
				DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
				Date date = (Date) formatter.parse(strDateArr[i]);
				dateTime[i] = date;
			}
			boolean sameDay = isDiffrentDates(dateTime);
			if (sameDay == false) {
				throw new ValidationException();
			}

		} catch (ValidationException ve) {
			throw new ValidationException(message.diffrentDateErr);
		} catch (Exception e) {
			throw new ValidationException(message.someThingWrongErr);
		}

		return dateTime;
	}

	public boolean isDiffrentDates(Date[] date) {

		if (date.length == 1)
			return true;

		for (int i = 0; i < date.length; i++) {

			for (int j = i + 1; j < date.length; j++) {
				if (date[i].getDay() == (date[j].getDay())) {
					return true;
				}
			}
		}
		return false;
	}
}
