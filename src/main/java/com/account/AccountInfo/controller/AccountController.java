package com.account.AccountInfo.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.account.AccountInfo.service.AccountService;

@RestController
@CrossOrigin(maxAge = 3600)
public class AccountController {
	
	private static final Logger logger = LoggerFactory.getLogger(AccountService.class);
	
	
	@Autowired
	AccountService accService;
	
	@GetMapping("/accounts")
	public ResponseEntity<Object> getTransactions(@RequestParam(name = "id", required = false) Integer id,
			@RequestHeader(value = "x-refresh", defaultValue = "false") boolean refresh) {
		try {
			Map<Integer,String> accountMap=new HashMap<Integer,String>();
			accountMap.put(10, "chequingAccount.json");
			accountMap.put(12, "savingsAccount.json");
			accountMap.put(19, "TfsaAccount.json");
			String jsonFile=id==null?"listOfAccounts.json":accountMap.get(id);
			if (refresh) {
				accService.evictCachedData(jsonFile);
			}
			return ResponseEntity.ok(accService.readFromFile(jsonFile));
		} catch (Exception ex) {
			logger.error("Exception", ex);
			return ResponseEntity.status(500).body("Internal Server Error");
		}
	}
	


	
	
	
	

	
	
	

}
