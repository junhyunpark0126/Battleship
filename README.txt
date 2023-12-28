=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 1200 Game Project README
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. 2D Arrays

  The gameboard is a 2D array of characters, where 's' represents a square containing a ship, 'n' represents a square
  containing nothing (water), 'x' represents when user hits nothing, and 'o' represents when user hits a ship. This
  feature is an appropriate use of the concept because I can easily modify the gameboard if it is in a 2D array
  layout, and it makes file I/O easier.

  2. File I/O

  My game contains file readers and writers so that when the user saves the game, all the attributes of the current
  state of the game are saved onto a text file, which is then read when the user loads the game. This is good because
  the user can exit out of the game, come back later, and still load up the game since the text file will not be
  touched.

  3. Collections

  My game contains a high score feature which uses a LinkedList to store all scores of the game and display
  the highest score of the game on screen. Also, whenever the user clicks save, the text file displays ALL
  of the previously obtained scores, which is an efficient use of LinkedLists instead of a normal variable. I used
  a LinkedList because scores can be repeated.(this was fully approved on Gradescope by a TA)

  4. JUnit Testable Component

  My game contains a test file which tests various components of the game without relying on the Swing GUI
  component. I simulate the game on the text file and watch the edge cases so that my game is ensured that it works,
  which is what JUnit tests are for.

===============================
=: File Structure Screenshot :=
===============================
- Include a screenshot of your project's file structure. This should include
  all of the files in your project, and the folders they are in. You can
  upload this screenshot in your homework submission to gradescope, named 
  "file_structure.png".

=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.

  Battleship.java contains the main logic of the game, such as controlling what clicking does,
  generating the board, what loading and saving does, etc. GameBoard.java controls the background of the game,
  like making sure clicking activates boxes, updating the status of the game, and painting the game.
  RunBattleship.java implements Runnable and controls the layout of the game, such as the buttons, texts,
  panes, and overall panel. Game.java is the class that allows the game to be run and BattleshipTest.java
  contains various JUnit tests that help the game ensure it is working well.


- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?

  The load button always seemed to have some kind of bug, but this was a result of my laziness to not want to
  read and write a lot of attributes of the game. I decided to end it once and for all
  by basically writing and reading almost ALL aspects of the game, and I even have some "miscellaneous" parts of the
  text file that are there to ensure the game is running smoothly, but are not important for the user.


- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?

  There is a good separation of functionality, but I think I could have organized my code a little better.
  The private state is very well encapsulated, with almost all variables being private and therefore requiring
  getters and setters for nearly every variable. If I could refactor, I would make some of the helper functions
  private and implement the game so that it randomly generates horizontal and vertical ships instead of just
  horizontal ships.



========================
=: External Resources :=
========================

- Cite any external resources (images, tutorials, etc.) that you may have used 
  while implementing your game.
