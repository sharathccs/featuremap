package com.adtechqe.featuremap.controller;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager; import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.webui.model.BusinessProcessModel;
import com.webui.model.TestExecutionQueueMaster;
import com.webui.service.BusinessProcessService;
import com.webui.util.Status;
import com.webui.util.StatusObjectCreator;

@RestController
public class FeatureMapController {


	private static Logger log = LogManager.getLogger(ComponentsController.class);
	
	@Autowired
	private FeatureMapService featureMapService;

	HttpHeaders headers = new HttpHeaders();
	Status status = new Status();
	

    /**
	 * insertRoot() method is used to inserting root content to DB
	 * 
	 * @param featureMap
	 * @return ResponseEntity<Status>
	 */
	@RequestMapping(value = "/insertRoot", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Status> insertRoot(@RequestBody featureMapModel featureMap) {
		
		log.info("In FeatureMapController | insertRoot() method | inserting root:" + featureMap.getBusprocname());
		FeatureMapModel rval = FeatureMapService.insertRootNode(featureMap);
		if (rval == null) {
			status = StatusObjectCreator.getStatusForDuplicates(-1, null);
			log.error("In FeatureMapContoller  | insertRoot() method | inserting root not found:"	);
		} else {
			status = StatusObjectCreator.getStatusForDuplicates(1, rval);
			}
		log.info("In FeatureMapContoller | insertRoot() method | Successfully inserting root:"	+ featureMap.getBusprocname());
		return new ResponseEntity<Status>(status, headers, HttpStatus.OK);
		
	}
	

    /**
	 * getAllRoots() method is used to fetching all the root content from DB
	 * 
	 * @return ResponseEntity<List<FeatureMapModel>>
	 */
	@RequestMapping(value = "/getAllRoots", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<FeatureMapModel>> getAllRoots() {
		
		log.info("In FeatureMapController | getAllRoots() method | retreiving roots:");
		List<FeatureMapModel> featureMapList = FeatureMapService.getAllRoots();
		if (featureMapList == null || featureMapList.isEmpty()) {
			log.error("In FeatureMapController | getAllRoots() method | retreiving roots data not found");
		}
		log.info("In FeatureMapController | getAllRoots() method | successfully retreiving Roots:");
		return new ResponseEntity<List<FeatureMapModel>>(featureMapList, headers, HttpStatus.OK);
	
	}
	

    /**
	 * getAllRootName() method is used to fetching all the rootname content from DB
	 * 
	 * @return ResponseEntity<List<String>>
	 */
	@RequestMapping(value = "/getAllRootName", method = RequestMethod.GET, produces = "application/json")
	public  ResponseEntity<List<String>> getAllRootName()
	{	
		log.info("In FeatureMapController | getAllRootName() method | retriving rootsname:");
		List<String> featureMapList = FeatureMapService.gerAllRootsName();
		if (featureMapList == null || featureMapList.isEmpty()) {
			log.error("In FeatureMapController | getAllRootName() method | retriving rootname data not found");
		}
		log.info("In FeatureMapController | getAllRootName() method | successfully retrieved  RootNames:");
		return new ResponseEntity<List<String>>(featureMapList, headers, HttpStatus.OK);
	}
	
    /**
	 * updateRoot() method is used to update the root content in DB
	 * 
	 * @param featureMap
	 * @return ResponseEntity<Status>
	 */
	@RequestMapping(value = "/updateRoot", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<Status> updateRoot(@RequestBody FeatureMapModel featureMap) {
		
		log.info("In FeatureMapController | updateRoot() method | update root:" + featureMap.getBusprocname());
		int count = FeatureMapService.updateRoot(featureMap);
		Status status = StatusObjectCreator.getStatusForUpdate(count, featureMap);
		log.info("In FeatureMapController | updateRoot() method | Successfully update root:"	+ featureMap.getBusprocname());
		return new ResponseEntity<Status>(status, headers, HttpStatus.OK);
		
	}
	

    /**
	 * deleteRoot() method is used to delete the root content in DB
	 * 
	 * @param businessprocess
	 * @return ResponseEntity<Status>
	 */
	@RequestMapping(value = "/deleteRoot", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<Status> deleteRoot(@RequestBody FeatureMapModel featureMap) {
		
		log.info("In FeatureMapController | deleteRoot() method | delete root:" + featureMap.getBusprocname());
		int count = FeatureMapService.deleteRoot(featureMap);
		Status status = StatusObjectCreator.getStatusForDelete(count, featureMap);
		log.info("In FeatureMapController | deleteRoot() method | Successfully deleted root:"	+ featureMap.getBusprocname());
		return new ResponseEntity<Status>(status, headers, HttpStatus.OK);
		
	}
	
    /**
	 * getBusinessProcess() method is used to fetch a root content from DB
	 * 
	 * @return ResponseEntity<BusinessProcessModel>
	 */
	@RequestMapping(value = "/getFeatureMap", method = RequestMethod.GET,  produces = "application/json")
	public ResponseEntity<FeatureMapModel> getFeatureMap(@RequestParam("featureMap") String featureMap) {
		
		log.info("In FeatureMapController | getFeatureMap() method | get root:" + featureMap);
		FeatureMapModel featureMapModel= new FeatureMapModel();
		
		featureMapModel.setBusprocname(featureMap);
		log.info("In FeatureMapController | getFeatureMap() method | retriving root:");
		FeatureMapModel featureMapList = FeatureMapService.getFeatureMapByName(FeatureMapModel);
		log.info("In FeatureMapController | getFeatureMap() method | successfully retriving Root:");
		return new ResponseEntity<FeatureMapModel>(featureMapList, headers, HttpStatus.OK);
	
	}
	

    /**
	 * updateLockedUserID() method is used to update the lockedUserID content in DB
	 * 
	 * @param featureMap
	 * @return ResponseEntity<Status>
	 */
	@RequestMapping(value = "/updateLockedUserId", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<Status> updateLockedUserID(@RequestBody FeatureMapModel featureMap) {
		
		log.info("In FeatureMapController | updateLockedUserID() method | update lockUserID:" + featureMap.getBusprocname());
		int count = FeatureMapService.updateLockedUserID(featureMap);
		Status status = StatusObjectCreator.getStatusForUpdate(count, featureMap);
		log.info("In FeatureMapController | updateLockedUserID() method | Successfully update lockedUserID:"	+ featureMap.getBusprocname());
		return new ResponseEntity<Status>(status, headers, HttpStatus.OK);
		
	}
	

    /**
	 * getLockedUserName() method is used to fetch a User Name content from DB
	 * 
	 * @return ResponseEntity<String>
	 */
	@RequestMapping(value = "/getLockedUserName", method = RequestMethod.GET,  produces = "application/json")
	public ResponseEntity<String> getLockedUserName(@RequestParam("userId") Integer UserId) {
		log.info("In FeatureMapController | getLockedUserName() method | retriving userName:");
		String userName =FeatureMapService.getLockedUserName(UserId);
		log.info("In BusinessProcessController | getLockedUserName() method | successfully retriving userName:");
		return new ResponseEntity<String>(userName, headers, HttpStatus.OK);
	}


    /**
	 * getAllQueues() method is used to fetching all Queues which are not manual .
	 * 
	 * @return ResponseEntity<List<Object>>
	 */
	@RequestMapping(value = "/getAllQueues", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Object>> getAllQueues() {
		
		log.info("In FeatureMapController | getAllQueues() method | retriving Queues:");
		List<Object> queueList = FeatureMapService.getAllQueues();
		log.info("In FeatureMapController  | getAllQueues() method | successfully retriving Queues:");
		return new ResponseEntity<List<Object>>(queueList, headers, HttpStatus.OK);
	
	}


}
