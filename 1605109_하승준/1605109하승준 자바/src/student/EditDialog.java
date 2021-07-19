package student;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

class EditDialog extends JDialog implements ActionListener {

	private StudentTextFieldEvent gui;
	private StudentDAO dao;
	private JTextField tf = new JTextField(20);

	public JLabel label = new JLabel("수정"); // 설명

	private JTextField d_tf_id = new JTextField(20); // id
	private JTextField d_tf_name = new JTextField(20); // 이름
	private JTextField d_tf_java = new JTextField(20); // 성적
	private JTextField d_tf_c = new JTextField(20); // 영어
	private JTextField d_tf_rinux = new JTextField(20); // 수학
	private JTextField d_tf_chool = new JTextField(20);

	private JLabel d_ja_id = new JLabel("수정할 ID");
	private JLabel d_ja_name = new JLabel("이름");
	private JLabel d_ja_java = new JLabel("자바");
	private JLabel d_ja_c = new JLabel("씨");
	private JLabel d_ja_rinux = new JLabel("리눅스");

	private JButton d_bt_check = new JButton("조 회");
	private JButton d_bt_ok = new JButton("수 정");
	private JButton d_bt_cancel = new JButton("취 소");
	private StudentTextFieldEvent stf;

	
	private JPanel p;
	ImageIcon icon = new ImageIcon("images/back1.jpg");
	JScrollPane scrollPane;
	ImageIcon icon1 = new ImageIcon("images/edit.jpg");

	EditDialog(JFrame frame, StudentDAO dao) {
		super(frame, "수정", true);
		this.dao = dao;
		Dimension frameSize = this.getSize();
		Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((windowSize.width - frameSize.width) / 3 + 50,
				(windowSize.height - frameSize.height) / 3 + 50);
		this.dialogDesign();
		this.dialogEvent();
		this.setSize(400, 380);
	}

