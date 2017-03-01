package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import java.awt.Rectangle;

import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;

public class Screen extends java.util.Observable {

	private final JFrame frame = new JFrame();	
	private JPanel pnlAgent1;	
	private JPanel pnlAgent2;
	private JLabel lblInput;
	private JTextArea textArea;
	private JLabel lblAgent1;
	private JLabel lblAgent2;
	private JTextField txtInput;
	private JLabel lblAgent1Output;
	private JLabel lblAgent2Output;
	private JButton btnAdd;
	
	public JPanel getPanelOne() {
		return this.pnlAgent1;
	}
	
	public JPanel getPanelTwo() {
		return this.pnlAgent2;
	}

	/**
	 * Create the application.
	 */
	public Screen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					initialize();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame.setResizable(false);
		frame.setBounds(100, 100, 799, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBorder(new LineBorder(new Color(0, 0, 0)));
		textArea.setBounds(new Rectangle(10, 101, 150, 459));
		textArea.setText("");
		frame.getContentPane().add(textArea);
		
		lblInput = new JLabel("Input");
		lblInput.setBounds(10, 11, 46, 14);
		frame.getContentPane().add(lblInput);
		
		pnlAgent1 = new JPanel();
		pnlAgent1.setBounds(170, 33, 301, 297);
		frame.getContentPane().add(pnlAgent1);
		pnlAgent1.setLayout(null);
		
		pnlAgent2 = new JPanel();
		pnlAgent2.setBounds(481, 33, 301, 297);
		frame.getContentPane().add(pnlAgent2);
		pnlAgent2.setLayout(null);
		
		lblAgent1 = new JLabel("Agent #1");
		lblAgent1.setBounds(170, 11, 86, 14);
		frame.getContentPane().add(lblAgent1);
		
		lblAgent2 = new JLabel("Agent #2");
		lblAgent2.setBounds(481, 11, 86, 14);
		frame.getContentPane().add(lblAgent2);
		
		txtInput = new JTextField();
		txtInput.setBounds(10, 33, 150, 23);
		txtInput.setColumns(10);
		frame.getContentPane().add(txtInput);
		
		btnAdd = new JButton("Add");
		btnAdd.setBounds(10, 67, 150, 23);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAddClicked();
			}
		});
		frame.getContentPane().add(btnAdd);
		
		lblAgent1Output = new JLabel("");
		lblAgent1Output.setBounds(170, 341, 46, 14);
		frame.getContentPane().add(lblAgent1Output);
		
		lblAgent2Output = new JLabel("");
		lblAgent2Output.setBounds(481, 341, 46, 14);
		frame.getContentPane().add(lblAgent2Output);
	}
	
	private void btnAddClicked() {
		String inputText = txtInput.getText().toLowerCase();
		
		setChanged();
		notifyObservers(inputText);
		
		txtInput.setText("");
		txtInput.grabFocus();
		
		if (textArea.getText().equals("")) {
			textArea.setText(inputText);
		} else {
			textArea.setText(textArea.getText() + "\n" + inputText);
		}
	}
}
