package university.x;

import hu.elte.txtuml.api.layout.Column;
import hu.elte.txtuml.api.layout.Right;
import hu.elte.txtuml.api.layout.StateMachineDiagram;
import university.x.model.Person;
import university.x.model.Person.Init;
import university.x.model.Person.InsertData;
import university.x.model.Person.InsertProfessor;
import university.x.model.Person.InsertStudent;
import university.x.model.Person.SelectPerson;

public class PersonSMDiagram extends StateMachineDiagram<Person>{
	
	@Column({Init.class, InsertData.class, SelectPerson.class, InsertProfessor.class})	 
	@Right(val = InsertStudent.class, from = InsertProfessor.class)
	class PersonLayout extends Layout{}

}
