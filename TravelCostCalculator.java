import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class TravelCostCalculator {
    static Map<String, Double> accomadationCost = new HashMap<>();
    static Map<String, Double> exchangeCost = new HashMap<>();
    static Map<String, Double> fuelCost = new HashMap<>();

    static void calculateAccomadationCost(String file) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String i; 

        while ((i = reader.readLine()) != null) {

            String[] dayCount = i.split(",");
            calculateAccomadationCost.put(dayCount[0].toUpperCase(), Double.parseDouble(dayCount[1]));

        }
    }

    static void calculateExchangeCost(String file) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String i;

        while ((i = reader.readLine()) != null) {

            String[] dayCount = i.split(",");
            calculateExchangeCost.put(dayCount[0].toUpperCase(), Double.parseDouble(dayCount[1]));

        }
    }

    static void calculateFuelCost(String file) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String i;

        while ((i = reader.readLine()) != null) {

            String[] dayCount = i.split(",");
            calculateFuelCost.put(dayCount[0].toUpperCase(), Double.parseDouble(dayCount[1]));

        }
    }

    public static void main(String[] args) {
        try {
            calculateAccomadationCost("data/hotel_rates.csv");
            calculateExchangeCost("data/exchange_rates.csv");
            calculateFuelCost("data/flight_costs.csv");

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            
            System.out.print("Enter your destination: ");
            String destination = reader.readLine().toUpperCase();

            System.out.print("Enter your stay duration in days: ");
            int stayDuration = Integer.parseInt(reader.readLine());

            System.out.print("Select your currency for final price estimation
            (" + String.join(", ", availableCurrencies) + "): ");
            String selectedCurrency = reader.readLine();
            
            
            double flightCost = fuelCost.getOrDefault(destination, 0.0);
            double hotelCost = accomadationCost.getOrDefault(destination, 0.0);

            hotelCost *= stayDuration;
            double totalCostUsd = flightCost + hotelCost;

            String[] availableCurrencies = exchangeCost.keySet().toArray(new String[0]);
          
            double finalPriceLocalCurrency = totalCostUsd * b.get(selectedCurrency);


            System.out.printf("Flight cost: USD %.2f\n", flightCost);
            System.out.printf("Hotel cost (%d days): USD %.2f\n", stayDuration, hotelCost);
            System.out.printf("Total: USD %.2f\n", totalCostUsd);
            System.out.printf("Total in %s: %.2f\n", selectedCurrency, finalPriceLocalCurrency);

        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
