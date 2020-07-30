package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import vo.PlanVO;
import vo.PlanVO_join;

/**
 * 실제 DB와 연결해서 SQL문을 수행하여 결과를 작성해서
 * Service에 전달하는 DAO의 interface
 * 
 * @author 2팀
 *
 */
public interface IPlanDao extends Remote {
	
	/**
	 * 페이지네이션 모든 일정 불러오는 메서드
	 * @param map 시작번호, 끝번호 담긴 Map 객체
	 * @return 일정VO 담긴 List
	 */
	List<PlanVO> getAllPlan(Map<String, Integer> map);
	
	/**
	 * 페이지 네이션 모든 일정과 emp_name을 불러오는 메소드
	 * @param map 시작번호, 끝번호 담긴 Map 객체 
	 * @return 일정VO가 담긴 List 
	 */
	List<PlanVO_join> getAllPlanEmp(Map<String, Integer> map);
	/**
	 * 일정 등록 메서드
	 * @param planVo 일정VO
	 * @return 성공시 1
	 */
	int insertPlan(PlanVO_join planVo);
	
	/**
	 * 일정 수정 메서드
	 * @param planVo 일정VO
	 * @return 성공시 1
	 */
	int updatePlan(PlanVO_join planVo);
	
	/**
	 * 일정 삭제 메서드
	 * @param planNo 일정번호
	 * @return 성공시 1
	 */
	int deletePlan(int planNo);
	
	
}





