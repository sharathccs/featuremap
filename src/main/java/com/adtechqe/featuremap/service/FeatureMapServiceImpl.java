package com.adtechqe.featuremap.service;

import java.sql.Timestamp;
import java.util.List;

import org.apache.logging.log4j.LogManager; import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.adtechqe.featuremap.model.FeatureMapModel;

//import com.webui.model.TestExecutionQueueMaster;
//import com.webui.model.Users;


import com.adtechqe.featuremap.repository.FeatureMapRepository;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;

//import com.webui.repository.EnvironmentRepository;

//import com.webui.repository.TestExecutionQueueMasterDaoRepository;
//import com.webui.repository.UserRepository;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


import org.apache.poi.common.usermodel.Hyperlink;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.Tuple;

import java.util.Set;


@Service("featureMapservice")
public class FeatureMapServiceImpl implements FeatureMapService {


    /* 
                @Autowired
                private FeatureMapRepository featureMapRepository;
                

                @Autowired
                private TestExecutionQueueMasterDaoRepository testExecutionQueueMasterDaoRepository;

                @Autowired
                private EnvironmentRepository environmentRepository;
                
                @Autowired
                private UserRepository userRepository;

    */

	
	@Autowired
	private RestTemplate restTemplate;


	private static Logger log = LogManager.getLogger(FeatureMapServiceImpl.class);
	
	JsonParser parser = new JsonParser();

    /**
	 * insertRootNode() method is used to insert root into the DB
	 */
	@Override
	public FeatureMapModel insertRootNode(FeatureMapModel featureMap);
	{
		try
		{
			if(featureMap != null && featureMap.getBusprocname() != null)
			{
				List<FeatureMapModel> featureMapList = featureMapRepository.findByBusprocname(featureMap.getBusprocname());
				System.out.println(featureMapList.size());
		
				if(featureMapList.size() > 0)
				{
					return null;
				}
				else
				{
			        Timestamp createdTimestamp = new Timestamp(System.currentTimeMillis());
					featureMap.setDateCreated(createdTimestamp.toString());
					featureMap.setDatemodified(createdTimestamp.toString());
					featureMap.setActiveFlag(1);
					featureMapRepository.saveAndFlush(featureMap);
				}
			}
		}
		catch (Exception e) {
			log.error("In FeatureMapServiceImpl | insertRootNode() method | inserting root not found:", e);
		}
		return featureMap;
	}
	


	/**
	 * getAllRoots() method is used to getting roots from DB
	 */
    @Override
	public List<FeatureMapModel>  getAllRoots()
	{
	 return featureMapRepository.getAllRoots();
	}


	/**
	 * gerAllRootsName() method is used to getting rootname from DB
	 */
	@Override
	public List<String> gerAllRootsName()
	{
		return featureMapRepository.getAllRootsName();
	}
	

	/**
	 * updateRoot() method is used to update the root with new data in DB
	 */
	@Override
	public int updateRoot(FeatureMapModel featureMap)
	{
		log.debug("In FEatureMapServiceImpl | updateRoot() method | updating root:" + featureMap.getBusprocname());
		int count = 0;
		try {
			featureMapRepository.updateRoot(featureMap.getStrucdata(),featureMap.getBusprocname(),featureMap.getUserId());
		} catch (Exception e) {
			log.error("In FeatureMapServiceImpl | updateRoot() method | updating root error", e);
			return count;
		}
		return count + 1;
	}
	

	/**
	 * deleteRoot() method is used to delete the root in DB
	 */
	@Override
	public int deleteRoot(FeatureMapModel featureMap)
	{
		log.debug("In FeatureMapServiceImpl | deleteRoot() method | delete root:" + featureMap.getBusprocname());
		int count = 0;
		try
		{
		    featureMapRepository.deleteRootNode(featureMap.getBusprocname());
		}
		catch (Exception e) {
			log.error("In FEatureMapServiceImp| deleteRoot() method | deleting root error", e);
			return count;
		}
		return count + 1;
	}


    /**
	 * getBusinessProcessByName() method is used to get the root from DB
	 */
	@Override
	public FeatureMapModel getFeatureMapByName(FeatureMapModel featureMap)
	{
		log.debug("In FeatureMapServiceImpl | getFeatureMapByName() method | Get root:" + featureMap.getBusprocname());
		FeatureMapModel featureMap = featureMapRepository.getFeatureMap(featureMap.getBusprocname());
		if(featureMap.getUserId() != null) {
			java.util.Optional<Users> user = userRepository.findById(featureMap.getUserId());
			if(user.isPresent()) {
				featureMap.setUserName(user.get().getUserName());
			}
			}
		return featureMap;
	}

	
    @Override
	public Object getQueues(String tagsBodyData)
	{
		JsonObjectFormatVisitor tagBodyJObj = parser.parse(tagsBodyData).getAsJsonObject();
		String scriptData = tagBodyJObj.get("script").toString();
		scriptData = scriptData.replace("[","");
		scriptData = scriptData.replace("]","");
		List<String> listQueueData = new ArrayList<String>(Arrays.asList(scriptData.split(",")));
		List<Object> QueueData =testExecutionQueueMasterDaoRepository.getQueues(listQueueData);
		HashMap<String, List<Object>> returnMapData = new HashMap<String,List<Object>>();
		returnMapData.put("script",QueueData);
		return returnMapData;
	}


















    

}
