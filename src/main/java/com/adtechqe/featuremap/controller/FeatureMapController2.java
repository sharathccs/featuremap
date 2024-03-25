package com.adtechqe.featuremap.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.apache.logging.log4j.LogManager; 
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;


import com.adtechqe.featuremap.model.FeatureMapModel;
//import com.webui.model.Parameter; // need to create this in this foler. But in BP, it is never used

import com.adtechqe.featuremap.service.FeatureMapService;
import com.adtechqe.featuremap.util.Status;




@RestController
public class FeatureMapController2 {

    Status status = new Status();
	HttpHeaders headers = new HttpHeaders();

	@Autowired
	private FeatureMapService2 featureMapService2 ;
	
	private static Logger log = LogManager.getLogger(FeatureMapController2.class);
	
	/**
	 * getBusinessProcess() method is used to fetch a root content from DB
	 * 
	 * @return ResponseEntity<BusinessProcessModel>
	 */



     /* This is not needed
      * 

            @RequestMapping(value = "/downloadBussinessProcess"
            @RequestMapping(value = "/uploadBussinessProcess",
            "/getManualTest",



      */
		
	




}
