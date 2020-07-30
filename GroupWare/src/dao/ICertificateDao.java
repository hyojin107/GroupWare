package dao;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import vo.CertificateVO;

/**
 * 실제 DB와 연결해서 SQL문을 수행하여 결과를 작성해서
 * Service에 전달하는 DAO의 interface
 * 
 * @author 2팀
 *
 */
public interface ICertificateDao {
	
	/**
	 * 증명서신청을 등록하는 메서드
	 * @param cerVo 증명서 VO
	 * @return 성공시 1
	 */
	int insertCerti(CertificateVO cerVo);
	
	/**
	 * 증명서신청을 승인하는 메서드
	 * @param cerNo 증명서VO
	 * @return 성공시 1
	 */
	int updateCerti(Map<String, Integer> map);
	
	/**
	 * 접속자가 신청한 증명서를 모두 가져오는 메서드
	 * @param empNo 사원번호
	 * @return 증명서 VO 가 담긴 List
	 */
	List<CertificateVO> getAllCertificate(int empNo);

	
	
	List<CertificateVO> getAllCertificate();
	
	List<CertificateVO> getAllCertificate(Map<String, Integer> map);
}
