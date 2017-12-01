package Memo;

import java.awt.FlowLayout;
import javax.swing.*;

public class OneMemo extends JPanel {
	JButton EditButton;
	JButton DeleteButton;
	JLabel TitleLabel;
	int num;
	String title;
	String content;
	void setTitle(String input)
	{
		title=input;
		TitleLabel.setText(input);
	}
	OneMemo(int num)
	{
		this.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		this.num=num;
	
		TitleLabel=new JLabel();
		this.add(TitleLabel);
		
		EditButton=new JButton("Update");
		this.add(EditButton);
		
		DeleteButton=new JButton("Delete");
		this.add(DeleteButton);
	
	}
	OneMemo()
	{
		this.num=-1;
	}
	
	
	
}
