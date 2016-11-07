package university.x;

import hu.elte.txtuml.api.layout.Above;
import hu.elte.txtuml.api.layout.Below;
import hu.elte.txtuml.api.layout.ClassDiagram;
import hu.elte.txtuml.api.layout.Row;
import university.x.model.Bachelor;
import university.x.model.DegreeProgram;
import university.x.model.Enrollment;
import university.x.model.Faculty;
import university.x.model.Master;
import university.x.model.Person;
import university.x.model.Professor;
import university.x.model.Room;
import university.x.model.Student;
import university.x.model.Subject;
import university.x.model.University;

public class UniversityClassDiagram extends ClassDiagram {
		 
	@Above(val = University.class, from = Person.class)
	@Above(val = Faculty.class, from = DegreeProgram.class)
	@Row({Person.class,DegreeProgram.class })
	@Row({Professor.class, Student.class, Bachelor.class, Master.class})
	@Below(val = Enrollment.class, from = Student.class)
	@Below(val = Subject.class, from = Bachelor.class)
	@Below(val = Room.class, from = Enrollment.class)
	class UniversityLayout extends Layout{}	

}
