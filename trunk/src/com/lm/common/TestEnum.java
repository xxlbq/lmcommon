package com.lm.common;

public class TestEnum {
	
	private final int value = 0;
	
	public enum PlayerTypeEnum {

		NORMAL(0),NEWER(-1),MASTER(1);
		
		PlayerTypeEnum(int value){
			this.value= value;
		}
		
		private final int value;

		public int getValue() {
			return value;
		}
	}
	
	public static void main(String[] args) {
		for ( PlayerTypeEnum pe: PlayerTypeEnum.values()) {
			System.out.println(pe.getValue());
		}
	}
}
