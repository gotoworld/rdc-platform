package com.hsd.devops.modular.system.service;


public interface IDictService {

    
    void addDict(String dictName, String dictValues);

    
    void editDict(Integer dictId, String dictName, String dicts);

    
    void delteDict(Integer dictId);

}
