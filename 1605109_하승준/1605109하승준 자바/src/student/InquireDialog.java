package student;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

class InquireDialog extends JDialog implements ActionListener {

	private StudentTextFieldEvent gui;
	private StudentDAO dao;
	private JTextField tf = new JTextField(20);
	JTextArea ta = new JTextArea();
	public JLabel advice = new JLabel("id를 입력하고 v를 눌러주세요"); // 설명
	private JScrollPane scroll;
	private JTextField d_tf_id = new JTextField(20); // id
	private JPanel p;

	JScrollPane scrollPane;
	private JLabel d_ja_id = new JLabel("ID");
	private JLabel d_ja_name = new JLabel("수정 할 이름");
	private JLabel d_ja_java = new JLabel("수정 할 성적");

	private JButton d_bt_check = new JButton("v");
	private JButton d_bt_id = new JButton("id 검색");
	private JButton d_bt_full = new JButton("전체확인");
	private JButton d_bt_name = new JButton("이름순으로 검색");

	private JButton d_bt_save = new JButton("저장하기");
	private JButton d_bt_invoke = new JButton("불러오기");

	private JButton d_bt_cancel = new JButton("나가기");
	private StudentTextFieldEvent stf;

	public JLabel label = new JLabel("조회"); // 설명
	private JFileChooser fc = new JFileChooser();

	ImageIcon icon = new ImageIcon("images/back1.jpg");
	ImageIcon icon1 = new ImageIcon("images/edit.jpg");

	String[] colNames = new String[] { "ID", "이름", "자바", "C언어", "리눅스", "출석" ,"평균"};

//	DefaultTableModel model = new DefaultTableModel(colNames,0);
	TableModel model = new DefaultTableModel(colNames, 0) {
		public Class getColumnClass(int column) {
			Class returnValue;
			if ((column >= 0) && (column < getColumnCount())) {
				returnValue = getValueAt(0, column).getClass();
			} else {
				returnValue = Object.class;
			}
			return returnValue;
		}
	};
	JTable table = new JTable(model);

	InquireDialog(JFrame frame, StudentDAO dao) {
		super(frame, "조회", true);
		this.dao = dao;
		Dimension frameSize = this.getSize();
		Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((windowSize.width - frameSize.width) / 2, (windowSize.height - frameSize.height) / 2);
		this.dialogDesign();
		this.dialogEvent();
		this.setSize(640, 480);
	}

	public void dialogDesign() {
		p = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(icon.getImage(), 0, 0, null);
				g.drawImage(icon1.getImage(), 10, 10, 50, 50, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		scrollPane = new JScrollPane(p);
		setContentPane(scrollPane);
		p.setLayout(null);

		label.setSize(205, 25);
		label.setLocation(60, 22);
		label.setForeground(Color.white);

		Font font = new Font("굴림", Font.BOLD, 15);
		label.setFont(font);
		p.add(label);

// --------------------------------------------
		d_tf_id.setSize(70, 25);
		d_tf_id.setLocation(60, 70);
		p.add(d_tf_id);

		RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
		table.setRowSorter(sorter);
		JScrollPane scrollPane = new JScrollPane(table);

		p.add(scrollPane);
		this.pack();
		scrollPane.setLocation(60, 100);
		scrollPane.setSize(500, 150);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());

		scrollPane.getViewport().setBackground(Color.WHITE);

		p.add(d_bt_id);
		d_bt_id.setBounds(135, 70, 80, 27);
		p.add(d_bt_full);
		d_bt_full.setBounds(470, 70, 90, 27);
		p.add(d_bt_cancel);
		d_bt_cancel.setBounds(270, 350, 80, 30);
		p.add(d_bt_save);
		d_bt_save.setBounds(370, 260, 90, 27);
		p.add(d_bt_invoke);
		d_bt_invoke.setBounds(470, 260, 90, 27);

		d_bt_id.setBackground(new Color(255, 192, 203));
		d_bt_id.setForeground(Color.white);

		d_bt_full.setBackground(new Color(255, 192, 203));
		d_bt_full.setForeground(Color.white);

		d_bt_cancel.setBackground(new Color(255, 192, 203));
		d_bt_cancel.setForeground(Color.white);

		d_bt_save.setBackground(new Color(255, 192, 203));
		d_bt_save.setForeground(Color.white);

		d_bt_invoke.setBackground(new Color(255, 192, 203));
		d_bt_invoke.setForeground(Color.white);
	}

