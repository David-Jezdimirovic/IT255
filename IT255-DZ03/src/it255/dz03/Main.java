/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it255.dz03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import com.google.gson.Gson;
/**
 *
 * @author Lenovo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
     public static void main(String[] args) {
        new Main();
    }

    public Main() {

        Korisnik k = new Korisnik();
       
       
        k.setFullName("Sima");
        k.setEmail("simasimic@gmail.com");
        k.setPassword("Sima123");
        k.setEmailConfirmed(true);
        k.setEmailConfirmationCode("ASG2T");
      
   
        
        try {
            URL url = new URL("http://89.216.56.107:8080/restfull/rest/users/");
           
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestProperty("Method", "POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            PrintWriter pw = new PrintWriter(conn.getOutputStream());
            pw.print(new Gson().toJson(k));
          
           
            pw.close();
            pw.flush();
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));
            String output;
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }
            conn.disconnect();

   	

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        

       
        try {
            URL url = new URL("http://89.216.56.107:8080/restfull/rest/users/");
         
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
//            conn.setRequestProperty("Method", "GET");
//            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);
                  
                    

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));
            
            String rezultat = "";
            String output;
            
            System.out.println("Odgovor  Servera .... \n");
            while ((output = br.readLine()) != null) {
                
                   rezultat += output; 
                  
               // System.out.println(output + "\n");
            }

            conn.disconnect();
            
            Gson gson  = new Gson();
 Korisnik[] korisnici = gson.fromJson(rezultat, Korisnik[].class);
            for (int i = 0; i < korisnici.length; i++) {
            	System.out.println( 
                                    "Ime: " + korisnici[i].getFullName() + "\n" +
                                    "Email: " + korisnici[i].getEmail() + "\n" +
                                    "Sifra: " + korisnici[i].getPassword() + "\n" + 
                                    "Verifikovan: " + korisnici[i].isEmailConfirmed() + "\n" + 
                                    "Verifikacioni kod: " + korisnici[i].getEmailConfirmationCode()+ "\n"); 
                                   
              }
            
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

