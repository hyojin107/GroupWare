package service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import dao.ITempSaveDao;
import dao.TempSaveDaoImpl;
import vo.Temp_SaveVO;

public class TempSaveServiceImpl extends UnicastRemoteObject implements ITempSaveService {

	private ITempSaveDao dao;
	private static TempSaveServiceImpl service;
	private TempSaveServiceImpl() throws RemoteException {
		dao = TempSaveDaoImpl.getInstance();
	}
	public static TempSaveServiceImpl getInstance() {
		try {
			if(service == null) service = new TempSaveServiceImpl();
		} catch (Exception e) {	}
		return service;
	}

	@Override
	public int insertTempSave(Temp_SaveVO temVo) throws RemoteException {
		return dao.insertTempSave(temVo);
	}

	@Override
	public int updateTempSave(Temp_SaveVO temVo) throws RemoteException {
		return dao.updateTempSave(temVo);
	}

	@Override
	public int deleteTempSave(int empNo) throws RemoteException {
		return dao.deleteTempSave(empNo);
	}
	
	@Override
	public Temp_SaveVO getTempSave(int empNo) throws RemoteException {
		return dao.getTempSave(empNo);
	}

}
