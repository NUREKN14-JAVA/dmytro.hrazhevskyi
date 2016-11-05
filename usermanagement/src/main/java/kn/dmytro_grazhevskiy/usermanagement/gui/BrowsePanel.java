package kn.dmytro_grazhevskiy.usermanagement.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class BrowsePanel extends JPanel implements ActionListener {

	private MainFrame parent;
	private JPanel buttonPanel;
	private JButton addButton;
	private JButton detailsButton;
	private JButton deleteButton;
	private JButton editButton;
	private JScrollPane tablePanel;
	private JTable userTable;

	public BrowsePanel(MainFrame frame) {
	parent = frame;
	initialize();
	
	}

	private void initialize() {
	this.setName("browsePanel");
	this.setLayout(new BorderLayout());
	this.add(getTablePanel(), BorderLayout.CENTER);
	this.add(getButtonPanel(), BorderLayout.SOUTH);
	
		
	}

	private JPanel getButtonPanel() {
	if (buttonPanel == null){
		buttonPanel = new JPanel();
		buttonPanel.add(getAddButton(),null);
		buttonPanel.add(getEditButton(),null);
		buttonPanel.add(getDeleteButton(),null);
		buttonPanel.add(getDetailsButton(),null);

	}
		return buttonPanel;
	}

	private JButton getDeleteButton() {
		if(deleteButton == null){
			deleteButton = new JButton();
			 deleteButton.setText("Delete");
			 deleteButton.setName("deleteButton");
			 deleteButton.addActionListener(this);
		}
		return deleteButton;
	}

	private JButton getDetailsButton() {
		if(detailsButton == null){
			detailsButton = new JButton();
				 detailsButton.setText("Details");
			 detailsButton.setName("detailButton");
			 detailsButton.addActionListener(this);
		}
		return detailsButton;
	}

	private JButton getEditButton() {
		if(editButton == null){
			editButton = new JButton();
				 editButton.setText("Edit");
			 editButton.setName("editButton");
			 editButton.addActionListener(this);
		}
		return editButton;
	}

	private JButton getAddButton() {
	if(addButton == null){
		addButton = new JButton();
		 addButton.setText("New");
		 addButton.setName("addButton");
		 addButton.addActionListener(this);
	}
		return addButton;
	}

	private JScrollPane getTablePanel() {
	if (tablePanel == null){
		tablePanel = new JScrollPane(getUserTable());
		
	}
		return tablePanel;
	}

	private JTable getUserTable() {
		if(userTable == null){
			userTable = new JTable();
				userTable.setName("userTable");
		}
		return userTable;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
