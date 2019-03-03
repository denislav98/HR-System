public  class Employee {

    private int id;
    
    private String name;
    
    private String department;
    
    private int salary;
    
    private int managerId;

    private double ratio;

	public Employee(int id, String name, String department, int salary, int managerId, double ratio) {
		this.id = id;
		this.name = name;
		this.department = department;
		this.salary = salary;
		this.managerId = managerId;
		this.ratio = ratio;
	}

	public double getRatio() {
		return ratio;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDepartment() {
		return department;
	}

	public int getSalary() {
		return salary;
	}

	public int getManagerId() {
		return managerId;
	}

	@Override
    public String toString() {
        return String.format( "%d - %s", id, name );
    }

}
