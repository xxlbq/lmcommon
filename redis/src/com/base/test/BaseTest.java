package com.base.test;

import redis.clients.jedis.Jedis;

public class BaseTest {
//    public static void main(String args[]) {
//        //����redis����
//        Jedis jedis = new Jedis("10.18.28.94", 6379);          
//        //������֤-�����û������redis����ɲ���֤����ʹ���������
//        jedis.auth("abcdefg");
//        //�򵥵�key-value �洢
//        jedis.set("tn", "thisuc");
//        System.out.println(jedis.get("tn"));
//        //��ԭ��ֵ�û��������,����֮ǰû�и�key�������key
//        //֮ǰ�Ѿ��趨��redis��Ӧ"myredis",�˾�ִ�б��ʹredis��Ӧ"myredisyourredis"
//        jedis.append("redis", "yourredis");
//        jedis.append("content", "rabbit");
//        
//        
//        //mset �����ö��key-valueֵ ������key1,value1,key2,value2,...,keyn,valuen��
//        //mget �ǻ�ȡ���key����Ӧ��valueֵ ������key1,key2,key3,...,keyn�� ���ص��Ǹ�
//        
//        jedis.mset("name1","thisuc","name2","gaoxc","name3","lemon");
//        System.out.println(jedis.mget("name1","name2","name3")); 
//        
//    }
	
	
	
	
	 public static void main(String[] args) {
	        // TODO Auto-generated method stub
	        Jedis jj = new  Jedis("localhost");
	        jj.set("key1", "I am value 1");
	        String ss = jj.get("key1");
	        System.out.println(ss);
	    }
}
