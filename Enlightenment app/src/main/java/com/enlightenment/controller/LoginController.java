package com.enlightenment.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.kraken.KrakenExchange;
import org.knowm.xchange.kraken.dto.marketdata.KrakenTicker;
import org.knowm.xchange.kraken.service.KrakenMarketDataServiceRaw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.enlightenment.model.User;
import com.enlightenment.service.UserService;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;

	@RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
	public ModelAndView login(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}
	
//	@RequestMapping(value="/login", method = RequestMethod.POST)
//	public ModelAndView login(@RequestParam(value = "email") String email, @RequestParam(value = "password") String password){
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.setViewName("login");
//		return modelAndView;
//	}
	
	@RequestMapping(value="/index", method = RequestMethod.GET)
	public ModelAndView index(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		return modelAndView;
	}
	
	@RequestMapping(value="/registration", method = RequestMethod.GET)
	public ModelAndView registration(){
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registration");
		return modelAndView;
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			bindingResult
					.rejectValue("email", "error.user",
							"There is already a user registered with the email provided");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registration");
		} else {
			userService.saveUser(user);
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("registration");
			
		}
		return modelAndView;
	}
	
	@RequestMapping(value="/admin/home", method = RequestMethod.GET)
	public ModelAndView home(){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
//		modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
//		modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
		modelAndView.setViewName("admin/home");
		return modelAndView;
	}
	
//	@RequestMapping(value="/krakenticker",method=RequestMethod.GET)
//	public ModelAndView ticker() throws IOException {
//
//		ModelAndView modelAndView = new ModelAndView();
//		// Use the factory to get Kraken exchange API using default settings
//		Exchange krakenExchange = ExchangeFactory.INSTANCE.createExchange(KrakenExchange.class.getName());
//		KrakenMarketDataServiceRaw krakenMarketDataService = (KrakenMarketDataServiceRaw) krakenExchange
//				.getMarketDataService();
//		// Get the latest ticker data showing BTC to EUR
//		KrakenTicker ticker = krakenMarketDataService.getKrakenTicker(CurrencyPair.BTC_EUR);
//		modelAndView.addObject("tickerask",  ticker.getAsk().getPrice() );
//		modelAndView.addObject("tickerbid",  ticker.getBid().getPrice() );
//		modelAndView.setViewName("admin/home");
//		return modelAndView;
//		
//	}
	
//	 private static void raw(Exchange krakenExchange) throws IOException {
//		 
//		    // Interested in the public market data feed (no authentication)
//		    KrakenMarketDataServiceRaw krakenMarketDataService =
//		        (KrakenMarketDataServiceRaw) krakenExchange.getMarketDataService();
//		    // Get the latest ticker data showing BTC to EUR
//		    KrakenTicker ticker = krakenMarketDataService.getKrakenTicker(CurrencyPair.BTC_EUR);
//		    System.out.println(ticker);
//		    
//		    
//	 }
}
