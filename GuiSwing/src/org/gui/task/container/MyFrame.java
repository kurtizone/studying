package org.gui.task.container;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.gui.task.model.ExecuteEntry;
import org.gui.task.model.MyFileChooser;
import org.gui.task.model.MyTableArrayModel;

public class MyFrame {

	private static int rows = 100;
	private static int cols = 3;
 	private JFrame frame;
 	private JPanel panel;
 	private JTable table;
 	private JDialog dialog;
 	private JButton buttonAdd;
 	private JButton buttonRemove;
 	private GridBagConstraints constraints;
 	private ExecuteEntry entry;
 	private MyTableArrayModel model;
	

	public MyFrame(String title) {
		frame = new JFrame(title);
		model = new MyTableArrayModel();
		frame.setSize(600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel(new GridBagLayout());
		constraints = new GridBagConstraints();
		frame.add(panel);
		addLabel();
		addTable();
		addButtons();
		createActions();
		frame.add(panel);
		frame.setVisible(true);
	}

	public void addLabel() {
		JLabel label = new JLabel(frame.getTitle());
		label.setHorizontalAlignment(JLabel.CENTER);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridwidth = 2;
		constraints.weighty = 10;
		constraints.gridy = 0;
		constraints.gridx = 0;
		panel.add(label, constraints);
	}

	public void addTable() {
		table = new JTable(model);
		JScrollPane tableScroll = new JScrollPane(table);
		constraints.gridwidth = 2;
		constraints.gridx = 0;
		constraints.gridy = 1;
		panel.add(tableScroll, constraints);
	}

	public void addButtons() {
		buttonAdd = new JButton("Add");
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 50;
		constraints.gridwidth = 1;
		constraints.gridx = 0;
		constraints.gridy = 2;
		panel.add(buttonAdd, constraints);

		buttonRemove = new JButton("Remove");
		constraints.gridx = 1;
		constraints.gridy = 2;
		panel.add(buttonRemove, constraints);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public JButton getButtonAdd() {
		return buttonAdd;
	}

	public void setButtonAdd(JButton buttonAdd) {
		this.buttonAdd = buttonAdd;
	}

	public JButton getButtonRemove() {
		return buttonRemove;
	}

	public void setButtonRemove(JButton buttonRemove) {
		this.buttonRemove = buttonRemove;
	}

	public void createActions() {
		buttonAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MyFileChooser fileChooser = new MyFileChooser();
				int ret = fileChooser.showDialog(frame, "Add");
				if (ret == JFileChooser.APPROVE_OPTION) {
					try {
						System.out.println(fileChooser.getSelectedFile().getAbsolutePath());
						Long time = new Date().getTime() 
								+ (Long.parseLong(fileChooser.getFieldValue()) * 1000);
						entry = new ExecuteEntry(
								fileChooser.getSelectedFile(), 
								new Date(time)
								);
						model.addRow(entry);
						System.out.println(entry);
					} catch (NullPointerException | IllegalArgumentException e2) {
						JOptionPane.showMessageDialog(frame,
								"You forgot to type time to add",
								"Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}

			}

		});

		buttonRemove.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					model.removeRow(table.getSelectedRow());
					
				} catch (NullPointerException | ArrayIndexOutOfBoundsException e2) {
					System.out.println("Error");
				}
			}
		});

	}


	
}
