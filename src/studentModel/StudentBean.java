package studentModel;

public class StudentBean {
	
    private String dept_name;
    private String total_credits;
    private String id;
    private String name;
    
    public String getDept_name() {
		return dept_name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public String getTotal_credits() {
		return total_credits;
	}

	public void setTotal_credits(String total_credits) {
		this.total_credits = total_credits;
	}

}
