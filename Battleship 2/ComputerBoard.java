/**
   ComputerBoard class extends board class, applies moves made against the 
   computer
*/

import java.util.*;
import java.io.*;

public class ComputerBoard extends Board
{
   
   /**
      constructor that takes a filename and passes it to Board constructor
      @param filename holds ship locations
   */
   public ComputerBoard(String filename) throws IOException
   {
      super(filename);
   }
   
   /**
      makePlayerMove method takes in a Move object and returns information
      about the move
      @return the move made or a message indicating a sunken ship
   */
   public String makePlayerMove(Move m)
   {
      if (super.moveTaken(m) == true)
         return null;
      super.applyMoveToLayout(m);
      ArrayList<ArrayList<CellStatus>> layout = super.getLayout();
      int targetRow = m.row();
      int targetCol = m.col();
      if (layout.get(targetRow).get(targetCol).compareTo(CellStatus.AIRCRAFT_CARRIER_SUNK) == 0)
         return String.format("You sank my Aircraft Carrier!");
      else if (layout.get(targetRow).get(targetCol).compareTo(CellStatus.BATTLESHIP_SUNK) == 0)
         return String.format("You sank my Battleship!");
      else if (layout.get(targetRow).get(targetCol).compareTo(CellStatus.SUB_SUNK) == 0)
         return String.format("You sank my Sub!");
      else if (layout.get(targetRow).get(targetCol).compareTo(CellStatus.CRUISER_SUNK) == 0)
         return String.format("You sank my Cruiser!");
      else if (layout.get(targetRow).get(targetCol).compareTo(CellStatus.DESTROYER_SUNK) == 0)
         return String.format("You sank my Destroyer!");
      else
         return null;
   }
   
   /**
      toString method prints a formatted board and indicates that it is the computers
      @return the board
   */
   @Override
   public String toString()
   {
      ArrayList<ArrayList<CellStatus>> compLayout = super.getLayout();
      System.out.print("\nCOMPUTER\n");
      System.out.print("   1 2 3 4 5 6 7 8 9 10\n");
      for (int i = 0; i < 10; i++)
      {
         if (i == 0)
            System.out.print("A  ");
         if (i == 1)
            System.out.print("B  ");
         if (i == 2)
            System.out.print("C  ");
         if (i == 3)
            System.out.print("D  ");
         if (i == 4)
            System.out.print("E  ");
         if (i == 5)
            System.out.print("F  ");
         if (i == 6)
            System.out.print("G  ");
         if (i == 7)
            System.out.print("H  ");
         if (i == 8)
            System.out.print("I  ");
         if (i == 9)
            System.out.print("J  ");
            
         for (int j = 0; j < 10; j++)
         {
            if (j == 9)
            {
               System.out.print(compLayout.get(i).get(j).toString().charAt(0) + "\n");
            }
            else
               System.out.print(compLayout.get(i).get(j).toString().charAt(0) + " ");
         }
      }
      return String.format("\n");
   }

}