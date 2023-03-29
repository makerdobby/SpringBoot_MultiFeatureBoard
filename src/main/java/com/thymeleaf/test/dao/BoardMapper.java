package com.thymeleaf.test.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.thymeleaf.test.vo.BoardVo;
import com.thymeleaf.test.vo.TestVo;
import com.thymeleaf.test.vo.UserVo;

@Mapper
public interface BoardMapper {

	public Integer select03();
	public List<BoardVo> select04(Map<String, Integer> pageData);
	public BoardVo select05(Integer bId);

	/** 비번 일치 여부를 확인하기 위해, NAME으로 검색해서 비밀번호 출력 */
	public String select06(UserVo uVo);

	/** 기존 가입여부를 알기 위해, NAME으로 검색해서 나온 결과 갯수 COUNT */
	public Integer select07(UserVo uVo);
	public List<TestVo> select08();

	public void update01(BoardVo vo);

	public void delete01(BoardVo vo);

	public void insert01(BoardVo vo);
	public void insert02(UserVo uVo);
	public void insert03(TestVo tVo);
}
