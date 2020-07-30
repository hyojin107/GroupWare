package main;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import service.AddressServiceImpl;
import service.AttendanceServiceImpl;
import service.BoardServiceImpl;
import service.CalendarServiceImpl;
import service.CertificateServiceImpl;
import service.EmailServiceImpl;
import service.EmployeeServiceImpl;
import service.IAddressService;
import service.IAttendanceService;
import service.IBoardService;
import service.ICalendarService;
import service.ICertificateService;
import service.IEmailService;
import service.IEmployeeService;
import service.INoticeService;
import service.IPaymentService;
import service.IPlanService;
import service.ISalarySpecsService;
import service.ITempSaveService;
import service.IVacationService;
import service.NoticeServiceImpl;
import service.PaymentServiceImpl;
import service.PlanServiceImpl;
import service.SalarySpecsServicecImpl;
import service.TempSaveServiceImpl;
import service.VacationServiceImpl;

public class ServerMain {
	
	public static void main(String[] args) {
		try {
			IAddressService addrService = AddressServiceImpl.getInstance();
			IAttendanceService attService = AttendanceServiceImpl.getInstance();
			IBoardService boardService = BoardServiceImpl.getInstance();
			ICertificateService cerService = CertificateServiceImpl.getInstance();
			IEmailService emailService = EmailServiceImpl.getInstance();
			IEmployeeService empService = EmployeeServiceImpl.getInstance();
			INoticeService notiService = NoticeServiceImpl.getInstance();
			IPaymentService payService = PaymentServiceImpl.getInstance();
	
			IPlanService planService = PlanServiceImpl.getInstance();
			ISalarySpecsService salService = SalarySpecsServicecImpl.getInstance();
			ITempSaveService tempService = TempSaveServiceImpl.getInstance();
			IVacationService vacService = VacationServiceImpl.getInstance();
			ICalendarService calService = CalendarServiceImpl.getInstance();
			
			Registry reg = LocateRegistry.createRegistry(9999);
			reg.rebind("addrService", addrService);
			reg.rebind("attService", attService);
			reg.rebind("boardService", boardService);
			reg.rebind("cerService", cerService);
			reg.rebind("empService", empService);
			reg.rebind("emailService", emailService);
			reg.rebind("notiService", notiService);
			reg.rebind("payService", payService);
			reg.rebind("planService", planService);
			reg.rebind("salService", salService);
			reg.rebind("tempService", tempService);
			reg.rebind("vacService", vacService);
			reg.rebind("calService", calService);
			
			System.out.println("서버가 준비되었습니다.");
			
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
