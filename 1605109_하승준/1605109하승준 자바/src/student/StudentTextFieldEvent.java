package student;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class StudentTextFieldEvent extends JFrame implements FocusListener, KeyListener, ActionListener {
	private StudentDAO dao = new StudentDAO();

	private JTextField tf_id = new JTextField("아이디를 입력하세요",20); // id
	
	private JTextField tf_name = new JTextField("이름을 입력하세요",20); // 이름
	private JTextField tf_java = new JTextField("자바성적을 입력하세요",20); // 자바
	private JTextField tf_c = new JTextField("씨성적을 입력하세요",20); // 씨언어
	private JTextField tf_rinux = new JTextField("리눅스성적 입력하세요",20); // 리눅스
//	private JTextField tf_chool = new JTextField("출석을 입력하세요",20); //출석 
	private String[] tf_chool ={"계근","1회결석","2회결석","3회결석","수업미참여"};
	 JComboBox strCombo= new JComboBox(tf_chool);


	
	
	private JPanel p;

	JScrollPane scrollPane;


	
//	bt_inquire.setOpaque(true);
//	bt_inquire.setBorderPainted(false);
	
	private JLabel ja_id = new JLabel("ID");
	private JLabel ja_name = new JLabel("이름");
	private JLabel ja_java = new JLabel("자바");
	private JLabel ja_c = new JLabel("C언어");
	private JLabel ja_rinux = new JLabel("리눅스");
	private JLabel ja_chool = new JLabel("출석");
	
	ImageIcon bener = new ImageIcon("images/bener.jpg");
	private JLabel ja_bener = new JLabel(bener);

	
	private JButton bt_ok = new JButton("등록");
	private JButton bt_inquire = new JButton("조회");
	private JButton bt_update = new JButton("수정");
	private JButton bt_delete = new JButton("삭제");
	private JButton bt_exit = new JButton("종료");
	private JScrollPane scroll;

	
	private JButton log_ok = new JButton("확인");
	private JButton log_no = new JButton("취소");

	private EditDialog edit;
	private DeleteDialog delete;
	private InquireDialog inquire;
	private Color c1 = new Color(0, 0, 0);
	private Font font2 = new Font("Serif", Font.BOLD, 15);

	ImageIcon icon = new ImageIcon("images/white.jpg");
	private JFileChooser fc = new JFileChooser();

	ArrayList<String> list; // 학생 리스트

	StudentTextFieldEvent() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Dimension frameSize = this.getSize();
		Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
//		this.setLocation((windowSize.width - frameSize.width) / 3, (windowSize.height - frameSize.height) / 3);
		this.setTitle("학생관리 프로그램");
		this.formDesign();
		this.eventHandler();
		this.setSize(500, 480);
		this.setVisible(true);
	}

	public void formDesign() {
		p = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(icon.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		scrollPane = new JScrollPane(p);
		setContentPane(scrollPane);

		p.setLayout(null);
		
		reset();
		
		
	}

	public void eventHandler() {
		tf_id.addFocusListener(this);

		tf_name.addFocusListener(this);
		tf_java.addFocusListener(this);
		tf_c.addFocusListener(this);
		tf_rinux.addFocusListener(this);

//		tf_chool[i].addFocusListener(this);
			
		
		
		tf_java.addKeyListener(this);

		bt_ok.addActionListener(this);
		bt_inquire.addActionListener(this);
		bt_update.addActionListener(this);
		bt_delete.addActionListener(this);
		bt_exit.addActionListener(this);

		

	}

	public static void main(String[] args) {
		new StudentTextFieldEvent();
	}

	@Override
	public void focusGained(FocusEvent e) {
		if(e.getSource().equals(tf_id)) {
			tf_id.setText("");
		}
		if(e.getSource().equals(tf_name)) {
			tf_name.setText("");
		}
		if(e.getSource().equals(tf_java)) {
			tf_java.setText("");
		}
		if(e.getSource().equals(tf_c)) {
			tf_c.setText("");
		}
		if(e.getSource().equals(tf_rinux)) {
			tf_rinux.setText("");
		}
//		if(e.getSource().equals(tf_chool)) {
//			tf_chool.setText("");
//		}
	}

	
//	else if(e.getSource().equals(tf_chool)) {
//		if(tf_chool.getText().equals("")) {
//			tf_chool_setText("출석을 입력하세요");
//		} else {
//			tf_rinux.setEditable(true);
//		}
//	}
	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource().equals(tf_id)) {
			if (tf_id.getText().equals("")) {
				tf_id.setText("아이디를 입력하세요");
			} else {
				tf_name.setEditable(true);
			}
		} else if (e.getSource().equals(tf_name)) {
			if (tf_name.getText().equals("")) {
				tf_name.setText("이름을 입력하세요");
			} else
				tf_java.setEditable(true);
		} else if (e.getSource().equals(tf_java)) {
			if (tf_java.getText().equals("")) {
				tf_java.setText("자바점수 입력하세요");
			} else {
				tf_c.setEditable(true);
			}
		} else if (e.getSource().equals(tf_c)) {
			if (tf_c.getText().equals("")) {
				tf_c.setText("씨점수 입력하세요");
			} else {
				tf_rinux.setEditable(true);
			}
		} else if (e.getSource().equals(tf_rinux)) {
			if (tf_rinux.getText().equals("")) {
				tf_rinux.setText("리눅스점수 입력하세요");
			} else {
				// 등록 버튼 활성화
				// 등록버튼이 포크스를 갖도록 한다.

				bt_ok.setEnabled(true);
				bt_ok.requestFocus();
			} 
		} 
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == e.VK_ENTER) {
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bt_ok) {
			// StudentDAO에 있는 등록 메뉴를 처리하는 메소드, insert()에 입력받은 학생 데이터를 전달하여 등록한다.
			for (int i = 0; i < dao.list.size(); i++) {
				Student stu = dao.list.get(i);
				if (tf_id.getText().equals(stu.getId())) {
					JOptionPane.showMessageDialog(null, "이미 존재하는 학번입니다. 다시입력하세요!!", "Message",
							JOptionPane.OK_CANCEL_OPTION);
					tf_id.setText("");
					return;
				}
			}
			if (Integer.parseInt(tf_java.getText()) >= 0 && Integer.parseInt(tf_java.getText()) <= 100
					&& Integer.parseInt(tf_c.getText()) >= 0 && Integer.parseInt(tf_c.getText()) <= 100
					&& Integer.parseInt(tf_rinux.getText()) >= 0 && Integer.parseInt(tf_rinux.getText()) <= 100) {
				dao.add(tf_id.getText(), tf_name.getText(), Integer.parseInt(tf_java.getText()),
						Integer.parseInt(tf_c.getText()), Integer.parseInt(tf_rinux.getText()),(String)strCombo.getSelectedItem());
//				tf_chool.getText()
				JOptionPane.showMessageDialog(null, "등록되었습니다!!", "Message", JOptionPane.OK_CANCEL_OPTION);
//				tf_name.setEditable(false);
//				tf_java.setEditable(false);
//				tf_c.setEditable(false);
//				tf_rinux.setEditable(false);
				tf_id.setText("아이디를 입력하세요");
				tf_name.setText("이름을 입력하세요");
				tf_java.setText("자바성적을 입력하세요");
				tf_c.setText("씨언어 점수를 입력하세요");
				tf_rinux.setText("리눅스 점수를 입력하세요");
//				tf_chool.setText("출석을 입력하세요");
			} else {
				JOptionPane.showMessageDialog(null, "0부터 100사이의 점수만 입력하세요!!", "Message", JOptionPane.OK_CANCEL_OPTION);
			}
			// 추가적으로 다른 버튼을 활성화
		}
		// -----------------------------------------------------------------------------------------------------------------------------------조회
		else if (e.getSource() == bt_inquire) {
			inquire = new InquireDialog(this, dao);
			inquire.setVisible(true);
		}
		// -----------------------------------------------------------------------------------------------------------------------------------수정
		else if (e.getSource() == bt_update) {
			edit = new EditDialog(this, dao);
			edit.setVisible(true);
		}
		// -----------------------------------------------------------------------------------------------------------------------------------삭제
		else if (e.getSource() == bt_delete) {
			delete = new DeleteDialog(this, dao);
			delete.setVisible(true);
		} // -----------------------------------------------------------------------------------------------------------------------------------종료
		else if (e.getSource() == bt_exit) {
			System.exit(0);
		}
		
		
	}
	public void  reset() {
		ja_bener.setLocation(35,20);
		ja_bener.setSize(420,70);
		p.add(ja_bener);
		
		
		p.revalidate();
		p.repaint();
		
	



		// --------------------------------------------

		tf_id.setSize(120, 30);
		tf_id.setLocation(30, 140);
		p.add(tf_id);

		tf_name.setSize(120, 30);
		tf_name.setLocation(30, 180);
		p.add(tf_name);

		tf_java.setSize(120, 30);
		tf_java.setLocation(30, 220);
		p.add(tf_java);

		tf_c.setSize(120, 30);
		tf_c.setLocation(30, 260);
		p.add(tf_c);

		tf_rinux.setSize(120, 30);
		tf_rinux.setLocation(30, 300);
		p.add(tf_rinux);
		
		
		strCombo.setLocation(30,340);
		strCombo.setSize(120, 30);
		p.add(strCombo);

//		tf_chool.setSize(120,30);
//		tf_chool.setLocation(30, 340);
//		p.add(tf_chool);

		p.add(bt_ok);
		bt_ok.setBounds(160, 140, 90, 30);
		p.add(bt_inquire);
		bt_inquire.setBounds(300, 140, 90, 30);
		p.add(bt_update);
		bt_update.setBounds(300, 180, 90, 30);
		p.add(bt_delete);
		bt_delete.setBounds(300, 220, 90, 30);
		p.add(bt_exit);
		bt_exit.setBounds(300, 260, 90, 30);


		
		
		bt_ok.setBackground(new Color(255, 192, 203));
		bt_ok.setForeground(Color.white);
		bt_inquire.setBackground(new Color(255, 192, 203));
		bt_inquire.setForeground(Color.white);
		bt_update.setBackground(new Color(255, 192, 203));
		bt_update.setForeground(Color.white);
		bt_delete.setBackground(new Color(255, 192, 203));
		bt_delete.setForeground(Color.white);
		bt_exit.setBackground(new Color(255, 192, 203));
		bt_exit.setForeground(Color.white);

	}
}