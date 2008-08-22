package com.lm.common.util.number;

import java.math.BigDecimal;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;

public class NumberCommonUtil {
	
	
	public static 
	String decimalFormat(BigDecimal price,NumberFormat nf){
		
		String formatString = null;
		formatString = nf.format(price.longValue());
	    return formatString;
	}
	
	
	public static 
	String decimalFormat(int number,NumberFormat nf){
		
		String formatString = null;
		formatString = nf.format(number);
	    return formatString;
	}

	
	public static void main(String[] args) {
		System.out.println(NumberCommonUtil.decimalFormat(0, new NumberFormat(){

			@Override
			public StringBuffer format(double number, StringBuffer toAppendTo,
					FieldPosition pos) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public StringBuffer format(long number, StringBuffer toAppendTo,
					FieldPosition pos) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Number parse(String source, ParsePosition parsePosition) {
				// TODO Auto-generated method stub
				return null;
			}
			
		}));
	}
}
