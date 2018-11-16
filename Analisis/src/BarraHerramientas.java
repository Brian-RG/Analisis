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

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;



public class BarraHerramientas extends JPanel implements ActionListener, ChangeListener {
    private JButton circulo, rectangulo,triangulo,line,clear,co,sel,save,load,move,canvascolor,CanvasSize,undo,nuevo;
    private Color current;
    private JColorChooser color;
    private int opcion=0;
    private Canvas c;
    private JFileChooser fc;
    private JSpinner rot;
    private Sistema s;
    
    
	public BarraHerramientas(Sistema s) {
        super();
        this.s=s;
        this.opcion=-1;
        this.setBackground(new Color(240,248,255));
        this.setPreferredSize(new Dimension(520,100));
        SpinnerModel sm = new SpinnerNumberModel(0, 0, 360, 1);
        this.rot=new JSpinner(sm);
        this.rot.addChangeListener(this);
        this.add(rot);
        this.circulo=new JButton();
        String p= new File("").getAbsolutePath();
        p+="\\src\\r";
        this.circulo.setIcon(new ImageIcon(new ImageIcon(p+"\\circle.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
        this.circulo.addActionListener(this);
        this.circulo.setBackground(Color.black);
        
        this.move= new JButton();
        this.move.setIcon(new ImageIcon(new ImageIcon(p+"\\moves.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
        this.move.addActionListener(this);
        this.move.setBackground(Color.black);
        this.add(move);
        
        this.sel=new JButton();
        this.sel.setIcon(new ImageIcon(new ImageIcon(p+"\\Selection.jpg").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
        this.sel.addActionListener(this);
        this.sel.setBackground(Color.black);
        this.add(this.sel);
        
        
        this.add(this.circulo);
        this.rectangulo=new JButton();
        this.rectangulo.setIcon(new ImageIcon(new ImageIcon(p+"\\rectangulo.jpg").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
        this.rectangulo.setBackground(Color.black);
        this.rectangulo.addActionListener(this);
        this.add(this.rectangulo);
        this.triangulo=new JButton();
        this.triangulo.setIcon(new ImageIcon(new ImageIcon(p+"\\triangulo.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
        this.triangulo.addActionListener(this);
        this.triangulo.setBackground(Color.black);
        this.add(triangulo);
        this.line=new JButton();
        this.line.setIcon(new ImageIcon(new ImageIcon(p+"\\line.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
        this.line.setBackground(Color.WHITE);
        this.line.addActionListener(this);
        this.add(line);
        
        co= new JButton(" ");
        //Color s= null;
        //co.setBackground(s);
        current=null;
        co.addActionListener(this);
        this.color= new JColorChooser();
        
        
        
        this.undo= new JButton("Undo");
        undo.addActionListener(this);
        this.add(undo);
        
        clear= new JButton("Clear");
        clear.addActionListener(this);
        this.add(clear);
        
        this.add(co);
        
        this.nuevo=new JButton("New");
        this.nuevo.addActionListener(this);
        this.add(nuevo);
        
        this.save= new JButton("Save");
        this.save.addActionListener(this);
        this.add(save);
        this.load= new JButton("Load");
        this.load.addActionListener(this);
        this.add(load);
        
        this.canvascolor= new JButton("Canvas Color");
        this.canvascolor.addActionListener(this);
        this.add(canvascolor);
        
        this.CanvasSize= new JButton("Change Canvas Size");
        this.CanvasSize.addActionListener(this);
        this.add(CanvasSize);
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
			current=color.showDialog(null,"change color",co.getBackground());
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
		else if(e.getSource()==this.CanvasSize) {
			int x=Integer.parseInt(JOptionPane.showInputDialog(this, "Insert X", "Change Canvas Size", 3));
			int y=Integer.parseInt(JOptionPane.showInputDialog(this, "Insert y", "Change Canvas Size", 3));
			c.setCanvasSize(x,y);
		}
		else if(e.getSource()==this.undo) {
			c.undo();
		}
		else if(e.getSource()==this.nuevo) {
			s.nuevo();
		}
		
	}

	
	public void saveCanvas() {
		c.setChange(false);
		String[] opcion= {"jpg","png","Exportar como pdf"};
		int rpta=JOptionPane.showOptionDialog(this, "Selecciona el formato.", "Save", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcion, opcion[0]);
		if(rpta==JOptionPane.CLOSED_OPTION) {
			c.setChange(true);
		}
		else {
			BufferedImage image=new BufferedImage(c.getWidth(), c.getHeight(),BufferedImage.TYPE_INT_RGB);
			Graphics2D g2=(Graphics2D)image.getGraphics();
			c.paintComponent(g2);
			float width= image.getWidth();
			float height = image.getHeight();
			try {
				fc= new JFileChooser();
				int r=fc.showSaveDialog(this);
				if(r==JFileChooser.APPROVE_OPTION) {
					String f= fc.getSelectedFile().getAbsolutePath();
					//f+="\\src\\";
//					String name= JOptionPane.showInputDialog("Insert a name for the image");
					switch(rpta) {
						case 0:
							f+=".jpg";
							ImageIO.write(image, "png", new File(f));
							
							
							break;
						case 1:
							
							f+=".png";
							ImageIO.write(image, "png", new File(f));
							
							
							break;
						case 2:
							String k = new File("").getAbsolutePath();
							k+="temporal.png";
							ImageIO.write(image, "png",new File(k));
							File z= new File(k);
							PDDocument document = new PDDocument();
							PDPage page = new PDPage(new PDRectangle(width, height));
							document.addPage(page);
							PDImageXObject img = PDImageXObject.createFromFile(k, document);
							PDPageContentStream contentStream = new PDPageContentStream(document, page);
							contentStream.drawImage(img, 0, 0);
							contentStream.close();
							document.save(f+".pdf");;
							document.close();
							z.delete();
							break;
						default:
							break;
					}
					//System.out.println(f);
					
				
				}
				
			} catch (Exception e) {
				System.out.println(e);
			}
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
