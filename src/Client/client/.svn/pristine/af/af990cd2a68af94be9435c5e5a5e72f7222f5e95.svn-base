package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Locale;
import java.util.ResourceBundle;
import org.json.JSONObject;
import controller.ErrorLogController;
import controller.Language;
import controller.MainPanelController;

public class Client {
	private Socket socket;
	
	public Socket getSocket() {
		return socket;
	}
	
	public static MainPanelController mainPanelController = new MainPanelController();
	private ErrorLogController errorLogController = new ErrorLogController();
	private Locale locale;
	private ResourceBundle bundle;
	
	 public static MainPanelController getMainPanelController() {
		return mainPanelController;
	}
	public static void setMainPanelController(
			MainPanelController mainPanelController) {
		Client.mainPanelController = mainPanelController;
	}
	
	public boolean initialize(String IP, Integer port, Integer screen) throws IOException {
		boolean result = false;
			try {
				System.out.println("init");
				socket = new Socket(IP, port);
				result = true;
				if(screen == 0){
	            	mainPanelController.init();
				}
			} catch (UnknownHostException e) {
				result = false;
				errorLogController.error(e.toString());
				locale = new Locale(Language.getLanguage());
		    	bundle = ResourceBundle.getBundle("language.lang", locale);
				mainPanelController.errorMessage(bundle.getString("serverError"), bundle.getString("noConnection"));
			} catch (IOException e) {
				result = false;
				errorLogController.error(e.toString());
				locale = new Locale(Language.getLanguage());
		    	bundle = ResourceBundle.getBundle("language.lang", locale);
				mainPanelController.errorMessage(bundle.getString("serverError"), bundle.getString("noConnection"));
			}
			System.out.println("result " + result);
			return result;
	}
	
	public void serverData(JSONObject object) throws IOException, InterruptedException {
		System.out.println("\nRunning client...");
		OutputStreamWriter writer = null;
		try {
			writer =new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
			System.out.println(object);
			writer.write(object.toString() + "\n");
			writer.flush();
			new Thread(){
				  public void run(){
					  	System.out.println(object);
						while(socket.isClosed() == false){
				    		BufferedReader reader = null;
							try {
								reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
							} catch (UnsupportedEncodingException e2) {
								e2.printStackTrace();
							} catch (IOException e2) {
								e2.printStackTrace();
							}
				    		String jsonText = null;
							try {
								jsonText = reader.readLine();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							//TODO pozivanje metode iz Main panel za crtanje
							//mainPanelController.init(initializeArg);
							//initializeArg = false;
							System.out.println("Server response");
				    		mainPanelController.collectSensorData(jsonText);
				    		if (object.getString("action") == "stop"){
				    			try {
				    				OutputStreamWriter writer= new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
				    				writer.write(object.toString() + "\n");
				    				writer.flush();
									socket.close();
									break;
								} catch (IOException e) {
									e.printStackTrace();
									mainPanelController.errorMessage("rerer", "rerer");
									try {
										errorLogController.error(e.toString());
									} catch (IOException e1) {
										e1.printStackTrace();
									}
								}
				    		}
						}
				  }
			}.start();
		} catch (Exception e) {
			errorLogController.error(e.toString());
		}
	}

}
