import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.crypto.spec.GCMParameterSpec;
import javax.swing.*;

public class Sistema extends JFrame{
	private Canvas c;
	private BarraHerramientas b= new BarraHerramientas(this);
	
	public Sistema() {
		super("peint");
		//this.setSize(new Dimension(520,400));
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
		String[] o= {"yes","no"};
		int r=1;
		if(c.getChange()) {
			r=JOptionPane.showOptionDialog(this, "Do you want to save?", "New Document", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,o,o[0]);
		}
		if(r!=JOptionPane.CLOSED_OPTION) {
				if(o[r]=="yes") {
					b.saveCanvas();
				}
				int x=Integer.parseInt(JOptionPane.showInputDialog(this,"Inserta el tamaño en x(px) del nuevo canvas"));
				int y=Integer.parseInt(JOptionPane.showInputDialog(this,"Inserta el tamaño en y(px) del nuevo canvas"));
				this.remove(c);
				c=null;
				System.gc();
				c= new Canvas(x,y,b,this);
				this.remove(b);
				this.add(b,BorderLayout.NORTH);
				this.add(c);
				this.pack();
			}

	}
	
	public static void main(String[] args) {
		Sistema s= new Sistema();
	}

}
