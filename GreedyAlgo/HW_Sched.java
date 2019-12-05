import java.util.*;

class Assignment implements Comparator<Assignment>{
	int number;
	int weight;
	int deadline;
	
	
	protected Assignment() {
	}
	
	protected Assignment(int number, int weight, int deadline) {
		this.number = number;
		this.weight = weight;
		this.deadline = deadline;
	}
	
	
	
	/**
	 * This method is used to sort to compare assignment objects for sorting. 
	 * Return -1 if a1 > a2
	 * Return 1 if a1 < a2
	 * Return 0 if a1 = a2 
	 */
	@Override
	public int compare(Assignment a1, Assignment a2) {
		if (a1.deadline>a2.deadline) {
			return 1;
		}
		else if(a1.deadline<a2.deadline) {
			return -1;
		}
		else {
			return 0;
		}
		
	}
}

public class HW_Sched {
	ArrayList<Assignment> Assignments = new ArrayList<Assignment>();
	int m;
	int lastDeadline = 0;
	
	protected HW_Sched(int[] weights, int[] deadlines, int size) {
		for (int i=0; i<size; i++) {
			Assignment homework = new Assignment(i, weights[i], deadlines[i]);
			this.Assignments.add(homework);
			if (homework.deadline > lastDeadline) {
				lastDeadline = homework.deadline;
			}
		}
		m =size;
	}
	
	
	/**
	 * 
	 * @return Array where output[i] corresponds to the assignment 
	 * that will be done at time i.
	 */
	public int[] SelectAssignments() {
		//TODO Implement this
		
		//Sort assignments
		//Order will depend on how compare function is implemented
		Collections.sort(Assignments, new Assignment());
		
		// If schedule[i] has a value -1, it indicates that the 
		// i'th timeslot in the schedule is empty
		int[] homeworkPlan = new int[lastDeadline];
		for (int i=0; i < homeworkPlan.length; ++i) {
			homeworkPlan[i] = -1;
		}
		int maxw=0;
		int currDeadline=Assignments.get(0).deadline;
		int time=0;
		int position=0;
		for(;time<lastDeadline;time++) {			
			while((position<Assignments.size())&&(Assignments.get(position).deadline==currDeadline)) {				
				if (Assignments.get(position).weight>=maxw) {
					maxw=Assignments.get(position).weight;
					homeworkPlan[time]=Assignments.get(position).number;
				}
				position+=1;
				
			}
			maxw=0;
			if(position<Assignments.size()) {
				currDeadline=Assignments.get(position).deadline;
			}
		
		}
				
		return homeworkPlan;
	}
}
	



