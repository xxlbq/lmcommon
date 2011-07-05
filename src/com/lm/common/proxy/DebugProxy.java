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
        //Greet�ӿڵČ��F��GreetImpl
        this.obj = obj;
    }

     //Method m���{�õķ���
   //Object[] args������Ҫ����ą���
     //invokeʵ�ֶ�GreetImpl�з����ĵ��ã�ͬʱҲ��������������Լ���Ҫʵ�ֵĲ�����
    //��Ȼ����ԭGreetImpl�еķ�����Ҫ����������������ص���ͨ���Զ��崦��ʵ��GreetImpl��û�еĹ���
    public Object invoke(Object proxy, Method m, Object[] args) throws Throwable
    {
        Object result;
        try
        {
            //�Զ��x��̎��
            System.out.println("--before method " + m.getName());
            //�{��GreetImpl�з���
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
//           //���ɵ�greet��tmp����ͬ��hashCode
//           //ͨ��DebugProxy�����greet��ԭtempӵ�и��๦��
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
