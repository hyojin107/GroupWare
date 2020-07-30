package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import vo.Salary_SpecsVO;

/**
 * 실제 DB와 연결해서 SQL문을 수행하여 결과를 작성해서
 * Service에 전달하는 DAO의 interface
 * 
 * @author 2팀
 *
 */
public interface ISalarySpecsDao extends Remote {
	
	/**
	 * 페이지네이션 급여명세서 모두 불러오는 메서드
	 * @param map 시작번호, 끝번호 담긴 Map 객체
	 * @return 급여명세서 VO 담긴 List
	 */
	List<Salary_SpecsVO> getAllSalary(int emp_no);
	
	/**
	 * 검색한 년도 급여명세서 목록 불러오는 메서드
	 * @param map 시작번호, 끝번호, 년도 담긴 Map 객체 
	 * @return 급여명세서 VO 담긴 List
	 */
	List<Salary_SpecsVO> searchSalary(Map<String, Integer> map);
	
}



