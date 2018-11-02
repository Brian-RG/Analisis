import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

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
		if(this.Lados==4) {
			g.drawRect(this.posx, this.posy, (this.x-this.posx), (this.y-this.posy));
			if(this.color!=null) {
				g.setColor(this.color);
				g.fillRect(this.posx, this.posy, (this.x-this.posx), (this.y-this.posy));
			}
		}
		else if(this.Lados==0) {
			g.drawOval(posx,posy,(x-posx),(y-posy));
			if(color!=null) {
				g.setColor(color);
				g.fillOval(posx,posy,(x-posx),(y-posy));
			}
		}
		
		else if(this.Lados==1) {
			if(color!=null) {
				g.setColor(color);
				g.drawLine(posx, posy, x, y);
			}
		}
		
		else if(this.Lados==3) {
			g.drawPolygon(new int[] {posx-(x-posx),posx,x}, new int[] {y,posy,y},3);
			if(color!=null) {
				g.setColor(color);
				g.fillPolygon(new int[] {posx-(x-posx),posx,x}, new int[] {y,posy,y},3);
			}
		}
		
	}

}
