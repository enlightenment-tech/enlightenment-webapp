package com.enlightenment.controller;

import java.io.IOException;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.kraken.KrakenExchange;
import org.knowm.xchange.kraken.dto.marketdata.KrakenTicker;
import org.knowm.xchange.kraken.service.KrakenMarketDataServiceRaw;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class KrakenApiController {
	
	@RequestMapping(value="/krakenticker",method=RequestMethod.GET)
	public ModelAndView ticker() throws IOException {

		ObjectMapper mapper = new ObjectMapper();
		ModelAndView modelAndView = new ModelAndView();
		
		// Use the factory to get Kraken exchange API using default settings
		Exchange krakenExchange = ExchangeFactory.INSTANCE.createExchange(KrakenExchange.class.getName());
		KrakenMarketDataServiceRaw krakenMarketDataService = (KrakenMarketDataServiceRaw) krakenExchange
				.getMarketDataService();
		
		// Get the latest ticker data showing BTC to EUR
		KrakenTicker ticker = krakenMarketDataService.getKrakenTicker(CurrencyPair.BTC_EUR);
		String jsonInString = mapper.writeValueAsString(ticker);
		modelAndView.addObject("tickerask",  ticker.getAsk().getPrice() );
		modelAndView.addObject("tickerbid",  ticker.getBid().getPrice() );
		modelAndView.setViewName("admin/home");
		return modelAndView;
		
	}
}
