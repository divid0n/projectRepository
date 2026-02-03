package tracker;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Font;

public class MainWindow {

	long startTime = 0;
	long endTime;
	float duration;
	String selectedGoal;
	int optionChosen;
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

		
		textArea.setEditable(false);
		textArea.setLineWrap(true);

		
		comboBox.addItem("Select a goal to work on...");
		comboBox.addItem("TU Wien Admission");
		comboBox.addItem("Coding projects");
		comboBox.addItem("School assignments");
		comboBox.addItem("Guitar Practice");
		comboBox.addItem("Check Data");
		comboBox.addItem("Erase all data");
		comboBox.setBounds(80, 30, 192, 22);
		frame.getContentPane().add(comboBox);

		
		elapsedTime.setFont(new Font("Tahoma", Font.PLAIN, 16));
		elapsedTime.setBounds(400, 39, 142, 33);
		frame.getContentPane().add(elapsedTime);

		JButton startButton = new JButton("Start");
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				optionChosen = comboBox.getSelectedIndex();
				if (comboBox.getSelectedIndex() == 5) {
					try {
						mngr = new SessionManager(selectedGoal, duration, optionChosen);
						mngr.readFile();
						textArea.setText(mngr.total() + mngr.showAveragePerSession());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (comboBox.getSelectedIndex() == 6) {
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
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		startButton.setBounds(435, 152, 89, 23);
		frame.getContentPane().add(startButton);

		JButton stopButton = new JButton("Stop");
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
					} else {
						JOptionPane.showMessageDialog(frame, "Ty trulo, sak to sa tak nerobi");
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(frame, "Ty trulo, sak to sa tak nerobi");
				}
				startTime = 0;
				timer.purge();
				timer.cancel();
			}
		});
		stopButton.setBounds(435, 186, 89, 23);
		frame.getContentPane().add(stopButton);

		textArea.setBounds(10, 89, 390, 200);
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
