package university.x;

import hu.elte.txtuml.api.layout.Row;
import hu.elte.txtuml.api.layout.StateMachineDiagram;
import university.x.model.Room;
import university.x.model.Room.Available;
import university.x.model.Room.Init;
import university.x.model.Room.Taken;

public class RoomStates extends StateMachineDiagram<Room>{
	@Row({Init.class, Available.class, Taken.class})
	class L extends Layout{}

}
