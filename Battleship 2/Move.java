/**
   Move class stores data about user moves
*/

public class Move
{
   /** row of the inteded move */
   private int row;
   /** column of the inteded move */
   private int col;
   
   /**
      constructor that takes two integer coordinates
      @param row is the row
      @param col is the col
   */
   public Move(int row, int col)
   {
      this.row = row;
      this.col = col;
   }
   
   /**
      constructor that takes a string coordinate
      @param row is the row
      @param col is the col
      @param space is the string to be split
      @param r takes the first character and puts it to upper case
      @param asciiR takes the ascii integer value of r
   */
   public Move(String space)
   {
      char r = space.charAt(0);
      r = Character.toUpperCase(r);
      int asciiR = r;
      row = asciiR - 65;
      col = Integer.parseInt(space.substring(1)) - 1;
   }
   
   /**
      row method returns the row
      @return row
   */
   public int row()
   {
      return row;
   }
   
   /**
      col method returns the row
      @return col
   */
   public int col()
   {
      return col;
   }
   
   /**
      toString indicates a readable output with character and int
      @param r is going from ascii int back to character
      @param rowOut makes r upper case
   */
   @Override
   public String toString()
   {
      char r = (char) (row + 65);
      char rowOut = Character.toUpperCase(r);
      return String.format("" + rowOut + (col+1));
   }

}