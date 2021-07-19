package student;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;



public class StudentDAO {
	private StudentTextFieldEvent gui;

	ArrayList<Student> list; // 학생 리스트
	private int cnt; // 등록된 학생수
	private Scanner scan;
	
	private String id; // 학생 id
	private String name; // 학생 이름
	private int java; // 학생 성적
	private String chool;
	
	
	String filePath;


	public StudentDAO() {
		scan = new Scanner(System.in);
		list = new ArrayList<Student>();
	}

	public void add(String tf_id, String tf_name, int tf_java, int tf_c, int tf_rinux ,String tf_chool) {
		// id가 중복 확

		for (int i = 0; i < list.size(); i++) {
			Student stu = list.get(i);
			if (tf_id.equals(stu.getId())) {
				System.out.println("이미 존재하는 학번입니다.");
				return;
			}
		}
		if (tf_java >= 0 && tf_java <= 100) {

			Student stu = new Student(tf_id, tf_name, tf_java, tf_c, tf_rinux,tf_chool);

			list.add(stu);
		

			System.out.println("등록 완료");

		} else {
			System.out.println("0부터 100사이의 숫자만 입력하세요.");
		}

	}

	// 2. 조회 메뉴
	public void inquire() {
		System.out.println("1.이름순으로 조회 2.성적순으로 조회");
		int search = scan.nextInt();
		scan.nextLine();
		if (search == 1) {
			Collections.sort(list);
			for (int i = 0; i < list.size(); i++) {
				System.out.println("ID : " + list.get(i).getId() + " 이름 : " + list.get(i).getName() + " 성적 : "
						+ list.get(i).getjava());
			}
		} else if (search == 2) {
			Collections.sort(list, new ScoreComparator());
			for (int i = 0; i < list.size(); i++) {
				System.out.println("ID : " + list.get(i).getId() + " 이름 : " + list.get(i).getName() + " 성적 : "
						+ list.get(i).getjava());
			}
		} else {
			System.out.println("다시 입력하세요");
		}
	}

	// 3. 수정 메뉴
	public void update() {
		System.out.println("수정할 학생 ID: ");
		String up_id = scan.nextLine();
		boolean found = false;
		int tempIndex = 0;

		for (int i = 0; i < list.size(); i++) {
			Student stu = list.get(i);
			if (up_id.equals(stu.getId())) {
				found = true;
				tempIndex = i;

				break;
			}
		}
		if (found) {
			System.out.println("수정ID : ");
			String new_id = scan.nextLine();

			System.out.println("수정할 이름 : ");
			String new_name = scan.nextLine();

			System.out.println("수정할 성적 : ");
			int new_java = scan.nextInt();

			System.out.println("수정할 성적 : ");
			int new_c = scan.nextInt();
//			System.out.println("수정할 성적 : ");

			System.out.println("수정할 출석 : ");
			String new_chool = scan.nextLine();
			
			int new_rinux = scan.nextInt();
			scan.nextLine(); // 개행문자(엔터)를 제거하기위해 추가
			Student new_stu = new Student(new_id, new_name, new_java, new_c, new_rinux, new_chool);
			list.set(tempIndex, new_stu);
			System.out.println("수정완료");
		} else {
			System.out.println("등록된 ID가 없습니다.");
		}
	}

	// 4. 삭제 메뉴
	public void delete() {

		System.out.println("삭제할 학생 학번을 입력하세요");
		String del_id = scan.nextLine();
		System.out.println("삭제할 학생 이름을 입력하세요");
		String del_name = scan.nextLine();
		boolean found = false;
		int tempIndex = 0;

		for (int i = 0; i < list.size(); i++) {
			Student stu = list.get(i);
			if (del_id.equals(stu.getId()) && del_name.equals(stu.getName())) {
				found = true;
				tempIndex = i;
				break;
			}
		}

		if (found) {
			System.out.println(list.get(tempIndex));
			list.remove(tempIndex);
			System.out.println("ID가 삭제됨");
			cnt--;
		} else {
			System.out.println("등록된 ID가 없습니다.");
		}
	}

	public ArrayList<Student> getList() {
		return list;
	}

	public void setList(ArrayList<Student> list) {
		this.list = list;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public int getCnt() {
		return cnt;
	}

	// 7. 저장
	public void save() {
		// 1) 적절한 스트림을 선정
		OutputStream out = null;
		ObjectOutputStream oos = null;

		try {
			out = new FileOutputStream("member.txt");
			oos = new ObjectOutputStream(out);

			oos.writeObject(list);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
				oos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// 8.불러오기
	public void invoke() {
		InputStream in = null;
		ObjectInputStream ois = null;
		ArrayList<Student> li = null;

		try {
			in = new FileInputStream("member.txt");
			ois = new ObjectInputStream(in);
			li = (ArrayList<Student>) ois.readObject();

			Iterator it = li.iterator();
			while (it.hasNext()) {
				System.out.println(it.next());
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				in.close();
				ois.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void setPath(String filePath) {
		// TODO Auto-generated method stub
		this.filePath = filePath;
	}
}
