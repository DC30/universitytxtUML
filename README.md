# universitytxtUML
Project related with txtUML, about the process in a University. 

## Motivation
This project was created to get a deeper understanding how works txtUML, which is an executable UML language embedded in Java.
With this project, we can see the flow how to enrollment a student in a course.

more information about the txtML http://txtuml.inf.elte.hu

## Installation txtUML

You can see the installation instructions in this link http://txtuml.inf.elte.hu/wiki/doku.php?id=v051:install.


## Getting Started

- Download and unzip the university project
- Import it into your Eclipse Workspace(File/Import/General/Existing project into workspace).
- Clean and build the project.

University project is implemented using XtxtUML syntax, you can see the files in the source package “university.x.model”  

In addition this project has 5 diagrams made in Java classes inheriting from the ClassDiagram or StateMachineDiagram type, you can see the class in the source package “university.x”.

<b>UniversityClassDiagram:</b> This Class contains the model of the class diagram related with the university.

<b>PersonSMDiagram:</b> State machine which describes the flow about to input person information and select if he/she is professor or student.

<b>RoomStates:</b> State machine allows to see if the Room is available or taken.

<b>ProfessorStates:</b> State machine allows to see if the Professor is available to start class or he is busy with another course.

<b>EnrollmentState:</b> This state machine describes the flow how to enrollment a student in a course.

## Running

Run/Run Configurations/txtUML Application

There is a main class “PersonGUI” in the source package “university.gui” which exists a graphical user interface, to enter the person information and open a course.

In order to open a course, the room as well as the professor should be available. If they are available, you can check resource then It is possible to open the course and start the class. You can see the executable modeling within the state machines Room, Professor and Enrollment.
