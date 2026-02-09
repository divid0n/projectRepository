package tracker;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Font;

public class MainWindow {

	long startTime = 0;
	long endTime; // MAKE A PAUSE BUTTON FOR THE TIMER.
	float duration; //  ADD AVERAGE PER DAY NOT ONLY PER SESSION.
	String selectedGoal; // MAKE IT WORK OUTSIDE ECLIPSE, F.E. FROM GITHUB
	int optionChosen; // ADD GAMING VS WORK COMPARISON IN CHECK DATA
	Timer timer;
	TimerTask task;
	JComboBox<String> comboBox = new JComboBox<>();
	JLabel elapsedTime = new JLabel("Time elapsed");
	SessionManager mngr;
	JTextArea textArea = new JTextArea();

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @throws IOException
	 */
	public MainWindow() throws IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws IOException
	 */
	private void initialize() throws IOException {
		frame = new JFrame();
		frame.setBounds(100, 100, 603, 351);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		textArea.setFont(new Font("Lucida Grande", Font.PLAIN, 12));

		
		textArea.setEditable(false); 
		textArea.setLineWrap(true);

		
		comboBox.addItem("Select a goal to work on...");
		comboBox.addItem("TU Wien Admission");
		comboBox.addItem("Coding projects");
		comboBox.addItem("School assignments");
		comboBox.addItem("Guitar Practice");
		comboBox.addItem("Gaming (slacking off)");
		comboBox.addItem("Work");
		comboBox.addItem("Check Data");
		comboBox.addItem("Erase all data");
		comboBox.setBounds(75, 30, 192, 22);
		frame.getContentPane().add(comboBox);

		
		elapsedTime.setFont(new Font("Tahoma", Font.PLAIN, 20));
		elapsedTime.setBounds(301, 16, 185, 49);
		frame.getContentPane().add(elapsedTime);

		JButton startButton = new JButton("Start");
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				optionChosen = comboBox.getSelectedIndex();
				if (comboBox.getSelectedIndex() == 7) {
					try {
						mngr = new SessionManager(selectedGoal, duration, optionChosen);
						mngr.readFile();
						textArea.setText(mngr.total() + mngr.showAveragePerSession());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (comboBox.getSelectedIndex() == 8) {
					try {
						 mngr = new SessionManager(selectedGoal, duration, optionChosen);
						textArea.setText(mngr.eraseData());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else if (comboBox.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(frame, "Please select a goal.");
				} else {
					try {
						timing();
						if(comboBox.getSelectedIndex() != 5) {
							textArea.setText("Work in progress...");
						} else {
							textArea.setText("Ya bitch, do work instead!");
						}
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		startButton.setBounds(453, 152, 89, 23);
		frame.getContentPane().add(startButton);

		JButton stopButton = new JButton("End");
		stopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (startTime > 0) {
						endTime = System.currentTimeMillis();
						duration = (endTime - startTime) / 3600000f;
						mngr = new SessionManager(selectedGoal, duration, optionChosen);
						mngr.writeFile();
						mngr.readFile();
						textArea.setText(mngr.today() + mngr.total());
						startTime = 0;
						timer.purge();
						timer.cancel();
					} else {
						JOptionPane.showMessageDialog(frame, "Ty trulo, sak to sa tak nerobi");
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(frame, "Ty trulo, sak to sa tak nerobi");
				}
				
			}
		});
		stopButton.setBounds(453, 186, 89, 23);
		frame.getContentPane().add(stopButton);

		textArea.setBounds(10, 89, 421, 200);
		frame.getContentPane().add(textArea);

	}


public void timing() throws IOException {
	startTime = System.currentTimeMillis();
	selectedGoal = (String) comboBox.getSelectedItem();
	mngr = new SessionManager(selectedGoal, duration, optionChosen);
	timer= new Timer();
	task = new TimerTask() {
		long time;

		@Override
		public void run() {
			// TODO Auto-generated method stub
			time = System.currentTimeMillis() -  startTime;
			long seconds = time / 1000;
			long minutes = seconds / 60;
			long hours = minutes / 60;
			seconds %= 60;
			minutes %= 60;
			
			String output =  String.format("%02d:%02d:%02d",hours, minutes, seconds);
			//String output = 
			
			elapsedTime.setText(output);
		}
		
	};
	
	timer.schedule(task, 0, 1000);
}}
