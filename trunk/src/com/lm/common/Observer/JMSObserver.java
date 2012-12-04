package com.lm.common.Observer;

import java.util.Observable;
import java.util.Observer;

/**
 * @author 张荣华(ahuaxuan)
 * @version $Id$
 */
public class JMSObserver implements Observer{

	public void update(Observable o, Object arg) {
		System.out.println("发送消息给jms服务器的观察者已经被执行");
	}
}
