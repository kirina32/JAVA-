package student;

import java.util.Comparator;

public class ScoreComparator implements Comparator<Student> {

	@Override
	public int compare(Student o1, Student o2) {
		// TODO Auto-generated method stub
		return o1.getscore() - o2.getscore();
	}

}
