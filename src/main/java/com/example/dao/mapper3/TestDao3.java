package com.example.dao.mapper3;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.entity.Data3;


@Mapper
public interface TestDao3 {
	/**
	 * 聚石塔销售数据
	 * @return
	 */
	List<Data3> jstData();
}
