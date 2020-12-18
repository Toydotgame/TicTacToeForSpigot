# Tic Tac Toe
## Usage
#### To start a new game:
```
/tictactoe
```
#### To make a move:
```
/tictactoe move <position>
```
_Positions on the board are numbers from 1 to 9._
##### Example:
```
/tictactoe move 5
```

## Contributing / Modifying
* Clone the repo. with whatever you prefer:
```git
git clone https://github.com/Toydotgame/TicTacToeForSpigot/ && cd TicTacToeForSpigot
gh repo clone Toydotgame/TicTacToeForSpigot && cd TicTacToeForSpigot
```
* Set up this code in your IDE.
* Make sure to add `spigot.jar` as an external JAR in the _Libraries_ tab (In the project's properties) of Eclipse, or whatever is the alternative for your IDE.

## Building
#### Compiling
When the project is set up in Eclipse (Again, _or the other IDE that you use_), Export the project as a plain JARfile - __not a runnable JAR file__!
Once exported, get it ready to be copied to the server folder:
#### Running the Plugin
Conveniently, the Spigot SDK (`spigot.jar`) is also the server executable.
  * Make a copy of `spigot.jar` in an empty folder.
  * Run the server:
  ```bash
  java -Xmx1G -Xms256M -jar spigot.jar nogui
  ```
  * Add your compiled plugin JAR to the plugins/ folder \[at the same level as `spigot.jar`\].
  * Type `reload` in the server console.
  * The plugin should be usable, either from said server console, or via connecting a Minecraft 1.6.4 client and using the same commands!
