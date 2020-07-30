package dao;

import java.rmi.Remote;
import java.rmi.RemoteException;

import vo.Temp_SaveVO;

/**
 * 실제 DB와 연결해서 SQL문을 수행하여 결과를 작성해서
 * Service에 전달하는 DAO의 interface
 * 
 * @author 2팀
 *
 */
public interface ITempSaveDao extends Remote {
	
	/**
	 * 임시저장함에 등록하는 메서드
	 * @param temVo 임시저장VO
	 * @return 성공시 1
	 */
	int insertTempSave(Temp_SaveVO temVo);
	
	/**
	 * 임시저장 업데이트 메서드
	 * @param temVo 임시저장 VO
	 * @return 성공시 1
	 */
	int updateTempSave(Temp_SaveVO temVo);
	
	/**
	 * 임시저장 삭제 메서드
	 * @param empNo 사원번호
	 * @return 성공시 1
	 */
	int deleteTempSave(int empNo);
	
	/**
	 * 임시저장 불러오는 메서드
	 * @param empNo 사원번호
	 * @return 임시저장VO 
	 */
	Temp_SaveVO getTempSave(int empNo);
	
}


