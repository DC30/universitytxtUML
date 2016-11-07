package university.gui;

import hu.elte.txtuml.api.model.external.ExternalClass;

public interface EnrollInterface extends ExternalClass{
	
	public void roomAvailable();
	public void roomTaken();
	public void profAvailable();
	public void profTaken();

}
