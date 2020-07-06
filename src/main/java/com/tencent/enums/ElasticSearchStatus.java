package com.tencent.enums;

public enum ElasticSearchStatus{

    OPEN_ELASTICSEARCH("open_elasticsearch");

    private String prop;

    ElasticSearchStatus(String prop){
        this.prop = prop;
    }

    public String getProp(){
        return prop;
    }
}
