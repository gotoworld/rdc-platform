package com.hsd.devops.core.support;


public class ObjectKit {
	
	public static boolean equals(Object obj1, Object obj2) {
		return (obj1 != null) ? (obj1.equals(obj2)) : (obj2 == null);
	}
}
