package com.adtechqe.featuremap.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.adtechqe.featuremap.model.FeatureMapModel;

import com.adtechqe.featuremap.model.




public class FeatureMapService2 {

    public XSSFWorkbook downloadBusinessProcess(String businessprocess);
	
	public FeatureMapModel readBusinessProcess(MultipartFile file);

	public List<?>  getManualTest(Integer projectId);



}
