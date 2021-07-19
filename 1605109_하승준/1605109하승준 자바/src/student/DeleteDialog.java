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

class DeleteDialog extends JDialog implements ActionListener {

	private StudentDAO dao;
	private JTextField tf = new JTextField(20);

	public JLabel label = new JLabel("삭제"); // 설명

	private JTextField d_tf_id = new JTextField(20); // id
	private JTextField d_tf_name = new JTextField(20); // 이름
	private JTextField d_tf_korean = new JTextField(20); // 성적

	private JLabel d_ja_id = new JLabel("ID :");
	private JLabel d_ja_name = new JLabel("이름 :");

	private JButton d_bt_check = new JButton("v");
	private JButton d_bt_ok = new JButton("삭 제");
	private JButton d_bt_cancel = new JButton("취 소");
	private StudentTextFieldEvent stf;

	private JPanel p;
	ImageIcon icon = new ImageIcon("images/back1.jpg");
	JScrollPane scrollPane;
	ImageIcon icon1 = new ImageIcon("images/edit.jpg");

	DeleteDialog(JFrame frame, StudentDAO dao) {
		super(frame, "삭제", true);
		this.dao = dao;
		Dimension frameSize = this.getSize();
		Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((windowSize.width - frameSize.width) / 3 + 80,
				(windowSize.height - frameSize.height) / 3 + 80);
		this.dialogDesign();
		this.dialogEvent();
		this.setSize(340, 330);
	}

	public void dialogDesign() {
		p = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(icon.getImage(), 0, 0, null);
				g.drawImage(icon1.getImage(), 10, 10, 30, 30, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		scrollPane = new JScrollPane(p);
		setContentPane(scrollPane);
		p.setLayout(null);

		label.setSize(205, 25);
		label.setLocation(40, 15);
		label.setForeground(Color.white);

		Font font = new Font("굴림", Font.BOLD, 15);
		label.setFont(font);
		p.add(label);

		d_ja_id.setSize(25, 25);
		d_ja_id.setLocation(100, 95);
		d_ja_id.setForeground(Color.white);
		p.add(d_ja_id);

		d_ja_name.setSize(35, 25);
		d_ja_name.setLocation(85, 135);
		d_ja_name.setForeground(Color.white);
		p.add(d_ja_name);

		// --------------------------------------------
		d_tf_id.setSize(90, 25);
		d_tf_id.setLocation(125, 95);

		p.add(d_tf_id);

		d_tf_name.setSize(90, 25);
		d_tf_name.setLocation(125, 135);
		p.add(d_tf_name);

		p.add(d_bt_ok);
		d_bt_ok.setBounds(100, 205, 65, 30);
		p.add(d_bt_cancel);
		d_bt_cancel.setBounds(170, 205, 65, 30);

		d_bt_ok.setBackground(new Color(255, 192, 203));
		d_bt_ok.setForeground(Color.white);
		d_bt_cancel.setBackground(new Color(255, 192, 203));
		d_bt_cancel.setForeground(Color.white);
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
				if (d_tf_id.getText().equals(stu.getId()) && d_tf_name.getText().equals(stu.getName())) {
					found = true;
					tempIndex = i;
					break;
				}
			}
			if (found) {
				int result = JOptionPane.showConfirmDialog(null, "정말 삭제하시겠습니까?", "Confirm", JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.CLOSED_OPTION)
					tf.setText("Just Closed without Selection");
				else if (result == JOptionPane.YES_OPTION) {
					JOptionPane.showMessageDialog(null, "삭제되었습니다", "Message", JOptionPane.OK_CANCEL_OPTION);
					dao.list.remove(tempIndex);
					DeleteDialog.this.setVisible(false);
				} else {
				}
			} else {
				JOptionPane.showMessageDialog(null, "입력한  정보가 없습니다", "", JOptionPane.OK_CANCEL_OPTION);
			}
		} else if (e.getSource() == d_bt_cancel) {
			d_tf_id.setText("");
			d_tf_name.setText("");
			d_tf_korean.setText("");
			DeleteDialog.this.setVisible(false);
		}
	}
}