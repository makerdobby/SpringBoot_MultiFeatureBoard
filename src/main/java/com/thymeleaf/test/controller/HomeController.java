package com.thymeleaf.test.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.thymeleaf.test.service.BoardService;
import com.thymeleaf.test.vo.BoardVo;
import com.thymeleaf.test.vo.UserVo;

@Controller
public class HomeController {

	@Autowired
	BoardService bService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	// 홈페이지 이동
	@GetMapping("/")
	public ModelAndView home( ModelAndView mv) {
		System.out.println("home 진입");

		List<BoardVo> voList = bService.getVoList();
		mv.addObject("voList", voList);

		mv.setViewName("home/index");
		return mv;
	}

	// 로그인 페이지 이동
	@GetMapping("/login")
	public ModelAndView login( ModelAndView mv) {
		System.out.println("login 진입");


		mv.setViewName("home/login");
		return mv;
	}

	// 로그인 페이지 이동
	@GetMapping("/doLogin")
	public String doLogin( UserVo uVo, HttpSession session ) {
		System.out.println("doLogin 진입");
		boolean result = false;

		String rawPw = uVo.getUserPw();
		String encodedPw = bService.confirmLogin(uVo);

		result = passwordEncoder.matches(rawPw, encodedPw);

		if(result) {
			session.setAttribute("userName", uVo.getUserName());
			return "home/index";
		}else {
			return "home/login";
		}
	}


	// 회원가입 페이지 이동
	@GetMapping("/signUp")
	public ModelAndView join( ModelAndView mv) {
		System.out.println("signUp 진입");


		mv.setViewName("home/signUp");
		return mv;
	}

	// 회원가입
	@PostMapping("/dosignUp")
	public ModelAndView dosignUp( ModelAndView mv, UserVo uVo) {
		System.out.println("dosignUp 진입");

		// 비밀번호 암호화
		String encodedPW = passwordEncoder.encode(uVo.getUserPw());
		uVo.setUserPw(encodedPW);

		bService.insertUser(uVo);

		mv.setViewName("redirect:/");
		return mv;
	}

	// 회원가입여부 확인. 반드시 GET으로
	@ResponseBody
	@GetMapping("/signUpAjax")
	public Integer dosignUpAjax( UserVo uVo) {
		System.out.println("dosignUpAjax 진입");

		return bService.checkJoined(uVo);
	}


	// 로그아웃
	@GetMapping("/logout")
	public ModelAndView logout( ModelAndView mv, HttpSession session ) {
		System.out.println("logout 진입");

		//session.removeAttribute("userName");
		session.invalidate();
		mv.setViewName("redirect:/");
		return mv;
	}

	// 파일 페이지로 이동
	@GetMapping("/file")
	public ModelAndView file( ModelAndView mv ) {
		System.out.println("file 진입");

		mv.setViewName("home/file");
		return mv;
	}

	// 파일 업로드
	@PostMapping("/fileUpload")
	public ModelAndView fileUpload( ModelAndView mv, MultipartFile data ) {
		System.out.println("fileUpload 진입");

		// service야 내가 엑셀파일 여기 줄테니까 가서 db에 넣고와
		bService.fileUpload(data);

		mv.setViewName("redirect:/file");
		return mv;
	}

	// 파일 다운로드
	@GetMapping("/fileDownload")
	public void fileDownload(HttpServletResponse res ) throws IOException {
		System.out.println("fileDownload 진입");

		// DB내용 workbook으로 받아오기
		XSSFWorkbook workbook = bService.fileDownload();

		// outputStream 생성
		// 헤더설정
		String fileName = "DB.xlsx";
		res.setContentType("application/vnd.ms-excel");
		res.setHeader("Content-Disposition", "attachment;filename="+fileName);


		OutputStream out = res.getOutputStream();

		try{
			// workbook 내용 reponse에 write
			workbook.write(out);


		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (workbook != null) workbook.close();
				if (out != null) {out.close(); out.flush();}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}


}
