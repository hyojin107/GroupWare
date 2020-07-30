package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import vo.BoardVO;
import vo.Boardemp_joinVO;

/**
 * Service객체는 DAO에 작성된 메서드를 원하는 작업에 맞게 호출하여 결과를 받아오고,
 * 받아온 자료를 Controller에게 보내주는 역할을 수행한다.
 * 
 * 보통 DAO의 메서드와 같은 구조로 되어있다.
 * 
 * @author 2팀
 *
 */
public interface IBoardService extends Remote {

	/**
	 * 게시판 글을 등록하는 메서드
	 * @param boardVo 보드VO
	 * @return 성공시 1
	 */
	int insertBoard(Boardemp_joinVO boardVo) throws RemoteException;
	
	/**
	 * 페이지네이션 모든 게시판을 불러오는 메서드
	 * @param map 시작번호, 끝번호가 담긴 Map 객체
	 * @return 보드 VO가 담긴 List
	 */
	List<BoardVO> getAllBoard(Map<String, Integer> map) throws RemoteException;
	
	/**
	 * 게시판을 수정하는 메서드
	 * @param boardVo 보드 VO
	 * @return 성공시 1
	 */
	int updateBoard(Boardemp_joinVO boardVo) throws RemoteException;
	
	/**
	 * 게시판 글을 삭제하는 메서드
	 * @param boardVo 보드 VO
	 * @return 성공시 1
	 */
	int deleteBoard(int boardVo) throws RemoteException;
	
	/**
	 * 검색한 결과를 불러오는 메서드
	 * @param map 검색어가 담긴 Map 객체
	 * @return 보드 VO객체가 담긴 List
	 */
	List<Boardemp_joinVO> searchBoard(Map<String, String> map) throws RemoteException;
	
	
	/**
	 * 자유게시판과 사원이름 조인해서 가져오는 메서드
	 * @return 자유게시판과 사원이름이 담긴  VO List
	 */
	List<Boardemp_joinVO> boardjoinList(Map<String, Integer> map) throws RemoteException;

}
