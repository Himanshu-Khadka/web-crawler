/**
 * A simple web crawler that extracts and prints URLs from a given starting point.
 *
 * @author Himanshu Khadka
 * @version 1.0
 *
 * This program prompts the user to enter a URL, and then crawls up to 100 URLs
 * starting from the given URL. It prints each URL it visits and extracts additional
 * URLs from the HTML content of each visited page.
 *
 * Classes:
 * - webcrawler: Contains the main method, crawl method, and getSubString method.
 *
 * Methods:
 * - public static void main(String[] args): Prompts the user for a URL and starts the crawling process.
 * - public static void crawl(String URLString): Manages the crawling process, tracks pending and visited URLs.
 * - public static ArrayList<String> getSubString(String URLString): Extracts and returns a list of URLs found in the HTML content.
 *
 * Usage:
 * 1. Run the program.
 * 2. Enter a starting URL when prompted.
 * 3. The program will print the URLs it crawls and the URLs found on each page.
 *
 * Example:
 * Enter the URL:
 * https://example.com
 * Crawl: https://example.com
 * https://example.com/page1
 * https://example.com/page2
 * ...
 *
 * Notes:
 * - This program uses simple string matching to find URLs within the HTML content.
 * - It is intended as a basic demonstration and may require enhancements for robust real-world use.
 */

package web.crawler;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class webcrawler {

    public static void main(String[] args) {
        //Scanner object
        Scanner input = new Scanner(System.in);
        //Prompting for url
        System.out.println("Enter the URL : ");
        String url = input.nextLine();
        //Calling crawl class
        crawl(url);
    }

    //Crawl method
    public static void crawl(String URLString){
        //Array list to hold URL String
        ArrayList<String> ListOfPendingURLs = new ArrayList<>();
        ArrayList<String> ListOfVisitedURLs = new ArrayList<>();

        //Adding Starting URLs
        ListOfPendingURLs.add(URLString);

        //Only crawling upto 100 url
        while(!ListOfPendingURLs.isEmpty() && ListOfVisitedURLs.size() <= 100){
            String urlString = ListOfPendingURLs.remove(0);

            if (!ListOfVisitedURLs.contains(urlString)){
                ListOfVisitedURLs.add(urlString);

                System.out.println("Crawl: " + urlString);

                for (String s: getSubString(urlString)){
                    System.out.println(s);
                }
            }
        }



    }

    public static ArrayList<String> getSubString(String URLstring){
        ArrayList<String> list = new ArrayList<>();

        try{
            URL url = new URL(URLstring);
            Scanner reader = new Scanner(url.openStream());
            int current = 0;
            while (reader.hasNext()){
                String line = reader.nextLine();
                //Index of URL in reference to current reference
                current = line.indexOf("http",current);

                while (current>0){
                    int endIndex = line.indexOf("\"",current);

                    //Ensures correct URL is found
                    if (endIndex>0){
                        //Extract URL
                        list.add(line.substring(current,endIndex));
                        //Search for the Next URL from the End index
                        current = line.indexOf("http",endIndex);
                    }else{
                        //Sets current to -1 if URL is not found
                        current = -1;
                    }
                }
            }

        }catch (MalformedURLException ex){
            System.out.println("URL Error !!!!!");
            ex.printStackTrace();
        }catch (IOException iex){
            System.out.println("Open Stream Error !!!");
            iex.printStackTrace();
        }
        return list;
    }
}


