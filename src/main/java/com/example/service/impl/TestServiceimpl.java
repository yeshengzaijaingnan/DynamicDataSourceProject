package com.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.mapper1.QigegeDao1;
import com.example.dao.mapper2.laxiaDao2;
import com.example.dao.mapper3.TestDao3;
import com.example.entity.Data3;
import com.example.entity.LaXiaEntity1;
import com.example.entity.QigegeEntity1;
import com.example.entity.headers1;
import com.example.entity.headers2;
import com.example.entity.headers3;
import com.example.entity.headers4;
import com.example.entity.headers5;
import com.example.service.TestService;
@Service
public class TestServiceimpl implements TestService{

	@Autowired
	private QigegeDao1 td1;
	
	@Autowired
	private laxiaDao2 td2;
	//聚石塔销量
	@Autowired
	private TestDao3 td3;
	@Override
	public List<QigegeEntity1> checkDeliveryWeekQigege() {
		return td1.checkDeliveryWeekQigege();
	}
	@Override
	public List<LaXiaEntity1> checkDeliveryWeekLaxia() {
		return td2.checkDeliveryWeekLaxia();
	}
	@Override
	public List<Data3> jstData() {
		
		return td3.jstData();
	}
	@Override
	public List<headers1> purchaseMonthQigege() {
		return td1.purchaseMonthQigege();
	}
	@Override
	public List<headers2> transfeInMonthQigege() {
		return td1.transfeInMonthQigege();
	}
	@Override
	public List<headers1> pinBackMonthQigege() {
		return td1.pinBackMonthQigege();
	}
	@Override
	public List<headers3> sellMonthQigege() {
		return td1.sellMonthQigege();
	}
	@Override
	public List<headers4> transfeOutMonthQigege() {
		return td1.transfeOutMonthQigege();
	}
	@Override
	public List<headers1> feedBackMonthQigege() {
		return td1.feedBackMonthQigege();
	}
	@Override
	public List<headers5> checkMonthQigege() {
		return td1.checkMonthQigege();
	}
	@Override
	public List<headers1> otherOutMonthQigege() {
		return td1.otherOutMonthQigege();
	}
	@Override
	public List<headers1> OtherInMonthQigege() {
		return td1.OtherInMonthQigege();
	}
	
}
