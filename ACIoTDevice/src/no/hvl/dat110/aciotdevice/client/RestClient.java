package no.hvl.dat110.aciotdevice.client;


import java.io.IOException;

import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class RestClient {
	

	public RestClient() {
		// TODO Auto-generated constructor stub
	}

	private static String logpath = "/accessdevice/log";

	public void doPostAccessEntry(String message) {

		// TODO: implement a HTTP POST on the service to post the message
		Gson gson = new Gson();
		MediaType json = MediaType.parse("application; charset=utf-8");
		OkHttpClient client = new OkHttpClient();
		
		AccessMessage msg = new AccessMessage(message);
	
		String newMsg = gson.toJson(msg);
		
		RequestBody body = RequestBody.create(json, newMsg);
		
		Request req = new Request.Builder()
				.url("http://localhost:8080" + logpath)
				.post(body)
				.build();
		
		System.out.println(req.toString());
		
		try {
			
			Response res = client.newCall(req).execute();
			System.out.println(res.body().string());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	private static String codepath = "/accessdevice/code";
	
	public AccessCode doGetAccessCode() {

		Gson gson = new Gson();
		AccessCode code = null;
		
		// TODO: implement a HTTP GET on the service to get current access code
		
		OkHttpClient client = new OkHttpClient();
		
		Request req = new Request.Builder()
				.url("http://localhost:8080" + codepath)
				.get()
				.build();
		
		System.out.println(req.toString());
		
		try {
			Response res = client.newCall(req).execute();
			String resp = res.body().string();
			code = gson.fromJson(resp, AccessCode.class);
	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			return code;
	}
}
