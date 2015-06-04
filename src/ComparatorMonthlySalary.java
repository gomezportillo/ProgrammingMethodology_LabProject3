import java.util.Comparator;

/*********************************************************************
* Class Name: ComparatorMonthlySalary
* Author/s name: Pedro-Manuel Gómez-Portillo López
* Release/Creation date: 27/4
* Class version: v1.0
* Class description: This class is used to order the vector of 
* 	fellowships by the monthly salary of each fellowship 
**********************************************************************/ 


public class ComparatorMonthlySalary implements Comparator{

	public int compare(Object a, Object b){
		int v =0;
		double dif = ((Fellowship)a).getSalaryMonth() - ((Fellowship)b).getSalaryMonth();
		
		if (dif < 0) {
			v = +1;
		} else if (dif >0){
			v = -1;
		}
		return v;
	}

}
