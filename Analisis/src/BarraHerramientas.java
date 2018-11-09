import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class BarraHerramientas extends JPanel implements ActionListener, ChangeListener {
    private JButton circulo, rectangulo,triangulo,line,clear,co,sel,save,load,move,canvascolor;
    private Color current;
    private JColorChooser color;
    private int opcion=0;
    private Canvas c;
    private JFileChooser fc;
    private JSpinner rot;
    
    
	public BarraHerramientas() {
		
        super();
        this.opcion=-1;
        this.setBackground(Color.BLUE);
        this.setPreferredSize(new Dimension(1000,100));
        SpinnerModel sm = new SpinnerNumberModel(0, 0, 360, 1);
        this.rot=new JSpinner(sm);
        this.rot.addChangeListener(this);
        this.add(rot);
        this.circulo=new JButton();
        String p= new File("").getAbsolutePath();
        p+="\\src\\r";
        this.circulo.setIcon(new ImageIcon(new ImageIcon(p+"\\a.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
        this.circulo.addActionListener(this);
        this.circulo.setBackground(Color.black);
        
        this.move= new JButton();
        this.move.setIcon(new ImageIcon(new ImageIcon(p+"\\moves.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
        this.move.addActionListener(this);
        this.move.setBackground(Color.black);
        this.add(move);
        
        this.sel=new JButton();
        this.sel.setIcon(new ImageIcon(new ImageIcon(p+"\\m.jpg").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
        this.sel.addActionListener(this);
        this.sel.setBackground(Color.black);
        this.add(this.sel);
        
        
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
        co.setBounds(co.getX(), co.getY(), 30, 30);
        this.color= new JColorChooser();

        clear= new JButton("Clear");
        clear.addActionListener(this);
        this.add(clear);
        
        this.add(co);
        this.save= new JButton("Save");
        this.save.addActionListener(this);
        this.add(save);
        this.load= new JButton("Load");
        this.load.addActionListener(this);
        this.add(load);
        
        this.canvascolor= new JButton("Canvas Color");
        this.canvascolor.addActionListener(this);
        this.add(canvascolor);
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
		else if(e.getSource()==this.move) {
			this.opcion=5;
		}
		else if(e.getSource()==this.save) {
			saveCanvas();
		}
		else if(e.getSource()==this.load) {
			c.loadImage();
		}
		else if(e.getSource()==this.canvascolor) {
			Color k=color.showDialog(null,"Canvas", c.getFondo());
			c.SetFondo(k);
		}
		
	}

	
	private void saveCanvas() {

		BufferedImage image=new BufferedImage(c.getWidth(), c.getHeight(),BufferedImage.TYPE_INT_RGB);
		Graphics2D g2=(Graphics2D)image.getGraphics();
		c.paintComponent(g2);
		try {
			fc= new JFileChooser();
			int r=fc.showSaveDialog(this);
			if(r==JFileChooser.APPROVE_OPTION) {
				String f= fc.getSelectedFile().getAbsolutePath();
				//f+="\\src\\";
//				String name= JOptionPane.showInputDialog("Insert a name for the image");
				ImageIO.write(image, "png", new File(f));
				System.out.println(f);
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		if((int)this.rot.getValue()==360) {
			this.rot.setValue(new Integer(0));
		}
		if(this.c.getCurrent()!=null) {
			this.c.getCurrent().setRotation((int)this.rot.getValue());
		}
	}

}
