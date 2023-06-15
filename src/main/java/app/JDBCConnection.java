package app;


import java.util.ArrayList;
import java.util.Locale;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;


/**
* Class for Managing the JDBC Connection to a SQLLite Database.
* Allows SQL queries to be used with the SQLLite Databse in Java.
*
* ...
*/
public class JDBCConnection {


   // Name of database file (contained in database folder)
   public static final String DATABASE = "jdbc:sqlite:database/ctg.db";
   public static final String DATABASE2 = "jdbc:sqlite:database/climate_database.db";
   // public static final String DATABASE = "jdbc:sqlite:database/climate.db";


   /**
    * This creates a JDBC Object so we can keep talking to the database
    */
   public JDBCConnection() {
       System.out.println("Created JDBC Connection Object");
   }


   /**
    * Get all of the LGAs in the database.
    * @return
    *    Returns an ArrayList of LGA objects
    */
   public ArrayList<countrydatapopulation1> getTempcountryDatapopulation(int start, int end, String country) {
   ArrayList<countrydatapopulation1> TempcountryDatapopulation = new ArrayList<>();


       // Setup the variable for the JDBC connection
       Connection connection = null;
          Statement statement = null;


       try {
           // Connect to JDBC database
           connection = DriverManager.getConnection(JDBCConnection.DATABASE2);


    statement = connection.createStatement();
           statement.setQueryTimeout(30);


           String query = "SELECT Country, Year, Population, AverageTemp " +
                   "FROM country_population " +
                   "WHERE Year >= '" + start + "' AND Year <= '" + end + "' AND Country = '" + country + "';";


           // Get Result
               ResultSet resultSet = statement.executeQuery(query);


           // Process all of the results
              while (resultSet.next()) {
   float averageTemp = resultSet.getFloat("AverageTemp");
   String Country = resultSet.getString("Country");
   int year = resultSet.getInt("Year");
         String population = resultSet.getString("Population");




   DecimalFormat temperatureFormat = new DecimalFormat("#0.00");
   String formattedAverageTemp = temperatureFormat.format(averageTemp);


 


   countrydatapopulation1 data = new countrydatapopulation1(country, year, year, population, formattedAverageTemp);


   TempcountryDatapopulation.add(data);
}






           // Close the statement because we are done with it
           statement.close();
       } catch (SQLException e) {
           // If there is an error, let's just print the error
           System.err.println(e.getMessage());
       } finally {
           // Safety code to cleanup
           try {
               if (connection != null) {
                   connection.close();
               }
           } catch (SQLException e) {
               // connection close failed.
               System.err.println(e.getMessage());
           }
       }


       // Finally, we return all of the country populations
        return TempcountryDatapopulation;
   }


   // TODO: Add your required methods here
}


