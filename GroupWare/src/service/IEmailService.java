package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import vo.Attach_FileVO;
import vo.EmailSendListVO;
import vo.EmailVO;
import vo.Email_ReceptionVO;

/**
 * Service객체는 DAO에 작성된 메서드를 원하는 작업에 맞게 호출하여 결과를 받아오고,
 * 받아온 자료를 Controller에게 보내주는 역할을 수행한다.
 * 
 * 보통 DAO의 메서드와 같은 구조로 되어있다.
 * 
 * @author 2팀
 *
 */
public interface IEmailService extends Remote {

	/**
	 * 보낸 이메일을 등록하는 메서드
	 * @param emailVo 이메일VO
	 * @return 성공시 1
	 */
	int sendEmail(EmailVO emailVo) throws RemoteException;
	
	/**
	 * 메일수신자를 등록하는 메서드
	 * @param email_recVo
	 * @return 성공시 1
	 */
	int insertEmailReception(Email_ReceptionVO email_recVo) throws RemoteException;
	
	/**
	 * 보낸 첨부파일을 등록하는 메서드
	 * @param attFileVo 첨부파일 VO
	 * @return 성공시 1
	 */
	int sendEmail(Attach_FileVO attFileVo) throws RemoteException;
	
	/**
	 * 페이지네이션 보낸 편지함 불러오기 메서드
	 * @param map 시작,끝, 사원번호 담긴 Map 객체
	 * @return 이메일VO가 담긴 List
	 */
	List<EmailVO> getAllEmail(Map<String, Integer> map) throws RemoteException;
	
	/**
	 * 보낸 메일을 삭제하는 메서드
	 * @param emailNo 메일번호
	 * @return 성공시 1
	 */
	int deleteEmail(int emailNo) throws RemoteException;
	
	/**
	 * 검색된 메일을 불러오는 메서드
	 * @param map 시작번호,끝번호, 검색어가 등록된 Map 객체
	 * @return 이메일VO 객체가 담긴 List
	 */
	List<EmailVO> searchEmail(Map<String, String> map) throws RemoteException;
	
	/**
	 * 페이지네이션으로 보낸 메일 리스트를 불러오는 메서드
	 * @return 보낸메일함 VO
	 */
	List<EmailSendListVO> getSendMailList(Map<String, Integer> map) throws RemoteException;
	
	/**
	 * 마지막으로 입력한 email_no(PK)을 불러오는 메서드
	 * @param empNo 본인 사원번호
	 * @return
	 */
	int getLastEmailNo(int empNo) throws RemoteException;
	
}
