package com.adtechqe.featuremap.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonObject;

import com.adtechqe.model.FeatureMapModel;



public class FeatureMapService {
    public FeatureMapModel insertRootNode(FeatureMapModel featureMap);
	
	public List<FeatureMapModel>  getAllRoots();
	
	public List<String> getAllRootsName();
	
	public int updateRoot(FeatureMapModel featureMap);
	
	public int deleteRoot(FeatureMapModel featureMap);
	
	public FeatureMapModel  getFeatureMapByName(FeatureMapModel featureMap);
	
	public int updateLockedUserID(FeatureMapModel featureMap);
	
	public Object getLockedUserIDForFeatureMap(FeatureMapModel featureMap);
	
	public String getLockedUserName(Integer userId);
	
	

}
