/**
   Game class streamlines Board, ComputerBoard, and UserBoard classes for implementation
*/

import java.util.*;
import java.io.*;

public class Game
{

   /** computer board object */
   private ComputerBoard computer;
   /** user board object */
   private UserBoard player;
   
   /**
      game constructor that initializes the two boards
   */
   public Game() throws IOException
   {
      computer = new ComputerBoard("compFleet.txt");
      player = new UserBoard("userFleet.txt");
   }
   
   /**
      makeComputerMove method calls the method of the same name
      from the UserBoard class and returns information about the
      move
      @return the move made, where it was made, and if a ship was sunk
   */
   public String[] makeComputerMove()
   {
      return player.makeComputerMove();
   }
   
   /**
      makePlayerMove method calls the method of the same name
      from the ComputerBoard class and returns information about the
      move
      @return the move made, where it was made, and if a ship was sunk
   */
   public String makePlayerMove(String move)
   {
      return computer.makePlayerMove(new Move(move));
   }
   
   /**
      userDefeated method checks to see if the user has lost
      @return true or false
   */
   public boolean userDefeated()
   {
      return player.gameOver();
   }
   
   /**
      computerDefeated method checks if the computer has lost
      @return true or false
   */
   public boolean computerDefeated()
   {
      return computer.gameOver();
   }
   
   /**
      toString method returns both boards well formatted
   */
   public String toString()
   {
      System.out.println(computer.toString());
      return player.toString();
   }

}