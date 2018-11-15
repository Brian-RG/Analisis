import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;

public class Sistema extends JFrame{
	private Canvas c;
	private BarraHerramientas b= new BarraHerramientas();
	
	public Sistema() {
		super("peint");
		this.setSize(new Dimension(520,400));
		this.setResizable(false);
		this.setMinimumSize(new Dimension(520,400));
		this.setVisible(true);
		//this.setLocationRelativeTo(null);
		c= new Canvas(520,400,b,this);
		this.b.addCanvas(c);
		this.add(b,BorderLayout.NORTH);
		this.add(c);
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void nuevo() {
	}
	
	public static void main(String[] args) {
		Sistema s= new Sistema();
	}

}
