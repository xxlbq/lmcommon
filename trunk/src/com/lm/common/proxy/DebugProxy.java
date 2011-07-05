package com.lm.common.proxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class DebugProxy implements java.lang.reflect.InvocationHandler
{
    private Object obj;

    public static Object newInstance(Object obj)
    {
        return java.lang.reflect.Proxy.newProxyInstance(obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(), new DebugProxy(obj));
    }

    private DebugProxy(Object obj)
    {
        //Greet接口的實現：GreetImpl
        this.obj = obj;
    }

     //Method m：調用的方法
   //Object[] args：方法要傳入的參數
     //invoke实现对GreetImpl中方法的调用，同时也可以在这里加入自己想要实现的操作，
    //虽然调用原GreetImpl中的方法重要，但我想这里更看重的是通过自定义处理实现GreetImpl中没有的功能
    public Object invoke(Object proxy, Method m, Object[] args) throws Throwable
    {
        Object result;
        try
        {
            //自定義的處理
            System.out.println("--before method " + m.getName());
            //調用GreetImpl中方法
            result = m.invoke(obj, args);
        }catch(InvocationTargetException e)
        {
            throw e.getTargetException();
        }catch(Exception e)
        {
            throw new RuntimeException("unexpected invocation exception: " + e.getMessage());
        }finally
        {
            System.out.println("--after method " + m.getName());
        }
        return result;
    }

    /**
     * @param args
     */
//    public static void main(String[] args)
//    {
//        Greet tmp = new GreetImpl();
//        
//        Greet greet = (Greet) DebugProxy.newInstance(tmp);
//           //生成的greet和tmp有相同的hashCode
//           //通过DebugProxy构造的greet比原temp拥有更多功能
//       greet.sayHello("walter");
//           greet.goodBye();
//    }
    
  public static void main(String[] args)
  {
      List<String> tmp = new ArrayList<String>();
      tmp.add("1");
      tmp.add("2");
      tmp.add("3");
      List<String> another = (List<String>) DebugProxy.newInstance(tmp);
      for (String s : another) {
		System.out.println(s);
	}
  }
    
    
}
