package dao;

import java.util.List;
import java.util.Map;

import vo.NoticeVO;
import vo.NoticeVO_join;

/**
 * 실제 DB와 연결해서 SQL문을 수행하여 결과를 작성해서
 * Service에 전달하는 DAO의 interface
 * 
 * @author 2팀
 *
 */
public interface INoticeDao {
	
	/**
	 * 공지사항 등록하는 메서드
	 * @param noticeVo 공지사항 VO
	 * @return 성공시 1
	 */
	int insertNotice(NoticeVO_join noticeVo);
	
	/**
	 * 페이지네이션 모든 공지 가져오는 메서드
	 * @param map 시작번호, 끝번호 담긴 Map 객체
	 * @return 공지사항 VO 담긴 List
	 */
	List<NoticeVO> getAllNotice(Map<String, Integer> map);
	
	/**
	 * 페이지네이션 모든 공지와 이름 가져오는 메서드 
	 * @param map 시작번호, 끝번호가 담긴 Map 객체 
	 * @return 공지사항 VO와 emp_name이 추가로 담긴 List 
	 */
	List<NoticeVO_join> getAllNotiEmp(Map<String, Integer> map);
	
	/**
	 * 공지사항 수정 메서드
	 * @param noticeVo 공지사항 VO
	 * @return 성공시 1
	 */
	int updateNotice(NoticeVO_join noticeVo);
	
	/**
	 * 공지사항 삭제 메서드
	 * @param noticeNo 공지번호
	 * @return 성공시 1
	 */
	int deleteNotice(int noticeNo);
	
	/**
	 * 검색한 공지 불러오는 메서드
	 * @param map 검색 필드, 검색어 담긴 Map 객체
	 * @return 공지사항VO 담긴 List
	 */
	List<NoticeVO_join> searchNotice(Map<String, String> map);
	
	
	
	
}
