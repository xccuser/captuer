package com.test.t;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.security.Permissions;

import javax.imageio.stream.FileImageOutputStream;

import org.omg.Messaging.SyncScopeHelper;

public class test {
	static String url="http://img2.xiazaizhijia.com/walls/20141224/mid_7a235953ae86b92.jpg";
	static String file="ww.txt";
	public static void main(String args[]){
		test();
	}

	public static void test(){
		long len=System.nanoTime();
		try{
			URL http=new URL(url);
			//			BufferedReader in=new BufferedReader(new InputStreamReader(http.openStream()));
			//	DataInputStream in=new DataInputStream(http.openStream());
			//			PrintWriter out=new PrintWriter("w.txt");
			//FileOutputStream out=new FileOutputStream(new File("w3.jpg"));
			//	byte[] byt=new byte[999999999];
			StringBuilder sr=new StringBuilder();
			// String s;
			//			    while((s = in.readLine()) != null )
			//			      out.println("\n"+ s);
			/*String str;
			while((str=rea.readLine())!=null){
				out.println(": " + str);
				//out.println("sada");
				//sr.append(str+"\n");
			}*/
			//	System.out.println(sr);
			/*	 int length;
			while((length=in.read())>0){
				 out.write(byt,0,length);
			}*/
			InputStream in=http.openStream();
			//FileChannel out=new FileOutputStream("mv.jpg").getChannel();
			ByteBuffer buff=ByteBuffer.allocate(in.available());
			byte[] b=new byte[1];
			//			System.out.println(buff.limit()+","+b.length);

			FileChannel fc=new FileOutputStream("meizi.jpg").getChannel();
			//		fc.close();
			//				fc=new FileInputStream("w1.jpg").getChannel();
			//fc.read(buff);
			int len1;
			while((len1=in.read(b))!=-1){
				fc.write(buff.wrap(b));
				buff.clear();
				//out.write(b, 0, len1);
			}
			fc.close();
		//	out.close();



			//	in.read(byt);
			//	in.close();
			//	OutputStream out=new FileOutputStream("test.jpg");
			//out.write(byt);
			//	int len;
			//out.write(byt);
			//	while((len=in.read(byt))!=-1){
			//	out.write(byt);
			//}out.write(byt);
			//out.close();
			//	System.out.println(byt.length+","+len);

			//	BufferedOutputStream t=new BufferedOutputStream(new FileOutputStream("w")); 
			//	t.write(byt);
			//  t.close();
			//FileChannel out=new FileOutputStream("url").getChannel();
			//ByteBuffer buffer=ByteBuffer.allocate(in.available());
			//out.write(buffer.wrap(byt));
			//out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println((System.nanoTime()-len)/1E9);
	}
}
