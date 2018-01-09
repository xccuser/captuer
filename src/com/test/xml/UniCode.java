package com.test.xml;

import java.io.UnsupportedEncodingException;

public class UniCode {

	static char c='汉';
	public static void main(String args[]) throws UnsupportedEncodingException {

		//转换为bit	
		
		toString("a","UTF-8");
		toString("中","UTF-8");
		toString("a","UTF-16");
		toString("中","UTF-16");
	/*	System.out.println((char)65279);
		//使用utf-8,转换为utf-16，不是unicode编码。
		System.out.println("a".getBytes("utf-8").length+"asdada");
		String str=new String("中".getBytes("Unicode"),"UTF-16");
		System.out.println(str.getBytes().length);
		System.out.println((int)c);
		toBinary((byte)c);
		System.out.println();
		
		
		System.out.println("中".getBytes("utf-16").length);*/
	}

	public static void toBinary(byte b){
		for(int i=7;i>=0;--i){
			if((b & 1<<i)!=0){
				System.out.print("1");
			}else{
				System.out.print("0");
			}
		}
		System.out.print(" ");
	}

	public static void toString(String str,String btye) throws UnsupportedEncodingException{
		System.out.print("\""+str+"\"的"+btye+"编码"+":");
	//	String str1=new String(str.getBytes(),btye);
		//String str1=new String(btye.getBytes(),str);
		for(byte b:str.getBytes(btye))
			toBinary(b);
		System.out.println();
	}

}