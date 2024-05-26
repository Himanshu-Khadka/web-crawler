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


