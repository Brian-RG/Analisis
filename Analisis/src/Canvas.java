import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Canvas extends JPanel implements Runnable,MouseListener,MouseMotionListener {
	private float x, y;
	private Color fondo;
	private ArrayList<Figura> figuras;
	private int inx,iny, fx,fy;
	private BarraHerramientas bh;
	private boolean drawing;
	private Image Loaded;
	private Figura current;
	private Sistema s;
	private boolean change;
	
	public Canvas(int x, int y,BarraHerramientas o,Sistema s) {
		super();
		this.s=s;
		Loaded=null;
		this.setBackground(Color.WHITE);
		drawing=false;
		this.setPreferredSize(new Dimension(x,y));
		this.bh=o;
		Thread t = new Thread(this);
		t.start();
		figuras= new ArrayList<Figura>();
		this.x=x;
		this.y=y;
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
	}
	
	public void setChange(boolean ch) {
		this.change=ch;
	}
	
	public boolean getChange() {
		return this.change;
	}
	
	public void setCanvasSize(int x, int y) {
		if(x>=this.getWidth() && y>=this.getHeight())
		{
		this.setSize(x,y);
		s.setSize(new Dimension(x,y));
		}
		else {
			JOptionPane.showMessageDialog(this, "ERROR, minimum size: 520x400px", "ERROR", 0);
			this.setSize(520,400);
			s.setSize(new Dimension(520,400));
		}
	}
	
	public void undo() {
		if(!figuras.isEmpty()) {
			figuras.remove(figuras.size()-1);
		}
	}
	
	public void deleteall() {
		this.figuras.clear();
		removeAll();
		repaint();
	}
	
	public void Reset() {
		this.Loaded=null;
		this.figuras.clear();
		removeAll();
	}
	
	public void loadImage() {
		
		try {
			JFileChooser fc = new JFileChooser();
			int r=fc.showOpenDialog(this);
			if(r==JFileChooser.APPROVE_OPTION){
				String path= fc.getSelectedFile().getAbsolutePath();
				
				Loaded=new ImageIcon(path).getImage();
				this.setCanvasSize(Loaded.getWidth(null), Loaded.getHeight(null));
			}
			//path+="\\src\\";
			//String name=JOptionPane.showInputDialog("Escribe el nombre del archivo a cargar");

		}
		catch(Exception e){
			System.out.println(e);
		}
		figuras.clear();
	}
	
	public Figura getCurrent() {
		return this.current;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if(Loaded!=null) {
			g.drawImage(Loaded,0,0,this);
		}
		
		for(Figura f:figuras) {
			f.DibujaFigura(g);
		}
		g.setColor(Color.black);
		if(drawing) {
			if(bh.getSelected()==1) {
				g.drawOval(inx, iny, fx-inx, fy-iny);
				if(bh.getColor()!=null) {
					g.setColor(bh.getColor());
					g.fillOval(inx, iny, fx-inx, fy-iny);
				}
			}
			else if(bh.getSelected()==0) {
				g.drawRect(inx, iny, fx-inx, fy-iny);
				if(bh.getColor()!=null) {
					g.setColor(bh.getColor());
					g.fillRect(inx, iny, fx-inx, fy-iny);
				}
			}
			else if(bh.getSelected()==2) {
				g.drawPolygon(new int[] {inx-(fx-inx),inx,fx}, new int[] {fy,iny,fy},3);
				if(bh.getColor()!=null) {
					g.setColor(bh.getColor());
					g.fillPolygon(new int[] {inx-(fx-inx),inx,fx}, new int[] {fy,iny,fy},3);
				}
			}
			else if(bh.getSelected()==3) {
				if(bh.getColor()!=null) {
					g.setColor(bh.getColor());
				}
					g.drawLine(inx, iny, fx, fy);
			}
			else if(bh.getSelected()==6) {
				/* STROKE
				Graphics2D g2=(Graphics2D) g;
				System.out.println(g2.getStroke());
				Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0); g2.setStroke(dashed);
				g2.setStroke(dashed);
				*/
			}
		}

		
	}
	
	private BufferedImage cropImage(BufferedImage src, Rectangle rect) {
	      BufferedImage dest = src.getSubimage(rect.x, rect.y, rect.width, rect.height);
	      return dest; 
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		fx=e.getX();
		fy=e.getY();
		if(bh.getSelected()==5) {
			this.current.setPos(fx-inx,fy-iny);
			inx=fx;
			iny=fy;
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		change=true;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		change=true;
		if(bh.getSelected()==5) {
			inx=fx=e.getX();
			iny=fy=e.getY();
		}
		else {
			inx=fx=e.getX();
			iny=fy=e.getY();
			
			drawing=true;
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		drawing=false;
		fx=e.getX();
		fy=e.getY();
		if(bh.getSelected()==0) {
			this.current=new Figura(4,fx,fy,0,bh.getColor(),inx,iny);
			figuras.add(current);
		}
		else if(bh.getSelected()==1) {
			this.current=new Figura(0,fx,fy,0,bh.getColor(),inx,iny);
			figuras.add(current);
		}
		else if(bh.getSelected()==2) {
			this.current=new Figura(3,fx,fy,0,bh.getColor(),inx,iny);
			figuras.add(current);
		}
		else if(bh.getSelected()==3) {
			this.current=new Figura(1,fx,fy,0,bh.getColor(),inx,iny);
			figuras.add(current);
		}
		else if(bh.getSelected()==6) {
			BufferedImage image = bh.getCanvasImage();
			this.deleteall();
			this.Loaded = cropImage(image, new Rectangle(inx,iny,(fx-inx),(fy-iny)));
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	public void SetFondo(Color c) {
		this.setBackground(c);
	}
	
	public Color getFondo() {
		return this.getBackground();
	}
	
	@Override
	public void run() {
        try{
            while(true){
                this.repaint();
                Thread.sleep(30);
            }
        }
        catch(Exception e){
            
        }
	}

}
