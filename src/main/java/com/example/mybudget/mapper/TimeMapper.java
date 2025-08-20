package com.example.mybudget.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TimeMapper {
    String selectTime();

    @Select("SELECT 1 +1")
    String selectTime2();
}













