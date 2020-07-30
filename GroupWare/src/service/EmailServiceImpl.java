package service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;

import dao.EmailDaoImpl;
import dao.IEmailDao;
import vo.Attach_FileVO;
import vo.EmailSendListVO;
import vo.EmailVO;
import vo.Email_ReceptionVO;

public class EmailServiceImpl extends UnicastRemoteObject implements IEmailService {

	private IEmailDao dao;
	private static EmailServiceImpl service;
	private EmailServiceImpl() throws RemoteException {
		dao = EmailDaoImpl.getInstance();
	}
	
	public static EmailServiceImpl getInstance() {
		try {
			if(service == null) service = new EmailServiceImpl();
		} catch (Exception e) {	}
		return service;
	}

	@Override
	public int sendEmail(EmailVO emailVo) throws RemoteException {
		return dao.sendEmail(emailVo);
	}

	@Override
	public int sendEmail(Attach_FileVO attFileVo) throws RemoteException {
		return dao.sendEmail(attFileVo);
	}

	@Override
	public List<EmailVO> getAllEmail(Map<String, Integer> map) throws RemoteException {
		return dao.getAllEmail(map);
	}

	@Override
	public int deleteEmail(int emailNo) throws RemoteException {
		return dao.deleteEmail(emailNo);
	}

	@Override
	public List<EmailVO> searchEmail(Map<String, String> map) throws RemoteException {
		return dao.searchEmail(map);
	}

	@Override
	public int insertEmailReception(Email_ReceptionVO email_recVo) throws RemoteException {
		return dao.insertEmailReception(email_recVo);
	}

	@Override
	public List<EmailSendListVO> getSendMailList(Map<String, Integer> map) throws RemoteException {
		return dao.getSendMailList(map);
	}

	@Override
	public int getLastEmailNo(int empNo) throws RemoteException {
		return dao.getLastEmailNo(empNo);
	}
	
}
