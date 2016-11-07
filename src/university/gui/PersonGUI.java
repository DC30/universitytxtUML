package university.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import hu.elte.txtuml.api.model.Action;
import hu.elte.txtuml.api.model.execution.ModelExecutor;
import university.x.model.Check;
import university.x.model.EndClass;
import university.x.model.Enroll;
import university.x.model.Enrollment;
import university.x.model.EnrollmentHasRoom;
import university.x.model.InsertPerson;
import university.x.model.Person;
import university.x.model.ProfAvailable;
import university.x.model.ProfTaken;
import university.x.model.Professor;
import university.x.model.Room;
import university.x.model.RoomAvailable;
import university.x.model.RoomTaken;
import university.x.model.SubjectHasProfessor;
import university.x.model.TermStarted;

import javax.swing.JPanel;
import javax.swing.JRadioButton;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import java.awt.Color;

public class PersonGUI implements EnrollInterface{

	static Person person;
	static Enrollment enrollment;
	static Room room;
	static Professor professor;
	static JFrame frame;
	static JTextField textField;
	static JLabel label;
	static JButton btnButton;
	static JRadioButton rdbtnAvailable;
	static JRadioButton rdbtnTaken;
	static JPanel panelRoom;
	static JPanel panelProf;
	static JRadioButton rdbtProfAvai;
	static JRadioButton rdbProfTaken;
	private static JButton btnOpen;
	static JButton btnCheck;
	private static JLabel lblOpenCourse;
	private static JButton btnStart;
	private static JButton btnEnd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		// ModelExecutor to animation to state machine
		ModelExecutor.create().setTraceLogging(true).start(PersonGUI::initialize);
		

	}

	/**
	 * Initialize the contents of the frame.
	 * @wbp.parser.entryPoint
	 */
	static void initialize() {
		
		//create the action to person

		person = Action.create(Person.class);		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 526, 527);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		
		JLabel lblRegisterAPerson = new JLabel("REGISTER A PERSON");
		lblRegisterAPerson.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblRegisterAPerson.setBounds(114, 11, 229, 14);
		frame.getContentPane().add(lblRegisterAPerson);
		
		JLabel lblEnterData = new JLabel("Name");
		lblEnterData.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblEnterData.setBounds(55, 72, 89, 14);
		frame.getContentPane().add(lblEnterData);
		
		textField = new JTextField();
		textField.setBounds(192, 69, 151, 17);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		
		String[] optionCombo ={"Profesor","Student"};
		JComboBox comboBox = new JComboBox(optionCombo);
		comboBox.setBounds(192, 116, 151, 23);
		frame.getContentPane().add(comboBox);
		comboBox.setSelectedIndex(1);
		comboBox.addActionListener( new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == comboBox){
					JComboBox cb = (JComboBox) e.getSource();
					String msg = (String) cb.getSelectedItem();
					switch (msg) {
					case "Profesor": label.setText("It's a Profesor");
					person.setTypePerson("P");
						break;
					case "Student": label.setText("It's a Student");
					person.setTypePerson("S");
					
						break;
					default:
						break;
					}
				}
				
			}
		});		
		
		btnButton = new JButton("Save");
		btnButton.setBounds(254, 175, 89, 23);
		frame.getContentPane().add(btnButton);
		btnButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				person.setName(textField.getText());
				if(person.getName()!= null && !person.getName().isEmpty()){					
					Action.send(new InsertPerson(),person);
					label.setText("Person saved");
					createEnroll();
				}else {
					label.setText("Enter the data");
				}
				
			}

		} );
						
				
		label = new JLabel();
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setForeground(Color.BLUE);
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		label.setBounds(207, 36, 290, 14);
		frame.getContentPane().add(label);
		
		JButton btnNewPerson = new JButton("New Person");
		btnNewPerson.setBounds(55, 175, 89, 23);
		frame.getContentPane().add(btnNewPerson);
		btnNewPerson.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		
		JLabel lblTypeOfPerson = new JLabel("Type of Person");
		lblTypeOfPerson.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblTypeOfPerson.setBounds(55, 120, 107, 14);
		frame.getContentPane().add(lblTypeOfPerson);
		
		panelRoom = new JPanel();
		FlowLayout fl_panelRoom = (FlowLayout) panelRoom.getLayout();
		fl_panelRoom.setAlignment(FlowLayout.LEFT);
		panelRoom.setBounds(55, 296, 338, 33);
		frame.getContentPane().add(panelRoom);
		
		JLabel lblRoom = new JLabel("Room");
		lblRoom.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRoom.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		panelRoom.add(lblRoom);
		
		rdbtnAvailable = new JRadioButton("Available");
		panelRoom.add(rdbtnAvailable);
		rdbtnAvailable.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				rdbtnAvailable.setSelected(true);
				rdbtnTaken.setSelected(false);
				Action.send(new RoomAvailable(), room);
				
			}
		});
		
		rdbtnTaken = new JRadioButton("Taken");
		panelRoom.add(rdbtnTaken);
		rdbtnTaken.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				rdbtnAvailable.setSelected(false);
				rdbtnTaken.setSelected(true);
				Action.send(new RoomTaken(), room);
				
			}
		});
		
		panelProf = new JPanel();
		FlowLayout fl_panelProf = (FlowLayout) panelProf.getLayout();
		fl_panelProf.setAlignment(FlowLayout.LEFT);
		panelProf.setBounds(55, 356, 338, 33);
		frame.getContentPane().add(panelProf);
		
		JLabel lblProfessor = new JLabel("Professor");
		lblProfessor.setHorizontalAlignment(SwingConstants.RIGHT);
		lblProfessor.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		panelProf.add(lblProfessor);
		
		rdbtProfAvai = new JRadioButton("Available");
		panelProf.add(rdbtProfAvai);
		rdbtProfAvai.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				rdbtProfAvai.setSelected(true);
				rdbProfTaken.setSelected(false);	
				Action.send(new ProfAvailable(), professor);
				
			}
		});
		
		rdbProfTaken = new JRadioButton("Taken");
		panelProf.add(rdbProfTaken);
		
		btnCheck = new JButton("Check");
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtProfAvai.isSelected() && rdbtnAvailable.isSelected()){
					Action.send(new Check(), enrollment);
					label.setText("You can open the Course");
					btnOpen.setVisible(true);
					btnCheck.setVisible(false);
				} else{
					label.setText("It is not possible to open the course");
				}
			}
		});
		btnCheck.setBounds(55, 416, 89, 23);
		frame.getContentPane().add(btnCheck);
		
		btnOpen = new JButton("Open");
		btnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Action.send(new Enroll(), enrollment);
				btnStart.setVisible(true);
				btnOpen.setVisible(false);
			}
		});
		
		btnOpen.setBounds(55, 444, 89, 23);
		frame.getContentPane().add(btnOpen);
		
		lblOpenCourse = new JLabel("OPEN COURSE");
		lblOpenCourse.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblOpenCourse.setBounds(114, 248, 145, 14);
		frame.getContentPane().add(lblOpenCourse);
		
		btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Action.send(new TermStarted(), enrollment);
				btnStart.setVisible(false);
				btnEnd.setVisible(true);
			}
		});
		btnStart.setBounds(254, 416, 89, 23);
		frame.getContentPane().add(btnStart);
		
		btnEnd = new JButton("End");
		btnEnd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Action.send(new EndClass(), enrollment);
				btnCheck.setVisible(true);
				btnEnd.setVisible(false);
				label.setText("");
			}
		});
		btnEnd.setBounds(254, 444, 89, 23);
		frame.getContentPane().add(btnEnd);
		rdbProfTaken.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				rdbtProfAvai.setSelected(false);
				rdbProfTaken.setSelected(true);	
				Action.send(new ProfTaken(), professor);
			
				
			}
		});
		
		
		
		frame.setVisible(true);
		btnNewPerson.setVisible(false);
		panelRoom.setVisible(false);
		panelProf.setVisible(false);
		btnOpen.setVisible(false);
		btnCheck.setVisible(false);
		btnStart.setVisible(false);
		lblOpenCourse.setVisible(false);
		btnEnd.setVisible(false);
		Action.start(person);
		room = Action.create(Room.class);
		professor = Action.create(Professor.class);
		enrollment = Action.create(Enrollment.class);
		Action.link(EnrollmentHasRoom.subjectTaken.class, enrollment, EnrollmentHasRoom.room.class, room);
		Action.link(SubjectHasProfessor.subjectTaken.class, enrollment, SubjectHasProfessor.professor.class, professor);
		
		Action.start(enrollment);
		Action.start(room);
		Action.start(professor);
		
	}
	
	public static void createEnroll() {		
			panelRoom.setVisible(true);
			panelProf.setVisible(true);
			btnCheck.setVisible(true);
			lblOpenCourse.setVisible(true);
	

	}

	@Override
	public void roomAvailable() {		
//		rdbtnAvailable.setEnabled(true);
		rdbtnAvailable.setSelected(true);
		rdbtnTaken.setSelected(false);
		
	}

	@Override
	public void roomTaken() {		
		rdbtnAvailable.setSelected(false);
		rdbtnTaken.setSelected(true);
		
	}

	@Override
	public void profAvailable() {
		rdbtProfAvai.setSelected(true);
		rdbProfTaken.setSelected(false);
		
	}

	@Override
	public void profTaken() {
		rdbtProfAvai.setSelected(false);
		rdbProfTaken.setSelected(true);	
	}
}
