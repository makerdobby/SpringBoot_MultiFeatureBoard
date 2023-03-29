package com.thymeleaf.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.thymeleaf.test.service.MarketService;
import com.thymeleaf.test.vo.MarketVo;

@Controller
public class MarketController {

	@Autowired
	MarketService mService;

	// board main 페이지 이동
	@GetMapping("/market")
	public ModelAndView market( ModelAndView mv, @RequestParam(required = false, defaultValue = "1") Integer curPage) {
		System.out.println("market");
		List<MarketVo> mVoList = mService.selectPList();
		mv.addObject("voList", mVoList);

		mv.setViewName("market/seller/home");
		return mv;
	}

	// board 상세 페이지 이동
	@GetMapping("/market/detail")
	public ModelAndView marketDetail( ModelAndView mv) {
		System.out.println("marketDetail 진입");

		mv.setViewName("market/seller/detail");
		return mv;
	}

	// board 상품등록 페이지 이동
	@GetMapping("/market/input")
	public ModelAndView markeInput( ModelAndView mv) {
		System.out.println("markeInput 진입");

		mv.setViewName("market/seller/input");
		return mv;
	}

	// 대 출력
	@ResponseBody
	@GetMapping("/market/ajaxL")
	public List<String> marketAjaxL(){
		return mService.selectLarge();
	}

	// 중 출력
	@ResponseBody
	@GetMapping("/market/ajaxM")
	public List<String> marketAjaxM(){
		return mService.selectMid();
	}

	// 소 출력
	@ResponseBody
	@GetMapping("/market/ajaxS")
	public List<String> marketAjaxS(){
		return mService.selectSmall();
	}



}
