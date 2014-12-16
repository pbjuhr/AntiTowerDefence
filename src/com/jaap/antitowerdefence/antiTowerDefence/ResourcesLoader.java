package com.jaap.antitowerdefence.antiTowerDefence;

import java.io.InputStream;

final public class ResourcesLoader {
    

    public static InputStream load(String path){
	InputStream input = ResourcesLoader.class.getResourceAsStream(path);

	if(input==null){
	    input = ResourcesLoader.class.getResourceAsStream("/"+path);
	}
	
	
	
	
	
//	System.out.println(">>>>>>>>>>>>>>>>>>"+path);
//	File file = new File(ResourcesLoader.class.getResource(path).getPath());
//	System.out.println(">>>>>>>>>>>>>>>>>>"+file);
//	if(file.exists()){
////	    System.out.println("if null");
////	    input = 
//	    file = new File(ResourcesLoader.class.getResource("/"+path).getPath());
//	    System.out.println(">>>>>>>>>>>>>>>>>>"+file);
////	    System.out.println(input==null);
//	}
	
	return input;
    }

}
