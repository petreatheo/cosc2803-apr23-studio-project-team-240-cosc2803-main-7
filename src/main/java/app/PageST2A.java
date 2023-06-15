package app;








import java.util.ArrayList;












import io.javalin.http.Context;
import io.javalin.http.Handler;




import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Year;








public class PageST2A implements Handler {








// URL of this page relative to http://localhost:7001/
public static final String URL = "/page2A.html";








@Override
public void handle(Context context) throws Exception {
  // Create a simple HTML webpage in a String
         String html = "<html>";








  // Add some Head information
     html = html + "<head>" +
       "<title>Country + World Population</title>";




       html = html + """
         <style>
           td, th {
               border: 1px solid #b5b5b5;
               text-align: left;
               padding: 8px;
              }
             
              tr:nth-child(even) {
               background-color: white;
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
                 background: grey;
               }


               td, th {
                 padding: 5px 8px;
               }
              
                  
              
               </style>
               """;
          




        html = html + "<link rel='stylesheet' type='text/css' href='common3.css' />";
         html = html + "<link rel='stylesheet' type='text/css' href='form.css' />";
      html = html + "</head>";








  // Add the body
     html = html + "<body>";






  // Add the topnav
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
              <h1>COUNTRY POPULATION</h1>
          </div>
               """;




  // Add Div for page Content
 html = html + "<div class='content'>";








html = html + "<form>";
          html = html + "<label for='startYear'>Select a start year:   </label>";
html = html + "<select name='startYear' id='startYear'>";
html = html + "<option value=''>-- Select a year --</option>"; // Add default blank option
for (int year = 1960; year <= 2013; year++) {
  html = html + "<option value='" + year + "'>" + year + "</option>";
}
html = html + "</select>";




html = html + "<label for='endYear'>Select an end year:   </label>";
html = html + "<select name='endYear' id='endYear'>";
html = html + "<option value=''>-- Select a year --</option>"; // Add default blank option
for (int year = 1960; year <= 2013; year++) {
  html = html + "<option value='" + year + "'>" + year + "</option>";
}
html = html + "</select>";




html = html + "<label for='country'>Select A Country:   </label>";
html = html + "<select name='country' id='country'>";
html = html + "<option value=''>-- Select a country --</option>";












// List of countries
          String[] countries = {
              "Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "Antigua and Barbuda",
  "Argentina", "Armenia", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain",
  "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bhutan", "Bolivia",
  "Bosnia and Herzegovina", "Botswana", "Brazil", "Brunei", "Bulgaria", "Burkina Faso",
  "Burundi", "Cabo Verde", "Cambodia", "Cameroon", "Canada", "Central African Republic",
  "Chad", "Chile", "China", "Colombia", "Comoros", "Congo", "Costa Rica", "Croatia",
  "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic",
  "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia",
  "Eswatini", "Ethiopia", "Fiji", "Finland", "France", "Gabon", "Gambia", "Georgia", "Germany",
  "Ghana", "Greece", "Grenada", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti",
  "Honduras", "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland", "Israel",
  "Italy", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Korea, North",
  "Korea, South", "Kosovo", "Kuwait", "Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho",
  "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg", "Madagascar", "Malawi",
  "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Mauritania", "Mauritius",
  "Mexico", "Micronesia", "Moldova", "Monaco", "Mongolia", "Montenegro", "Morocco",
  "Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal", "Netherlands", "New Zealand",
  "Nicaragua", "Niger", "Nigeria", "North Macedonia", "Norway", "Oman", "Pakistan", "Palau",
  "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Poland", "Portugal",
  "Qatar", "Romania", "Russia", "Rwanda", "Saint Kitts and Nevis", "Saint Lucia",
  "Saint Vincent and the Grenadines", "Samoa", "San Marino", "Sao Tome and Principe",
  "Saudi Arabia", "Senegal", "Serbia", "Seychelles", "Sierra Leone", "Singapore", "Slovakia",
  "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Sudan", "Spain", "Sri Lanka",
  "Sudan", "Suriname", "Sweden", "Switzerland", "Syria", "Taiwan", "Tajikistan", "Tanzania",
  "Thailand", "Togo", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan",
  "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States",
  "Uruguay", "Uzbekistan", "Vanuatu", "Vatican City", "Venezuela", "Vietnam", "Yemen",
  "Zambia", "Zimbabwe"
          };
        
          for (String country : countries) {
              html = html + "<option value='" + country + "'>" + country + "</option>";
          }
        
          html = html + "</select>";
          html = html + "<input type='submit' value='Show Data'>";
          html = html + "</form>";
        
          String startYear = context.queryParam("startYear");
          String endYear = context.queryParam("endYear");
          String country = context.queryParam("country");
          if (startYear != null && endYear != null && country != null) {
              int start = Integer.parseInt(startYear);
              int end = Integer.parseInt(endYear);
              JDBCConnection jdbcConnection = new JDBCConnection();




ArrayList<countrydatapopulation1> TempcountryDatapopulation= jdbcConnection.getTempcountryDatapopulation(start, end, country);
// Display the temperature data
   




if (TempcountryDatapopulation.isEmpty()) {
  html += "<p>No data available.</p>";
} else {
  boolean headerAdded = false; // Flag to track if the header has been added




  for (countrydatapopulation1 data : TempcountryDatapopulation) {
      if (!headerAdded) {
          html += "<h1>" + data.getCountry()  + "'s Population and Average Temperature </h1>";
          headerAdded = true; // Set the flag to true after adding the header
      }
    
      html += "<br>";
      html += "<br>";
      html += "<div class='table-container'>";
      html += "<table>";
      html += "<thead>";
html += "<tr>";
html += "<th>Year</th>";
html += "<th>Country</th>";
html += "<th>Average Temperature</th>";
html += "<th>Population</th>";
html += "</tr>";
html += "</thead>";








      html += "<tr>";
      html += "<td>" + data.getYearValue1() + "</td>";
      html += "<td>" + data.getCountry() + "</td>";
      html += "<td>" + data.getTemperature() + "</td>";
      html += "<td>" + data.getPopulation() + "</td>";
      html += "</tr>";
      html += "</table>";
html += "</div>";
    
  }
}






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
}
