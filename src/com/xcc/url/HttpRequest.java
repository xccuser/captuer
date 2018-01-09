package com.xcc.url;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.CookieHandler;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class HttpRequest {
	public static  String read="http://kuaijisishu.oss-cn-hangzhou.aliyuncs.com/data/ques/";
	public static String sendGet(String url, String param) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url/* + "?" + param*/;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

			// 建立实际的连接
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			/*for (String key : map.keySet()) {
				System.out.println(key + "--->" + map.get(key));
			}*/
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line+"\n";
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}


	public static String sendPost(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("Accept", " */*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("Content-Type", "application/json");
			//contentType:"application/json"
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			conn.setRequestProperty("Host", "tk.accfun.com");

			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line+"\n";

			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！"+e);
			e.printStackTrace();
		}
		//使用finally块来关闭输出流、输入流
		finally{
			try{
				if(out!=null){
					out.close();
				}
				if(in!=null){
					in.close();
				}
			}
			catch(IOException ex){
				ex.printStackTrace();
			}
		}
		return result;
	}    

	public static void main(String[] args) throws Exception {
		/*System.out.println("{"+ "\"userName\":\"xcc\""+",\"userPassword\":\"1234567\","
				+ "\"phone\":\"13812667231\",\"mac\":\"10:23:13:43\"}");

		String sr=HttpRequest.sendPost("http://localhost:8080/Electrombile/userPassword","{"
				+ "\"userName\":\"xcc\""+",\"userpassword\":\"12345s6271s\",\"phone\":\"13812667134\",\"mac\":\"10:13:13:43\"}");

		System.out.println(sr);*/
		setmodel();
		//test();
		//JsonTest();
	}


	public static void setmodel() throws IOException{
		FileInputStream inp = new FileInputStream("file.txt");
		byte[] b = new byte[1024];
		int length = inp.read(b);
		String s = new String(b,0,length);
		//   System.out.println(s);
		inp.close();
		new Thread(new Runnable() {
			Socket socket=null;
			@Override
			public void run() {
				System.out.println(System.currentTimeMillis());
				String result = "";
				try {
					socket = new Socket("tk.accfun.com", 80);
					OutputStreamWriter streamWriter = new OutputStreamWriter(socket.getOutputStream());  
					BufferedWriter bufferedWriter = new BufferedWriter(streamWriter);  
					bufferedWriter.write(s);
					bufferedWriter.flush();
					BufferedReader	in = new BufferedReader(
							new InputStreamReader(socket.getInputStream()));
					String line;
					while ((line = in.readLine()) != null) {
						result += line+"\n";
					}
					/*JsonParser parser=new JsonParser(); 
						JsonObject object=(JsonObject) parser.parse(result.substring(285));*/
					System.out.println(result.substring(286));
					JsonTest(result.substring(286));
					System.out.println(Thread.currentThread());
					/*JSONObject obj;
					try {
						obj = new JSONObject(result.substring(286,7961));
						System.out.println(obj.get("state"));
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}  */

					//System.out.println(result.substring(285));
					System.out.println(System.currentTimeMillis());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();





		/*

GET http://tk.accfun.com/accCjzc.html?getQuesList&type=test&knowId=cc532c7dfa0213ebdac3782cdc89b0b2&testId=efb9b66c6d8381e4f5b9d595837a5c34 HTTP/1.1
Host: tk.accfun.com
Connection: keep-alive
Cache-Control: max-age=0
Upgrade-Insecure-Requests: 1
User-Agent: Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.89 Safari/537.36
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*//*;q=0.8
Accept-Encoding: gzip, deflate
Accept-Language: zh-CN,zh;q=0.9
Cookie: JSESSIONID=152EA725DB91B0321462E2CFA03F04AC; nginx=4d77745cfe3c2eaf4d675706b49206e8; Hm_lvt_c97145c1d82787f6f2157fdacf2128cf=1515420707; Hm_lpvt_c97145c1d82787f6f2157fdacf2128cf=1515426817

 *
 *
 *
 */










	}



	public static void test(){
		//String str="https://www.baidu.com/";
		//http://tk.accfun.com/accCjzc.html?buildKonwTestByNew&type=test&testType=1&knowId=cc532c7dfa0213ebdac3782cdc89b0b2

		String str=HttpRequest.sendGet("http://kuaijisishu.oss-cn-hangzhou.aliyuncs.com/data/ques/8f31bd2dfc1e91cb3966c38d894a3e8f.o","");

		System.out.println(str);
		//http://tk.accfun.com/accCjzc.html?buildKonwTestByNew&type=test&testType=1&knowId=cc532c7dfa0213ebdac3782cdc89b0b2
	}

	public static void JsonTest(String urlstr) throws Exception{
		boolean flag=true;
		//FileInputStream inp = new FileInputStream("te.txt");
		//byte[] b = new byte[41207];
		//int length = inp.read(b);
		// System.out.println(length);
	//	String s = new String(b,0,length);
		//System.out.println(s);
		//String match="\"id\":\"..\\w\\w\\w\\w\\w\\w\\w\\w\\w\\w\\w\\w\\w\\w\\w\\w\\w\\w\\w\\w\\w\\w\\w\\w\\w\\w\\w\\w\\w\\w\"";
		String match="\"id\":\"\\w+\"";
		//String match="\"tmp1\":\".+\\w+/ques/\\w+\\.o";
		System.out.println(match);
		//b2a54b901c1bbef66bb5b3934bd8f808
		//5a2bfeecffa597fcca620016c1e6467b
		//078d6b08bbad6f75ae931f86dd97a780
		Pattern p=Pattern.compile(match);
		Matcher m=p.matcher(urlstr);
		boolean result=m.find();
		String[] strs=new String[100];
		int temp=0;
		while (m.find()) {
			strs[temp]=read+m.group().substring(6,38)+".o";
			temp++;
		}
		System.out.println(result);
		for(int i=0;i<temp;i++){
			String str=sendGet(strs[i],"");
		//	System.out.println(str);
			JSONObject object=new JSONObject(str);
			if(object.get("type").equals("5")){
				System.out.println(i+1+"."+object.get("content"));
				JSONArray array1=object.getJSONArray("list");
				for(int temp1=0;temp1<array1.length();temp1++){
					JSONObject object1=(JSONObject) array1.get(temp1);
					System.out.println(object1.get("content"));
					JSONArray array2=object1.getJSONArray("list");
					for(int temp3=0;temp3<array2.length();temp3++){
						JSONObject object3=(JSONObject) array2.get(temp3);
						System.out.print(object3.get("letter"));
						System.out.println(object3.get("content"));
					}
					System.out.print("正确答案：");
					System.out.println(object1.get("answerOption"));
					System.out.println("分析："+object1.get("analysis")+"\n");
				}
				
				//JSONArray array2=object.getJSONArray("list");
				
				
				
			}else{
				System.out.println(i+1+"."+object.get("content"));
				JSONArray array=object.getJSONArray("list");


				for(int temp1=0;temp1<array.length();temp1++){
					JSONObject object1=(JSONObject) array.get(temp1);
						System.out.print(object1.get("letter"));
						System.out.println(object1.get("content"));
				}
			
					System.out.print("正确答案：");
					System.out.println(object.get("answerOption"));
					System.out.println("分析："+object.get("analysis")+"\n");
			}
		}

		//BufferedInputStream io=new BufferedInputStream(url.openStream());



		/*JsonParser parser=new JsonParser(); 
		JsonObject object=(JsonObject) parser.parse(s);*/
		/*JSONObject obj = new JSONObject(s);  
		System.out.println(obj.get("state"));
		 */
	}



}
