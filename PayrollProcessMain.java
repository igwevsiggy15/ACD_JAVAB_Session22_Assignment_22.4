package payroll;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.Transaction;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.TransactionException;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class PayrollProcessMain {
	private static SessionFactory factory;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DateFormat df=new SimpleDateFormat("MMYYYY");
		factory = new Configuration().configure().buildSessionFactory();
		Session ses=factory.openSession();
		Employee em=new Employee();
		SalForMonth sfm;
		HashSet<SalForMonth> rid=new HashSet();
		try {
			sfm = new SalForMonth(em.getId(), df.parse("092019"), 1000, 275, 550, 275, 2200, false);
			rid.add(sfm);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Account ac=new Account(678,"HSBC","HSBC001","Savings");
		Salary sl=new Salary(1000,.25,.25,.5);
		em.setActive(true);
		em.setEmail("email@a.com");
		em.setName("name");
		em.setOverpay(100);
		em.setPhone("4049991111");
		em.setSalaryRef(sl);
		/*ac.setAccountNumber(678);
		ac.setBankName("HSBC");
		ac.setBranchCode("HSBC001");
		ac.setType("Savings");*/
		ac.setAccountId(em.getId());
		/*
		sl.setBase(1000);
		sl.setFoodAllowance(.25);
		sl.setInsurance(.5);
		sl.setTravelAllowance(.25);*/
		sl.setId(em.getId());
	
		ac.setEmp(em);
		HashSet<Employee> temp=new HashSet();
		temp.add(em);
		sl.setEmps(temp);
	Salary sLs[]=new Salary[3];
	sLs[0]= new Salary(1000, 0.25, 0.5, 0.25);
	sLs[1]= new Salary(5000, 0.1, 0.15, 0.20);
	sLs[2]=new Salary(2000, 0.20, 0.35, 0.25);
	Account accs[]=new Account[6];
	accs[0]=new Account(12345, "HSBC", "HSBC001", "Savings");
	accs[1]=new Account(55555,"Chase","CHS001","Checking");
	accs[2]=new Account(65432,"Wells Fargo","WF001","Savings");
	accs[3]=new Account(99999,"Chase","CHS002","Checking");
	accs[4]=new Account(87101,"RBC","RBC001","Savings");
	accs[5]=new Account(86753,"RBC","RBC001","Savings");
	Employee eList[]=new Employee[6];
	eList[0]=new  Employee(1, "a@a.com", true, "a", 100, "1234567890");
	eList[0].setSalaryRef(sLs[0]);
	eList[0].setAccount_id(accs[0]);
	accs[0].setEmp(eList[0]);
	
	eList[1]=new  Employee(2, "b@b.com", true, "b", 1000, "9876543210");
	eList[1].setSalaryRef(sLs[0]);
	eList[1].setAccount_id(accs[1]);
	accs[1].setEmp(eList[1]);
	
	eList[2]=new  Employee(3, "c@c.com", true, "c", 0, "5556667777");
	eList[2].setSalaryRef(sLs[1]);
	eList[2].setAccount_id(accs[2]);
	accs[2].setEmp(eList[2]);
	
	eList[3]=new  Employee(4, "d@d.com", true, "d", 500, "1234445678");
	eList[3].setSalaryRef(sLs[1]);
	eList[3].setAccount_id(accs[3]);
	accs[3].setEmp(eList[3]);
	
	eList[4]=new  Employee(5, "e@e.com", true, "e", 200, "9999999999");
	eList[4].setSalaryRef(sLs[2]);
	eList[4].setAccount_id(accs[4]);
	accs[4].setEmp(eList[4]);
	
	eList[5]=new  Employee(6, "e@e.com", true, "e", 300, "9129899659");
	eList[5].setSalaryRef(sLs[2]);
	eList[5].setAccount_id(accs[5]);
	accs[5].setEmp(eList[5]);
	
	for(Salary s:sLs) {
		addSal(ses,s);
	}
	for(Employee emp:eList) {
		addEmployee(ses,emp);
	}
		em.setRd(rid);
		org.hibernate.Transaction tx1=ses.getTransaction();
		
		
		List<Employee> empList=getAllEmp(ses,"where id<>2");
		for(Employee emp:empList) {
			try {
				calcSal(emp,df.parse("092019"),false);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		Employee id3=getEmp(ses, 3);
		id3.setOverpay(id3.getOverpay()+150);
		updateEmployee(ses,id3);
		
		double octSal=0;
		try {
			octSal=calcMonthSal(ses,df.parse("102019"),false);
		}
		catch(ParseException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		Date d1=null;
		Employee id1=getEmp(ses,1);
		try {
			d1=df.parse("102019");
			SalForMonth sal=id1.sfmCalc(d1, false,21/31);
			updateSfm(ses,sal);
			id1.setActive(false);
			updateEmployee(ses,id1);
		}
		catch(ParseException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		List<Salary> salList=getAllSal(ses);
		for(Salary s:salList) {
			s.setBase(s.getBase()*1.1);
			updateSal(ses,s);
		}
		
		
		double novSal=0;
		try {
			novSal=calcMonthSal(ses,df.parse("112019"),false);
		}
		catch(ParseException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		Employee id6=getEmp(ses, 6);
		Account act=id6.getAccount_id();
		act.setAccountNumber(123444111);
		act.setBankName("Wells Fargo");
		act.setBranchCode("WF003");
		id6.setAccount_id(act);
		updateEmployee(ses,id6);
		
		Employee id4=getEmp(ses, 4);
		act=id4.getAccount_id();
		act.setAccountNumber(765876987);
		act.setBankName("RBC");
		act.setBranchCode("RBC005");
		id6.setAccount_id(act);
		updateEmployee(ses,id6);
		
		double decSal=0;
		try {
			decSal=calcMonthSal(ses,df.parse("122019"),false);
		}
		catch(ParseException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		double q4Sal=octSal+novSal+decSal;
		
		ses.close();
		
	}
	public static double calcMonthSal(Session ses,Date month,boolean status ) {
			org.hibernate.Transaction tx1=ses.beginTransaction();
			double total=0;
			List<Employee> emL=getAllEmp(ses,"");
			for(Employee emp:emL) {
				if(emp.getId()!=1) {
					SalForMonth sal=emp.sfmCalc(month, status);
					addSfm(ses,sal);
					total+=sal.getFinalSal();
				}
				
			}
			tx1.commit();
			return total;
	}
	public static void calcSal(Employee emp,Date month,boolean status ) {
		if(emp.isActive()) {
			Set<SalForMonth> sfm= emp.getSfm();
			emp.getRd().add(emp.sfmCalc(month,status));
		}
	}
	public static void calcPartialSal(Employee emp,Date month,boolean status,double fraction ) {
		if(emp.isActive()) {
			Set<SalForMonth> sfm= emp.getSfm();
			emp.getRd().add(emp.sfmCalc(month,status,fraction));
		}
	}
	public static Integer addSal(Session ses,Salary sl) {
		return (Integer) ses.save(sl);
	}
	public static Salary getSal(Session ses,Integer id) {
		return ses.get(Salary.class,id);
	}
	
	public static void updateSal(Session ses,Salary sl) {
		org.hibernate.Transaction  tx=ses.beginTransaction();
		try {
			ses.update(sl);
			tx.commit();
		}
		catch(HibernateException ex) {
			ex.printStackTrace();
			tx.rollback();
		}
	}
	public static List<Salary> getAllSal(Session ses) {
		return ses.createQuery("from Salary").list();
	}
	public static Integer addEmployee(Session session,Employee e) {
		return (Integer) session.save(e);
	}
	public static Employee getEmp(Session ses,int id) {
		return ses.get(Employee.class, id);
	}
	public static List<Employee> getAllEmp(Session ses,String where) {
		if(where!=null && !where.isEmpty()) {
				return ses.createQuery("From Employee "+where).list();
		}
		else {
			return ses.createQuery("From Employee").list();
		}
	}
	public static void updateEmployee(Session ses,Employee em) {
		org.hibernate.Transaction  tx=ses.beginTransaction();
		try {
			ses.update(em);
			tx.commit();
		}
		catch(HibernateException ex) {
			ex.printStackTrace();
			tx.rollback();
		}
		
			
	}
	public static Integer addSfm(Session ses,SalForMonth sl) {
		return (Integer) ses.save(sl);
	}
	public static void updateSfm(Session ses,SalForMonth sfm) {
		org.hibernate.Transaction  tx=ses.beginTransaction();
		try {
			ses.update(sfm);
			tx.commit();
		}
		catch(HibernateException ex) {
			ex.printStackTrace();
			tx.rollback();
		}
	}
	public static List<SalForMonth> getSfm(Session ses,int id) {
		//return (SalForMonth) ses.get("ref_id", id);
		return ses.createQuery("From SalForMonth").list();
		//return ses.get(SalForMonth.class, id);
	}

}