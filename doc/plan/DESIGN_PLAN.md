##Introduction
*This section describes the problem your team is trying to solve by writing this program, the primary design goals of the project 
(i.e., where is it most flexible), and the primary architecture of the design (i.e., what is closed and what is open). Discuss 
the design at a high-level (i.e., without referencing specific classes, data structures, or code).*
##Overview
*This section serves as a map of your design for other programmers to gain a general understanding of how and why the program was 
divided up, and how the individual parts work together to provide the desired functionality. Describe specific modules you intend 
to create, their purpose with regards to the program's functionality, and how they collaborate with each other, focusing 
specifically on each one's API. Include a picture of how the modules are related (these pictures can be hand drawn and 
scanned in, created with a standard drawing program, or screen shots from a UML design program). Discuss specific classes, 
methods, and data structures, but not individual lines of code.*

In the model, 
##Design Details
*This section describes each module introduced in the Overview in detail (as well as any other sub-modules that may be needed but 
are not significant to include in a high-level description of the program). Describe how each module's API handles specific features
given in the assignment specification, what resources it might use, how it collaborates with other modules, and how each could be 
extended to include additional requirements (from the assignment specification or discussed by your team). Finally, justify the 
decision to create each module with respect to the design's key goals, principles, and abstractions.*

###Data Files

### "onCollide" property
The "onCollide" property specifies the object with which the parent object is expected to collide with, 
as well as an *action* and *payload*.

####gameobjects.json
The "objects" property declares all the types of game objects that are
expected to be created for the game. These types can be overriden.

####level2.json
An example of a level configuration file. The "objects" property declares the objects that are expected
to be declared in this level. Each object must be of a type declared in gameobjects.json and has its location in the level
layout specified. 

####config.json
Specifies game information, such as name, levels, and objects. Properties in "levels" correspond to 
level data files, and properties in "objects" correspond to data files containing game object types.

##Design Considerations
*This section describes any issues which need to be addressed or resolved before attempting to devise a complete design solution. 
Include any design decisions discussed at length (include pros and cons from all sides of the discussion) as well as any ambiguities,
assumptions, or dependencies regarding the program that impact the overall design.*

- class for every single object type.
- all updates go through controller. Instead of view looking at model, view asking controller for updates.
- actions are strings representing methods (reflection or lambdas) 
- 


##Example games
*Describe three example games that differ significantly in detail. Clearly identify how the functional differences in these games is 
supported by your design. Use these examples to help clarify the abstractions in your design.*
##Test Plan
*This section sets the team's commitment to the project's quality by addressing expectations for both "happy" and "sad" possible 
input/interactions. It is generally believed that easily testable code is better designed so describe at least two specific 
strategies your team discussed to make your APIs more easily testable (such as useful parameters and return values, using smaller 
classes or methods, throwing Exceptions, etc.). Your team must also describe three test scenarios each for four project features
(at least one of which is negative/sad/error producing), the expected outcome, and how your design supports testing for it 
(such as methods you can call, specific values you can use as parameters and return values to check, specific expected UI displays 
or changes that could be checked, etc.). A test scenario describes the expected results from a user's action to initiate a 
feature in your program and the steps that lead to that result.*