package student;

import java.io.Serializable;
import java.text.DecimalFormat;

/*
 * Student.java
 * 
 * 학생관리시스템에서 학생 데이터 하나를 객체단위로 보관하기 위한 클래스
 * 학생 각각의 정보는 하나의 Student 데이터 객체가 된다.
 *
 */
//객체 데이터를 파일에 저장하려면 객체를 직렬화 해주어야 한다.
//객체직렬화는 Serializable 인터페이스를 구현 해주어야 한다.
public class Student implements Comparable<Student>, Serializable{
	private String id;		//학번	
	private String name;	//이름
	private int java;		//국어
	private int c;		//영어
	private int rinux;		//수학
	private String check;
	private String chool;

	public String getChool() {
		return chool;
	}

	public void setChool(String chool) {
		this.chool = chool;
	}

	public Student(String id, String name, int java, int c, int rinux, String chool) {
		this.id = id;
		this.name = name;
		this.java = java;
		this.c = c;
		this.rinux = rinux;
		this.chool = chool;
	}

	public String getCheck() {
		return check;
	}

	public void setCheck(String check) {
		this.check = check;
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

	public int getjava() {
		return java;
	}

	public void setjava(int java) {
		this.java = java;
	}

	
	public int getc() {
		return c;
	}

	public void setc(int c) {
		this.c = c;
	}
	public int getrinux() {
		return rinux;
	}

	public void setrinux(int rinux) {
		this.rinux = rinux;
	}
	//객체 정보를 문자열로 표현
	public String toString() {
		return id+":"+name+":"+java+":"+c+":"+rinux+":"+chool;
	}
	//객체 같은지 검사
	public boolean equals(Student stu) {
		boolean result = false;
		if(id.equals(stu.id))
			return true;
		return result;
	}

	@Override
	public int compareTo(Student o) {
		// TODO Auto-generated method stub
		return this.name.compareTo(o.name);
	}
	
	public int getscore() {
		DecimalFormat form = new DecimalFormat("#.##");

		float avg = (this.java+this.c+this.rinux)/3;
		String avg1 = form.format(avg);
		return (int)avg;
	}
}









