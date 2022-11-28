package congestion.calculator.controller;

import java.text.ParseException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import congestion.calculator.custom.exception.Messages;
import congestion.calculator.custom.exception.ValidationException;
import congestion.calculator.service.CongestionTaxCalculator;
import congestion.calculator.service.ValidationForTaxData;

@Controller
public class TaxController {

	@Autowired
	CongestionTaxCalculator congectionTaxCalculator;
	
	@Autowired
	ValidationForTaxData validationForTaxData;
	
	@Autowired
	Messages messages;

	@RequestMapping("/")
	public String home() {
		return "home.jsp";
	}

	@RequestMapping("/getTax")
	public ModelAndView getTaxCalculation(@RequestParam("Vehicle") String vehicleName,
			@RequestParam("date") String dateStr) throws ParseException {
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("home.jsp");
		
		
		if (validationForTaxData.isTollFree(vehicleName)) {
			mv.addObject("TollFree",messages.tollFreeVehicleMsg);
			return mv;
		}
		
		try {
			Date[] date = validationForTaxData.getFormatedDate(dateStr);
			int resultTax = congectionTaxCalculator.getTotalTax(vehicleName, date);
			
			mv.addObject("Result", resultTax);

		} catch (ValidationException validationException) {
			return mv.addObject("error", validationException.getErrorMessage());
		} 

		return mv;
	}
}
