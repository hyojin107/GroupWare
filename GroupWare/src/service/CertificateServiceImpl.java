package service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;

import dao.CertificateDaoImpl;
import dao.ICertificateDao;
import vo.CertificateVO;

public class CertificateServiceImpl extends UnicastRemoteObject implements ICertificateService {

	private ICertificateDao dao;
	private static CertificateServiceImpl service;

	private CertificateServiceImpl() throws RemoteException {
		dao = CertificateDaoImpl.getInstance();
	}

	public static CertificateServiceImpl getInstance() {
		try {
			if (service == null)
				service = new CertificateServiceImpl();
		} catch (Exception e) {
		}
		return service;
	}

	@Override
	public int insertCerti(CertificateVO cerVo) throws RemoteException {
		return dao.insertCerti(cerVo);
	}

	@Override
	public int updateCerti(Map<String, Integer> map) throws RemoteException {
		return dao.updateCerti(map);
	}

	
  @Override public List<CertificateVO> getAllCertificate(int empNo) throws RemoteException { 
		  return dao.getAllCertificate(empNo); 
		  }
	  
	 
	@Override
	public List<CertificateVO> getAllCertificate() throws RemoteException {
		return dao.getAllCertificate();
	}

	/**
	 * 여기고침
	 */
	@Override
	public List<CertificateVO> getAllCertificate(Map<String, Integer> map) throws RemoteException {
		
		return dao.getAllCertificate(map);
	}



}
