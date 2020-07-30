package dao;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import util.BuildedSqlMapClient;
import vo.Address_BookVO;
import vo.PlanVO;
import vo.PlanVO_join;

public class PlanDaoImpl implements IPlanDao{

	private static PlanDaoImpl instance;
	private SqlMapClient smc;
	
	private PlanDaoImpl() {
		smc = BuildedSqlMapClient.getSqlMapClient();
	}
	
	public static PlanDaoImpl getInstance(){
		if(instance == null) instance = new PlanDaoImpl();
		return instance;
	}



	@Override
	public int insertPlan(PlanVO_join planVo) {
		int cnt = 0;
		try {
			Object obj = smc.insert("groupware.insertPlan", planVo);
			cnt = obj == null ? 1 : 0;
		} catch (Exception e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updatePlan(PlanVO_join planVo) {
		int cnt = 0;
		try {
			cnt = smc.update("groupware.updatePlan", planVo);
		} catch (Exception e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deletePlan(int planNo) {
		int cnt = 0;
		try {
			cnt = smc.update("groupware.deletePlan", planNo);
			
		} catch (Exception e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}
	
	@Override
	public List<PlanVO> getAllPlan(Map<String, Integer> map) {
		List<PlanVO> planList = null;

		try {
			planList = smc.queryForList("groupware.getAllPlan", map);
		} catch (Exception e) {
			planList = null;
			e.printStackTrace();
		}			
		return planList;
	}
	
	@Override
	public List<PlanVO_join> getAllPlanEmp(Map<String, Integer> map) {
		List<PlanVO_join> planList = null; 
		try {
			planList = smc.queryForList("groupware.getAllPlanEmp",map);
		} catch (Exception e) {
			planList = null;
			e.printStackTrace();
		}
		return planList;
	}

}
