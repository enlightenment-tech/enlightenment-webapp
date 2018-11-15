//package com.enl-tech.controller;
//
//import java.io.IOException;
//
//import org.knowm.xchange.Exchange;
//import org.knowm.xchange.ExchangeFactory;
//import org.knowm.xchange.currency.CurrencyPair;
//import org.knowm.xchange.bitstamp.BitstampExchange;
//import org.knowm.xchange.bitstamp.dto.marketdata.BitstampTicker;
//import org.knowm.xchange.bitstamp.service.BitstampMarketDataServiceRaw;
//import org.knowm.xchange.service.marketdata.MarketDataService;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//public class BitstampApiController {
//	
//String jsonInString;
//private static Exchange poloniex;
//private static CurrencyPair currencyPair;
//
//private BitstampTicker raw(BitstampMarketDataServiceRaw marketDataService) throws IOException {
//
//    BitstampTicker bitstampTicker = marketDataService.getBitstampTicker(CurrencyPair.BTC_USD);
//	return bitstampTicker;
//  }
//
//	public String poloniextickerfunc() {
//		ObjectMapper mapper = new ObjectMapper();
//		Exchange bitstamp = ExchangeFactory.INSTANCE.createExchange(BitstampExchange.class.getName());
//		MarketDataService marketDataService = bitstamp.getMarketDataService();
//		BitstampTicker bitticker = null;
//		try {
//			
//			 bitticker = raw((BitstampMarketDataServiceRaw) marketDataService);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		 try {
//				jsonInString = mapper.writeValueAsString(bitticker);
//			} catch (JsonProcessingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		return jsonInString;
//		
//	}
//	
//
//}
