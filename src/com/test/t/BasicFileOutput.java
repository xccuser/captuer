package com.test.t;


import java.io.*;
import java.net.URL;

public class BasicFileOutput {
  static String file = "exml.txt";
  public static void main(String[] args)
  throws IOException {
	  URL http=new URL("http://www.baidu.com");
		BufferedReader in=new BufferedReader(new InputStreamReader(http.openStream()));
    /*BufferedReader in = new BufferedReader(
      new StringReader(
        BufferedInputFile.read("xml")));*/
    PrintWriter out = new PrintWriter(
      new BufferedWriter(new FileWriter(file)));
    int lineCount = 1;
    String s;
    while((s = in.readLine()) != null )
      out.println(lineCount++ + ": " + s);
    out.close();
    // Show the stored file:
   // System.out.println(BufferedInputFile.read(file));
  }
} /* (Execute to see output) *///:~
