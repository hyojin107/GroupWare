package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import vo.Boardemp_joinVO;
import vo.EmployeeVO;
import vo.Employee_joinVO;
import vo.ImgFileVO;

/**
 * Service객체는 DAO에 작성된 메서드를 원하는 작업에 맞게 호출하여 결과를 받아오고,
 * 받아온 자료를 Controller에게 보내주는 역할을 수행한다.
 * 
 * 보통 DAO의 메서드와 같은 구조로 되어있다.
 * 
 * @author 2팀
 *
 */
public interface IEmployeeService extends Remote {

	/**
	 * 사원 등록 메서드
	 * @param empVo 사원VO
	 * @return 성공시 1
	 */
	int insertEmp(EmployeeVO empVo) throws RemoteException;
	
	/**
	 * 사원 수정 메서드
	 * @param map 수정할 컬럼과 값이 담긴 Map 객체
	 * @return
	 */
	int updateEmp(Map<String, String> map) throws RemoteException;
	
	/**
	 * 전 사원을 불러오는 메서드
	 * @return 사원 VO 담긴 List
	 */
	List<EmployeeVO> getAllEmp() throws RemoteException;
	
	/**
	 * 검색한 사원을 불러오는 메서드
	 * @param map 검색어가 담긴 Map 객체
	 * @return 사원VO 담긴 List
	 */
	List<Employee_joinVO> searchEmp(Map<String, String> map) throws RemoteException;
	
	/**
	 * 로그인한 사원 정보를 불러오는 메서드
	 * @param empNo 사원번호
	 * @return 사원VO
	 */
	EmployeeVO getEmpLogin(int empNo) throws RemoteException;
	
	/**
	 * 비밀번호 찾을 때, 이메일로 임시비밀번호를 전송하는 메서드
	 * @param empNo
	 * @return 전송 실패시 0, 성공 시 1
	 * @throws RemoteException
	 */
	int sendEmail(int empNo) throws RemoteException;
	
	/**
	 * 사원 사진을 서버에 저장하고, DB에 파일 경로를 저장하는 메서드
	 * @param empVo	사원사진에 대한 정보가 담긴 사원VO
	 */
	int setImgPhoto(ImgFileVO fileVo) throws RemoteException;
	
	/**
	 * 사원 싸인 파일을 서버에 저장하고, DB에 파일 경로를 저장하는 메서드
	 * @param empVo	싸인파일에 대한 정보가 담긴 사원VO
	 */
	int setImgSign(ImgFileVO fileVo) throws RemoteException;
	
	/**
	 * 사원사진과 싸인파일을 서버에서 클라이언트로 보내주기 위한 메서드
	 * @param empVo
	 * @return 이미지 파일 정보가 들어있는 VO객체
	 */
	ImgFileVO getImgFile(int empNo) throws RemoteException;
	
	/**
	 * 전 사원을 불러오는 메서드
	 * @return 사원 VO 담긴 List
	 */
	List<Employee_joinVO> allemplist(Map<String, Integer> map) throws RemoteException;
	
	
	int gikwonenroll(EmployeeVO empVo) throws RemoteException;
	
	
	List<Employee_joinVO> kido(Map<String, Integer> map) throws RemoteException;
}
