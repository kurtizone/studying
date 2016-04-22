package org.gui.task;

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
    private String file = "";

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
        return file;
    }
    
    
    
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        file = value.toString();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                fileChooser.setSelectedFile(new File(file));
                if (fileChooser.showOpenDialog(field) == JFileChooser.APPROVE_OPTION) {
                    file = fileChooser.getSelectedFile().getAbsolutePath();
                }
                fireEditingStopped();
            }
        });
        ;
        return field;
    }
}