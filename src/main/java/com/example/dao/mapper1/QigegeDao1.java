package com.example.dao.mapper1;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.entity.QigegeEntity1;
import com.example.entity.headers1;
import com.example.entity.headers2;
import com.example.entity.headers3;
import com.example.entity.headers4;
import com.example.entity.headers5;


@Mapper
public interface QigegeDao1 {
	//取消的快递发出
	List<QigegeEntity1> checkDeliveryWeekQigege();
	//采购入库
	List<headers1> purchaseMonthQigege();
	//调拨入库
	List<headers2> transfeInMonthQigege();
	//销退
	List<headers1> pinBackMonthQigege();
	//销售出库
	List<headers3> sellMonthQigege();
	//调拨出库
	List<headers4> transfeOutMonthQigege();
	//采退
	List<headers1> feedBackMonthQigege();
	//盘点
	List<headers5> checkMonthQigege();
	//其他出库
	List<headers1> otherOutMonthQigege();
	//其他入库
	List<headers1> OtherInMonthQigege();
}
