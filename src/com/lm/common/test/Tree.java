package com.lm.common.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Tree implements java.io.Serializable  { 
	     public Tree left; 
	     public Tree right; 
	     public int id; 
	     public int level; 

	     private static int count = 0 ; 

	     public Tree( int depth)  { 
	         id = count ++ ; 
	         level = depth; 
	         if (depth > 0 )  { 
	             left = new Tree(depth - 1 ); 
	             right = new Tree(depth - 1 ); 
	         } 
	     } 

	     public void print( int levels)  { 
	         for ( int i = 0 ; i < level; i ++ ) 
	             System.out.print( "    " ); 
	         System.out.println( " node " + id); 

	         if (level <= levels && left != null ) 
	             left.print(levels); 

	         if (level <= levels && right != null ) 
	             right.print(levels); 
	     } 


	     public static void main (String argv[])  { 

	         try  { 
	             /**/ /* 创建一个文件写入序列化树。 */ 
	             FileOutputStream ostream = new FileOutputStream( " tree.tmp " ); 
	             /**/ /* 创建输出流 */ 
	             ObjectOutputStream p = new ObjectOutputStream(ostream); 

	             /**/ /* 创建一个二层的树。 */ 
	             Tree base = new Tree( 2 ); 

	             p.writeObject(base); // 将树写入流中。 
	              p.writeObject( " LiLy is 惠止南国 " );
	             p.flush(); 
	             ostream.close();     // 关闭文件。 
	 
	              /**/ /* 打开文件并设置成从中读取对象。 */ 
	             FileInputStream istream = new FileInputStream( " tree.tmp " ); 
	             ObjectInputStream q = new ObjectInputStream(istream); 

	             /**/ /* 读取树对象，以及所有子树 */ 
	             Tree new_tree = (Tree)q.readObject(); 

	             new_tree.print( 2 );   // 打印出树形结构的最上面 ２级 
	              String name = (String)q.readObject();
	             System.out.println( " \n " + name);
	         } catch (Exception ex)  { 
	             ex.printStackTrace(); 
	         } 
	     } 
	} 
