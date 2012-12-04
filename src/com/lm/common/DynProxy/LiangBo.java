package com.lm.common.DynProxy;

public class LiangBo implements Singer {
	public String name;
	public String teacher;
	public LiangBo(String n) {
		this.name = n;
		this.teacher = "那英";
		System.out.println(this.name+" 初始化  ...");
	}
	@Override
	public void sing() {
		// TODO Auto-generated method stub
		System.out.println(this.name+" 在唱歌");
	}
	
	public void singLiangBo(){
		
		sing();
		System.out.println("导师："+teacher);
	}
}
