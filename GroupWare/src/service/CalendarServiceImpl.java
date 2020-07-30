package service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;

import dao.CalendarDao;
import vo.CalendarVO;
import vo.calDateVO;

public class CalendarServiceImpl extends UnicastRemoteObject implements ICalendarService{
	private static CalendarServiceImpl calService;
	private CalendarDao calDao;
	
	public static CalendarServiceImpl getInstance() throws RemoteException {
		if(calService == null)
			calService = new CalendarServiceImpl();
		return calService;
	}
	public CalendarServiceImpl()  throws RemoteException {
		calDao = CalendarDao.getInstance();
	}
	@Override
	public void insertCal(CalendarVO calVo) throws RemoteException  {
		calDao.insertCal(calVo);
	}
	@Override
	public List<CalendarVO> getAllCal(Map<String, Integer> calMap) throws RemoteException  {
		return calDao.getAllCal(calMap);
	}
	@Override
	public List<calDateVO> getDetail(Map<String, Integer> calMap) throws RemoteException  {
		return calDao.getDetail(calMap);
	}
	@Override
	public void deleteCal(int cal_num) throws RemoteException  {
		calDao.deleteCal(cal_num);
	}
	@Override
	public void updateDetail(CalendarVO calVo) throws RemoteException {
		calDao.updateDetail(calVo);
	}
}
