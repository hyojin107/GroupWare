package service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;

import dao.IPlanDao;
import dao.PlanDaoImpl;
import vo.PlanVO;
import vo.PlanVO_join;

public class PlanServiceImpl extends UnicastRemoteObject implements IPlanService {

	private IPlanDao dao;
	private static PlanServiceImpl service;
	private PlanServiceImpl() throws RemoteException {
		dao = PlanDaoImpl.getInstance();
	}
	
	public static PlanServiceImpl getInstance() {
		try {
			if(service == null) service = new PlanServiceImpl();
		} catch (Exception e) {	}
		return service;
	}

	@Override
	public List<PlanVO> getAllPlan(Map<String, Integer> map) throws RemoteException {
		return dao.getAllPlan(map);
	}

	@Override
	public int insertPlan(PlanVO_join planVo) throws RemoteException {
		return dao.insertPlan(planVo);
	}

	@Override
	public int updatePlan(PlanVO_join planVo) throws RemoteException {
		return dao.updatePlan(planVo);
	}

	@Override
	public int deletePlan(int planNo) throws RemoteException {
		return dao.deletePlan(planNo);
	}

	@Override
	public List<PlanVO_join> getAllPlanEmp(Map<String, Integer> map) throws RemoteException {
		return dao.getAllPlanEmp(map);

	}

}
