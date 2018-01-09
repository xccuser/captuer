package com.test.t;

import java.awt.BufferCapabilities;
import java.io.*;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class CharAsBuffer {

	public static void main(String args[]) throws IOException{
		File file=new File("w1.jpg");
		FileChannel fc=new FileInputStream(file).getChannel();
		ByteBuffer buff=ByteBuffer.allocate((int) file.length());
		fc.read(buff);
		buff.flip();
		//	CharBuffer bf=((ByteBuffer) buff.rewind()).asCharBuffer();
		/*	System.out.println(System.getProperty("file.encoding"));
		while(buff.hasRemaining()){
			System.out.print(Charset.forName("gb2312").decode(buff));
		}*/
		/*DataOutputStream test_out=new DataOutputStream(new FileOutputStream("te.txt"));
		test_out.writeUTF("徐c");
		test_out.close();*/
		BufferedReader test_read=new BufferedReader(new FileReader("te.txt"));
		int i;
		Byte b;
		String s;
		FileChannel fc1=new FileInputStream("te.txt").getChannel();
		ByteBuffer bf=ByteBuffer.allocate(114);
		fc1.read(bf);
		bf.flip();
		System.out.println(Charset.forName("UTF-8").decode(bf));
		while((i=test_read.read())!=-1){
			System.out.print((char)i);
		}
		//System.out.println((char)23628);
		//fc.read(buff);
		//buff.flip();
		/*	while(buff.hasRemaining()){
			System.out.print((char)buff.get());
		}*/
		//System.out.println(buff);
		//System.out.println(Charset.forName("UTF-8").decode(buff));
	}
}
