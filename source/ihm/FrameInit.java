package source.ihm;

import java.awt.Frame;
import java.awt.*;
import javax.swing.JFrame;

public class FrameInit extends JFrame
{
	private PanelInit panel = new PanelInit();

    public FrameInit() 
    {
    	this.setTitle("Tourisme à VitesseC");
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setVisible(true);
		
		this.setLayout(new GridLayout(1, 1));

		this.add(this.panel);
		this.setVisible(true);
	}

	public static void main(String[] args)
    {
		new FrameInit();
	}
}