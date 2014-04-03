package game;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GraphicsConfiguration;
import java.awt.Rectangle;
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
	private JTextField input;
	private JTextArea output;

	public JFrame getFrame() {
		return frame;
	}

	public JTextField getInput() {
		return input;
	}

	public JTextArea getOutput() {
		return output;
	}

	public ManfighterGUI(ActionListener a) {
		frame = new JFrame("Manfighter");
		frame.setDefaultCloseOperation(3);

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setOpaque(true);
		output = new JTextArea(15, 80);
		output.setWrapStyleWord(true);
		output.setEditable(false);
		JScrollPane scrollMain = new JScrollPane(output);
		scrollMain.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollMain.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		DefaultCaret caretMain = (DefaultCaret) output.getCaret();
		caretMain.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		panel.add(scrollMain, BorderLayout.CENTER);

		JPanel inputpanel = new JPanel();
		inputpanel.setLayout(new FlowLayout());
		input = new JTextField(10);
		JButton enter = new JButton("Enter");
		enter.setActionCommand("Enter");
		enter.addActionListener(a);
		input.setActionCommand("Enter");
		input.addActionListener(a);
		inputpanel.add(input);
		inputpanel.add(enter);
		panel.add(inputpanel, BorderLayout.SOUTH);
		frame.getContentPane().add(BorderLayout.CENTER, panel);
		frame.pack();

		GraphicsConfiguration gc = frame.getGraphicsConfiguration();  
		Rectangle bounds = gc.getBounds();
		frame.setLocation((bounds.width/2) - (frame.getWidth()/2), (bounds.height/2));

		frame.setVisible(true);
		frame.setResizable(false);
		input.requestFocus();
	}
}
