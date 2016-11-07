package university.x;

import hu.elte.txtuml.api.layout.Row;
import hu.elte.txtuml.api.layout.StateMachineDiagram;
import university.x.model.Enrollment;
import university.x.model.Enrollment.BeingTaugh;
import university.x.model.Enrollment.CheckResource;
import university.x.model.Enrollment.Init;
import university.x.model.Enrollment.OpenEnroll;
import university.x.model.Enrollment.Waiting;

public class EnrollmentState extends StateMachineDiagram<Enrollment>{
	@Row({Init.class, Waiting.class, CheckResource.class, OpenEnroll.class, BeingTaugh.class})
	class L extends Layout{}

}