	public void dialogEvent() {
		d_bt_name.addActionListener(this);
		d_bt_id.addActionListener(this);
		d_bt_full.addActionListener(this);
		d_bt_cancel.addActionListener(this);
		d_bt_save.addActionListener(this);
		d_bt_invoke.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == d_bt_id) {
			boolean found = false;
			((DefaultTableModel) model).setNumRows(0);
			for (int i = 0; i < dao.list.size(); i++) {

				ta.setText("");
				Student stu = dao.list.get(i);
				if (d_tf_id.getText().equals(stu.getId())) {
//					((DefaultTableModel) model).setNumRows(0);
					String[] rows = new String[7];
					rows[0] = dao.list.get(i).getId(); // ID
					rows[1] = dao.list.get(i).getName(); // 이름
					rows[2] = "" + dao.list.get(i).getjava(); // 자바
					rows[3] = "" + dao.list.get(i).getc(); // 씨언어
					rows[4] = "" + dao.list.get(i).getrinux(); // 리눅스
					rows[5] = "" + dao.list.get(i).getChool();// 출석
					rows[6] = ""
							+ (dao.list.get(i).getc() + dao.list.get(i).getjava() + dao.list.get(i).getrinux())
									/ 3; // 평균
					((DefaultTableModel) model).addRow(rows);
					d_tf_id.setText("");
					found = true;
					return;
				} else {
				}
			}
			if (!found) {
				JOptionPane.showMessageDialog(null, "일치하는 정보가 없습니다", "", JOptionPane.OK_CANCEL_OPTION);
			}
		}
//------------------------------------------------------------------------------------------------------------------------------------저장하기	
		else if (e.getSource() == d_bt_save) {
			FileNameExtensionFilter filter = new FileNameExtensionFilter("TXT", "txt");
			fc.setFileFilter(filter);

			int ret = fc.showSaveDialog(null); // 열기파일 다이얼로그 출력
			if (ret != JFileChooser.APPROVE_OPTION) {
				JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다", "경고", JOptionPane.WARNING_MESSAGE);
				return;
			}
			// 사용자가 저장 버튼을 누른 경우
			String filePath = fc.getSelectedFile().getPath();
			FileWriter fw = null;
			try {
				fw = new FileWriter(filePath);
				fw.write(table.getRowCount() + "\n"); // 저장할 내용의 테이블 갯수를 구한다.
				for (int i = 0; i < table.getRowCount(); i++) {
					for (int j = 0; j < table.getColumnCount(); j++) {
						fw.write((String) table.getValueAt(i, j) + "\n");
						// 각 열과 행에 행당값을 저장한다.
					}
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} finally {
				try {
					fw.close(); // 파일 쓰기가 끝나면 꼭 닫아 줘야 한다.
				} catch (IOException e1)

				{
					e1.printStackTrace();
				}
			}
		}
//------------------------------------------------------------------------------------------------------------------------------------불러오기
		else if (e.getSource() == d_bt_invoke) {
			
			((DefaultTableModel) model).setRowCount(0);// 테이블을 초기화 한다.
			FileNameExtensionFilter filter = new FileNameExtensionFilter("TXT", "txt");
			fc.setFileFilter(filter);

			int ret = fc.showOpenDialog(null); // 열기파일 다이얼로그 출력
			if (ret != JFileChooser.APPROVE_OPTION) {
				JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다", "경고", JOptionPane.WARNING_MESSAGE);
				return;
			}
			String filePath = fc.getSelectedFile().getPath();
			try {
				BufferedReader br = new BufferedReader(new FileReader(filePath));
				int cnt = Integer.parseInt(br.readLine()); // 파일에 저장된 내용을 줄 단위로 읽어온다.
				for (int i = 0; i < cnt; i++) // Row 를 먼저 읽어 읽는다.
				{
					String str[] = new String[6];

					for (int j = 0; j < 6; j++) // 들어갈 내용의 항목이 6개 이므로 6번 읽는다.
					{
						str[j] = br.readLine();
					}
					((DefaultTableModel) model).addRow(str); // 테이블에 추가시킨다.
					Student stu1 = new Student(str[0], str[1], Integer.parseInt(str[2]), Integer.parseInt(str[3]),
							Integer.parseInt(str[4]),str[5]); //중요 출 추가함
					dao.list.add(stu1);
				}
				br.close();// 불러오기가 끝나면 꼭 close를 해야된다.
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(this, "열기 오류");
			}

		} else if (e.getSource() == d_bt_full) {
			((DefaultTableModel) model).setNumRows(0);
			if (dao.list.size() < 1) {
				JOptionPane.showMessageDialog(null, "입력한  정보가 없습니다", "", JOptionPane.OK_CANCEL_OPTION);
			}
			Collections.sort(dao.list, new ScoreComparator());
			ta.setText("");
			for (int i = 0; i < dao.list.size(); i++) {


				String[] rows = new String[7];
				rows[0] = dao.list.get(i).getId(); // ID
				rows[1] = dao.list.get(i).getName(); // 이름
				rows[2] = "" + dao.list.get(i).getjava(); // 자바
				rows[3] = "" + dao.list.get(i).getc(); // 씨
				rows[4] = "" + dao.list.get(i).getrinux(); // 리눅스
				rows[5] = "" + dao.list.get(i).getChool();//출석
				rows[6] = ""
						+ (dao.list.get(i).getc() + dao.list.get(i).getjava() + dao.list.get(i).getrinux()) / 3; // 평균
				((DefaultTableModel) model).addRow(rows);
			}
		} else if (e.getSource() == d_bt_cancel) {
			d_tf_id.setText("");
			InquireDialog.this.setVisible(false);
		}
	}
}
