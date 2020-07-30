package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import vo.CertificateVO;

/**
 * Service객체는 DAO에 작성된 메서드를 원하는 작업에 맞게 호출하여 결과를 받아오고,
 * 받아온 자료를 Controller에게 보내주는 역할을 수행한다.
 * 
 * 보통 DAO의 메서드와 같은 구조로 되어있다.
 * 
 * @author 2팀
 *
 */
public interface ICertificateService extends Remote {

	/**
	 * 증명서신청을 등록하는 메서드
	 * @param cerVo 증명서 VO
	 * @return 성공시 1
	 */
	int insertCerti(CertificateVO cerVo) throws RemoteException;
	
	/**
	 * 증명서신청을 승인하는 메서드
	 * @param cerNo 증명서VO
	 * @return 성공시 1
	 */
	int updateCerti(Map<String, Integer> map) throws RemoteException;
	
	/**
	 *여기도고침
	 * 접속자가 신청한 증명서를 모두 가져오는 메서드
	 * @param empNo 사원번호
	 * @return 증명서 VO 가 담긴 List
	 */
	
	List<CertificateVO> getAllCertificate(int empNo) throws RemoteException;
	

	List<CertificateVO> getAllCertificate()throws RemoteException;
	
	/**
	 * 이거고침
	 * @param map
	 * @return
	 * @throws RemoteException
	 */
	List<CertificateVO> getAllCertificate(Map<String, Integer> map) throws RemoteException;
}
