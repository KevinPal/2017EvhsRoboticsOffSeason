import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class WayPointCanvas extends JPanel{
	
	private Path p;
	private Dimension size;
	
	public WayPointCanvas(Dimension size) {
		p = new Path();
		this.size = size;
		
	}	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		Color c = g.getColor();
		g.setColor(new Color(225, 225, 225));
		g.fillRect(0, 0, size.width, size.height);
		g.setColor(c);
		p.drawPaths(g);
		for(Waypoint wp : p.getPath()) {
			wp.repaint();
		}
		
	}

	public void addWayPoint(Waypoint w) {
		p.addPoint(w);
	}
	public Path getPath() {
		return p;
	}
	
	
}