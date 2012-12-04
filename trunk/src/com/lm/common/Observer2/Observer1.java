package com.lm.common.Observer2;

import java.util.Observable;
import java.util.Observer;

public class Observer1 implements Observer {

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		System.out.println("Observer 1 :"+String.valueOf(arg));
	}

}