package service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;

import dao.INoticeDao;
import dao.NoticeDaoImpl;
import vo.NoticeVO;
import vo.NoticeVO_join;

public class NoticeServiceImpl extends UnicastRemoteObject implements INoticeService {

	private INoticeDao dao;
	private static NoticeServiceImpl service;
	private NoticeServiceImpl() throws RemoteException {
		dao = NoticeDaoImpl.getInstance();
	}
	
	public static NoticeServiceImpl getInstance() throws RemoteException {
		try {
			if(service == null) service = new NoticeServiceImpl();
		} catch (Exception e) {	}
		return service;
	}

	@Override
	public int insertNotice(NoticeVO_join noticeVo) throws RemoteException {
		return dao.insertNotice(noticeVo);
	}

	@Override
	public List<NoticeVO> getAllNotice(Map<String, Integer> map) throws RemoteException {
		return dao.getAllNotice(map);
	}

	@Override
	public int updateNotice(NoticeVO_join noticeVo) throws RemoteException {
		return dao.updateNotice(noticeVo);
	}

	@Override
	public int deleteNotice(int noticeNo) throws RemoteException {
		return dao.deleteNotice(noticeNo);
	}

	@Override
	public List<NoticeVO_join> searchNotice(Map<String, String> map) throws RemoteException {
		return dao.searchNotice(map);
	}

	@Override
	public List<NoticeVO_join> getAllNotiEmp(Map<String, Integer> map) throws RemoteException {
		return dao.getAllNotiEmp(map);
	}
	
}
