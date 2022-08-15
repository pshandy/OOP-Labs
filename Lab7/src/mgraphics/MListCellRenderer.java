package mgraphics;

import javax.swing.*;
import java.awt.*;

public class MListCellRenderer extends DefaultListCellRenderer {
    public Component getListCellRendererComponent(JList list, Object value, 
    							int index, boolean isSelected, boolean cellHasFocus) {
    	JLabel label = (JLabel)super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
    	if (label.getText() != "")
    		label.setText(label.getText().substring(10));
        return label;
    }
}
