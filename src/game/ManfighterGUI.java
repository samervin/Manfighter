package game;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.DefaultCaret;

public class ManfighterGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private JFrame frame;
	private JPanel panel;
	private JTextField input;
	private JTextArea outputMain;
	private JTextArea outputWeaponPlayer;
	private JTextArea outputWeaponEnemy;
	private JButton enter;
	
//	public static void main(String[] args) {
//		ManfighterGUI gui = new ManfighterGUI();
//		Manfighter man = new Manfighter();
//	}
	
	public ManfighterGUI() {
		createGUI();
	}
	
	public void createGUI() {
		frame = new JFrame("Manfighter");
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setOpaque(true);
		ButtonListener butlis = new ButtonListener();
		outputMain = new JTextArea(15, 40);
        outputMain.setWrapStyleWord(true);
        outputMain.setEditable(false);
        JScrollPane scrollMain = new JScrollPane(outputMain);
        scrollMain.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollMain.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        DefaultCaret caretMain = (DefaultCaret) outputMain.getCaret();
        caretMain.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        panel.add(scrollMain, BorderLayout.CENTER);
        
        outputWeaponPlayer = new JTextArea(10, 20);
        outputWeaponPlayer.setWrapStyleWord(true);
        outputWeaponPlayer.setEditable(false);
        JScrollPane scrollWeaponPlayer = new JScrollPane(outputWeaponPlayer);
        scrollWeaponPlayer.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollWeaponPlayer.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        DefaultCaret caretWeaponPlayer = (DefaultCaret) outputWeaponPlayer.getCaret();
        caretWeaponPlayer.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        panel.add(scrollWeaponPlayer, BorderLayout.WEST);
        
        outputWeaponEnemy = new JTextArea(10, 20);
        outputWeaponEnemy.setWrapStyleWord(true);
        outputWeaponEnemy.setEditable(false);
        JScrollPane scrollWeaponEnemy = new JScrollPane(outputWeaponEnemy);
        scrollWeaponEnemy.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollWeaponEnemy.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        DefaultCaret caretWeaponEnemy = (DefaultCaret) outputWeaponEnemy.getCaret();
        caretWeaponEnemy.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        panel.add(scrollWeaponEnemy, BorderLayout.EAST);
        
        JPanel inputpanel = new JPanel();
        inputpanel.setLayout(new FlowLayout());
        input = new JTextField(10);
        enter = new JButton("Enter");
        enter.setActionCommand("Enter");
        enter.addActionListener(butlis);
        input.setActionCommand("Enter");
        input.addActionListener(butlis);
        inputpanel.add(input);
        inputpanel.add(enter);
        panel.add(inputpanel, BorderLayout.SOUTH);
        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.pack();
        frame.setLocationByPlatform(true);
        //center of screen
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
        input.requestFocus();
	}
	
	public class ButtonListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			String inputLine = input.getText().trim();
			if(inputLine.length() > 0) {
				outputMain.append(inputLine);
				input.setText("");
			}
			
			input.requestFocus();
		}
		
	}
}
