
package com.hsd.devops.core.shiro.check;



public interface ICheck {

    
    boolean check(Object[] permissions);

    
    boolean checkAll();
}
