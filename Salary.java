package payroll;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="salary")
public class Salary {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id",unique=true,nullable=false)
	private int id;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="sal_id",unique=true)
	private Set<Employee> emps;
	@Column(name="base",nullable=false)
	private double base;
	@Column(name="travel_allowance",nullable=false)
	private double travelAllowance;
	@Column(name="food_allowance",nullable=false)
	private double foodAllowance;
	@Column(name="insurance",nullable=false)
	private double insurance;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getBase() {
		return base;
	}
	public void setBase(double d) {
		this.base = d;
	}
	public double getTravelAllowance() {
		return travelAllowance;
	}
	public void setTravelAllowance(double travelAllowance) {
		this.travelAllowance = travelAllowance;
	}
	public double getFoodAllowance() {
		return foodAllowance;
	}
	public void setFoodAllowance(double foodAllowance) {
		this.foodAllowance = foodAllowance;
	}
	public double getInsurance() {
		return insurance;
	}
	public void setInsurance(double insurance) {
		this.insurance = insurance;
	}
	
	public Set<Employee> getEmps() {
		return emps;
	}
	public void setEmps(Set<Employee> emps) {
		this.emps = emps;
	}
	@Override
	public String toString() {
		return "Salary [id=" + id + ", emps=" + emps + ", base=" + base + ", travelAllowance=" + travelAllowance
				+ ", foodAllowance=" + foodAllowance + ", insurance=" + insurance + "]";
	}
	public Salary(int base, double travelAllowance, double foodAllowance, double insurance) {
		super();
		this.base = base;
		this.travelAllowance = travelAllowance;
		this.foodAllowance = foodAllowance;
		this.insurance = insurance;
	}
	public Salary() {
		
	}
	
}