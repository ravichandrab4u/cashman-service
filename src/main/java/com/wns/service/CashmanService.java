package com.wns.service;

import java.util.Map;

/**
 * 
 * @author Ravichandra Vemuri
 */
public interface CashmanService {

	/**
	 * This api is used to get Current Cash Stats
	 * @return
	 */
	public Map<String,Integer> getCurrentCashStats();
	
	/**
	 * This api is used to Modify Currency 
	 * @param currencyMap
	 * @return
	 */
	public boolean modifyCurrency(Map<String,Object> currencyMap);
	
	/**
	 * This api is used to with draw cash
	 * @param withDrawAmount
	 * @return
	 */
	public Map<String,Object> withDraw(Integer withDrawAmount);

}
