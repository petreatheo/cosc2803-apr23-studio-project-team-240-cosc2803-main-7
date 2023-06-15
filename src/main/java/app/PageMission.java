package app;

import java.util.ArrayList;

import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Example Index HTML class using Javalin
 * <p>
 * Generate a static HTML page using Javalin
 * by writing the raw HTML into a Java String object
 *
 * @author Timothy Wiley, 2023. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 */
public class PageMission implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/mission.html";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Head information
        html = html + "<head>" + 
               "<title>Our Mission</title>";

        // Add some CSS (external file)
        html = html + "<link rel='stylesheet' type='text/css' href='common2.css' />";
        html = html + "<meta charset='utf-8'>";
        html = html + "<meta name='viewport' content='width=device-width, initial-scale=1'>";
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
                <h1>Our Mission</h1>
            </div>
        """;

        // Add Div for page Content
        html = html + "<div class='content'>";

        // This example uses JDBC to lookup the LGAs
        JDBCConnection jdbc = new JDBCConnection();

        // Next we will ask this *class* for the LGAs
       

        // Add HTML for the LGA list

        html = html + """

<div class='row'>
  <div class='column middle'>Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test </div>
  <div class='column side' style='background-color:#ccc;'><Strong>Team Members:
  <br><br>
  <div class='mini'>Petrea Theodorakopoulos<br><br><br>s4008914</div>
  <br>
  <div class='mini'>Thomas Williams<br><br><br>s4005637</div></div></div></Strong>
</div>

<br>
    
    """;

        // Close Content div
        html = html + "</div>";

        html = html + "<div class='row'>";
ArrayList<String> Attributes1 = getPersonaInfo("1", "Attributes");
ArrayList<String> Needs1 = getPersonaInfo("1", "N&G");
ArrayList<String> Skills1 = getPersonaInfo("1", "Skills");
ArrayList<String> Attributes2 = getPersonaInfo("2", "Attributes");
ArrayList<String> Needs2 = getPersonaInfo("2", "N&G");
ArrayList<String> Skills2 = getPersonaInfo("2", "Skills");
ArrayList<String> Attributes3 = getPersonaInfo("3", "Attributes");
ArrayList<String> Needs3 = getPersonaInfo("3", "N&G");
ArrayList<String> Skills3 = getPersonaInfo("3", "Skills");
ArrayList<String> Quotes = getPersonaInfo("1' OR personaID='2' OR personaID='3", "Quote");
html = html + "<div class='persona' style='background-color:#ccc;'><Strong>Persona 1:</Strong><br><br>";
String Quote = Quotes.get(0);
html = html + Quote + "<br>"; 
  html = html + "<br>Attributes/Background:<ul>";
  
  
  for (String entry : Attributes1) {
            html = html + "<li>" + entry + "</li>";
        }

  html = html + """
    </ul>  
Needs and Goals:
<ul>
    
    """;

    for (String entry : Needs1) {
      html = html + "<li>" + entry + "</li>";
  }

html = html + """
  </ul> 
Skill & experience levels:
<ul>
    """;
  
for (String entry : Skills1) {
  html = html + "<li>" + entry + "</li>";
}

html = html + """
  </ul> 
  </div>
  <div class='divider'></div>
      """;
      html = html + "<div class='persona' style='background-color:#ccc;'><Strong>Persona 2:</Strong><br><br>";
      Quote = Quotes.get(1);
      html = html + Quote + "<br>"; 
      html = html + "<br>Attributes/Background:<ul>";
  
  
  for (String entry : Attributes2) {
            html = html + "<li>" + entry + "</li>";
        }

  html = html + """
    </ul>  
Needs and Goals:
<ul>
    
    """;

    for (String entry : Needs2) {
      html = html + "<li>" + entry + "</li>";
  }

html = html + """
  </ul> 
Skill & experience levels:
<ul>
    """;
  
for (String entry : Skills2) {
  html = html + "<li>" + entry + "</li>";
}

html = html + """
  </ul> 
  </div>
  <div class='divider'></div>      """;
  html = html + "<div class='persona' style='background-color:#ccc;'><Strong>Persona 3:</Strong><br><br>";
  Quote = Quotes.get(2);
  html = html + Quote + "<br>"; 
  html = html + "<br>Attributes/Background:<ul>";
  
  
  for (String entry : Attributes3) {
            html = html + "<li>" + entry + "</li>";
        }

  html = html + """
    </ul>  
Needs and Goals:
<ul>
    
    """;

    for (String entry : Needs3) {
      html = html + "<li>" + entry + "</li>";
  }

html = html + """
  </ul> 
Skill & experience levels:
<ul>
    """;
  
for (String entry : Skills3) {
  html = html + "<li>" + entry + "</li>";
}

html = html + """
  </ul> 
  </div>
</div>
<br>

        """;

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

    /**
     * Get all of the Movie Titles in the database
     * @return
     *    Returns an ArrayList of String with ONLY the movie titles
     */
    public ArrayList<String> getPersonaInfo(String ID, String Column) {
        // Create the ArrayList to return - of Strings for the movie titles
        ArrayList<String> movies = new ArrayList<String>();

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(JDBCConnection.DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
//test
            // The Query
            String query = "SELECT * FROM personas WHERE personaID='" + ID + "';";
            
            
            // Get Result
            ResultSet results = statement.executeQuery(query);

            // Process all of the results
            // The "results" variable is similar to an array
            // We can iterate through all of the database query results
            while (results.next()) {
                String entry;
                // We can lookup a column of the a single record in the
                // result using the column name
                // BUT, we must be careful of the column type!
                String movieName     = results.getString(Column);
                int j = 0;
                for (int i = 0; i < movieName.length(); i++) {
                    char letter = movieName.charAt(i);
                    if (letter == '|') {
                    entry = movieName.substring(j, i);
                    j = i + 1;
                    movies.add(entry);
                    }
                } 
                entry = movieName.substring(j, movieName.length());
                movies.add(entry);


                // Store the movieName in the ArrayList to return
                
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

        // Finally we return all of the movie titles
        return movies;
    }

}
