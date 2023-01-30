# A1 - Piraten Karpen

  * Author: < Swesan Pathmanathan >
  * Email: < 201360on50@gmail.com >

## Build and Execution

  * To clean your working directory:
    * `mvn clean`
  * To compile the project:
    * `mvn compile`
  * To run the project in development mode:
    * `mvn -q exec:java` (here, `-q` tells maven to be _quiet_)
  * To run the project with logs and strategy.
    * `mvn exec:java -DLEVEL=TRACE -Dexec.mainClass=test.Main -Dexec.args="random combo"`
  * To package the project as a turn-key artefact:
    * `mvn package`
  * To run the packaged delivery with logs and strategy. 
    * `java -jar -DLEVEL=TRACE target/piraten-karpen-jar-with-dependencies.jar "random combo"` 

Remark: **We are assuming here you are using a _real_ shell (e.g., anything but PowerShell on Windows)**

## Feature Backlog

 * Status: 
   * Pending (P), Started (S), Blocked (B), Done (D)
 * Definition of Done (DoD):
   * The game is able to function with two players in a 42 game simulation where we print out win % of each player after. 
   * Score can be calculated by multiplying the number of gold coins and diamonds by 100
   * The choice of dice by the player is at random and their turn ends once they obtain 3 skulls,

### Backlog 

| MVP? |       ID       | Feature                                     | Status |  Started   | Delivered   |
|:----:|:--------------:|---------------------------------------------|:------:|:----------:|-------------|
|  x   |      F01       | Roll a dice                                 |   D    |  01/01/23  | 01/10/23    |
|  x   |      F02       | Roll eight dices                            |   D    |  01/12/23  | 01/12/23    |
|  x   |      F03       | Select how many games as a command-line arg |   D    | 01/14/2023 | 01/14/2023  |
|  x   |      F04       | end of game with three skulls               |   D    | 01/14/2023 | 01/14/2023  |
|  x   |      F05       | Player keeping random dice at their turn    |   D    | 01/14/2023 | 01/15/2023  |
|  x   |      F06       | Score points 3-of-a-kind                    |   D    | 01/15/2023 | 01/15/2023  |
|  x   |     combos     | Score points 4 to 8 of a kind               |   D    | 01/16/2023 | 01/16/2023  |
|  x   |   strategies   | Random and combo strategy                   |   D    | 01/16/2023 | 01/16/2023  |
|  x   |   seabattle    | Sea battle fortune card                     |   D    | 01/17/2023 | 01/17/2023  |
|  x   |   strategies   | Leverage mechanic                           |   D    | 01/18/2023 | 01/18/2023  |
|  x   | monkeybusiness | Monkey business fortune card                |   D    | 01/18/2023 | 01/18/2023  |
|  x   |   skullisle    | Introduce Island of Skulls                  |   S    | 01/26/2023 |             |