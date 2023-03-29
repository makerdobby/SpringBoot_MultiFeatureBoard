package com.thymeleaf.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thymeleaf.test.dao.MarketMapper;
import com.thymeleaf.test.vo.MarketVo;

@Service
public class MarketService {

	@Autowired
	MarketMapper mMapper;

	public List<MarketVo> selectPList() {
		return mMapper.select01();
	}

	public List<String> selectLarge(){
		return mMapper.select02();
	}

	public List<String> selectMid(){
		return mMapper.select03();
	}

	public List<String> selectSmall(){
		return mMapper.select04();
	}



}

