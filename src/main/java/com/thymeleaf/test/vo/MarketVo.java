package com.thymeleaf.test.vo;

import lombok.Data;



@Data
public class MarketVo {

	private int pId;
	private String pCatId;
	private String pTitle;
	private String pPrice;
	private String pFromDt;
	private String pToDt;
	private String pThumbImg;
	private String pDetailImg;
	private String pBrandName;
	private String pRegiDt;

}
