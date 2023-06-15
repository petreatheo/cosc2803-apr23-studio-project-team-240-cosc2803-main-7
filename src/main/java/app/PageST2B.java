package app;

import java.util.ArrayList;


import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Example Index HTML class using Javalin
 * <p>
 * Generate a static HTML page using Javalin
 * by writing the raw HTML into a Java String object
 *
 * @author Timothy Wiley, 2023. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 */
public class PageST2B implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/page2B.html";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Head information
        html = html + "<head>" + 
               "<title>Subtask 2.2</title>";
        html = html + """
            <style>
            td, th {
                border: 1px solid #b5b5b5;
                text-align: left;
                padding: 8px;
               }
               
               tr:nth-child(even) {
                background-color: #b5b5b5;
               }
               table {
                  font-family: arial, sans-serif;
                  border-collapse: collapse;
                  width: 100%;
               }
               .table-container {
                  max-height: 400px;
                  border: 1px solid red;
                  overflow: auto;
                  width: 50%;
                }

                th {
                  position: sticky;
                  top: 0;
                  background: white;
                }

                td, th {
                  padding: 4px 8px;
                }
                
                    
                
                </style>
                """;
        // Add some CSS (external file)
        html = html + "<link rel='stylesheet' type='text/css' href='form.css' />";
        html = html + "<link rel='stylesheet' type='text/css' href='common.css' />";
        html = html + "</head>";

        // Add the body
        html = html + "<body>";

        // Add the topnav
        // This uses a Java v15+ Text Block
        html = html + """
            <div class='topnav'>
            <a href='/'>Homepage</a>
            <a href='mission.html'>Our Mission</a>
            <a href='page2A.html'>Country + World Population</a>
            <a href='page2B.html'>Sub Task 2.B</a>
            <a href='page3A.html'>Change In Temperature</a>
            <a href='page3B.html'>Sub Task 3.B</a>
            </div>
        """;

        // Add header content block
        html = html + """
            <div class='header'>
                <h1>Subtask 2.B</h1>
            </div>
        """;

        // Add Div for page Content
        html = html + "<div class='content'>";

        // Add HTML for the page content
        html = html + "<form class='form-inline' action='/page2B.html' method='post'>";



        html = html + "   <div class='form-group'>";
        html = html + "      <label for='startyear_textbox'>Start Year: </label>";
        html = html + "      <input class='form-control' id='startyear_textbox' name='startyear_textbox'>";
        html = html + "   </div>";
        html = html + "   <div class='form-group'>";
        html = html + "      <label for='endyear_textbox'>End Year: </label>";
        html = html + "      <input class='form-control' id='endyear_textbox' name='endyear_textbox'>";
        html = html + "   </div>";
        html = html + "   <br><br>";
        html = html + "   <div class='form-group'>";
        html = html + "      <label for='country_textbox'>Country: </label>";
        html = html + "      <input class='form-control' id='country_textbox' name='country_textbox'>";
        html = html + "   </div>";
        html = html + " <div class='form-group'>";  
        html = html + " <label class='radio-inline'>States</label>";
        html = html + " <input type='radio' value='state' name='region_radio' checked>";
        html = html + " <label class='radio-inline'>Cities</label>";
        html = html + " <input type='radio' value='city' name='region_radio'>";
        html = html + " </div>";
        html = html + "   <button type='submit' class='btn btn-primary'>Submit</button>";
        html = html + "</form>";
        //html = html + outputData("1750", "1800");

        String startyear_textbox = context.formParam("startyear_textbox");
        String endyear_textbox = context.formParam("endyear_textbox");
        String country_textbox = context.formParam("country_textbox");
        String region_radio = context.formParam("region_radio");
        if ((endyear_textbox == null || endyear_textbox == "") && (startyear_textbox == null || startyear_textbox == "")) {
            // If NULL, nothing to show, therefore we make some "no results" HTML
            html = html + "<h2><i>No Results to show for textbox</i></h2>";
        } else {
            // If NOT NULL, then lookup the movie by type!
            html = html + outputData(startyear_textbox, endyear_textbox, region_radio, country_textbox);
        }

        // Close Content div
        html = html + "</div>";

        // Footer
        html = html + """
            <div class='footer'>
                <p>COSC2803 - Studio Project Starter Code (Apr23)</p>
            </div>
        """;

        // Finish the HTML webpage
        html = html + "</body>" + "</html>";
        

        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }

    public String outputData(String start, String end, String region, String country) {
        NumberFormat formatter = new DecimalFormat("+#0.000;-#0.000");
        String html = "";
        html = html + "<h2>Temperature change in " + country + " from " + start + " to " + end + " sorted by " + region + "</h2>";

        // Look up movies from JDBC
        ArrayList<City> CityAvg = getCityAvg(start, end, region, country);
        
        // Add HTML for the movies list
        html = html + "<div class='table-container'> <table>";
        html = html + """
            <thead>
          <tr>
            <th>City/State</th>
            <th>Average</th>
            <th>Min</th>
            <th>Max</th>
          </tr>
          </thead>
          <tbody>
                """;
        for (City City : CityAvg) {
            html = html + "<tr>";
            html = html + "<td>" + City.getName() + "</td>";
            html = html + "<td>" + formatter.format(City.getAvg()) + "</td>";
            html = html + "<td>" + formatter.format(City.getMin()) + "</td>";
            html = html + "<td>" + formatter.format(City.getMax()) + "</td>";
            html = html + "</tr>";
        }
        html = html + "</tbody></table></div>";

        return html;
    }

    /**
     * Get all the movies in the database by a given type.
     * Note this takes a string of the type as an argument!
     * This has been implemented for you as an example.
     @return
     * HINT: you can use this to find all of the horror movies!
     */
    public ArrayList<City> getCityAvg(String start, String end, String region, String country) {
        ArrayList<City> city = new ArrayList<City>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(JDBCConnection.DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = " SELECT " + region + ", Sum(CASE year WHEN '" + start +"' THEN AverageTemperature WHEN '" + end + "' THEN -AverageTemperature END) AS Average, Sum(CASE year WHEN '" + start +"' THEN MaximumTemperature WHEN '" + end +"' THEN -MaximumTemperature END) AS Max, Sum(CASE year WHEN '" + start +"' THEN MinimumTemperature WHEN '" + end +"' THEN -MinimumTemperature END) AS Min FROM '" + region + "' WHERE country LIKE '" + country +"' GROUP  BY " + region +" ORDER BY Average DESC; ";
    
            
            // Get Result
            ResultSet results = statement.executeQuery(query);
            
            // Process all of the results
            while (results.next()) {
                String name = results.getString(region);
                String avg1 = results.getString("Average");
                String min1 = results.getString("Min");
                String max1 = results.getString("Max");
                double avg = Double.parseDouble(avg1);
                double min = Double.parseDouble(min1);
                double max = Double.parseDouble(max1);
                city.add(new City(name, avg, min, max));
            }
            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
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

        // Finally we return all of the movies
        return city;

    }
}
