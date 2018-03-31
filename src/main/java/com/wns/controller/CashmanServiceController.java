package com.wns.controller;

import io.swagger.annotations.ApiOperation;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wns.service.CashmanService;
/**
 * 
 * @author Ravichandra Vemuri
 *
 */
@RestController
@RequestMapping(value = "/cashman")
public class CashmanServiceController {
	
	@Autowired
	private CashmanService cashmanService; 

	@RequestMapping(value = "/health", method = RequestMethod.GET)
	public String health() {
		return "Health is OK";
	}

	@RequestMapping(value = "/currectcash", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Get Current Avalible Currecy Stats.", notes = "Returns counts of 20$ & 50& Currency as JSON String.")
	public Map<String,Integer> getCurrentCashStats(){
		return cashmanService.getCurrentCashStats();
	}
	
	@RequestMapping(value = "/modifycurrency", method = RequestMethod.PUT, produces = "application/json")
	@ApiOperation(value = "Modify  No of Current Notes.", notes = "Returns boolean true if success else false")
	public boolean modifyCurrency(@RequestBody Map<String, Object> currencyMap){
		return cashmanService.modifyCurrency(currencyMap);
	}
	
	@RequestMapping(value = "/withdraw", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "With Draw Amount in $20 & $50 denomination based on the given amount", notes = "Returns with drawn amount. Else gives error message as JSON String.")
	public Map<String,Object> withDraw(@RequestParam Integer withdrawamount){
		return cashmanService.withDraw(withdrawamount);
	}
	
}
