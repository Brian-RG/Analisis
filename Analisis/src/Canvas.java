import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Canvas extends JPanel implements Runnable,MouseListener,MouseMotionListener {
	private float x, y;
	private Color fondo;
	private ArrayList<Figura> figuras;
	private int inx,iny, fx,fy;
	private BarraHerramientas bh;
	private boolean drawing;
	
	public Canvas(float x, float y,BarraHerramientas o) {
		super();
		drawing=false;
		this.setPreferredSize(new Dimension(600,600));
		this.bh=o;
		Thread t = new Thread(this);
		t.start();
		figuras= new ArrayList();
		this.x=x;
		this.y=y;
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
	}
	
	public void deleteall() {
		this.figuras.clear();
		removeAll();
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
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
					g.drawLine(inx, iny, fx, fy);
				}
				
			}
		}

		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		fx=e.getX();
		fy=e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		inx=fx=e.getX();
		iny=fy=e.getY();
		
		drawing=true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		drawing=false;
		fx=e.getX();
		fy=e.getY();
		if(bh.getSelected()==0) {
			figuras.add(new Figura(4,fx,fy,90,bh.getColor(),inx,iny));
		}
		else if(bh.getSelected()==1) {
			figuras.add(new Figura(0,fx,fy,90,bh.getColor(),inx,iny));
		}
		else if(bh.getSelected()==2) {
			figuras.add(new Figura(3,fx,fy,90,bh.getColor(),inx,iny));
		}
		else if(bh.getSelected()==3) {
			figuras.add(new Figura(1,fx,fy,90,bh.getColor(),inx,iny));
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
