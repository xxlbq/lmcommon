package com.lm.common.jmx.demo.jack;

import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

public class Jack extends NotificationBroadcasterSupport implements JackMBean {

	private int seq = 0;

	public void hi(String name) {
		Notification n = new Notification(// 创建一个信息包
				""+name+".hi",// 给这个Notification起个名称
				this, // 由谁发出的Notification
				++seq,// 一系列通知中的序列号,可以设任意数值
				System.currentTimeMillis(),// 发出时间
				name   // 发出的消息文本
		);
		
		
		// 发出去
		sendNotification(n);
	}

}
