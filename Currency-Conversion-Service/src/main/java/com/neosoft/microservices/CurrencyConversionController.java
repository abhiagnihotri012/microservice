package com.neosoft.microservices;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyConversionController {

	@Autowired
	private CurrencyExchangeProxy proxy;
	
	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversion(
			@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity
			) {
		//return new CurrencyConversion(10001L, from, to, quantity, BigDecimal.ONE, BigDecimal.ONE, "");
		/*HashMap<String, String> uriVariable = new HashMap<>();
		uriVariable.put("from", from);
		uriVariable.put("to",to);
		
		ResponseEntity<CurrencyConversion> responseEntity =new RestTemplate().
				getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversion.class, uriVariable);
	
		CurrencyConversion currencyConversion = responseEntity.getBody();
		*/
		CurrencyConversion currencyConversion = proxy.getExchangeValues(from, to);
		return new CurrencyConversion(currencyConversion.getId(),from,to,quantity,currencyConversion.getConversionMultiple(),quantity.multiply(currencyConversion.getConversionMultiple()),currencyConversion.getEnvironment()+" "+"rest template");
	}
	
}
