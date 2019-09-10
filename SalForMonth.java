package payroll;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="sal_month")
public class SalForMonth {
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Id
	@Column(name="id",unique=true,nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	//@JoinColumn(name="emp_id",unique=true)
	@ManyToOne
	@JoinColumn(name="emp_id")
	private Employee emp; 
	@Column(name="month",nullable=false)
	private Date month;
	@Column(name="base_sal",nullable=false)
	private double baseSal;
	@Column(name="p1",nullable=false)
	private double p1;
	@Column(name="p2",nullable=false)
	private double p2;
	@Column(name="p3",nullable=false)
	private double p3;
	@Column(name="final_sal",nullable=false)
	private double finalSal;
	@Column(name="payment_status",nullable=false)
	private boolean paymentStatus;//true-has been paid, false-not yet paid
	public Date getMonth() {
		return month;
	}
	public void setMonth(Date month) {
		this.month = month;
	}
	public double getBaseSal() {
		return baseSal;
	}
	public void setBaseSal(double baseSal) {
		this.baseSal = baseSal;
	}
	public double getP1() {
		return p1;
	}
	public void setP1(double p1) {
		this.p1 = p1;
	}
	public double getP2() {
		return p2;
	}
	public void setP2(double p2) {
		this.p2 = p2;
	}
	public double getP3() {
		return p3;
	}
	public void setP3(double p3) {
		this.p3 = p3;
	}
	public double getFinalSal() {
		return finalSal;
	}
	public void setFinalSal(double finalSal) {
		this.finalSal = finalSal;
	}
	public boolean isPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(boolean paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	
	@Override
	public String toString() {
		return "SalForMonth [id=" + id + ", emp=" + emp + ", month=" + month + ", baseSal=" + baseSal + ", p1=" + p1
				+ ", p2=" + p2 + ", p3=" + p3 + ", finalSal=" + finalSal + ", paymentStatus=" + paymentStatus + "]";
	}
	public SalForMonth(Employee emp, Date month, double baseSal, double p1, double p2, double p3, double finalSal, boolean paymentStatus) {
		super();
		this.emp = emp;
		this.month = month;
		this.baseSal = baseSal;
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		this.finalSal = finalSal;
		this.paymentStatus = paymentStatus;
	}
	public SalForMonth(int id, Date month, double baseSal, double p1, double p2, double p3, double finalSal, boolean paymentStatus) {
		super();
		this.id = id;
		this.month = month;
		this.baseSal = baseSal;
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		this.finalSal = finalSal;
		this.paymentStatus = paymentStatus;
	}
	public SalForMonth() {
		
	}
	
}