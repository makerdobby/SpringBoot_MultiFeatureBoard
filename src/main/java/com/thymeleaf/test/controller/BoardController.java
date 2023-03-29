package com.thymeleaf.test.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.thymeleaf.test.service.BoardService;
import com.thymeleaf.test.vo.BoardVo;

@Controller
public class BoardController {

	@Autowired
	BoardService bService;

	@GetMapping("/board/test")
	public String boardTest(  BoardVo bVo) {
		System.out.println("hello!");


		System.out.println(bVo.getVoList().get(0).getBTitle());
		System.out.println(bVo.getVoList().get(0).getBCont());
		System.out.println(bVo.getVoList().get(1).getBTitle());
		System.out.println(bVo.getVoList().get(1).getBCont());


		return "/board/input";
	}




	// board main 페이지 이동
	@GetMapping("/board")
	public ModelAndView board( ModelAndView mv, @RequestParam(required = false, defaultValue = "1") Integer curPage) {
		System.out.println("board 진입");
		int pageSize = 5;
		int startRow = (curPage -1)*pageSize;

		// DB에 넣을 파라미터 Map 만들기
		Map<String, Integer> pageData = new HashMap<>();
		pageData.put("startRow", startRow);
		pageData.put("pageSize", pageSize);

		List<BoardVo> voList = bService.getVoList(pageData);

		mv.addObject("voList", voList);

		// 전체 페이지
		int dataCount = bService.getDatacount();
		int lastPage = (int) Math.ceil((double)dataCount/(double)pageSize);
		mv.addObject("lastPage",lastPage);


		mv.setViewName("board/home");
		return mv;
	}
	/*
	 * // board main 페이지 이동
	 *
	 * @ResponseBody
	 *
	 * @GetMapping("/boardAjax") public List<BoardVo> boardAjax( ModelAndView
	 * mv, @RequestParam(required = false, defaultValue = "1") Integer curPage) {
	 * System.out.println("boardAjax 진입"); int pageSize = 3; int startRow = (curPage
	 * -1)*pageSize;
	 *
	 * // DB에 넣을 파라미터 Map 만들기 Map<String, Integer> pageData = new HashMap<>();
	 * pageData.put("startRow", startRow); pageData.put("pageSize", pageSize);
	 *
	 * List<BoardVo> voList = bService.getVoList(pageData); mv.addObject("voList",
	 * voList); return voList; }
	 *
	 * @ResponseBody
	 *
	 * @GetMapping("/lastPageAax") public lastPage lastPageAax() {
	 * System.out.println("lastPageAax 진입"); int pageSize = 3;
	 *
	 * // 전체 페이지 int dataCount = bService.getDatacount(); int lastPage = (int)
	 * Math.ceil((double)dataCount/(double)pageSize);
	 *
	 * return lastPage; }
	 */

	// board detail 페이지 이동
	@GetMapping("/board/detail")
	public ModelAndView boardDetail( ModelAndView mv, Integer bId) {
		System.out.println("boardDetail 진입");

		BoardVo vo = bService.getVoOne(bId);
		mv.addObject("vo", vo);

		mv.setViewName("board/detail");
		return mv;
	}

	// board input (등록)페이지 이동
	@GetMapping("/board/input")
	public ModelAndView boardInput( ModelAndView mv) {
		System.out.println("boardInput 진입");

		List<BoardVo> voList = bService.getVoList();
		mv.addObject("voList", voList);

		mv.setViewName("board/input");
		return mv;
	}

	// board input 실행
	@PostMapping("/board/insert")
	public ModelAndView boardInsert( ModelAndView mv , BoardVo vo) {
		System.out.println("boardInsert 진입");

		bService.insertBoard(vo);

		mv.setViewName("redirect:/board");
		return mv;
	}

	// board update 실행
	@PostMapping("/board/update")
	public ModelAndView boardUpdate( ModelAndView mv, BoardVo vo) {
		System.out.println("boardInput 진입");

		bService.updateBoard(vo);

		mv.setViewName("redirect:/board");
		return mv;
	}

	// board delete 실행
	@PostMapping("/board/delete")
	public ModelAndView boardDelete( ModelAndView mv, BoardVo vo) {
		System.out.println("boardDelete 진입");

		bService.deleteBoard(vo);

		mv.setViewName("redirect:/board");
		return mv;
	}


}
