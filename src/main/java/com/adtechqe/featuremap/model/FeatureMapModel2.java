package com.adtechqe.featuremap.model;

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
public class FeatureMapModel2 {

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
	
	public FeatureMapModel2() {
		super();
	}

	public FeatureMapModel2(Integer busprocmapid, String busprocname, String strucdata) {
		super();
		this.busprocmapid = busprocmapid;
		this.busprocname = busprocname;
		this.strucdata = strucdata;
	}

	@Override
	public String toString() {
		return "FeatureMapModel2 [busprocmapid=" + busprocmapid + ", busprocname=" + busprocname + ", strucdata=" + strucdata + "]";
	}




}
