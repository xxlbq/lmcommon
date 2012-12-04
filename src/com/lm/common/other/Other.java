package com.lm.common.other;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Other {

	public static void main(String[] args) {
		
		
//		List<? extends Number> numList = new ArrayList<Number>();
//		numList.add(new BigDecimal("1"));
//		numList.add(new BigDecimal("2"));
//		numList.add(new BigDecimal("3"));
		
		List<? super Number> numList = new ArrayList<Number>();
		numList.add(new BigDecimal("1"));
		numList.add(new BigDecimal("2"));
		numList.add(new BigDecimal("3"));
		
		
		
	}
}
