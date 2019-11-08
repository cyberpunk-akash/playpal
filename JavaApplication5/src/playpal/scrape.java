/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playpal;
//package location_distance;
import org.apache.commons.lang3.StringUtils;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
//import javax.servlet;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.concurrent.*;

/**
 *
 * @author MAHE
 */


public class scrape {
static double longi, lati;
    //public static void main (String args[]){
	public static void scraper() {
                print("running...");
		Document document;
		try {
			//Get Document object after parsing the html from given url.
                    document = Jsoup.connect("https://www.bing.com/search?q=my+current+location&form=PRINEN&httpsmsn=1&msnews=1&refig=bdf67719eb104b84a4a9267e79fd54ff&sp=-1&pq=my+current+location&sc=8-19&qs=n&sk=&cvid=bdf67719eb104b84a4a9267e79fd54ff").get();
//                        window.setTimeout(2000);
                    Elements link = document.select("h2");
                    String title = document.location();
                    int i = 0;
                    for(Element ele : link){
                        if(i<6){
                        //print(" maybe "+ ele);
                            String stringValue=ele.toString();
                            if(stringValue.contains("ppois")){
                                String bar = StringUtils.substringBetween(stringValue, "ppois=", "20576104_~&amp");
                                String latitude = StringUtils.substringBefore(bar,"_");
                                String longitude = StringUtils.substringBetween(bar, "_","_");
                                String city2 = StringUtils.substringBetween(bar, "_","%2c");
                                String city = StringUtils.substringAfter(city2,"_");
                                String State = StringUtils.substringBetween(bar, "%20","%");
                                longi = Double.parseDouble(longitude);
                                lati = Double.parseDouble(latitude);
                              //  System.out.println("longi" + longi + " lati " + lati);
                            }
                        }
                    }
		} 
                catch (IOException e) {
                    e.printStackTrace();
		}
		//print("done");
	}

	public static void print(String string) {
		//System.out.println(string);
	}
    
}
//                        print("@@@@@@@@@@@@@@@@@@@@@@@@@");
//                        print("this is it" + stringValue );

                        //print("latitude =" + latitude);
                        //print("longitude =" + longitude);
                       // print("city =" + city);
                       // print("State =" + State );
                        
//                        print("double values" + longi + " , " + lati );
//                        }
//                            i++;}
//                            else{
////                            {print(ele.toString());
//                                break;}
