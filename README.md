# A1 - Piraten Karpen

  * Author: < You name here >
  * Email: < Your email here >

## Build and Execution

  * To clean your working directory:
    * `mvn clean`
  * To compile the project:
    * `mvn compile`
  * To run the project in development mode:
    * `mvn -q exec:java` (here, `-q` tells maven to be _quiet_)
  * To package the project as a turn-key artefact:
    * `mvn package`
  * To run the packaged delivery:
    * `java -jar target/piraten-karpen-jar-with-dependencies.jar` 

Remark: **We are assuming here you are using a _real_ shell (e.g., anything but PowerShell on Windows)**

## Feature Backlog

 * Status: 
   * Pending (P), Started (S), Blocked (B), Done (D)
 * Definition of Done (DoD):
   * The	score is	computed	by	counting	the	numbers	of	gold	coins	and	diamonds	and	multiplying	
     it	by	100.
   * We	only	have	two players,	both	playing	using	the	same	strategy.
   * We	have	a	single	strategy	 for	a	player,	which	is	choosing	randomly	 the	dices the	player	is	    keeping,	and	re-rolling	until	obtaining	three	skulls	to	end	the	turn.	
   * We	always	play	42	games during	a	simulation.
   * At	the	end	of	the	simulation,	we	print	on	stdout the	percentage	of	wins	for	each	player

### Backlog 

| MVP? | Id  | Feature  | Status  |  Started  | Delivered |
| :-:  |:-:  |---       | :-:     | :-:       | :-:       |
| x   | F01 | Roll a dice |  S | 01/01/23 |  |
| x   | F02 | Roll eight dices  |  B (F01) |   |
| x   | F03 | Select how many games as command-line arg.  |  P  |   |
| x   | F04 | end of game with three cranes | P | |
| x   | F05 | Player keeping random dice at their turn | B (F02) | | 
| x   | F06 | Score points: 3-of-a-kind | B (F04) | | 
| ... | ... | ... |

