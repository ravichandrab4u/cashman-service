package com.wns.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.wns.CashmanServiceApplication;
import com.wns.service.CashmanService;

/**
 * 
 * @author Ravichandra Vemuri
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CashmanServiceApplication.class)
@WebAppConfiguration
public class CashmanServiceImplTest {

	@Autowired
	private CashmanService cashmanService;
	

	@Test
	public void getCurrentCashStatsTest() throws Exception {
		Map<String, Integer> cashMap = cashmanService.getCurrentCashStats();
		Integer defaultValue = 100;
		Assert.assertEquals(defaultValue, cashMap.get("20$"));
	}
	
	@Test
	public void modifyCurrencyTest(){
		Map<String,Object> currencyMap = new HashMap<String, Object>();
		currencyMap.put("OPERATION", "REMOVE");
		currencyMap.put("20$", 10);
		boolean status = cashmanService.modifyCurrency(currencyMap);
		Assert.assertEquals(status, true);
		currencyMap.put("OPERATION", "ADD");
		status = cashmanService.modifyCurrency(currencyMap);
		Assert.assertEquals(status, true);
	}
	
	@Test
	public void withDrawTest(){
		Integer withDrawAmount = 220;
		Map<String, Object> cashMap = cashmanService.withDraw(withDrawAmount);
		Map<Integer,Integer> noteMap = (Map<Integer, Integer>) cashMap.get("With Draw Amount");
		Integer noof20DollorNotes = noteMap.get(20);
		Integer noof50DollorNotes = noteMap.get(50);
		
		Assert.assertEquals(new Integer(1), noof20DollorNotes);
		Assert.assertEquals(new Integer(4), noof50DollorNotes);
		
	}
	
	
}
