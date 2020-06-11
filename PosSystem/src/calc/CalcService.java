package calc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.MainFrame;

public class CalcService implements ActionListener {
	
	MainFrame mainframe;

	public CalcService(MainFrame mainframe) {
		this.mainframe = mainframe;
		//calcDao = new CalcDao();

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		
		
		if(ob ==mainframe.mBtnCalc) {
			mainframe.btn.show(mainframe.pFBtn, "Calcbtn");
		}
		
		
	}

}
