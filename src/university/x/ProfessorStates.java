package university.x;

import hu.elte.txtuml.api.layout.Row;
import hu.elte.txtuml.api.layout.StateMachineDiagram;
import university.x.model.Professor;
import university.x.model.Professor.Available;
import university.x.model.Professor.Init;
import university.x.model.Professor.Taken;

public class ProfessorStates extends StateMachineDiagram<Professor>{
	@Row({Init.class, Available.class, Taken.class})
	class L extends Layout{}

}
