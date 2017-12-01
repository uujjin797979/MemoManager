package Memo;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

public class MemoManager extends JFrame implements ActionListener {

	JPanel TotalPanel, MemoListPanel, MemoEditPanel, InMemoListPanel, ListPanel, MainMenuPanel;
	JTextField MemoTitle;
	JScrollPane MemoScrollPane;
	JButton ReturnMenuBtn, AddMemoBtn, SaveBtn, ToMemoBtn, ToBackBtn;
	JLabel TitleLabel, ContentLabel;
	GridBagLayout MemoListLayout;
	GridBagConstraints c;
	TextArea MemoContent;
	ArrayList<OneMemo> Memos;
	OneMemo tempMemo;
	int mode;// add는 0 edit=1
	int editindex;// 현재 수정중인 메모
	int RowsCount;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MemoManager frame = new MemoManager();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		});
	}

	public MemoManager() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		TotalPanel = new JPanel();
		TotalPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		TotalPanel.setLayout(new CardLayout(0, 0));
		setContentPane(TotalPanel);
		Memos = new ArrayList<OneMemo>();
		mode = 0;
		editindex = 0;
		MemoListLayout = new GridBagLayout();
		c = new GridBagConstraints();

		MemoListPanel = new JPanel(null);
		MemoListPanel.setLayout(null);

		MainMenuPanel = new JPanel();
		MainMenuPanel.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel("Main Menu");

		ToMemoBtn = new JButton("Add Memo");
		ToMemoBtn.addActionListener(this);

		ListPanel = new JPanel();
		ListPanel.setLayout(MemoListLayout);

		MemoScrollPane = new JScrollPane(ListPanel);
		MemoScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		MemoScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		MemoScrollPane.setBounds(10, 100, 750, 400);
		MemoScrollPane.setPreferredSize(new Dimension(750, 400));

		ReturnMenuBtn = new JButton("Return MainMenu");
		ReturnMenuBtn.addActionListener(this);
		ReturnMenuBtn.setFont(new Font("휴먼모음T", Font.PLAIN, 20));
		ReturnMenuBtn.setBounds(110, 10, 504, 68);

		AddMemoBtn = new JButton("Add");
		AddMemoBtn.setFont(new Font("휴먼모음T", Font.PLAIN, 20));
		AddMemoBtn.addActionListener(this);
		AddMemoBtn.setBounds(613, 506, 149, 36);

		MemoEditPanel = new JPanel();
		MemoEditPanel.setLayout(null);

		MemoTitle = new JTextField();
		MemoTitle.setBounds(100, 62, 649, 46);
		MemoEditPanel.add(MemoTitle);
		MemoTitle.setColumns(10);

		SaveBtn = new JButton("Save");
		SaveBtn.setFont(new Font("휴먼모음T", Font.PLAIN, 20));
		SaveBtn.addActionListener(this);
		SaveBtn.setBounds(604, 10, 145, 42);

		TitleLabel = new JLabel("Title");
		TitleLabel.setFont(new Font("휴먼모음T", Font.PLAIN, 20));
		TitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		TitleLabel.setBounds(0, 62, 88, 46);

		ContentLabel = new JLabel("Content");
		ContentLabel.setFont(new Font("휴먼모음T", Font.PLAIN, 20));
		ContentLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ContentLabel.setBounds(0, 118, 88, 46);

		MemoContent = new TextArea("", 10, 168, TextArea.SCROLLBARS_VERTICAL_ONLY);
		MemoContent.setColumns(WIDTH);
		MemoContent.setBounds(10, 168, 739, 352);

		MainMenuPanel.add(lblNewLabel, BorderLayout.NORTH);
		MainMenuPanel.add(ToMemoBtn, BorderLayout.CENTER);

		MemoListPanel.add(MemoScrollPane);
		MemoListPanel.add(ReturnMenuBtn);
		MemoListPanel.add(AddMemoBtn);

		MemoEditPanel.add(SaveBtn);
		MemoEditPanel.add(TitleLabel);
		MemoEditPanel.add(ContentLabel);
		MemoEditPanel.add(MemoContent);

		TotalPanel.add(MemoListPanel, "MemoList");
		TotalPanel.add(MainMenuPanel, "MainMenu");
		TotalPanel.add(MemoEditPanel, "MemoEdit");

		ToBackBtn = new JButton("->");
		ToBackBtn.setFont(new Font("휴먼모음T", Font.PLAIN, 20));
		ToBackBtn.setBounds(447, 10, 145, 42);
		ToBackBtn.addActionListener(this);
		MemoEditPanel.add(ToBackBtn);

		// this.pack();
	}// end MemoManager

	void create() {
		OneMemo temp = new OneMemo(RowsCount);
		temp.setTitle(MemoTitle.getText());
		temp.content = MemoContent.getText();
		temp.EditButton.addActionListener(this);
		temp.DeleteButton.addActionListener(this);

		Memos.add(temp);

		RowsCount++;
		layout(temp, 0, RowsCount, 1, 1);
	}

	void delete(int del) {

		ListPanel.remove(Memos.get(del));
		Memos.remove(del);
		RowsCount--;
		ListPanel.repaint();
	}

	void update(int n) {
		tempMemo = Memos.get(n);
		tempMemo.setTitle(MemoTitle.getText());
		tempMemo.content = MemoContent.getText();

		mode = 0;
	}

	public void layout(OneMemo obj, int x, int y, int width, int height) {
		c.gridx = 1; // 시작위치 x
		c.gridy = y; // 시작위치 y
		c.gridwidth = width; // 컨테이너 너비
		c.gridheight = height; // 컨테이너 높이
		ListPanel.add(obj, c);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == AddMemoBtn) {
			MemoListPanel.setVisible(false);
			MemoEditPanel.setVisible(true);
		} else if (e.getSource() == SaveBtn) {
			if (mode == 0) {
				create();
			} else if (mode == 1) {
				update(editindex);
			}
			MemoTitle.setText("");
			MemoContent.setText("");
			MemoEditPanel.setVisible(false);
			MemoListPanel.setVisible(true);

		} else if (e.getSource() == ReturnMenuBtn) {
			MemoListPanel.setVisible(false);
			MainMenuPanel.setVisible(true);
		} else if (e.getSource() == ToMemoBtn) {
			MainMenuPanel.setVisible(false);
			MemoListPanel.setVisible(true);
		} else if (e.getSource() == ToBackBtn) {

			MemoTitle.setText("");
			MemoContent.setText("");
			MemoEditPanel.setVisible(false);
			MemoListPanel.setVisible(true);

		}

		for (int i = 0; i < RowsCount; i++) {
			tempMemo = Memos.get(i);
			if (e.getSource() == tempMemo.EditButton) {

				MemoTitle.setText(tempMemo.title);
				MemoContent.setText(tempMemo.content);
				mode = 1;
				editindex = i;
				MemoListPanel.setVisible(false);
				MemoEditPanel.setVisible(true);
			} else if (e.getSource() == tempMemo.DeleteButton) {
				delete(i);
			}
		}

	}
}
