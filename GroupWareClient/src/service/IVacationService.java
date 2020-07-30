package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import vo.MyVacationVO;
import vo.Payment_LineVO;
import vo.VacationCheckVO;
import vo.VacationVO;

/**
 * Service객체는 DAO에 작성된 메서드를 원하는 작업에 맞게 호출하여 결과를 받아오고,
 * 받아온 자료를 Controller에게 보내주는 역할을 수행한다.
 * 
 * 보통 DAO의 메서드와 같은 구조로 되어있다.
 * 
 * @author 2팀
 *
 */
public interface IVacationService extends Remote {

	/**
	 * 페이지네이션 모든 휴가 불러오는 메서드
	 * @param map 시작번호,끝번호, 사원번호 담긴 Map 객체
	 * @return 휴가 VO 담긴 List
	 */
	List<MyVacationVO> getAllVacation(Map<String, Integer> map) throws RemoteException;
	
	/**
	 * 휴가 추가하는 메서드
	 * @param vacVo 휴가VO
	 * @return  성공시 1
	 */
	int insertVacation(VacationVO vacVo) throws RemoteException;
	
	/**
	 * 휴가 수정/결재하는 메서드
	 * @param map 휴가번호, 승인1/반려2 담긴 Map 객체
	 * @return 성공시 1
	 */
	int updateVacCheck(Map<String, Integer> map) throws RemoteException;
	
	/**
	 * 결재해야할 문서 다들고오는 메서드
	 * @param emp_no
	 * @return
	 * @throws RemoteException
	 */
	List<Payment_LineVO> getCheckVacList(int emp_no) throws RemoteException;
	
	/**
	 * pay_no로 결재문서 정보 가져오는 메서드
	 * @param doc_no
	 * @return
	 * @throws RemoteException
	 */
	VacationCheckVO getVacListNo(int pay_no) throws RemoteException;
}
