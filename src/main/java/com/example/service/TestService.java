package com.example.service;

import java.util.List;

import com.example.entity.Data3;
import com.example.entity.LaXiaEntity1;
import com.example.entity.QigegeEntity1;
import com.example.entity.headers1;
import com.example.entity.headers2;
import com.example.entity.headers3;
import com.example.entity.headers4;
import com.example.entity.headers5;

public interface TestService {
	List<QigegeEntity1>checkDeliveryWeekQigege();
	List<LaXiaEntity1> checkDeliveryWeekLaxia();
	List<Data3> jstData();
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
