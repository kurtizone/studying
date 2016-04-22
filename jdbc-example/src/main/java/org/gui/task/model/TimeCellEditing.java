package org.gui.task.model;

import java.awt.Component;
import java.io.File;
import java.util.Date;

import javax.swing.DefaultCellEditor;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.TableCellEditor;

import org.gui.task.container.MyFrame;

public class TimeCellEditing extends DefaultCellEditor implements TableCellEditor {

	/** Number of clicks to start editing */
	private static final int CLICK_COUNT_TO_START = 2;
	/** Selected file */
	private String time;
	/** Editor component */
	private JTextField field;

	private Date date;

	/**
	 * Constructor.
	 */
	public TimeCellEditing() {
		super(new JTextField());
		setClickCountToStart(CLICK_COUNT_TO_START);
	}

	@Override
	public Object getCellEditorValue() {
		return time;
	}

	@Override
    public Component getTableCellEditorComponent(final JTable table, final Object value, final boolean isSelected, final int row, final int column) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	Date date = parseDate(value.toString());
            	time = date.getHours() + ":" + date.getMinutes();
            	String output = (String)JOptionPane.showInputDialog(
                        table,
                        "Type a new time value:",
                        "Edit time",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        null,
                        time);
            	if(output != null && MyFrame.verifyDate(output.split(":"))) {
            		
            		table.getModel().setValueAt(output, row, column);;
            		fireEditingStopped();
            		
            	} else {
            		JOptionPane.showMessageDialog(table,
							"You wrong time",
							"Error",
							JOptionPane.ERROR_MESSAGE);
            	}
               
            }
        });
      
        return field;
    }

	public static Date parseDate(String str) {

		String[] tokens = str.split(" ");
		Date newDate = new Date();
		newDate.setDate(Integer.parseInt(tokens[2]));
		String[] hourTokens = tokens[3].split(":");
		newDate.setHours(Integer.parseInt(hourTokens[0]));
		newDate.setMinutes(Integer.parseInt(hourTokens[1]));
		newDate.setSeconds(0);
		newDate.setYear(Integer.parseInt(tokens[5]));
		System.out.println(newDate);

		return newDate;

	}
}