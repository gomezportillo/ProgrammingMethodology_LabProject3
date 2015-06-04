
/*********************************************************************
* Class Name: Fellowship
*  Gómez-Portillo López
* Release/Creation date: 27/4
* Class version: v1.0
* Class description: This class models the object of a fellowship in
* 	the point of view of the program.
**********************************************************************/ 

public class Fellowship {
	
	private int id, start_m, end_m, salary_per_m;

	/*****************************************************************
	*
	* Method name: Fellowship
	*
	* Description of the Method: constructor method of the clashh
	*
	* Calling arguments: 
	* 	int id: id of the fellowship
	* 	int start_m: starting month of the fellowship
	* 	int end_m: ending month of the fellowship
	* 	int salary_per_m: salary per month of the fellowship
	*
	* Return value: Fellowship
	*
	* Required Files: no file is required for this method
	*
	* List of Checked Exceptions: it method should not throw any exception
	*********************************************************************/
	
	public Fellowship(int id, int start_m, int end_m, int salary_per_m) {
		super();
		this.id = id;
		this.start_m = start_m;
		this.end_m = end_m;
		this.salary_per_m = salary_per_m;
	}

	/*********************************************************************
	*
	* Method name: compatibleWith
	*
	* Description of the Method: this method is used for computing if this
	* 	felloship is compatible in time with the one passed through the args
	*
	* Calling arguments: 
	* 	Fellowship other: the fellowship we want to compare with
	*
	* Return value: boolean
	*
	* Required Files: no file is required for this method
	*
	* List of Checked Exceptions: it method should not throw any exception
	*
	*********************************************************************/ 
	public boolean compatibleWith(Fellowship other){
		if(this.end_m < other.start_m || this.start_m > other.end_m) 
			return true;
		else 
			return false;
	}
		
	public int getDuration() {
		return this.end_m - this.start_m + 1;		
	}
		
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStart_m() {
		return start_m;
	}

	public void setStart_m(int start_m) {
		this.start_m = start_m;
	}

	public int getEnd_m() {
		return end_m;
	}

	public void setEnd_m(int end_m) {
		this.end_m = end_m;
	}

	public void setSalary_per_m(int salary_per_m) {
		this.salary_per_m = salary_per_m;
	}
	

	public int getSalaryMonth() {
		return this.salary_per_m;
	}

	public int getTotalSalary(){
		return this.getSalaryMonth() * this.getDuration();
	}
	
	@Override
	public String toString() {
		return "[id=" + this.id + ", start=" + this.start_m + ", end=" + this.end_m + ", salary = "+ this.salary_per_m + "]";
	}
	
	
}
