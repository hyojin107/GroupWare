package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import vo.Document_FormVO;
import vo.PaymentListVO;
import vo.PaymentVO;
import vo.Payment_LineVO;
import vo.ReferenceVO;
import vo.SangsinSearchVO;

/**
 * Service객체는 DAO에 작성된 메서드를 원하는 작업에 맞게 호출하여 결과를 받아오고,
 * 받아온 자료를 Controller에게 보내주는 역할을 수행한다.
 * 
 * 보통 DAO의 메서드와 같은 구조로 되어있다.
 * 
 * @author 2팀
 *
 */
public interface IPaymentDao extends Remote {

	/**
	 * 페이지네이션 열람 가능한 모든 결재문서 불러오는 메서드
	 * @param map 시작번호, 끝번호, 접속자 사원번호 담긴 Map 객체
	 * @return 결재문서 VO 담긴 List
	 */
	List<PaymentListVO> getAllPay(int emp_no);
	
	/**
	 * 모든 열람자를 불러오는 메서드
	 * @param map 결재문서번호가 담긴 Map 객체
	 * @return 참조VO
	 */
	List<SangsinSearchVO> getAllRef(int pay_no);
	
	/**
	 * 모든 결재선을 불러오는 메서드
	 * @param map 결재문서번호가 담긴 Map 객체
	 * @return 결재선VO
	 */
	List<Payment_LineVO> getAllPayLine(Map<String, Integer> map);
	
	/**
	 * 
	 * @param map
	 * @return
	 */
	List<PaymentListVO> getComboPay(Map<String, Integer> map);
	
	List<ReferenceVO> getComboRef(Map<String, Integer> map);
	
	List<Payment_LineVO> getComboPayLine(Map<String, Integer> map);
	
	/**
	 * payNo로 모든 결재선 가져오는 메서드
	 * @param payNo 담긴 Map 객체
	 * @return 결재선VO
	 */
	List<Payment_LineVO> searchPay(Map<String, String> map);
	
	/**
	 * 결재문서 등록하는 메서드
	 * @param payVo 결재VO
	 * @return 성공시 1
	 */
	int insertPay(PaymentVO payVo);
	
	/**
	 * 참조자 등록하는 메서드
	 * @param refVo 참조 VO
	 * @return 성공시 1
	 */
	int insertRef(ReferenceVO refVo);
	
	/**
	 * 결재선 등록하는 메서드
	 * @param payLineVo 결재선VO
	 * @return 성공시 1
	 */
	int insertPayLine(Payment_LineVO payLineVo);
	
	/**
	 * 결재/참조 등록을 위해 이름으로 사원 검색하는 메서드
	 * @param name 사원명
	 * @return 사원VO 담긴 List
	 */
	List<SangsinSearchVO> searchName(String name);
	
	/**
	 * 결재 진행 상태 체크하는 메서드
	 * @param payLineVo 결재선 VO
	 * @return 0 진행중 1 승인 2 반려
	 */
	int payCheck(Payment_LineVO payLineVo);

	List<SangsinSearchVO> getAllPayLineName(int pay_no);
	
	/**
	 * 문서번호로 결재정보 가져오는 메서드
	 * @param payNo
	 * @return
	 * @throws RemoteException
	 */
	PaymentListVO getPayListNo(int payNo);
	
//	List<PaymentListVO> getPayCheck()
	/**
	 * 결재하는 메서드
	 * @param map
	 * @return
	 * @throws RemoteException
	 */
	int Check(Map map);
	
	/**
	 * payment 상태를 변경하는 메서드
	 * @param map
	 * @return
	 * @throws RemoteException
	 */
	int Check2(Map map);
	
	/**
	 * 방금 작성한 결재문서no 받아오는 메서드
	 * @param empNo
	 * @return
	 * @throws RemoteException
	 */
	int writeNo(int empNo);
	
	/**
	 * 결재문서 모두 불러오는 메서드
	 * @return
	 * @throws RemoteException
	 */
	List<Document_FormVO> getDocument();
	
	/**
	 * 나의 결재대기 서류 모두 받아오는 메서드 
	 * @return
	 * @throws RemoteException
	 */
	List<Payment_LineVO> getCheckList(int emp_no);
	
	/**
	 * 사인 파일을 가져오는 메서드 
	 * @param emp_no
	 * @return
	 * @throws RemoteException
	 */
	PaymentListVO getSign(int emp_no);
}
