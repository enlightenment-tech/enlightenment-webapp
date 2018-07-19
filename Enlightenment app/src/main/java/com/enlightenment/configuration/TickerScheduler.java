package com.enlightenment.configuration;

import java.io.IOException;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.kraken.KrakenExchange;
import org.knowm.xchange.kraken.dto.marketdata.KrakenTicker;
import org.knowm.xchange.kraken.service.KrakenMarketDataServiceRaw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ResponseBody;

import com.enlightenment.controller.KrakenApiController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableScheduling
public class TickerScheduler {
	
	@Autowired
    private SimpMessagingTemplate template;
	
	@Autowired
	KrakenApiController kraken;
	
//	@Autowired
//	BitstampApiController bitstamp;
	
	String jsonInString;
	
	
		
	 @Scheduled(fixedRate = 5000)
	    public void publishUpdates(){
	       String res = kraken.krakentickerfunc();
	      // String res1 = bitstamp.poloniextickerfunc();
	        template.convertAndSend("/topic/myticker", res);
	    }

}
