package payroll;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="emp")
public class Employee {
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="emp_id",unique=true,nullable=false)
	private int empId;
	@Column(name="name",nullable=false)
	private String name;
	@Column(name="email",nullable=false)
	private String email;
	@Column(name="phone",nullable=false)
	private String phone;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="sal_id",nullable=false)
	private Salary salaryRef;
	

	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="act_account_id",nullable=false)
	//@MapsId
	private Account account_id;
	@OneToMany(mappedBy="emp")
//	@JoinColumn(name = "ref_id",referencedColumnName="id")
	private Set<SalForMonth> rd=new HashSet<SalForMonth>();
	
	@Column(name="overpay",nullable=false)
	private double overpay;
	@Column(name="is_active",nullable=false)
	private boolean isActive;

	public int getId() {
		return empId;
	}
	public void setId(int empId) {
		this.empId = empId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public double getOverpay() {
		return overpay;
	}
	public void setOverpay(double overpay) {
		this.overpay = overpay;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	public Salary getSalaryRef() {
		return salaryRef;
	}
	public void setSalaryRef(Salary salaryRef) {
		this.salaryRef = salaryRef;
	}
	

	public Set<SalForMonth> getRd() {
		return rd;
	}
	public void setRd(Set<SalForMonth> rd) {
		this.rd = rd;
	}
	public Account getAccount_id() {
		return account_id;
	}
	public void setAccount_id(Account account_id) {
		this.account_id = account_id;
	}
	@Override
	public String toString() {
		return "Employee [id=" + empId + ", name=" + name + ", email=" + email + ", phone=" + phone +  ", overpay=" + overpay + ", isActive=" + isActive + "]";
	}
	
	public Set<SalForMonth> getSfm() {
		return rd;
	}
	public SalForMonth sfmCalc(Date date,boolean status) {
		Double base=overpay+salaryRef.getBase();
		Double travel=base*salaryRef.getTravelAllowance();
		Double insurance=base*salaryRef.getInsurance();
		Double food=base*salaryRef.getFoodAllowance();
		return new SalForMonth(this,date,base,insurance,travel,food,base+insurance+travel+food,status);
		
	}
	public SalForMonth sfmCalc(Date date,boolean status,double partial) {
		Double base=(overpay+salaryRef.getBase())*partial;
		Double travel=base*salaryRef.getTravelAllowance();
		Double insurance=base*salaryRef.getInsurance();
		Double food=base*salaryRef.getFoodAllowance();
		return new SalForMonth(this,date,base,insurance,travel,food,base+insurance+travel+food,status);
		
	}
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Employee(int empId, String email,boolean isActive, String name,int overpay, String phone) {
		
		super();
		this.empId = empId;
		this.name = name;
		this.email = email;
		this.phone = phone;

		this.overpay = overpay;
		this.isActive = isActive;
	}
	
	
	
}