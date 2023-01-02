/**
   BattleShipDriver contains the main method and loops while the game is not finished
*/

import java.util.*;
import java.io.*;

public class BattleShipDriver
{

   public static void main(String[] args) throws IOException
   {
   
      // initialize objects and variables
      String userMove;
      String compMove;
      Random rand = new Random();
      Game liveGame = new Game();
      Scanner keyboard = new Scanner(System.in);
      int coinToss = rand.nextInt(2);
      String nextMove;
      
      // decide who starts
      if (coinToss == 0)
         nextMove = "PLAYER";
      else
         nextMove = "COMPUTER";
      
      // display greeting to the game
      System.out.println("Welcome to Battleship!\n\n");
      if (nextMove == "PLAYER")
         System.out.println("You have won the coin toss and will go first.");
      else
         System.out.println("The computer has won the coin toss and will go first.");
      
      Scanner cont = new Scanner(System.in);
      // loop through the game until someone wins
      while (liveGame.userDefeated() == false && liveGame.computerDefeated() == false)
      {
         if (nextMove == "PLAYER")
         {
            System.out.print("What's your move? ");
            userMove = keyboard.nextLine().toUpperCase();
            // here I try to catch input error, but had tons of issues when trying to check all cases
            // this is my best attempt but i can only catch one type or the other, and not both
            if (userMove.length() > 1)
            {
               while (Integer.parseInt(userMove.substring(1)) < 1 || Integer.parseInt(userMove.substring(1)) > 10)
               {
                  System.out.print("Invalid input. Try again (Example A1 or a1): ");
                  userMove = keyboard.nextLine().toUpperCase();
               }
            }
            else if (userMove.length() < 2)
            {
               while (userMove.length() < 2)
               {
                  System.out.print("Invalid input. Try again (Example A1 or a1): ");
                  userMove = keyboard.nextLine().toUpperCase();
               }
            }
            String userMsg = liveGame.makePlayerMove(userMove);
            nextMove = "COMPUTER";
            if (userMsg != null)
               System.out.println("The computer says: " + '"' + userMsg + '"');
         }
         else if (nextMove == "COMPUTER")
         {
            System.out.print("Computer's turn: Press any key to continue ");
            compMove = cont.nextLine();
            String[] compMsg = liveGame.makeComputerMove();
            System.out.println("Computer chose: " + compMsg[0]);
            nextMove = "PLAYER";
            if (compMsg[1] != null)
               System.out.println('"' + compMsg[1] + '"');
         }
         System.out.print(liveGame.toString());
      }
      
      if (liveGame.userDefeated() == true)
         System.out.println("Game Over! The computer has won.");
      else
         System.out.println("Game Over! You have beaten the computer.");
   
   }

}