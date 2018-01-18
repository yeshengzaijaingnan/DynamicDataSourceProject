package com.example.dao.mapper2;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.entity.LaXiaEntity1;



@Mapper
public interface laxiaDao2 {
	//拉夏快递是否发出
	List<LaXiaEntity1> checkDeliveryWeekLaxia();
}
