package BI.automation.selenium.edge.common;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class BIActionListner implements ActionListener {

	
	public void actionPerformed(ActionEvent e) {
        e.getActionCommand();
}

}
