package org.gui.task.container;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
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
import org.gui.task.thread.FileExecutorThread;

public class MyFrame{

 	private JFrame frame;
 	private JPanel panel;
 	private JTable table;
 	private JButton buttonAdd;
 	private JButton buttonEdit;
 	private JButton buttonRemove;
 	private GridBagConstraints constraints;
 	private ExecuteEntry entry;
 	private MyTableArrayModel model;
	

	public MyFrame(String title) throws IOException {
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
		saveSystemProp();
		new FileExecutorThread(model).start();;
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
		
		buttonEdit = new JButton("Edit");
		constraints.gridx = 1;
		constraints.gridy = 2;
		panel.add(buttonEdit, constraints);

		buttonRemove = new JButton("Remove");
		constraints.gridx = 2;
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
				try {
						int ret = fileChooser.showDialog(frame, "Add");
						if (ret == JFileChooser.APPROVE_OPTION) {
						
						if (!verifyDate(fileChooser.getFieldValue().split(":"))) {
							throw new NullPointerException();
						}
						
						Date date = new Date();
						Date diff = new SimpleDateFormat("HH:mm").parse(fileChooser.getFieldValue());
						date.setHours(diff.getHours());
						date.setMinutes(diff.getMinutes());
						date.setSeconds(0);
						entry = new ExecuteEntry(
								fileChooser.getSelectedFile(), 
								date
								);
						model.addRow(entry);
						System.out.println(entry);
						}
					} catch (NullPointerException | ParseException | IllegalArgumentException e2) {
						JOptionPane.showMessageDialog(frame,
								"You wrong time",
								"Error",
								JOptionPane.ERROR_MESSAGE);
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
	
	public boolean verifyDate(String[] tokens) {
		if(tokens.length != 2) return false;
		if(Integer.parseInt(tokens[0]) >= 0 && Integer.parseInt(tokens[0]) <= 23 && 
				 Integer.parseInt(tokens[1]) >= 0 && Integer.parseInt(tokens[1]) <= 59 ) {
			return true;
		} else {
			return false;
		}
		
	}
	
	public void saveSystemProp() throws IOException {
		File file = new File("system.properties");
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(file)));
		List<String> list = new ArrayList<>();
		Set<Thread> set = Thread.getAllStackTraces().keySet();
		list.add("Free memory = " + Runtime.getRuntime().freeMemory());
		list.add("Total memory = " + Runtime.getRuntime().totalMemory());
		list.add("Max memory = " + Runtime.getRuntime().maxMemory());
		list.add("Available processors = " + Runtime.getRuntime().availableProcessors());
		list.add("Threads : ");
		int i = 1;
		for (Thread thread : set) {
			list.add(i++ + " " + thread);
		}
		
		for (String string : list) {
			writer.write(string);
			writer.newLine();
		}
		
		writer.close();
	}


	
}
