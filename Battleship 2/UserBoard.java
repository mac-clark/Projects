/**
   UserBoard
*/

import java.util.*;
import java.io.*;

public class UserBoard extends Board
{
   /** arraylist of possible moves the computer can make */
   private ArrayList<Move> moves = new ArrayList<Move>();
   /** rand for randomly selecting a move */
   private Random rand;
   
   /**
      constructor that takes a filename in, passes it to Board constructor and
      initializes the list of moves the computer can make
      @param filename holds ships locations
   */
   public UserBoard(String filename) throws IOException
   {
      super(filename);
      rand = new Random();
      for (int i = 0; i < 100; i++)
      {
         if (i < 10)
            moves.add(new Move(0,i));
         if (i < 20 && i >= 10)
            moves.add(new Move(1,i - 10));
         if (i < 30 && i >= 20)
            moves.add(new Move(2,i - 20));
         if (i < 40 && i >= 30)
            moves.add(new Move(3,i - 30));
         if (i < 50 && i >= 40)
            moves.add(new Move(4,i - 40));
         if (i < 60 && i >= 50)
            moves.add(new Move(5,i - 50));
         if (i < 70 && i >= 60)
            moves.add(new Move(6,i - 60));
         if (i < 80 && i >= 70)
            moves.add(new Move(7,i - 70));
         if (i < 90 && i >= 80)
            moves.add(new Move(8,i - 80));
         if (i < 100 && i >= 90)
            moves.add(new Move(9,i - 90));
      }
   }
   
   /**
      makeComputerMove method takes a random move from the list and applies it to the
      user's board, returns a string array with information about the move made
      @return string array with two strings indicating if a move was made, where it was made
      and if it sank a ship
   */
   public String[] makeComputerMove()
   {
      int index = rand.nextInt(moves.size() - 1);
      Move m = moves.get(index);
      if (super.moveTaken(m) == true)
      {
         return null;
      }
      super.applyMoveToLayout(m);
      String[] out = new String[2];
      out[0] = m.toString();
      ArrayList<ArrayList<CellStatus>> layout = super.getLayout();
      int targetRow = m.row();
      int targetCol = m.col();
      if (layout.get(targetRow).get(targetCol).compareTo(CellStatus.AIRCRAFT_CARRIER_SUNK) == 0)
         out[1] = "The computer sank your Aircraft Carrier!";
      else if (layout.get(targetRow).get(targetCol).compareTo(CellStatus.BATTLESHIP_SUNK) == 0)
         out[1] = "The computer sank your Battleship!";
      else if (layout.get(targetRow).get(targetCol).compareTo(CellStatus.SUB_SUNK) == 0)
         out[1] = "The computer sank your Sub!";
      else if (layout.get(targetRow).get(targetCol).compareTo(CellStatus.CRUISER_SUNK) == 0)
         out[1] = "The computer sank your Cruiser!";
      else if (layout.get(targetRow).get(targetCol).compareTo(CellStatus.DESTROYER_SUNK) == 0)
         out[1] = "The computer sank your Destroyer!";
      else
         out[1] = null;
      moves.remove(index);
      return out;
   }
   
   /**
      toString method prints a formatted board of the user's layout
   */
   @Override
   public String toString()
   {
      ArrayList<ArrayList<CellStatus>> compLayout = super.getLayout();
      System.out.print("\nPLAYER\n");
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
               System.out.print(compLayout.get(i).get(j).toString().charAt(1) + "\n");
            }
            else
               System.out.print(compLayout.get(i).get(j).toString().charAt(1) + " ");
         }
      }
      return String.format("\n");
   }
}
