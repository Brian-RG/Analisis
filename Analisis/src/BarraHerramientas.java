import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BarraHerramientas extends JPanel implements ActionListener {
    JButton circulo, rectangulo,triangulo,line,clear,co;
    Color current;
    JColorChooser color;
    private int opcion=0;
    private Canvas c;
	public BarraHerramientas() {
        super();
        this.setBackground(Color.BLUE);
        this.setPreferredSize(new Dimension(1000,100));
        this.circulo=new JButton();
        String p= new File("").getAbsolutePath();
        p+="\\src\\r";
        this.circulo.setIcon(new ImageIcon(new ImageIcon(p+"\\a.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
        circulo.addActionListener(this);
        circulo.setBackground(Color.black);
        this.add(this.circulo);
        this.rectangulo=new JButton();
        this.rectangulo.setIcon(new ImageIcon(new ImageIcon(p+"\\s.jpg").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
        this.rectangulo.setBackground(Color.black);
        this.rectangulo.addActionListener(this);
        this.add(this.rectangulo);
        this.triangulo=new JButton();
        this.triangulo.setIcon(new ImageIcon(new ImageIcon(p+"\\t.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
        this.triangulo.addActionListener(this);
        this.triangulo.setBackground(Color.black);
        this.add(triangulo);
        this.line=new JButton();
        this.line.setIcon(new ImageIcon(new ImageIcon(p+"\\li.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
        this.line.setBackground(Color.WHITE);
        this.line.addActionListener(this);
        this.add(line);
        
        co= new JButton();
        //Color s= null;
        //co.setBackground(s);
        current=null;
        co.addActionListener(this);
        System.out.println(co.getBackground().toString());
        
        this.color= new JColorChooser();

        clear= new JButton("Clear");
        clear.addActionListener(this);
        this.add(clear);
        this.add(co);
	}
	
	public void addCanvas(Canvas e) {
		this.c=e;
	}
	
	public int getSelected() {
		return this.opcion;
	}
	
	public Color getColor() {
		return this.current;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.rectangulo) {
			opcion=0;
		}
		else if (e.getSource()==this.circulo) {
			this.opcion=1;
		}
		else if(e.getSource()==this.clear) {
			c.deleteall();
		}
		else if(e.getSource()==this.triangulo) {
			this.opcion=2;
		}
		else if(e.getSource()==this.line) {
			this.opcion=3;
		}
		else if(e.getSource()==this.co) {
			
			current=color.showDialog(null,"change",co.getBackground());
			co.setBackground(current);
		}
		
	}

}