	public void dialogDesign() {
		p = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(icon.getImage(), 0, 0, null);
				g.drawImage(icon1.getImage(), 10, 10, 40, 40, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		scrollPane = new JScrollPane(p);
		setContentPane(scrollPane);
		p.setLayout(null);

		label.setSize(205, 25);
		label.setLocation(55, 19);
		label.setForeground(Color.white);

		Font font = new Font("굴림", Font.BOLD, 15);
		label.setFont(font);
		p.add(label);

		d_ja_id.setSize(80, 25);
		d_ja_id.setLocation(100, 75);
		d_ja_id.setForeground(Color.black);
		p.add(d_ja_id);

		d_ja_name.setSize(80, 25);
		d_ja_name.setLocation(125, 105);
		d_ja_name.setForeground(Color.black);
		p.add(d_ja_name);

		d_ja_java.setSize(80, 25);
		d_ja_java.setLocation(125, 135);
		d_ja_java.setForeground(Color.black);
		p.add(d_ja_java);

		d_ja_c.setSize(80, 25);
		d_ja_c.setLocation(125, 165);
		d_ja_c.setForeground(Color.black);
		p.add(d_ja_c);

		d_ja_rinux.setSize(80, 25);
		d_ja_rinux.setLocation(125, 195);
		d_ja_rinux.setForeground(Color.black);
		p.add(d_ja_rinux);
		// --------------------------------------------
		d_tf_id.setSize(90, 25);
		d_tf_id.setLocation(155, 75);
		p.add(d_tf_id);

		d_tf_name.setSize(90, 25);
		d_tf_name.setLocation(155, 105);
		p.add(d_tf_name);

		d_tf_java.setSize(90, 25);
		d_tf_java.setLocation(155, 135);
		p.add(d_tf_java);

		d_tf_c.setSize(90, 25);
		d_tf_c.setLocation(155, 165);
		p.add(d_tf_c);

		d_tf_rinux.setSize(90, 25);
		d_tf_rinux.setLocation(155, 195);
		p.add(d_tf_rinux);

		p.add(d_bt_check);
		d_bt_check.setBounds(250, 75, 65, 30);

		p.add(d_bt_ok);
		d_bt_ok.setBounds(135, 255, 65, 30);
		p.add(d_bt_cancel);
		d_bt_cancel.setBounds(205, 255, 65, 30);

		d_bt_check.setBackground(new Color(255, 192, 203));
		d_bt_check.setForeground(Color.white);
		d_bt_ok.setBackground(new Color(0, 0, 0));
		d_bt_ok.setForeground(Color.white);
		d_bt_cancel.setBackground(new Color(0, 0, 0));
		d_bt_cancel.setForeground(Color.white);
		d_tf_name.setEditable(false);

		d_tf_java.setEditable(false);
		d_tf_rinux.setEditable(false);
		d_tf_c.setEditable(false);
	}

	public void dialogEvent() {
		d_bt_check.addActionListener(this);
		d_bt_ok.addActionListener(this);
		d_bt_cancel.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == d_bt_ok) {
			boolean found = false;
			int tempIndex = 0;

			for (int i = 0; i < dao.list.size(); i++) {
				Student stu = dao.list.get(i);
				if (d_tf_id.getText().equals(stu.getId())) {
					found = true;
					tempIndex = i;
					break;
				}
			}
			if (found) {

				if (Integer.parseInt(d_tf_java.getText()) >= 0 && Integer.parseInt(d_tf_java.getText()) <= 100
						&& Integer.parseInt(d_tf_c.getText()) >= 0
						&& Integer.parseInt(d_tf_c.getText()) <= 100 && Integer.parseInt(d_tf_rinux.getText()) >= 0
						&& Integer.parseInt(d_tf_rinux.getText()) <= 100) {
					Student new_stu = new Student(d_tf_id.getText(), d_tf_name.getText(),
							Integer.parseInt(d_tf_java.getText()), Integer.parseInt(d_tf_c.getText()),
							Integer.parseInt(d_tf_rinux.getText()), d_tf_chool.getText());
					dao.list.set(tempIndex, new_stu);
					JOptionPane.showMessageDialog(null, "수정되었습니다", "Message", JOptionPane.OK_CANCEL_OPTION);
					EditDialog.this.setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null, "0부터 100사이의 점수만 입력하세요!!", "Message",
							JOptionPane.OK_CANCEL_OPTION);
				}
			} else {
				d_tf_name.setEditable(false);
				d_tf_java.setEditable(false);
			}
		}

		else if (e.getSource() == d_bt_check) {
			boolean found = false;
			for (int i = 0; i < dao.list.size(); i++) {
				Student stu = dao.list.get(i);
				if (d_tf_id.getText().equals(stu.getId())) {
					d_tf_name.setEditable(true);
					d_tf_java.setEditable(true);
					d_tf_c.setEditable(true);
					d_tf_rinux.setEditable(true);

					d_tf_name.setText(dao.list.get(i).getName());
					d_tf_java.setText("" + dao.list.get(i).getjava());
					d_tf_c.setText("" + dao.list.get(i).getc());
					d_tf_rinux.setText("" + dao.list.get(i).getrinux());
					found = true;
					break;
				} else {
					d_tf_name.setEditable(false);
					d_tf_java.setEditable(false);
					d_tf_c.setEditable(false);
					d_tf_rinux.setEditable(false);

					d_tf_name.setText("");
					d_tf_java.setText("");
					d_tf_c.setText("");
					d_tf_rinux.setText("");
				}
			}
			if (!found) {
				JOptionPane.showMessageDialog(null, "일치하는 정보가 없습니다", "", JOptionPane.OK_CANCEL_OPTION);
			}

		} else if (e.getSource() == d_bt_cancel) {
			d_tf_id.setText("");
			d_tf_name.setText("");
			d_tf_java.setText("");
			EditDialog.this.setVisible(false);
		}
	}
}
