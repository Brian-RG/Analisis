import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;

public class Sistema extends JFrame{
	private Canvas c;
	private BarraHerramientas b= new BarraHerramientas();
	
	public Sistema() {
		super();
		this.setSize(new Dimension(1200,800));
		this.setResizable(false);
		this.setVisible(true);
		c= new Canvas(600,600,b);
		this.b.addCanvas(c);
		this.add(b,BorderLayout.NORTH);
		this.add(c);
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		Sistema s= new Sistema();
	}

}
