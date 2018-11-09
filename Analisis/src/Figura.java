import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;

public class Figura {
	private int Lados;
	private float Rotacion;
	private int x, y;
	private int posx,posy;
	private Color color;
	
	public Figura(int Lados, int x, int y, float rotacion, Color c,int posx, int posy) {
		this.Lados=Lados;
		this.Rotacion=rotacion;
		this.x=x;
		this.y=y;
		this.color=c;
		this.posx=posx;
		this.posy=posy;	
	}
	
	public void DibujaFigura(Graphics g) {
		g.setColor(color.black);
		Graphics2D g2d = (Graphics2D)g;
		AffineTransform old = g2d.getTransform();
		g2d.rotate(Math.toRadians(this.Rotacion),(posx+x)/2,(posy+y)/2);
		if(this.Lados==4) {
			g2d.drawRect(this.posx, this.posy, (this.x-this.posx), (this.y-this.posy));
			if(this.color!=null) {
				g2d.setColor(this.color);
				g2d.fillRect(this.posx, this.posy, (this.x-this.posx), (this.y-this.posy));
			}
		}
		else if(this.Lados==0) {
			g2d.drawOval(posx,posy,(x-posx),(y-posy));
			if(color!=null) {
				g2d.setColor(color);
				g2d.fillOval(posx,posy,(x-posx),(y-posy));
			}
		}
		
		else if(this.Lados==1) {
			if(color!=null) {
				g.setColor(color);
			}
			g.drawLine(posx, posy, x, y);
		}
		
		else if(this.Lados==3) {
			g2d.drawPolygon(new int[] {posx-(x-posx),posx,x}, new int[] {y,posy,y},3);
			if(color!=null) {
				g2d.setColor(color);
				g2d.fillPolygon(new int[] {posx-(x-posx),posx,x}, new int[] {y,posy,y},3);
			}
		}
		g2d.setTransform(old);
		
	}

	public int getX() {
		return this.posx;
	}
	
	public int getY() {
		return this.posy;
	}
	
	public void setPos(int j, int k) {
		this.x+=j;
		this.y+=k;
		this.posx+=j;
		this.posy+=k;
	}
	
	public void setRotation(int r) {
		this.Rotacion=r;
	}
	/*
	public boolean contains(Point p) {
		return (distance(new Point(posx,posy), p) + distance(new Point(x,y), p) == distance(new Point(posx,posy), new Point(x,y)));
	}
	private double distance(Point a, Point b) {
        return Math.sqrt((a.getX()-b.getX())*(a.getX()-b.getX()) + (a.getY()-b.getY())*(a.getY()-b.getY()));
    }*/
}
