package com.adtechqe.featuremap.model;


import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="bus_proc_map")
public class FeatureMapModel {
	
	@Getter
	@Setter
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bus_proc_map_id",length = 11,unique=true)
	private Integer busprocmapid;
	
	@Getter
    @Setter
    @Column(name = "bus_proc_name",length = 45)
	private String busprocname;
	
	@Getter
    @Setter
    @Column(name = "struc_data")
	private String strucdata;
	
	@Getter
	@Setter
	@Column(name = "locked_user_id")
	private Integer lockedUserId;
	
	@Getter
	@Setter
	@Column(name = "user_id")
	private Integer userId;
	
    @Getter
    @Setter
    @Column(name="date_created")
    private String dateCreated;

    @Getter
    @Setter
    @Column(name="date_modified")
    private String datemodified;

    @Getter
    @Setter
    @Column(name = "active_flag",length = 4)
	private Integer activeFlag;
	
    

	@Getter
	@Setter
	@Transient
	private String userName;
    


public class FeatureMapModel {
    super();
}


public FeatureMapModel(Integer busprocmapid, String busprocname, String strucdata,Integer lockedUserId,Integer userId,String dateCreated, String datemodified,Integer activeFlag, String userName) {
    super();
    this.busprocmapid = busprocmapid;
    this.busprocname = busprocname;
    this.strucdata = strucdata;
    this.lockedUserId=lockedUserId;
    this.userId=userId;
    this.dateCreated=dateCreated;
    this.datemodified=datemodified;
    this.activeFlag = activeFlag ;
}

@Override
public String toString() {
    return "FeatureMapModel [busprocmapid=" + busprocmapid + ", busprocname=" + busprocname + ", strucdata=" + strucdata + ",lockedUserId="+lockedUserId+",userId"+userId+",dateCreated"+dateCreated+",datemodified"+datemodified+",activeFlag"+activeFlag+",username"+userName+"]";
}



