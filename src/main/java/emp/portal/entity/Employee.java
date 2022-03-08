package emp.portal.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(
        name = "Employee")
public class Employee {
	
		@Id
	    @Column(
	    		name="Emp_Id"
	    		)
	    
	    private long id;
	    
	    @Column(
	    		name="Emp_Name"
	    		)
	    private String name;
	    
	    @Column(
	    		name="Emp_Email"
	    		)
	    private String emp_email;
	    
	    @Column(
	    		name="Emp_Designation"
	    		)
	    private String designation;
	    
	    @Column(
	    		name="Account_Mapped"
	    		)
	    private String  acc_Mapped;
	    
	    @Column(
	    		name="Reporting_Manager"
	    		)
	    private String  rep_Manager;
	    
	    
		
		public Employee() {}



		public Employee(long id, String name, String emp_email, String designation, String acc_Mapped,
				String rep_Manager) {
			this.id = id;
			this.name = name;
			this.emp_email = emp_email;
			this.designation = designation;
			this.acc_Mapped = acc_Mapped;
			this.rep_Manager = rep_Manager;
		}



		public long getId() {
			return id;
		}



		public void setId(long id) {
			this.id = id;
		}



		public String getName() {
			return name;
		}



		public void setName(String name) {
			this.name = name;
		}



		public String getEmp_email() {
			return emp_email;
		}



		public void setEmp_email(String emp_email) {
			this.emp_email = emp_email;
		}



		public String getDesignation() {
			return designation;
		}



		public void setDesignation(String designation) {
			this.designation = designation;
		}



		public String getAcc_Mapped() {
			return acc_Mapped;
		}



		public void setAcc_Mapped(String acc_Mapped) {
			this.acc_Mapped = acc_Mapped;
		}



		public String getRep_Manager() {
			return rep_Manager;
		}



		public void setRep_Manager(String rep_Manager) {
			this.rep_Manager = rep_Manager;
		}
		
}
