package com.tencent.service.impl;

import com.tencent.dao.IOptionsDao;
import com.tencent.entity.Options;
import com.tencent.service.IOptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service("optionsService")
public class OptionsServiceImpl implements IOptionsService {

    @Autowired(required = false)
    private IOptionsDao optionsDao;

    @Override
    @Cacheable(value = "default",key = "'options'")
    public Options getOptions() {
        return optionsDao.getOptions();
    }

    @Override
    @CacheEvict(value = "default",key = "'options'")
    public void insertOptions(Options options) {
        optionsDao.insert(options);
    }

    @Override
    @CacheEvict(value = "default",key = "'options'")
    public void updateOptions(Options options) {
        optionsDao.update(options);
    }
}
