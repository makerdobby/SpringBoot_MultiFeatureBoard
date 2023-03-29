package com.thymeleaf.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thymeleaf.test.dao.SurveyMapper;
import com.thymeleaf.test.vo.SurveyVo;

@Service
public class SurveyService {

	@Autowired
	SurveyMapper sMapper;

	public List<SurveyVo> getVoList() {
		List<SurveyVo> voList = sMapper.select04();

		return voList;
	}

	public SurveyVo getVoOne(Integer bId) {
		SurveyVo vo = sMapper.select05(bId);

		return vo;
	}

	public void updateBoard(SurveyVo vo) {
		sMapper.update01(vo);

	}

	public void insertBoard(SurveyVo vo) {
		sMapper.insert01(vo);

	}

	public void deleteBoard(SurveyVo vo) {
		sMapper.delete01(vo);
	}
}
