package university.x;

import java.io.IOException;

import hu.elte.txtuml.api.model.Action;
import hu.elte.txtuml.api.model.execution.ModelExecutor;
import university.x.model.Check;
import university.x.model.EndClass;
import university.x.model.Enroll;
import university.x.model.Enrollment;
import university.x.model.TermStarted;

public class Main {
	
	static Enrollment enrollment; 
	
	static void init(){
		enrollment = Action.create(Enrollment.class);
		Action.start(enrollment);
	}
	
	public static void main(String[] args) throws IOException {
		ModelExecutor.create().setTraceLogging(true).start(Main::init);// we can use await initialization to not use the system in read
		System.in.read();
		Action.send(new Check(), enrollment);
		Action.send(new Enroll(), enrollment);
		Action.send(new TermStarted(), enrollment);
		Action.send(new EndClass(), enrollment);
		
		
	}

	
	
	

}
