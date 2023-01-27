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
   * The game is able to function with two players in a 42 game simulation where we print out win % of each player after. 
   * Score can be calculated by multiplying the number of gold coins and diamonds by 100
   * The choice of dice by the player is at random and their turn ends once they obtain 3 skulls,

### Backlog 

| MVP? | Id  | Feature  | Status  |  Started  | Delivered |
| :-:  |:-:  |---       | :-:     | :-:       | :-:       |
| x   | F01 | Roll a dice |  D | 01/01/23 | 01/10/23 | 
| x   | F02 | Roll eight dices  |  D  | 01/12/23  | 01/12/23
| x   | F03 | Select how many games as command-line arg.  | D | 01/14/2023  | 01/14/2023
| x   | F04 | end of game with three cranes | D | 01/14/2023 | 01/14/2023
| x   | F05 | Player keeping random dice at their turn | D | 01/14/2023 | 01/15/2023 
| x   | F06 | Score points: 3-of-a-kind | S | 01/15/2023 |  01/1/2023
| ... | ... | ... |

