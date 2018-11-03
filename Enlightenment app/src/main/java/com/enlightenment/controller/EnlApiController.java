package com.enlightenment.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.kraken.KrakenExchange;
import org.knowm.xchange.kraken.dto.marketdata.KrakenTicker;
import org.knowm.xchange.kraken.service.KrakenMarketDataServiceRaw;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Controller
public class EnlApiController {

	String jsonInString;
	
	public String enltickerfunc() {
		//ObjectMapper mapper = new ObjectMapper();
		// Use the factory to get Kraken exchange API using default settings
		//Exchange krakenExchange = ExchangeFactory.INSTANCE.createExchange(KrakenExchange.class.getName());
		//KrakenMarketDataServiceRaw krakenMarketDataService = (KrakenMarketDataServiceRaw) krakenExchange
		//		.getMarketDataService();
		// Get the latest ticker data showing BTC to EUR
		try {
			//https://dk-oodevelop-enl-sim.azurewebsites.net/enl-trade-engine/direct-pricing/DirectUserId/prices/BTCEUR/0.002/best-bid-ask
			RestTemplate restTemplate = new RestTemplate();
			jsonInString = restTemplate.getForObject(
					"https://dk-oodevelop-enl-sim.azurewebsites.net/enl-trade-engine/direct-pricing/DirectUserId/prices/BTCEUR/0.002/best-bid-ask",
					String.class);

			System.out.println("Q: " + jsonInString);

			//ticker = krakenMarketDataService.getKrakenTicker(CurrencyPair.BTC_EUR);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jsonInString;
	}
}
