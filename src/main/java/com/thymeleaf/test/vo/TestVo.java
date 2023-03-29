package com.thymeleaf.test.vo;

import org.apache.poi.xssf.usermodel.XSSFCell;

import lombok.Data;

@Data
public class TestVo {

	public TestVo(Integer cell, String cell2) {
		this.setId(cell);
		this.setName(cell2);
	}

	public TestVo(XSSFCell cell, XSSFCell cell2) {

		// 첫번 째 값을 toString 으로 받아온다.
		String id = cell.toString();

		// 만약 . 이 있다면 .전까지 자른다
		id = id.substring(0, id.indexOf('.'));

		// 숫자로 바꿔서 넣어준다
		this.setId(Integer.parseInt(id));

		// Name은 그냥 넣어준다.
		String name = cell2.toString();
		this.setName(name);
	}

	private Integer id;
	private String name;

}
