package com.lm.common.jaxb;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyHashMapType {

    public List<MyHashMapEntryType> entry = new ArrayList<MyHashMapEntryType>();
    
    
    public MyHashMapType(Map<String,Integer> map) {
    	
        for( Map.Entry<String,Integer> e : map.entrySet() )
            entry.add(new MyHashMapEntryType(e));
        
    }
    
    public MyHashMapType() {}
}
