package payroll;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="account")
public class Account {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="account_id",unique=true,nullable=false)
	private int account_id;
	public int getAccountId() {
		return account_id;
	}
	public void setAccountId(int account_id) {
		this.account_id = account_id;
	}
	//@JoinColumn(name="account_id",unique=true)
	@OneToOne(mappedBy="account_id",cascade=CascadeType.ALL)
	private Employee emp;
	@Column(name="type",nullable=false)
	private String type;
	@Column(name="bank_name",nullable=false)
	private String bankName;
	@Column(name="account_number",nullable=false)
	private int accountNumber;
	@Column(name="branch_code",nullable=false)
	private String branchCode;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	
	public int getAccount_id() {
		return account_id;
	}
	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}
	public Employee getEmp() {
		return emp;
	}
	public void setEmp(Employee emp) {
		this.emp = emp;
	}
	@Override
	public String toString() {
		return "Account [account_id=" + account_id + ", emp=" + emp + ", type=" + type + ", bankName=" + bankName
				+ ", accountNumber=" + accountNumber + ", branchCode=" + branchCode + "]";
	}
	public Account(int accountNumber, String bankName, String branchCode,String type) {
		super();
		this.accountNumber=accountNumber;
		this.type = type;
		this.bankName = bankName;
		this.branchCode = branchCode;
	}
	public Account() {
		
	}
	
	
}