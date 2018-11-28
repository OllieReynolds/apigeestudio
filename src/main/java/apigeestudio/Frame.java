package apigeestudio;

import java.awt.EventQueue;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class Frame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4274917102750371736L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame frame = new Frame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public Frame() throws IOException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Frame.class.getResource("/apigeestudio/32x32.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 836, 490);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Download");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("GitHub Repo");
		mnNewMenu.add(mntmNewMenuItem);
		getContentPane().setLayout(null);
		
		JTextArea txtrProgressArea = new JTextArea();
		txtrProgressArea.setText("PROGRESS AREA");
		txtrProgressArea.setBounds(198, 6, 546, 362);
		getContentPane().add(txtrProgressArea);
		
		JButton btnNewButton = new JButton("Download Git Repos");
		btnNewButton.setBounds(6, 6, 180, 62);
		getContentPane().add(btnNewButton);
		InputStream stream = getClass().getResourceAsStream("32x32.png");
		ImageIcon icon= new ImageIcon(ImageIO.read(stream));
	}
}
