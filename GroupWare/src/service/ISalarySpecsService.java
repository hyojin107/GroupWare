package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import vo.Salary_SpecsVO;

/**
 * Service객체는 DAO에 작성된 메서드를 원하는 작업에 맞게 호출하여 결과를 받아오고,
 * 받아온 자료를 Controller에게 보내주는 역할을 수행한다.
 * 
 * 보통 DAO의 메서드와 같은 구조로 되어있다.
 * 
 * @author 2팀
 *
 */
public interface ISalarySpecsService extends Remote {

	/**
	 * 페이지네이션 급여명세서 모두 불러오는 메서드
	 * @param map 시작번호, 끝번호 담긴 Map 객체
	 * @return 급여명세서 VO 담긴 List
	 */
	List<Salary_SpecsVO> getAllSalary(int emp_no) throws RemoteException;
	
	/**
	 * 검색한 년도 급여명세서 목록 불러오는 메서드
	 * @param map 시작번호, 끝번호, 년도 담긴 Map 객체 
	 * @return 급여명세서 VO 담긴 List
	 */
	List<Salary_SpecsVO> searchSalary(Map<String, Integer> map) throws RemoteException;
	
}
