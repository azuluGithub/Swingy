# Swingy
# INTRO:
	- Ok, I am self-taught, peer learning software engineering student
	- This is my second java project called Swingy
	- the subject of this project was written by Academy+Plus which is a branch of 42
	- THIS PROJECT IS ONLY MISSING JAVAX ANNOTATION VALIDATION

# SUBJECT:
	- implement a minimalistic text-based RPG game and apply the best practices suited for this problem
	- At the end of the project you will understand how to abstract the user interface or the view from other parts of the application.

# WHAT YOU NEED TO KNOW:
	- How to read from a file:
		=> heroes are read from a hero.txt file at the beginning of the game
	- How to write on a file:
		=> created heroes are saved on hero.txt file
	- How to use Model View Controller:
		=> View = graphic User Interface
		=> Model = data and methods of the program
		=> Controller = handles interactions between view and model
	- How to use Maven:
		=> Maven builds jar package which compiles the program
		=> Maven also allows easy access to dependencies and plugins
	- How to validate with javax validator:
		=> user input must be validated with javax annotations
	- How to Use Swing library:
		=> Swing library is for creating the graphic user interface
	- recommendations:
		=> Project Lombok helps generate boiler plate-code e.g @Getters and @Setters 
		=> Builder Design Pattern can be used where constructors takes longer parameters

# REQUIREMENTS AND PROGRAM BEHAVIOR:
	- Game must be in 2 phases:
		=> First phase for hardcore hipsters that will be console based
		=> Second phase for regular hipsters, that will also have a simple GUI for taking user input
	- Respect the Model-View-Controller design pattern
	- Automated build with Maven
	- Annotation based user input validation
	- implement a text-based RPG based on the gameplay
	- Follow the Model-View-Controller architecture and allow switching between the console view and GUI view
	- When the player starts the game he has 2 options: 
    		=> Create a hero
		=> Select a previously created hero
	- hero needs to navigate a square map
	- The initial position of the hero is in the center of the map
	- He wins the game if he reaches on of the borders of the map
	- 4 directions: - North, East, South and West
	- When a map is generated, villains of varying power will be spread randomly over the map
	- When a hero moves to a position occupied by a villain, de hero has 2 options:
    		=> Fight, which engages him in a battle with the villain
		=> Run, which gives him a 50% chance of returning to the previous position. If the odds aren’t on his side, he must fight the villain
	- simulate the battle between the hero and monster and present the user the outcome of the battle
	- If a hero looses a battle, he dies and also looses the mission
	- If a hero wins a battle, he gains:
		=> Experience points, based on the villain power. Of course, he will level up if he reaches the next level experience.
		=> An artifact, which he can keep or leave. Of course, winning a battle doesn’t guarantee that an artefact will be droped
	- A user’s heroes and their state will be preserved, when the user exits the game, in a text file. 
	- When starting the game, your program will load the heroes from this file
  
# COMMANDS ARE RUN ON FOLDER WITH POM.XML FILE:
	-mvn clean package
	-java -jar swingy.jar console
          OR
	-java -jar swingy.jar gui
  
# MY PROJECT RESOURCES:
	- Web Pages:
            => Hibernate Validator – Java Bean Validation Example ~ https://howtodoinjava.com/hibernate/hibernate-validator-java-bean-validation/
	- Youtube:
            => Maven ~in28minutes ~ https://www.youtube.com/watch?v=0CFWeVgzsqY
            => How to make a text adventure game with gui in java: Part1 - Part6 ~ https://www.youtube.com/watch?v=G5yr4sekAI0
            => Model View Controller (MVC) with Java ~ https://www.youtube.com/watch?v=wVS_dgk0ovk

swingy@azulu2019
