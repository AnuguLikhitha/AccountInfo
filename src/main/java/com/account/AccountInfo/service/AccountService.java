package com.account.AccountInfo.service;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service

public class AccountService {
	
	private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

	
	@Cacheable(value="fileread")
	public Object readFromFile(String fileName) {
	    logger.info("Reading from file: " + fileName);
	    Object jsonData = null;
	    try {
	        Resource resource = new ClassPathResource(fileName);
	        InputStream inputStream = resource.getInputStream();
	        byte[] data = FileCopyUtils.copyToByteArray(inputStream);
	        ObjectMapper objectMapper = new ObjectMapper();
	        jsonData = objectMapper.readValue(data, Object.class);
	    } catch (IOException e) {
	        logger.error("File not found: " + e);
	    }
	    return jsonData;
	}
	
	
	@CacheEvict(value = "fileread")
    public void evictCachedData(String fileName) {
		logger.info("Cache evicted:"+fileName);
    }
	
	
	
	
	
}
