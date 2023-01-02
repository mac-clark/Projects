/**
   Board class handles the updating of the board
   has both user and computer boards
*/

import java.util.*;
import java.io.*;

public class Board
{
   /** size of the rows and columnns */
   public static final int SIZE = 10;
   /** 2D arraylist of cellstatus' */
   private ArrayList<ArrayList<CellStatus>> layout = new ArrayList<ArrayList<CellStatus>>();
   /** Fleet object refering to fleet of ships */
   private Fleet fleet;
   /** String array of ships and positions read from file */
   private String[] ships = new String[15];
   
   /**
      Board constructor takes a filename and initializes the board
      with the ships in their corresponding spots among the matrix
      @param filename is the filename with the ship locations
      @param row creates a new row to be added to layout
      @param ship is an array of strings to be processed
   */
   public Board(String filename) throws IOException
   {
      // initialize layout
      for (int i = 0; i < SIZE; i++)
      {
         ArrayList<CellStatus> row = new ArrayList<CellStatus>(SIZE);
         for (int j = 0; j < SIZE; j++)
         {
            row.add(CellStatus.NOTHING);
         }
         layout.add(row);
      }
      
      // get the data for ship locations
      String[] ship = loadFromFile(filename);
      
      // update layout with ships
      for (int k = 0; k < 15; k += 3)
      {
         String type = ship[k];
         String start = ship[k+1];
         String end = ship[k+2];
         // get row and column values for start and end
         char sr = start.charAt(0);
         int asciiSr = sr;
         int startRow = asciiSr - 65;
         int startCol = Integer.parseInt(start.substring(1,2)) - 1;
         char er = end.charAt(0);
         int asciiEr = er;
         int endRow = asciiEr - 65;
         int endCol = Integer.parseInt(end.substring(1)) - 1;
         // add type of ship to corresponding cell
         if (type.compareTo("A") == 0)
         {
            if (endRow - startRow == 0)
            {
               for (int z = 0; z < (endCol - startCol) + 1; z++)
               {
                  layout.get(startRow).set(startCol + z, CellStatus.AIRCRAFT_CARRIER);
               }
            }
            else
            {
               for (int z = 0; z < (endRow - startRow) + 1; z++)
               {
                  layout.get(startRow + z).set(startCol, CellStatus.AIRCRAFT_CARRIER);
               }
            }
         }
         if (type.compareTo("B") == 0)
         {
            if (endRow - startRow == 0)
            {
               for (int z = 0; z < (endCol - startCol) + 1; z++)
               {
                  layout.get(startRow).set(startCol + z, CellStatus.BATTLESHIP);
               }
            }
            else
            {
               for (int z = 0; z < (endRow - startRow) + 1; z++)
               {
                  layout.get(startRow + z).set(startCol, CellStatus.BATTLESHIP);
               }
            }
         }
         if (type.compareTo("C") == 0)
         {
            if (endRow - startRow == 0)
            {
               for (int z = 0; z < (endCol - startCol) + 1; z++)
               {
                  layout.get(startRow).set(startCol + z, CellStatus.CRUISER);
               }
            }
            else
            {
               for (int z = 0; z < (endRow - startRow) + 1; z++)
               {
                  layout.get(startRow + z).set(startCol, CellStatus.CRUISER);
               }
            }
         }
         if (type.compareTo("D") == 0)
         {
            if (endRow - startRow == 0)
            {
               for (int z = 0; z < (endCol - startCol) + 1; z++)
               {
                  layout.get(startRow).set(startCol + z, CellStatus.DESTROYER);
               }
            }
            else
            {
               for (int z = 0; z < (endRow - startRow) + 1; z++)
               {
                  layout.get(startRow + z).set(startCol, CellStatus.DESTROYER);
               }
            }
         }
         if (type.compareTo("S") == 0)
         {
            if (endRow - startRow == 0)
            {
               for (int z = 0; z < (endCol - startCol) + 1; z++)
               {
                  layout.get(startRow).set(startCol + z, CellStatus.SUB);
               }
            }
            else
            {
               for (int z = 0; z < (endRow - startRow) + 1; z++)
               {
                  layout.get(startRow + z).set(startCol, CellStatus.SUB);
               }
            }
         }
      }
      
      // initialize fleet
      fleet = new Fleet();
   }
   
   /**
      loadFromFile method simply takes in the filename of ship
      locations and reads the data to an array of strings to be
      processed in the board constructor
      @return a string array with ship data
   */
   private String[] loadFromFile(String filename) throws IOException
   {
      File dummy = new File(filename);
      Scanner inputFile = new Scanner(dummy);
      int i = 0;
      String currentLine;
      while (inputFile.hasNext())
      {
         currentLine = inputFile.nextLine();    
         ships[i] = currentLine.substring(0,1);
         i++;
         ships[i] = currentLine.substring(2);
         i++;
         ships[i] = currentLine.substring(5);
         i++;
      }
      inputFile.close();
      return ships;
   }
   
