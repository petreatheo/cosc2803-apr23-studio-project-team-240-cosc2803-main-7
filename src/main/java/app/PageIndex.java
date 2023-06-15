
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
public class PageIndex implements Handler {


   // URL of this page relative to http://localhost:7001/
   public static final String URL = "/";


   @Override
   public void handle(Context context) throws Exception {
       // Create a simple HTML webpage in a String
       String html = "<html>";


       // Add some Header information
       html = html + "<head>" +
              "<title>Smart Temp</title>";


       // Add some CSS (external file)
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
               <a href='page3B.html'>Sources</a>
           </div>
    
    <nav>
      <div class="navbar">
        <div class="container nav-container">
            <input class="checkbox" type="checkbox" name="" id="" />
            <div class="hamburger-lines">
              <span class="line line1"></span>
              <span class="line line2"></span>
              <span class="line line3"></span>
            </div>  
          <div class="logo">
           
          </div>
          <div class="menu-items">
            <li>  <a href='/'>Homepage</a></li>
          <li>  <a href='mission.html'>Our Mission</a></li>
            <li>  <a href='page2A.html'>Country + World Population</a></li>
            <li> <a href='page2B.html'>Sub Task 2.B</a></li>
            <li>  <a href='page3A.html'>Change In Temperature</a></li>
             <li>   <a href='page3B.html'>Sub Task 3.B</a></li>
           <li>   <a href='page3B.html'>Sources</a></li>
          
          </div>
        </div>
      </div>
    </nav>
  </body>
               """;


       // Add header content block
       html = html + """
           <div class='header'>
               <h1>
                   <img src='sunlogo2.jpg' class='top-image' alt='Smart Temp Logo' height='100'>
                   Smart Temp
               </h1>
           </div>
       """;


        // Add Div for page Content
        html = html + "<div class='content'>";

        // Add HTML for the page content
       
        
            html = html + """
                <div class='video-container'>
                <h1></h1>
                <h1> Global Temperature Anomalies from 1880 to 2021 From NASA</h1>
                <video controls>
                <video src="TempChange.mp4" autoplay controls></video>
              <source src="TempChange.mp4" type="video/mp4">
              Your browser does not support the video tag.
            </video>
            <p> Earth's global average surface temperature in 2020 statistically tied with 2016 as the hottest year on record, 
            continuing a long-term warming trend due to human activities. </p>
            <p> The shift in global surface temperatures is depicted in the animation on the right. 
            Dark blue indicates places that are cooler than usual. 
            Overheated parts are shown by dark red. A 5-year running average is used to smooth out short-term changes and enhance 
            the visibility of patterns in this map. </P>
                """;
                html = html + "</div>";

            html = html + """
                    <div class='timeline'>
                    <h1>TIMELINE OF OUR DATA</h1>
                    <ul>
                        <li style="--accent-color:#41516C">
                            <div class="date">1750</div>
                            <div class="title">The Earliest Temperature Recorded In Our Database!</div>
                            <div class="descr">In 1750, the global average temperature was 8.7&#8451;. While the maximum temperature was 15.9&#8451;, and the lowest was 2.77&#8451;!</div>
                        </li>
                        <li style="--accent-color:#FBCA3E">
                        <div class="date">1800</div>
                        <div class="title">The Start Of The 19th Century</div>
                        <div class="descr">In 1800, the global average temperature was 8.5&#8451;. While the maximum temperature was 13.8&#8451;, and the lowest was 2.8&#8451;!</div>
                    </li>
                    <li style="--accent-color:#E24A68">
                        <div class="date">1850</div>
                        <div class="title">1850 Statistics</div>
                        <div class="descr">In 1850, the global average temperature was 7.9&#8451;. In 1850, ocean temperature was first recorded. In 1850, the average temperature in the ocean was 14.9&#8451;!</div>
                    </li>
                    </li>
                    <li style="--accent-color:#1B5F8C">
                        <div class="date">1900</div>
                        <div class="title">The Start of the 20th Century</div>
                        <div class="descr">In 1900, the global average temperature was 8.5&#8451;. In 1900, the average temperature in the ocean was 15.1&#8451;!</div>
                    </li>
                    <li style="--accent-color:#4CADAD">
                        <div class="date">1950</div>
                        <div class="title">1950 Statistics</div>
                        <div class="descr">In 1950, the global average temperature was 8.4&#8451;. In 1950, the average temperature in the ocean was 15.1&#8451;!</div>
                    </li>
                    <li style="--accent-color:#dab849">
                        <div class="date">1960</div>
                        <div class="title">The First Population Data!</div>
                        <div class="descr">In 1960, the global average temperature was 8.6&#8451;. In 1960, the average temperature in the ocean was 15.3&#8451;. In 1960, we have our first population data in our database! The population in 1960 was 3,031,564,839!</div>
                    </li>
                    <li style="--accent-color:#FFABAB">
                        <div class="date">2000</div>
                        <div class="title">The Start of the 21st Century</div>
                        <div class="descr">In 2000, the global average temperature was 9.2&#8451;. In 2000, the average temperature in the ocean was 15.6&#8451;. The population in 2000 was 6,144,322,697!</div>
                    </li>
                    <li style="--accent-color:#B28DFF">
                        <div class="date">2013</div>
                        <div class="title">The Last Date In Our Database</div>
                        <div class="descr">In 2013, the global average temperature was 9.6&#8451;. In 2013, the average temperature in the ocean was 15&#8451;. The population in 2013 was 7,229,184,551! 2013 is the last year we have in our database.</div>
                    </li>
                </ul>
            </div>
        """;
      
       
       

// Finish the List HTML
html = html + "</ul>";


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