package com.adtechqe.featuremap.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.adtechqe.featuremap.model.FeatureMapModel;
import com.adtechqe.featuremap.model.FeatureMapModel2;

public class FeatureMapServiceImpl2 implements FeatureMapService2 {
    
    
    @Autowired
	private FeatureMapRepository2 featureMapRepository2;

    private static Logger log = LogManager.getLogger(FeatureMapServiceImpl2.class);


    public FeatureMapModel2 getFeatureMapByName2   (FeatureMapModel2 featureMap2) {
		log.debug("In FeatureMapServiceImpl2 | getFeatureMapByName2() method | Get root:"
				+ featureMap2.getFeatureMapByName());
		FeatureMapModel2 featureMap2 = featureMapRepository2.
				.getFeatureMap(featureMap.getBusprocname());
		return featureMap2;
	}




}
