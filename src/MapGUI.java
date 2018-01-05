import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class MapGUI {

	private static Window window;
	private Path path;
	
	public MapGUI() {
		window = new Window(new Dimension(800, 800));
		window.getFrame().addMouseListener(new ScreenManger());
		//window.getScreen().setLayout(null);
		path = new Path();
	}
	
	class ScreenManger implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			System.out.println("Click at " + e.getX() + " " + e.getY());
			if(JOptionPane.showConfirmDialog(null, "Create a waypoint?") == 0) {
				Waypoint point = new Waypoint(e.getX(), e.getY());
				window.getCanvas().addWayPoint(point);
				window.getCanvas().add(point);
				
				point.revalidate();
				point.repaint();
				

//				window.getCanvas().revalidate();
				window.getCanvas().repaint();
//				window.getFrame().revalidate();
//				window.getFrame().repaint();
				
				//System.out.println(window.getCanvas().getComponentCount() + " " + window.getFrame().getComponentCount());
				///path.drawPaths(window.getScreenGraphics());
				
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
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stubui
			
		}
		
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MapGUI();
			}
		});
		
	}

	public static Window getWindow() {
		return window;
	}
	
}
