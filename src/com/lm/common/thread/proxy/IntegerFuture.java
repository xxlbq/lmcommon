package com.lm.common.thread.proxy;

public class IntegerFuture  extends FutureProxy<DisplayResult> implements DisplayResult{
	IntegerFuture future;
	@Override
	protected DisplayResult createInstance() {
		
		return future;
	}

	@Override
	protected Class<? extends DisplayResult> getInterface() {
		// TODO Auto-generated method stub
		return future.getInterface();
	}

	@Override
	public String display() {
		// TODO Auto-generated method stub
		return future.display();
	}


}
