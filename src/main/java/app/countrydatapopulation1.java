package app;




public class countrydatapopulation1 {
   private String countryName;
   private int yearValue1;
   private int yearValue2;
    private String population;
   private String temperature;


   public countrydatapopulation1(String countryName, int yearValue1, int yearValue2, String population, String temperature) {
       this.countryName = countryName;
       this.yearValue1 = yearValue1;
       this.yearValue2 = yearValue2;
       this.population = population;
       this.temperature = temperature;
   }


   public String getCountry() {
       return countryName;
   }


   public int getYear() {
       return yearValue1;
   }


   public int getYearValue1() {
       return yearValue1;
   }


   public int getYearValue2() {
       return yearValue2;
   }


   public String getPopulation() {
       return population;
   }


   public String getTemperature() {
       return temperature;
   }
}

