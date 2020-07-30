package dao;

import java.util.List;
import java.util.Map;

import vo.BoardVO;
import vo.Boardemp_joinVO;

/**
 * 실제 DB와 연결해서 SQL문을 수행하여 결과를 작성해서
 * Service에 전달하는 DAO의 interface
 * 
 * @author 2팀
 *
 */
public interface IBoardDao {
	
	/**
	 * 게시판 글을 등록하는 메서드
	 * @param boardVo 보드VO
	 * @return 성공시 1
	 */
	int insertBoard(Boardemp_joinVO boardVo);
	
	/**
	 * 페이지네이션 모든 게시판을 불러오는 메서드
	 * @param map 시작번호, 끝번호가 담긴 Map 객체
	 * @return 보드 VO가 담긴 List
	 */
	List<BoardVO> getAllBoard(Map<String, Integer> map);
	
	/**
	 * 게시판을 수정하는 메서드
	 * @param boardVo 보드 VO
	 * @return 성공시 1
	 */
	int updateBoard(Boardemp_joinVO boardVo);
	
	/**
	 * 게시판 글을 삭제하는 메서드
	 * @param boardVo 보드 VO
	 * @return 성공시 1
	 */
	int deleteBoard(int boardNo);
	
	/**
	 * 검색한 결과를 불러오는 메서드
	 * @param map 검색어가 담긴 Map 객체
	 * @return 보드 VO객체가 담긴 List
	 */
	List<Boardemp_joinVO> searchBoard(Map<String, String> map);
	
	/**
	 * 자유게시판과 사원이름 조인해서 가져오는 메서드
	 * @return 자유게시판과 사원이름이 담긴  VO List
	 */
	List<Boardemp_joinVO> boardjoinList(Map<String, Integer> map);
	
	
}
