package com.lm.common.test;

import java.io.IOException;
import java.io.Serializable;

class Employee implements Serializable
{
   String name;
   int age;
   double salary;
   transient Thread t = new Thread();
   
   
   public Employee(String name, int age, double salary)
    {
       this .name = name;
       this .age = age;
       this .salary = salary;
   } 
   private void writeObject(java.io.ObjectOutputStream oos) throws IOException
    {
       oos.writeInt(age);
       oos.writeUTF(name);
       System.out.println( " Write Object " );
   } 
   private void readObject(java.io.ObjectInputStream ois) throws IOException
    {
       age = ois.readInt();
       name = ois.readUTF();
       System.out.println( " Read Object " );
   } 

} 
