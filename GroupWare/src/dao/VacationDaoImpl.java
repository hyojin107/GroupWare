package dao;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import util.BuildedSqlMapClient;
import vo.MyVacationVO;
import vo.Payment_LineVO;
import vo.VacationCheckVO;
import vo.VacationVO;

public class VacationDaoImpl implements IVacationDao{
	
	private static VacationDaoImpl instance;
	private SqlMapClient smc;
	
	private VacationDaoImpl() {
		smc = BuildedSqlMapClient.getSqlMapClient();
	}
	
	public static VacationDaoImpl getInstance(){
		if(instance == null) instance = new VacationDaoImpl();
		return instance;
	}

	@Override
	public List<MyVacationVO> getAllVacation(Map<String, Integer> map) {
		List<MyVacationVO> vacationList = null;

		try {
			vacationList = smc.queryForList("groupware.getAllVacation", map);
		} catch (Exception e) {
			vacationList = null;
			e.printStackTrace();
		}			
		return vacationList;
	}

	@Override
	public int insertVacation(VacationVO vacVo) {
		int cnt = 0;
		try {
			Object obj = smc.insert("groupware.insertVacation", vacVo);
			cnt = obj == null ? 1 : 0;
		} catch (Exception e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateVacCheck(Map<String, Integer> map) {
		int cnt = 0;
		try {
			Object obj = smc.update("groupware.updateVacCheck", map);
			cnt = obj == null ? 1 : 0;
		} catch (Exception e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<Payment_LineVO> getCheckVacList(int emp_no) {
		List<Payment_LineVO> list = null;
		try {
			list = smc.queryForList("groupware.getCheckVacList", emp_no);
		} catch (Exception e) {
			list = null;
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public VacationCheckVO getVacListNo(int pay_no) {
		VacationCheckVO vo = null;
		try {
			vo = (VacationCheckVO) smc.queryForObject("groupware.getVacListNo", pay_no);
		} catch (Exception e) {
			vo = null;
			e.printStackTrace();
		}
		return vo;
	}
	
	
}
