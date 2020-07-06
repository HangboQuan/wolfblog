package com.tencent.service;

import com.tencent.entity.Options;

public interface IOptionsService {

    /**
     * 获得基本信息
     * @return
     */
    Options getOptions();


    void insertOptions(Options options);

    void updateOptions(Options options);

}
