/**
   enumerated ShipType tells the appropriate ship type
   that it has been hit if it is hit
*/

public enum ShipType
{

   ST_AIRCRAFT_CARRIER{
        @Override
        public String toString(){ return "ST_AIRCRAFT_CARRIER"; }
    },
   ST_BATTLESHIP{
        @Override
        public String toString(){ return "ST_BATTLESHIP"; }
    },
   ST_CRUISER{
        @Override
        public String toString(){ return "ST_CRUISER"; }
    },
   ST_DESTROYER{
        @Override
        public String toString(){ return "ST_DESTROYER"; }
    },
   ST_SUB{
        @Override
        public String toString(){ return "ST_SUB"; }
    }

}