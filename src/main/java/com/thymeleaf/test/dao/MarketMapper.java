package com.thymeleaf.test.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.thymeleaf.test.vo.MarketVo;

@Mapper
public interface MarketMapper {

	public List<MarketVo> select01();
	public List<String> select02();
	public List<String> select03();
	public List<String> select04();

}
