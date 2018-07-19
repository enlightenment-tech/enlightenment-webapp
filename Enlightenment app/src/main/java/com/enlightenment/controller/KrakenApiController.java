package com.enlightenment.controller;

import java.io.IOException;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.kraken.KrakenExchange;
import org.knowm.xchange.kraken.dto.marketdata.KrakenTicker;
import org.knowm.xchange.kraken.service.KrakenMarketDataServiceRaw;
import org.springframework.stereotype.Controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class KrakenApiController {

	String jsonInString;
	
	public String krakentickerfunc() {
		ObjectMapper mapper = new ObjectMapper();
		// Use the factory to get Kraken exchange API using default settings
		Exchange krakenExchange = ExchangeFactory.INSTANCE.createExchange(KrakenExchange.class.getName());
		KrakenMarketDataServiceRaw krakenMarketDataService = (KrakenMarketDataServiceRaw) krakenExchange
				.getMarketDataService();
		// Get the latest ticker data showing BTC to EUR
		KrakenTicker ticker = null;
		try {
			ticker = krakenMarketDataService.getKrakenTicker(CurrencyPair.BTC_EUR);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    try {
			jsonInString = mapper.writeValueAsString(ticker);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonInString;
	}
}
