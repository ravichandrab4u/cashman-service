package com.wns;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.wns.controller.CashmanServiceController;
import com.wns.service.CashmanService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CashmanServiceController.class, secure = false)
public class CashmanServiceControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CashmanService cashmanService;
	
	
	@Test
	public void healthTest() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/cashman/health").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected = "Health is OK";
		Assert.assertEquals(expected, result.getResponse().getContentAsString());
	}
	
	@Test
	public void getCurrentCashStatsTest() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/cashman/currectcash").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		Assert.assertEquals(200, result.getResponse().getStatus());
	}
	
	@Test
	public void modifyCurrencyTest() throws Exception {
		String inputJson = "{\"OPERATION\":\"ADD\",\"20$\":100,\"50$\":100}";
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.put("/cashman/modifycurrency")
				.accept(MediaType.APPLICATION_JSON).content(inputJson)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		Assert.assertEquals(200, result.getResponse().getStatus());
	}
	
	@Test
	public void withDrawTest() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/cashman/withdraw?withdrawamount=100").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		Assert.assertEquals(200, result.getResponse().getStatus());
	}
	
}