   /**
      applyMoveToLayout updates the layout given a move made
      @param targetRow is the row to be tested
      @param targetCol is the column to be tested
   */
   public void applyMoveToLayout(Move m)
   {
      int targetRow = m.row();
      int targetCol = m.col();
      if (layout.get(targetRow).get(targetCol).compareTo(CellStatus.NOTHING) == 0)
      {
         layout.get(targetRow).set(targetCol, CellStatus.NOTHING_HIT);
      }
      if (layout.get(targetRow).get(targetCol).compareTo(CellStatus.AIRCRAFT_CARRIER) == 0)
      {
         layout.get(targetRow).set(targetCol, CellStatus.AIRCRAFT_CARRIER_HIT);
         if (fleet.updateFleet(ShipType.ST_AIRCRAFT_CARRIER) == true)
         {
            for (int i = 0; i < SIZE; i++)
            {
               for (int j = 0; j < SIZE; j++)
               {
                  if (layout.get(i).get(j).compareTo(CellStatus.AIRCRAFT_CARRIER_HIT) == 0)
                     layout.get(i).set(j, CellStatus.AIRCRAFT_CARRIER_SUNK);
               }
            }
          }
      }
      if (layout.get(targetRow).get(targetCol).compareTo(CellStatus.BATTLESHIP) == 0)
      {
         layout.get(targetRow).set(targetCol, CellStatus.BATTLESHIP_HIT);
         if (fleet.updateFleet(ShipType.ST_BATTLESHIP) == true)
         {
            for (int i = 0; i < SIZE; i++)
            {
               for (int j = 0; j < SIZE; j++)
               {
                  if (layout.get(i).get(j).compareTo(CellStatus.BATTLESHIP_HIT) == 0)
                     layout.get(i).set(j, CellStatus.BATTLESHIP_SUNK);
               }
            }
          }
      }
      if (layout.get(targetRow).get(targetCol).compareTo(CellStatus.SUB) == 0)
      {
         layout.get(targetRow).set(targetCol, CellStatus.SUB_HIT);
         if (fleet.updateFleet(ShipType.ST_SUB) == true)
         {
            for (int i = 0; i < SIZE; i++)
            {
               for (int j = 0; j < SIZE; j++)
               {
                  if (layout.get(i).get(j).compareTo(CellStatus.SUB_HIT) == 0)
                     layout.get(i).set(j, CellStatus.SUB_SUNK);
               }
            }
          }
      }
      if (layout.get(targetRow).get(targetCol).compareTo(CellStatus.CRUISER) == 0)
      {
         layout.get(targetRow).set(targetCol, CellStatus.CRUISER_HIT);
         if (fleet.updateFleet(ShipType.ST_CRUISER) == true)
         {
            for (int i = 0; i < SIZE; i++)
            {
               for (int j = 0; j < SIZE; j++)
               {
                  if (layout.get(i).get(j).compareTo(CellStatus.CRUISER_HIT) == 0)
                     layout.get(i).set(j, CellStatus.CRUISER_SUNK);
               }
            }
          }
      }
      if (layout.get(targetRow).get(targetCol).compareTo(CellStatus.DESTROYER) == 0)
      {
         layout.get(targetRow).set(targetCol, CellStatus.DESTROYER_HIT);
         if (fleet.updateFleet(ShipType.ST_DESTROYER) == true)
         {
            for (int i = 0; i < SIZE; i++)
            {
               for (int j = 0; j < SIZE; j++)
               {
                  if (layout.get(i).get(j).compareTo(CellStatus.DESTROYER_HIT) == 0)
                     layout.get(i).set(j, CellStatus.DESTROYER_SUNK);
               }
            }
          }
      }
   }
   
   /**
      moveTaken method determines whether a spot on the board
      has already been taken or not
      @param targetRow is the row to be tested
      @param targetCol is the column to be tested
      @return whether the move is taken or not
   */
   public boolean moveTaken(Move m)
   {
      int targetRow = m.row();
      int targetCol = m.col();
      if (layout.get(targetRow).get(targetCol).compareTo(CellStatus.NOTHING_HIT) == 0)
         return true;
      else if (layout.get(targetRow).get(targetCol).compareTo(CellStatus.AIRCRAFT_CARRIER_HIT) == 0)
         return true;
      else if (layout.get(targetRow).get(targetCol).compareTo(CellStatus.BATTLESHIP_HIT) == 0)
         return true;
      else if (layout.get(targetRow).get(targetCol).compareTo(CellStatus.SUB_HIT) == 0)
         return true;
      else if (layout.get(targetRow).get(targetCol).compareTo(CellStatus.CRUISER_HIT) == 0)
         return true;
      else if (layout.get(targetRow).get(targetCol).compareTo(CellStatus.DESTROYER_HIT) == 0)
         return true;
      else
         return false;
   }
   
   /**
      getLayout returns a copy of the current layout to be processed
      @return the board as it looks now
   */
   public ArrayList<ArrayList<CellStatus>> getLayout()
   {
      ArrayList<ArrayList<CellStatus>> currentLayout = layout;
      return currentLayout;
   }
   
   /**
      getFleet returns a reference to the fleet
      @return the fleet
   */
   public Fleet getFleet()
   {
      return fleet;
   }
   
   /**
      gameOver tells us if the game is over or not
      @return if the game is over or not
   */
   public boolean gameOver()
   {
      return fleet.gameOver();
   }

}