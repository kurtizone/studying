package org.client.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.client.model.PropertiesTableModel;
import org.server.controller.PropertiesDataController;

public class ClientFrame {

	private JFrame frame;
	private JPanel panel;
	private JTable table;
	private JButton buttonCommit;
	private JButton buttonRefresh;
	private GridBagConstraints constraints;
	private PropertiesTableModel model;
	
	public ClientFrame(PropertiesDataController controller) {
		frame =  new JFrame("RMI TESTS TASK");
		model = new PropertiesTableModel(controller);
		frame.setSize(700, 600);
		frame.setResizable(true);
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
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.NORTH;
		constraints.gridwidth = 50;
		panel.add(label, constraints);
	}

	public void addTable() {
		table = new JTable(model);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		JScrollPane tableScroll = new JScrollPane(table);
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.gridwidth = 2;
		constraints.weighty = 1;
		constraints.weightx = 1;
		constraints.gridx = 0;
		constraints.gridy = 1;
		panel.add(tableScroll, constraints);
	}

	public void addButtons() {
		buttonCommit = new JButton("Commit");
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 40;
		constraints.gridwidth = 1;
		constraints.gridx = 0;
		constraints.gridy = 2;
		panel.add(buttonCommit, constraints);

		buttonRefresh = new JButton("Refresh");
		constraints.gridx = 1;
		constraints.gridy = 2;
		panel.add(buttonRefresh, constraints);
	}

	
	private void createActions() {
		buttonCommit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.commit();
			}
		});
		buttonRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.updateModel();
			}
		});
	}
	
	public static void main(String[] args) {
		new ClientFrame(null);
	}
	

}
