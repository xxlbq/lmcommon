package com.lm.common.DynProxy;

public class MoMoWu implements Singer {
	public String name;
	public String teacher;
	
	
	public MoMoWu(String n) {
		this.name = n;
		this.teacher = "庾澄庆";
		System.out.println(this.name+" 初始化  ...");
	}
	@Override
	public void sing() {
		// TODO Auto-generated method stub
		System.out.println(this.name+" 在唱歌");
	}
	
	public void singMoMoWu(){
		
		sing();
		System.out.println("导师："+teacher);
	}
	
	
}
