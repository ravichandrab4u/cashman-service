package com.wns.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.wns.service.CashmanService;
import com.wns.utils.CashmanConstants;
/**
 * This is Service Implementation Class which contains all business Logic
 * @author Ravichandra Vemuri
 *
 */
@Service
public class CashmanServiceImpl implements CashmanService {
	
	private final static Logger LOG = LogManager.getLogger(CashmanServiceImpl.class);

	 private int[] denominations = new int[]{ 50, 20 };
	 private int[] noOfNotes = new int [] {};
	 
     int[] noteCounter = new int[2];
     
     
	public Map<String,Integer> currency = new HashMap<String,Integer>();
	
	@PostConstruct
	private void loadIntialCurrency (){
		noOfNotes = new int [] {100,100};
	}
	
	public Map<String,Integer> getCurrentCashStats(){
		Map<String,Integer> currencyMap = new HashMap<String,Integer>();
		currencyMap.put(denominations[0]+"$", noOfNotes[0]);
		currencyMap.put(denominations[1]+"$", noOfNotes[1]);
		return currencyMap;
	}
	
	public boolean modifyCurrency(Map<String,Object> currencyMap) {
		try{
			String operation = (String) currencyMap.get(CashmanConstants.OPERATION);
			
			Integer twentyDollors = (Integer) currencyMap.get(CashmanConstants.TWENTY_DOLLOR);
			Integer fiftyDollors = (Integer) currencyMap.get(CashmanConstants.FIFTY_DOLLOR);
			
			if(operation.equalsIgnoreCase(CashmanConstants.ADD)){
				if(fiftyDollors!=null){
					noOfNotes[0] +=  fiftyDollors;
				}
				if(twentyDollors!=null){
					noOfNotes[1] +=  twentyDollors;
				}
				
			} else if(operation.equalsIgnoreCase(CashmanConstants.REMOVE)){
				if(fiftyDollors!=null){
					noOfNotes[0] -= fiftyDollors;
				}
				if(twentyDollors!=null){
					noOfNotes[1] -=  twentyDollors;
				}
			}
			return true;
		} catch(Exception e){
			LOG.error("Error while modifying currency",e);
			return false;
		}
				
	}
	
	public Map<String,Object> withDraw(Integer withDrawAmount){
		Map<String,Object> map = new HashMap<String,Object>();
		if(hasBalance(withDrawAmount)){
			Map<Integer,Integer> noteMap = countCurrencyNotes(withDrawAmount);
			if(canDispense(noteMap,withDrawAmount)){
				for (Integer key : noteMap.keySet()) {
					if(key == 50){
						noOfNotes[0] = noOfNotes[0] - noteMap.get(key);
					}
					if(key == 20){
						noOfNotes[1] = noOfNotes[1] - noteMap.get(key);
					}
					map.put("With Draw Amount", noteMap);
				}
				
			}else{
				map.put("Error", "Please give with draw amount in $20 and $50");
			}
			
		}else{
			map.put("Error", "No sufficent Balance");
		}
		return map;
	}

	private Map<Integer,Integer> countCurrencyNotes(int amount) {
		Map<Integer,Integer> notesMap = new HashMap<Integer,Integer>();
        for (int i = 0; i < 2; i++) {
            if (amount >= denominations[i]) {
                noteCounter[i] = amount / denominations[i];
                amount = amount - noteCounter[i] * denominations[i];
            }
        }
      
        for (int i = 0; i < 2; i++) {
            if (noteCounter[i] != 0) {
            	notesMap.put(denominations[i], noteCounter[i]);
            }
        }
        
        return notesMap;
    }
	
	private boolean canDispense(Map<Integer,Integer> notesMap, int withDrawAmount){
		boolean dispense = false;
		int amount = 0;
		for (Integer key : notesMap.keySet()) {
			amount += (key * notesMap.get(key));
		}
		if(amount == withDrawAmount){
			dispense = true;
		}
		return dispense;
	}
	
	private boolean hasBalance(Integer withDrawAmount){
		int totalAmount = 0;
		for (int i = 0; i < noOfNotes.length; i++) {
			totalAmount += denominations[i] * noOfNotes[i];
		}
		if(withDrawAmount > totalAmount){
			return false;
		}
		return true;
	}
}
