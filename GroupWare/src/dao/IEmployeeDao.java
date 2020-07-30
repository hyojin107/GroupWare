package dao;

import java.util.List;
import java.util.Map;

import vo.EmployeeVO;
import vo.ImgFileVO;
import vo.Employee_joinVO;

/**
 * 실제 DB와 연결해서 SQL문을 수행하여 결과를 작성해서
 * Service에 전달하는 DAO의 interface
 * 
 * @author 2팀
 *
 */
public interface IEmployeeDao {
	
	/**
	 * 사원 등록 메서드
	 * @param empVo 사원VO
	 * @return 성공시 1
	 */
	int insertEmp(EmployeeVO empVo);
	
	/**
	 * 사원 수정 메서드
	 * @param map 수정할 컬럼과 값이 담긴 Map 객체
	 * @return
	 */
	int updateEmp(Map<String, String> map);
	
	/**
	 * 전 사원을 불러오는 메서드
	 * @return 사원 VO 담긴 List
	 */
	List<EmployeeVO> getAllEmp();
	
	/**
	 * 검색한 사원을 불러오는 메서드
	 * @param map 검색어가 담긴 Map 객체
	 * @return 사원VO 담긴 List
	 */
	List<Employee_joinVO> searchEmp(Map<String, String> map);
	
	/**
	 * 로그인한 사원 정보를 불러오는 메서드
	 * @param empNo 사원번호
	 * @return 사원VO
	 */
	EmployeeVO getEmpLogin(int empNo);
	
	/**
	 * 사원 사진을 서버에 저장하하고, DB에 파일 경로를 저장하는 메서드
	 * @param empVo	사원사진에 대한 정보가 담긴 파일VO
	 */
	int setImgPhoto(ImgFileVO fileVo);
	
	/**
	 * 사원 싸인 파일을 서버에 저장하고, DB에 파일 경로를 저장하는 메서드
	 * @param empVo	싸인파일에 대한 정보가 담긴 파일VO
	 */
	int setImgSign(ImgFileVO fileVo);
	
	/**
	 * 사원사진과 싸인파일을 서버에서 클라이언트로 보내주기 위한 메서드
	 * @param empNo 사원번호
	 * @return 이미지 파일 정보가 들어있는 VO객체
	 */
	ImgFileVO getImgFile(int empNo);
	
	/**
	 * 전체 사원 리스트를 불러오는 메서드
	 * @param map 검색어가 담긴 Map 객체
	 * @return 사원VO 담긴 List
	 */
	List<Employee_joinVO> allemplist(Map<String, Integer> map);
	
	int gikwonenroll(EmployeeVO empVo);

	List<Employee_joinVO> kido(Map<String, Integer> map);

	
	
}





