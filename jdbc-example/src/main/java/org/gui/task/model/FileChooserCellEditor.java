package org.gui.task.model;

import java.awt.Component;
import java.io.File;

import javax.swing.DefaultCellEditor;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.TableCellEditor;

public class FileChooserCellEditor extends DefaultCellEditor implements TableCellEditor {
	

    /** Number of clicks to start editing */
    private static final int CLICK_COUNT_TO_START = 2;
    /** Editor component */
    private JTextField field;
    /** File chooser */
    private JFileChooser fileChooser;
    /** Selected file */
    private String path;

    /**
     * Constructor.
     */
    public FileChooserCellEditor() {
        super(new JTextField());
        setClickCountToStart(CLICK_COUNT_TO_START);

        // Dialog which will do the actual editing
        fileChooser = new JFileChooser();
    }
    
    

    @Override
    public Object getCellEditorValue() {
        return path;
    }
    
    
    
    @Override
    public Component getTableCellEditorComponent(final JTable table, final Object value, final boolean isSelected, final int row, final int column) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	if(isSelected) {
            		
            		path = value.toString();
            		fileChooser.setSelectedFile(new File(path));
            		if (fileChooser.showOpenDialog(field) == JFileChooser.APPROVE_OPTION) {
            			path = fileChooser.getSelectedFile().getAbsolutePath();
            			table.getModel().setValueAt(path, row, column);;
            		}
            		fireEditingStopped();
            		
            	}
            }
        });
      
        return field;
    }
}