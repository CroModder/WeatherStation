package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Scanner;

import org.json.JSONObject;

public class FirstUse{
	String fileName = "config.json";
	Config config = new Config();
	DbController dbController = new DbController();
	Scanner in = new Scanner(System.in);
	int id = 0;

	public void runServer(String mode){
		if(exist()){
			if (mode.equals("GUI")){

				System.out.println("Starting server in GUI mode...");
            	javafx.application.Application.launch(GUI.class);
			}
			else{
				System.out.println("Starting server...");
				try {
					socket.Server.start(id);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			System.out.println("Starting server failed!");
		}
	}
	
	public boolean exist(){
		Boolean ok = false;
		File f = new File(fileName);
		if(!f.exists()){
		   if(installation()){
			   ok = true;
		   }
		} else {
			try (BufferedReader br = new BufferedReader(new FileReader(fileName)))
			{
				String sCurrentLine;
				StringBuilder stringObj = new StringBuilder();
	 
				while ((sCurrentLine = br.readLine()) != null) {
					stringObj.append(sCurrentLine.toString());
				}
				JSONObject main= new JSONObject(stringObj.toString());
				id = main.getInt("id");
			}catch (Exception e) {
			}
			ok = true;
		}
		return ok;
    }
	public boolean installation(){
		JSONObject main = new JSONObject();
		String ip = "";
		int port = 0;
		String name = "";
		System.out.println("Weather station installation wizard");
		for (int i = 0; i < 35; i++) {System.out.print("=");}
		System.out.println("\n");
		config.dbInit();
		try {
			dbController.dbInit();
		} catch (IOException e1) {
			System.out.println("Error initializing database!");
			e1.printStackTrace();
		}
    	try {
			ServerSocket serverSocket= new ServerSocket(0);
			ip = getIP().getHostAddress();
			port = serverSocket.getLocalPort();
			serverSocket.close();
			boolean wsOk = false;
			while(!wsOk){
				try {
			    	boolean wsExist = true;
			    	while(wsExist){
			    		System.out.print("Weather Station name: ");
				    	name = in.nextLine();
				    	if(dbController.checkWsExist(name)){
				    		System.out.println("Device name already exist! Please enter unique device name.");
							config.listMenu();
				    	}
				    	else{
				    		wsExist = false;
				    	}
			    	}
			    	Integer intInterval = 1;
			    	boolean intervalOk = false;
			    	while(!intervalOk){
						System.out.print("New interval[100-60000 ms]: ");
				    	String interval = in.nextLine();
				    	try {
							intInterval = Integer.valueOf(interval);
							if (intInterval < 100 || intInterval > 60000) {
								System.out.println("\"" + interval +"\"" +" is not a valid value!");
							}
							else{
								intervalOk = true;
							}
						} catch (Exception e) {
							System.out.println("\"" + interval +"\"" +" is not a valid value!");
						}
					}
			    	boolean addWs = dbController.newWs(name, ip, port, 1, intInterval);
					if (addWs){
						id = dbController.getDeviceID(name);
						main.put("id", id);
						configWrite(main);
						wsOk = true;
						System.out.println("\nWeather Station installed successfully!\n\n\t Details\n============================\nID:\t\t"+ 
						id +"\nName:\t\t" + name + "\nIP address:\t" + ip + "\nPort:\t\t" + port + "\nInterval:\t" + intInterval);
				    	System.out.print("\nStart the server? [y/n]\n> ");
				    	String start = in.nextLine();
				    	in.close();
				    	if (start.equals("y") || start.equals("Y")) {
							break;
						}
				    	else {
				    		System.exit(0);
				    	}
					} else {
						System.out.println("Problem with installing Weather station!");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
    		}
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return true;
	}
	
	private void configWrite(JSONObject main){
		FileWriter file = null;
		try {
			file = new FileWriter(fileName);
			file.write(main.toString());
	        file.flush();
	        file.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
		static private InetAddress getIP() throws SocketException, UnknownHostException {
		    String os = System.getProperty("os.name").toLowerCase();
		    if(os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0) {   // for Linux(Raspberry Pi)
		        NetworkInterface ni = NetworkInterface.getByName("eth0");
		        Enumeration<InetAddress> ias = ni.getInetAddresses();
		        InetAddress iaddress;
		        do {
		            iaddress = ias.nextElement();
		        } while(!(iaddress instanceof Inet4Address));
		        return iaddress;
		    }
		    return InetAddress.getLocalHost();  // for Windows and OS X
		}

}