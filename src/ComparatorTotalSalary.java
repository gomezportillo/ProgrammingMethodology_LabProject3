import java.util.Comparator;

/*********************************************************************
* Class Name: ComparatorTotalSalary
* Author/s name: Pedro-Manuel Gómez-Portillo López
* Release/Creation date: 27/4
* Class version: v1.0
* Class description: This class is used to order the vector of 
* 	fellowships by the total salary of each fellowship 
**********************************************************************/ 


public class ComparatorTotalSalary implements Comparator{

	public int compare(Object a, Object b){
		int v =0;
		double dif = ((Fellowship)a).getTotalSalary() - ((Fellowship)b).getTotalSalary();
		if (dif < 0) {
			v = +1;
		} else if (dif >0){
			v = -1;
		}
		return v;
	}

}
