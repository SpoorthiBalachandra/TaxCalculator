package congestion.calculator.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

import congestion.calculator.model.*;

@Service
@Component
public class CongestionTaxCalculator {

	@Autowired
	Car car;

	@Autowired
	Busses bus;

	@Autowired
	Motorcycle motorcycle;

	@Autowired
	Tractor tractor;

	@Autowired
	Emergency emergency;

	@Autowired
	Diplomat diplomat;

	@Autowired
	Foreign foreign;

	@Autowired
	Military military;

	private static Map<String, Integer> tollFreeVehicles = new HashMap<>();

	static {
		tollFreeVehicles.put("Motorcycle", 0);
		tollFreeVehicles.put("Busses", 1);
		tollFreeVehicles.put("Emergency", 2);
		tollFreeVehicles.put("Diplomat", 3);
		tollFreeVehicles.put("Foreign", 4);
		tollFreeVehicles.put("Military", 5);

	}

	public int getTotalTax(String vechileName, Date date[]) {

		Vehicle vehicleObj = getVehicleObj(vechileName);

		return getTax(vehicleObj, date);
	}

	/*
	 * Changes made by spoorthi Added new method to fetch the Vehicle object
	 */
	public Vehicle getVehicleObj(String vehicleName) {

		switch (vehicleName) {
		case "car":
			return car;
		case "Busses":
			return bus;
		case "Motorcycle":
			return motorcycle;
		case "Tractor":
			return tractor;
		case "Emergency":
			return emergency;
		case "Diplomat":
			return diplomat;
		case "Foreign":
			return foreign;
		case "Military":
			return military;
		}
		return null;
	}

	/*
	 * Changes made by spoorthi calculation for The single charge rule has fixed
	 * 
	 */
	public int getTax(Vehicle vehicle, Date[] dates) {
		Date intervalStart = dates[0];
		int totalFee = 0;
		int tempFee = getTollFee(intervalStart, vehicle); //The single charge rule has fixed

		for (int i = 0; i < dates.length; i++) {
			Date date = dates[i];
			int nextFee = getTollFee(date, vehicle);

			long diffInMillies = date.getTime() - intervalStart.getTime();
			long minutes = diffInMillies / 1000 / 60;

			if (minutes <= 60) {
				if (totalFee > 0)
					totalFee -= tempFee;
				if (nextFee >= tempFee)
					tempFee = nextFee;
				totalFee += tempFee;
			} else {
				totalFee += nextFee;
			}
		}

		if (totalFee > 60)
			totalFee = 60;
		return totalFee;
	}

	public boolean isTollFreeVehicle(Vehicle vehicle) {
		if (vehicle == null)
			return false;
		String vehicleType = vehicle.getVehicleType();
		return tollFreeVehicles.containsKey(vehicleType);
	}

	/*
	 * Changes made by spoorthi : For below logic it was returning 0 sek instead of
	 * 8 sek or 18 sek else if (hour >= 8 && hour <= 14 && minute >= 30 && minute <=
	 * 59) return 8; else if (hour == 15 && minute >= 0 || hour == 16 && minute <=
	 * 59) return 18; This issues as been fixed now
	 */
	public int getTollFee(Date date, Vehicle vehicle) {
		if (isTollFreeDate(date) || isTollFreeVehicle(vehicle))
			return 0;

		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(date);

		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);

		if (hour < 6 || (hour == 6 && minute >= 0 && minute <= 29))
			return 8;
		else if (hour == 6 && minute >= 30 && minute <= 59)
			return 13;
		else if (hour == 7 && minute >= 0 && minute <= 59)
			return 18;
		else if (hour == 8 && minute >= 0 && minute <= 29)
			return 13;
		else if (hour >= 8 && hour <= 14 && (minute >= 30 || minute <= 59))
			return 8;
		else if (hour == 15 && minute >= 0 && minute <= 29)
			return 13;
		else if ((hour == 15 || hour == 16) && (minute >= 0 || minute <= 59))
			return 18;
		else if (hour == 17 && minute >= 0 && minute <= 59)
			return 13;
		else if (hour == 18 && minute >= 0 && minute <= 29)
			return 8;
		else
			return 0;
	}

	private Boolean isTollFreeDate(Date date) {

		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(date);

		int year = calendar.get(Calendar.YEAR);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int month = calendar.get(Calendar.MONTH);
		int dayOfTheWeek = calendar.get(Calendar.DAY_OF_WEEK);

		if (dayOfTheWeek == Calendar.SATURDAY || dayOfTheWeek == Calendar.SUNDAY)
			return true;

		/*
		 * Changes made by spoorthi : Public holidays are updated as per current year
		 * 2022. The tax is not charged on weekends (Saturdays and Sundays), public
		 * holidays, Days before a public holiday and during the month of July.
		 */

		if (year == GregorianCalendar.getInstance().getWeekYear()) {
			if (month == Calendar.JANUARY && (day == 1 || day == 5 || day == 6)
					|| month == Calendar.APRIL
							&& (day == 14 || day == 15 || day == 16 || day == 17 || day == 18 || day == 30)
					|| month == Calendar.MAY && (day == 1 || day == 25 || day == 26)
					|| month == Calendar.JUNE
							&& (day == 4 || day == 5 || day == 6 || day == 26 || day == 24 || day == 25)
					|| month == Calendar.JULY || month == Calendar.NOVEMBER && (day == 4 || day == 5)
					|| month == Calendar.DECEMBER
							&& (day == 23 || day == 24 || day == 25 || day == 26 || day == 30 || day == 31)) {
				return true;
			}
		}
		return false;
	}
}
