package app;

/**
 * Class represeting a LGA from the Studio Project database
 * In the template, this only uses the code and name for 2016
 *
 * @author Timothy Wiley, 2023. email: timothy.wiley@rmit.edu.au
 */
public class City {
   // LGA Code
   private double avg;

   private double min;

   private double max;

   // LGA Name
   private String name;

   // LGA Year
   private int year;

   /**
    * Create an LGA and set the fields
    */
   public City(String name, double avg, double min, double max) {
      this.avg = avg;
      this.name = name;
      this.max = max;
      this.min = min;
   }

   public double getAvg() {
      return avg;
   }

   public double getMax() {
      return max;
   }

   public double getMin() {
      return min;
   }

   public String getName() {
      return name;
   }

   public int getYear() {
      return year;
   }
}
