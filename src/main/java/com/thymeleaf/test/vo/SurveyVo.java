package com.thymeleaf.test.vo;

import java.util.List;

import lombok.Data;

@Data
public class SurveyVo {

	private int bId;
	private String bTitle;
	private String bCont;
	private String bMan;
	private String bDt;
	private List<SurveyVo> voList;

}
