package com.thymeleaf.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.thymeleaf.test.service.SurveyService;
import com.thymeleaf.test.vo.SurveyVo;

@Controller
public class SurveyController {

	@Autowired
	SurveyService sService;

	// survey main 페이지 이동
	@GetMapping("/survey")
	public ModelAndView survey( ModelAndView mv) {
		System.out.println("survey 진입");

		List<SurveyVo> voList = sService.getVoList();
		mv.addObject("voList", voList);

		mv.setViewName("survey_admin/home");
		return mv;
	}

	// survey detail 페이지 이동
	@GetMapping("/survey/detail")
	public ModelAndView surveyDetail( ModelAndView mv, Integer bId) {
		System.out.println("surveyDetail 진입");

		SurveyVo vo = sService.getVoOne(bId);
		mv.addObject("vo", vo);

		mv.setViewName("survey_admin/detail");
		return mv;
	}

	// survey input (등록)페이지 이동
	@GetMapping("/survey/input")
	public ModelAndView surveyInput( ModelAndView mv) {
		System.out.println("surveyInput 진입");

		List<SurveyVo> voList = sService.getVoList();
		mv.addObject("voList", voList);

		mv.setViewName("survey_admin/input");
		return mv;
	}

	// survey input 실행
	@PostMapping("/survey/insert")
	public ModelAndView surveyInsert( ModelAndView mv , SurveyVo vo) {
		System.out.println("surveyInsert 진입");

		sService.insertBoard(vo);

		mv.setViewName("redirect:/survey");
		return mv;
	}

	// survey update 실행
	@PostMapping("/survey/update")
	public ModelAndView surveyUpdate( ModelAndView mv, SurveyVo vo) {
		System.out.println("surveyInput 진입");

		sService.updateBoard(vo);

		mv.setViewName("redirect:/survey");
		return mv;
	}

	// survey delete 실행
	@PostMapping("/survey/delete")
	public ModelAndView surveyDelete( ModelAndView mv, SurveyVo vo) {
		System.out.println("surveyDelete 진입");

		sService.deleteBoard(vo);

		mv.setViewName("redirect:/survey");
		return mv;
	}


}
