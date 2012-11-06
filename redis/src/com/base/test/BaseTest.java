package com.base.test;

import redis.clients.jedis.Jedis;

public class BaseTest {
//    public static void main(String args[]) {
//        //连接redis服务
//        Jedis jedis = new Jedis("10.18.28.94", 6379);          
//        //密码验证-如果你没有设置redis密码可不验证即可使用相关命令
//        jedis.auth("abcdefg");
//        //简单的key-value 存储
//        jedis.set("tn", "thisuc");
//        System.out.println(jedis.get("tn"));
//        //在原有值得基础上添加,如若之前没有该key，则导入该key
//        //之前已经设定了redis对应"myredis",此句执行便会使redis对应"myredisyourredis"
//        jedis.append("redis", "yourredis");
//        jedis.append("content", "rabbit");
//        
//        
//        //mset 是设置多个key-value值 参数（key1,value1,key2,value2,...,keyn,valuen）
//        //mget 是获取多个key所对应的value值 参数（key1,key2,key3,...,keyn） 返回的是个
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
