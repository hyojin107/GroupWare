package service;

import java.rmi.Remote;
import java.rmi.RemoteException;

import vo.Temp_SaveVO;

/**
 * Service객체는 DAO에 작성된 메서드를 원하는 작업에 맞게 호출하여 결과를 받아오고,
 * 받아온 자료를 Controller에게 보내주는 역할을 수행한다.
 * 
 * 보통 DAO의 메서드와 같은 구조로 되어있다.
 * 
 * @author 2팀
 *
 */
public interface ITempSaveService extends Remote {

	/**
	 * 임시저장함에 등록하는 메서드
	 * @param temVo 임시저장VO
	 * @return 성공시 1
	 */
	int insertTempSave(Temp_SaveVO temVo) throws RemoteException;
	
	/**
	 * 임시저장 업데이트 메서드
	 * @param temVo 임시저장 VO
	 * @return 성공시 1
	 */
	int updateTempSave(Temp_SaveVO temVo) throws RemoteException;
	
	/**
	 * 임시저장 삭제 메서드
	 * @param empNo 사원번호
	 * @return 성공시 1
	 */
	int deleteTempSave(int empNo) throws RemoteException;
	
	/**
	 * 임시저장 불러오는 메서드
	 * @param empNo 사원번호
	 * @return 임시저장VO 
	 */
	Temp_SaveVO getTempSave(int empNo) throws RemoteException;
	
}
