package com.lm.common.Observer2;

import java.util.Observable;
import java.util.Observer;

public class Observer2 implements Observer {

	@Override
	public void update(Observable o, Object arg) {
		int n = Integer.parseInt(String.valueOf(arg));
		if(n > 5){
			System.out.println("Observer 2 :"+ n);
		}
	}

}
