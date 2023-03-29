package com.thymeleaf.test.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.thymeleaf.test.vo.SurveyVo;

@Mapper
public interface SurveyMapper {

	public List<SurveyVo> select04();
	public SurveyVo select05(Integer bId);
	public void update01(SurveyVo vo);
	public void delete01(SurveyVo vo);
	public void insert01(SurveyVo vo);
}
