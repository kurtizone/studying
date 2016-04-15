package org.gui.task.model;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MyFileChooser extends JFileChooser {

	private JTextField field;

	public MyFileChooser() {

		field = new JTextField("10");
		field.setPreferredSize(new Dimension(60, 25));

		JPanel panel1 = (JPanel) this.getComponent(3);
		JPanel panel2 = (JPanel) panel1.getComponent(3);

		Component c1 = panel2.getComponent(0);// optional used to add the
												// buttons after combobox
		Component c2 = panel2.getComponent(1);// optional used to add the
												// buttons after combobox
		panel2.removeAll();
		JLabel label = new JLabel("Set time, s");
		panel2.add(label);
		panel2.add(field);
		panel2.add(c1);// optional used to add the buttons after combobox
		panel2.add(c2);// optional used to add the buttons after combobox

	}

	public JTextField getField() {
		return field;
	}
	
	public String getFieldValue() {
		return field.getText();
	}

	public void setField(JTextField field) {
		this.field = field;
	}
	
	

}