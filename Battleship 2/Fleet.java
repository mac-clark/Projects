/**
   Fleet class is composed of ship objects
*/

public class Fleet
{

   /** battleship */
   private Ship battleShip;
   private int batCount = 0;
   /** cruiser */
   private Ship cruiser;
   private int cruCount = 0;
   /** sub */
   private Ship sub;
   private int subCount = 0;
   /** destroyer */
   private Ship destroyer;
   private int desCount = 0;
   /** carrier */
   private Ship aircraftCarrier;
   private int airCount = 0;
   private int total = 0;
   
   /**
      constructor initializes ships
   */
   public Fleet()
   {
      destroyer = new Destroyer();
      sub = new Sub();
      aircraftCarrier = new AircraftCarrier();
      cruiser = new Cruiser();
      battleShip = new Battleship();
   }
   
   /**
      updateFleet updates when a move is made and returns
      wether the ship has been sunk or not
      @param st is the shiptype
      @return status of the ship
   */
   public boolean updateFleet(ShipType st)
   {
      String ship = st.toString();
      CellStatus status;
      if (ship.compareTo("ST_BATTLESHIP") == 0)
      {
         batCount++;
         status = CellStatus.BATTLESHIP_HIT;
         if (batCount == 4)
         {
            total++;
            status = CellStatus.BATTLESHIP_SUNK;
            return true;
         }
      }
      if (ship.compareTo("ST_CRUISER") == 0)
      {
         cruCount++;
         status = CellStatus.CRUISER_HIT;
         if (cruCount == 3)
         {
            total++;
            status = CellStatus.CRUISER_SUNK;
            return true;
         }
      }
      if (ship.compareTo("ST_DESTROYER") == 0)
      {
         desCount++;
         status = CellStatus.DESTROYER_HIT;
         if (desCount == 2)
         {
            total++;
            status = CellStatus.DESTROYER_SUNK;
            return true;
         }
      }
      if (ship.compareTo("ST_SUB") == 0)
      {
         subCount++;
         status = CellStatus.SUB_HIT;
         if (subCount == 3)
         {
            total++;
            status = CellStatus.SUB_SUNK;
            return true;
         }
      }
      if (ship.compareTo("ST_AIRCRAFT_CARRIER") == 0)
      {
         airCount++;
         status = CellStatus.AIRCRAFT_CARRIER_HIT;
         if (airCount == 5)
         {
            total++;
            status = CellStatus.AIRCRAFT_CARRIER_SUNK;
            return true;
         }
      }
      return false;
   }
   
   /**
      gameOver indicates wether the game is over or not
      @return true or false
   */
   public boolean gameOver()
   {
      if (total == 5)
         return true;
      else
         return false;
   }

}