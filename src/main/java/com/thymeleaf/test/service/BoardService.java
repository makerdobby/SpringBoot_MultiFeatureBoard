package com.thymeleaf.test.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.thymeleaf.test.dao.BoardMapper;
import com.thymeleaf.test.vo.BoardVo;
import com.thymeleaf.test.vo.TestVo;
import com.thymeleaf.test.vo.UserVo;

@Service
public class BoardService {

	@Autowired
	BoardMapper bMapper;

	public void insertUser(UserVo uVo) {
		bMapper.insert02(uVo);
	}

	// 초기 로딩 화면
	public List<BoardVo> getVoList(Map<String, Integer> pageData) {

		List<BoardVo> voList = bMapper.select04(pageData);

		return voList;
	}

	// 초기 로딩 화면 - 데이터 전체 갯수
	public int getDatacount() {
		int dataCount = bMapper.select03();

		return dataCount;
	}

	public BoardVo getVoOne(Integer bId) {
		BoardVo vo = bMapper.select05(bId);

		return vo;
	}

	public void updateBoard(BoardVo vo) {
		bMapper.update01(vo);

	}

	public void insertBoard(BoardVo vo) {
		bMapper.insert01(vo);

	}

	public void deleteBoard(BoardVo vo) {
		bMapper.delete01(vo);
	}

	public String confirmLogin(UserVo uVo) {
		return bMapper.select06(uVo);
	}

	public Integer checkJoined(UserVo uVo) {
		return bMapper.select07(uVo);

	}

	// 저는 갓 받은 따끈따끈한 file을 DB에 넣는 작업을 해요 출력은 없습니다.
	public void fileUpload(MultipartFile data) {

		try {
			// input Stream을 읽어온다.
			OPCPackage opcPackage = OPCPackage.open(data.getInputStream());

			try (// inputStream을 이용해 엑셀파일 역할을 하는 excel을 받아온다.
			XSSFWorkbook excel = new XSSFWorkbook(opcPackage)) {
				// 첫번째 시트
				XSSFSheet sheet = excel.getSheetAt(0);

				// 시트의 row를 탐색한다
				for( int i=1 ; i <= sheet.getLastRowNum(); i++) {

					// i번째 row
					XSSFRow row = sheet.getRow(i);

					TestVo tVo = new TestVo(row.getCell(0), row.getCell(1));

					// 이걸 이제 DB에 넣어줘
					bMapper.insert03(tVo);
				}
			}


		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// DB정보를 일단 받아오겠습니다. 출력형은 저도 모르겠네요 아직 output stream이 아닐까요..?
	public void fileDownload1( String filePath, String fileName) throws IOException {
		List<TestVo> voList = new ArrayList <TestVo>();
		voList = bMapper.select08();

		// 빈 workbook 생성
		XSSFWorkbook excel = new XSSFWorkbook();

		// 이제 outputStream 작업을 해볼까
		File file = new File(filePath,fileName);
		FileOutputStream out = new FileOutputStream( file );

		try {


			// 빈 sheet 생성
			XSSFSheet sheet = excel.createSheet("sheet1");

			// 한 row씩 내용 채우기

			// 자 row가 총 몇개지? voList의 size만큼! 그럼 그만큼 반복을 먼저하자

			// 일단 첫 번째 행은 그대로 넣고
			XSSFRow row = sheet.createRow(0);
			row.createCell(0).setCellValue("ID");
			row.createCell(1).setCellValue("NAME");

			for(int i=1; i<=voList.size();i++ ) {

				row = sheet.createRow(i);
				row.createCell(0).setCellValue(voList.get(i-1).getId() );
				row.createCell(1).setCellValue(voList.get(i-1).getName());
			}

			excel.write(out);

			// 자 서버에는 저장했으니 이걸 사용자가 download받을 수 있게 해볼까


		}finally {
			try {
				if(excel != null) excel.close();
				if(out != null) out.close();
			}catch( Exception e) {
				e.printStackTrace();
			}
		}

	}


	public void streamTest() throws IOException {
		File file  = new File("data");

		FileInputStream fis = null;
		FileOutputStream fos = null;

		try {
			fos = new FileOutputStream(file, false);
			fos.write(44);


			fis = new FileInputStream(file);
			int data = 0;
			data = fis.read();
			System.out.println(data);


		}finally {
			if(fis != null) {
				fis.close();
			}
			if(fos != null) {
				fos.close();
			}

		}

	}

	public void fileDownload2(HttpServletResponse res) throws IOException {
		List<TestVo> voList = new ArrayList <TestVo>();
		voList = bMapper.select08();

		// 빈 workbook 생성
		XSSFWorkbook excel = new XSSFWorkbook();

		// outputStream 작업(헤더설정)
		String fileName = "DB.xlsx";
		res.setContentType("application/vnd.ms-excel");
		res.setHeader("Content-Disposition", "attachment;filename="+fileName);

		OutputStream out = res.getOutputStream();

		try {


			// 빈 sheet 생성
			XSSFSheet sheet = excel.createSheet("sheet1");

			// 한 row씩 내용 채우기

			// 자 row가 총 몇개지? voList의 size만큼! 그럼 그만큼 반복을 먼저하자

			// 일단 첫 번째 행은 그대로 넣고
			XSSFRow row = sheet.createRow(0);
			row.createCell(0).setCellValue("ID");
			row.createCell(1).setCellValue("NAME");

			for(int i=1; i<=voList.size();i++ ) {

				row = sheet.createRow(i);
				row.createCell(0).setCellValue(voList.get(i-1).getId() );
				row.createCell(1).setCellValue(voList.get(i-1).getName());
			}

			excel.write(out);

			// 자 서버에는 저장했으니 이걸 사용자가 download받을 수 있게 해볼까


		}finally {
			try {
				if(excel != null) excel.close();
				if(out != null) out.close();
			}catch( Exception e) {
				e.printStackTrace();
			}
		}

	}

	public XSSFWorkbook fileDownload() throws IOException {
		List<TestVo> voList = new ArrayList <TestVo>();
		voList = bMapper.select08();

		// 빈 workbook 생성
		XSSFWorkbook excel = new XSSFWorkbook();

		try {


			// 빈 sheet 생성
			XSSFSheet sheet = excel.createSheet("sheet1");

			// 한 row씩 내용 채우기

			// 자 row가 총 몇개지? voList의 size만큼! 그럼 그만큼 반복을 먼저하자

			// 일단 첫 번째 행은 그대로 넣고
			XSSFRow row = sheet.createRow(0);
			row.createCell(0).setCellValue("ID");
			row.createCell(1).setCellValue("NAME");

			for(int i=1; i<=voList.size();i++ ) {

				row = sheet.createRow(i);
				row.createCell(0).setCellValue(voList.get(i-1).getId() );
				row.createCell(1).setCellValue(voList.get(i-1).getName());
			}

			// 자 서버에는 저장했으니 이걸 사용자가 download받을 수 있게 해볼까


		}catch (Exception e) {
			e.printStackTrace();
		}

		return excel;

	}

	public List<BoardVo> getVoList() {
		// TODO Auto-generated method stub
		return null;
	}



}

