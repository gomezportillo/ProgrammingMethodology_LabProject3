import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Scanner;

/*********************************************************************
 * Class Name: Manager
 * Author/s name: Pedro-Manuel Gómez-Portillo López
 * Release/Creation date: 27/4
 * Class version: v2.0
 * Class description: This is the main class of the program
 **********************************************************************/ 

@SuppressWarnings({"unchecked", "unused"})
public class Manager {

	public static void main (String[] argv){

		List<Fellowship> _possiblesFellowships = new ArrayList<Fellowship>();
		List<Fellowship> _selectedFellowships = new ArrayList<Fellowship>();

		String file = "fellowships_1.txt";
		int n_fellowships = 8;

		try {
			readAllFellowships(_possiblesFellowships, file, n_fellowships);
		} catch (FileNotFoundException e) {
			System.out.println(e + "\nExiting program...");
			System.exit(0);
		}

		/************************************************************************* 
		 * For selecting one sorting method just comment the other one
		 **************************************************************************/
		shortFellowshipsByMonthlySalary(_possiblesFellowships);
		//shortFellowshipsByTotalSalary(_possiblesFellowships);


		/************************************************************************* 
		 * For selecting one selecting method just comment the other one
		 **************************************************************************/
		//selectFellowships(_possiblesFellowships, _selectedFellowships);
		backTrackingSelectFellowships(_possiblesFellowships, n_fellowships);

		//printSelectedFellowship(_selectedFellowships);
	}

	private static void backTrackingSelectFellowships(List<Fellowship> fel, int n_fellowships) {
		int [] x, sol;
		x =  new int[n_fellowships]; 	for (int i:x) i = -1;
		sol = new int[n_fellowships]; 	for (int i:sol) i = 0;
			
		backtrackFellowships(x, sol, 0, fel, n_fellowships);
		
		System.out.println("Result of the backtracking approach (should be the optimal one)");
		for (int i=0; i< n_fellowships; i++)
			if (sol[i] == 1)
				System.out.println(fel.get(i));
	}

	/*********************************************************************
	 *
	 * Method name: backtrackfellowships
	 *
	 * Description of the Method: this method computed by the backtracking
	 * 		paradigm the optimal set of fellowship for this problem.
	 *
	 * Calling arguments: 
	 * 	pos: the list with all the fellowships
	 * 	sel: an emply list for storing in order the selected list
	 * 	level: level of the tree in which we are
	 * 	sol: the class solution is for being able to store the optimal result
	 * 			by reference
	 *
	 * Return value: void
	 *
	 * Required Files: no file is required for this method
	 *
	 * List of Checked Exceptions: it method should not throw any exception
	 *
	 *********************************************************************/ 
	private static void backtrackFellowships(int[] x, int[] sol, int level, List<Fellowship> fel, int n_fellowships) {
		if (level == n_fellowships) {								//if we are on the leaves of the tree
			if (isBetter(x, sol, fel, n_fellowships)){ 				//if the actual result is better than the solution
				for (int i=0; i<n_fellowships; i++) sol[i] = x[i];  //copying the new best solution
			}

		} else { //if we are NOT on the leaves we keep going down
			if (compatibleWithAll(level, x, fel)) { //if it is compatible with all we try with it
				x[level] = 1;
				backtrackFellowships(x, sol, level+1, fel, n_fellowships);
			}

			x[level] = 0;
			backtrackFellowships(x, sol, level+1, fel, n_fellowships);
		}
	}	


	private static boolean isBetter(int[] x, int[] sol, List<Fellowship> fel, int n_fellowships) {
		int money_x = 0, money_sol = 0;
		
		for (int i = 0; i< n_fellowships; i++){
			if (x[i] == 1) money_x +=  fel.get(i).getTotalSalary();
			if (sol[i] == 1) money_sol +=  fel.get(i).getTotalSalary();
		}
		
		return money_x > money_sol;
	}

	private static int totalMoney(List<Fellowship> sel) {
		int totalMoney = 0;
		for (int i=0; i< sel.size(); i++){
			totalMoney+=sel.get(i).getTotalSalary();			
		}
		return totalMoney;
	}

/**-------------------------------------------------------GREEDY----------------------------------------------------------*/

	/*********************************************************************
	 *
	 * Method name: selectFellowships
	 *
	 * Description of the Method: this method is used for selecting the
	 * 	best fellowships available following the sorting we have aplied.
	 *
	 * Calling arguments: 
	 * 	pos: the list with all the fellowships
	 * 	sel: an emply list for storing in order the selected list
	 *
	 * Return value: void
	 *
	 * Required Files: no file is required for this method
	 *
	 * List of Checked Exceptions: it method should not throw any exception
	 *
	 *********************************************************************/ 
	private static void selectFellowships (List<Fellowship> pos, List<Fellowship> sel) {

		sel.add(pos.get(0));
		pos.remove(0);

		for(int i=0; i<pos.size(); i++){ //for each fship in possibles
			if (compatibleWithAll(pos.get(i), sel)){ //si todos son compatibles
				sel.add(pos.get(i));
				pos.remove(i);
			}
		}
	}	

	private static boolean compatibleWithAll(Fellowship f, List<Fellowship> sel){

		for(int j=0; j<sel.size(); j++){ //for each fship in selected

			if (!sel.get(j).compatibleWith(f)) 
				return false;
		}
		return true;

	}

	private static boolean compatibleWithAll(int level, int[] x, List<Fellowship> fellows){

		for(int j=0; j<=level; j++){ 	//for each fship
			if (x[j] == 1) {	 				//if it was selected
				if (!fellows.get(j).compatibleWith(fellows.get(level))); 
					return false;
			}
		}
		return true;
	}

	private static void printSelectedFellowship (List<Fellowship> sel) {
		System.out.println("Optimal fellowship set:\n" + sel);
		System.out.println("Earnings obtained: " + totalMoney(sel));
	}

	private static void shortFellowshipsByMonthlySalary(List<Fellowship> _possiblesFellowships) {
		Collections.sort(_possiblesFellowships, new ComparatorMonthlySalary());		
	}

	private static void shortFellowshipsByTotalSalary(List<Fellowship> _possiblesFellowships) {
		Collections.sort(_possiblesFellowships, new ComparatorTotalSalary());		
	}

	/*********************************************************************
	 *
	 * Method name: readAllFellowships
	 *
	 * Description of the Method: this method reads from a file with all the
	 * 	fellowships and inserts them in an ArrayList<Fellowship>
	 *
	 * Calling arguments: 
	 * 	List<Fellowship> list: the list in where we will save the read fellowships
	 * 	String file: an string with the location an name of the file
	 * 	int n_rows: the number of fellowships in the file
	 *
	 * Return value: void
	 *
	 * Required Files: a file with all the fellowships
	 *
	 * List of Checked Exceptions: it method may throw an FileNotFoundException
	 *
	 *********************************************************************/ 
	private static void readAllFellowships(List<Fellowship> list, String file, int n_rows) throws FileNotFoundException {

		Scanner read = new Scanner(new File(file));

		Fellowship tmp_fship;

		for (int i = 0; i<n_rows; i++){
			tmp_fship = new Fellowship(read.nextInt(), read.nextInt(), read.nextInt(), read.nextInt());
			list.add(tmp_fship);			
		}
		read.close();
	}
}
