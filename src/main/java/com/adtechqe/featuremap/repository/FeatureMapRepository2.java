package com.adtechqe.featuremap.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.adtechqe.featuremap.model.FeatureMapModel;



@Transactional
public class FeatureMapRepository2 extends JpaRepository<FeatureMapModel,Integer> {

    @Query(value = "select * from bus_proc_map where bus_proc_name = :rootname ",nativeQuery = true)
	FeatureMapModel getFeatureMap(@Param("rootname") String rootname);
	
	@Modifying
	@Query(value = "UPDATE bus_proc_map set struc_data = :data where bus_proc_name = :rootname ",nativeQuery = true)
	void updateRoot(@Param("data")String data,@Param("rootname")String rootname);
	
	


}
