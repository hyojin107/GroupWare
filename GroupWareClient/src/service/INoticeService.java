package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import vo.NoticeVO;
import vo.NoticeVO_join;

/**
 * Service객체는 DAO에 작성된 메서드를 원하는 작업에 맞게 호출하여 결과를 받아오고,
 * 받아온 자료를 Controller에게 보내주는 역할을 수행한다.
 * 
 * 보통 DAO의 메서드와 같은 구조로 되어있다.
 * 
 * @author 2팀
 *
 */
public interface INoticeService extends Remote {

	/**
	 * 공지사항 등록하는 메서드
	 * @param joinvo 공지사항 VO
	 * @return 성공시 1
	 */
	int insertNotice(NoticeVO_join joinvo) throws RemoteException;
	
	/**
	 * 페이지네이션 모든 공지 가져오는 메서드
	 * @param map 시작번호, 끝번호 담긴 Map 객체
	 * @return 공지사항 VO 담긴 List
	 */
	List<NoticeVO> getAllNotice(Map<String, Integer> map) throws RemoteException;
	
	/**
	 * 공지사항 수정 메서드
	 * @param noticeVo 공지사항 VO
	 * @return 성공시 1
	 */
	
	List<NoticeVO_join> getAllNotiEmp(Map<String, Integer> map) throws RemoteException;
	
	/**
	 * 공지사항 수정 메서드
	 * @param vo 공지사항 VO
	 * @return 성공시 1
	 */
	int updateNotice(NoticeVO_join vo) throws RemoteException;
	
	/**
	 * 공지사항 삭제 메서드
	 * @param noticeNo 공지번호
	 * @return 성공시 1
	 */
	int deleteNotice(int noticeNo) throws RemoteException;
	
	/**
	 * 검색한 공지 불러오는 메서드
	 * @param map 검색 필드, 검색어 담긴 Map 객체
	 * @return 공지사항VO 담긴 List
	 */
	List<NoticeVO_join> searchNotice(Map<String, String> map) throws RemoteException;
	
}
