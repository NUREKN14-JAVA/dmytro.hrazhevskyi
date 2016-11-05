package kn.dmytro_grazhevskiy.usermanagement.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddPanel extends JPanel implements ActionListener {
	 private JPanel buttonPanel;
	private JButton cancelButton;
	private JButton okButton;
	private JPanel fieldPanel;
	private MainFrame parent;
	private JTextField firstNameField;
    private JTextField dateOfBirthField;
    private JTextField lastNameField;

	public AddPanel(MainFrame parent) {
	        this.parent = parent;
	        initialize();
	    }

	    private void initialize() {
	        this.setName("addPanel");
	        this.setLayout(new BorderLayout());
	        this.add(getFieldPanel(), BorderLayout.NORTH);
	        this.add(getButtonPanel(), BorderLayout.SOUTH);
	        
	    }

	    private JPanel getFieldPanel() {
	    	 if (fieldPanel == null) {
	             fieldPanel = new JPanel();
	             fieldPanel.setLayout(new GridLayout(3, 2));
	             addLabeledField(fieldPanel, "Firstname", getFirstNameField()); 
	             addLabeledField(fieldPanel,"Lastname", getLastNameField()); 
	             addLabeledField(fieldPanel, "Date of Birth", getDateOfBirthField());
	    	 }
	         return fieldPanel;
		}

		private JTextField getDateOfBirthField() {
			  if (dateOfBirthField == null) {
		            dateOfBirthField = new JTextField();
		            dateOfBirthField.setName("dateOfBirthField"); //$NON-NLS-1$
		        }
		        return dateOfBirthField;
		}

		private JTextField getLastNameField() {
			if (lastNameField == null) {
	            lastNameField = new JTextField();
	            lastNameField.setName("lastNameField"); //$NON-NLS-1$
	        }
	        return lastNameField;
		}

		private void addLabeledField(JPanel panel, String labelText, JTextField textField) {
		JLabel label = new JLabel(labelText);
		label.setLabelFor(textField);
		panel.add(label);
		panel.add(textField);
			
		}

		private JTextField getFirstNameField() {
			if (firstNameField == null) {
	            firstNameField = new JTextField();
	            firstNameField.setName("firstNameField"); 
	        }
	        return firstNameField;
		}

		private JPanel getButtonPanel() {
	        if (buttonPanel == null) {
	            buttonPanel = new JPanel();
	            buttonPanel.add(getOkButton(), null);
	            buttonPanel.add(getCancelButton(), null);
	        }
	        return buttonPanel;
	    }

	    private JButton getCancelButton() {
	        if (cancelButton == null) {
	            cancelButton = new JButton();
	            cancelButton.setName("cancelButton"); 
	            cancelButton.setActionCommand("cancel"); 
	            cancelButton.addActionListener(this);
	        }
	        return cancelButton;
	    }

	    private JButton getOkButton() {
	        if (okButton == null) {
	            okButton = new JButton();
	            okButton.setName("okButton");
	            okButton.setActionCommand("ok"); 
	            okButton.addActionListener(this);
	        }
	        return okButton;
	    }
	    
	    

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}

}
