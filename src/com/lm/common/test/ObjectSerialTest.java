package com.lm.common.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

class ObjectSerialTest
{
   public static void main(String[] args) throws Exception
    {
       Employee e1 = new Employee( " zhangsan " , 25 , 3000.50 );
       Employee e2 = new Employee( " lisi " , 24 , 3200.40 );
       Employee e3 = new Employee( " wangwu " , 27 , 3800.55 );
      
       FileOutputStream fos = new FileOutputStream( " employee.txt " );
       ObjectOutputStream oos = new ObjectOutputStream(fos);
       oos.writeObject(e1);
       oos.writeObject(e2);
       oos.writeObject(e3);
       oos.close();
      
       FileInputStream fis = new FileInputStream( " employee.txt " );
       java.io.ObjectInputStream ois = new ObjectInputStream(fis);
       Employee e;
       for ( int i = 0 ;i < 3 ;i ++ )
        {
           e = (Employee)ois.readObject();
           System.out.println(e.name + " : " + e.age + " : " + e.salary);
       } 
       ois.close();
   } 
} 
