package com.tencent.dao;

import com.tencent.entity.Options;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IOptionsDao {

    void insert(Options options);

    void update(Options options);

    void deleteById(Integer optionId);

    Options getOptionsById(Integer optionId);

    Options getOptions();
}
