package ie.gmit.sw;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {

		Scanner console = new Scanner(System.in);
		Parser p = new Parser();

		final int PORT = p.getPort();
		String host = p.getIp();
		String downloadDir = p.getDownloadDir();

		Socket requestSocket = null;
		ObjectOutputStream out = null;
		ObjectInputStream in = null;
		boolean connected = false;

		while (true) {
			int option;
			System.out.println("1. Connect to Server");
			System.out.println("2. Print File Listing");
			System.out.println("3. Download File");
			System.out.println("4. Quit");
			System.out.println();
			System.out.print("Type Option [1-4]> ");
			try {
				option = console.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Incorrect input");

				continue;
			}

			switch (option) {
			case 1:
				if (!connected) {
					requestSocket = new Socket(host, PORT); // Connection to
															// server
					out = new ObjectOutputStream(requestSocket.getOutputStream());
					out.flush();
					in = new ObjectInputStream(requestSocket.getInputStream());
					connected = true;

					Request r = new Request("1", host, new Date());
					out.writeObject(r);
					out.flush();

					String message = null;
					do {
						message = (String) in.readObject();
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					} while (message == null);

					System.out.println(message);
				} else {
					System.out.println("Server already connected !!!");
				}
				break;

			case 2:
				if (connected) { // Print all files listing
					Request r = new Request("2", host, new Date());
					out.writeObject(r);
					out.flush();
					String message = null;
					do {
						message = (String) in.readObject();
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					} while (message == null);

					System.out.println("Files: " + message);
				} else {
					System.out.println("Please conect to server first");
				}
				break;

			case 3:
				// Download file
				if (connected) {
					Request r = new Request("3", host, new Date());
					out.writeObject(r);
					out.flush();

					console.nextLine();
					System.out.print("Enter name of file to download: > ");
					String file = console.nextLine();

					out.writeObject(file);
					out.flush();

					String message = null;
					do {
						message = (String) in.readObject();
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					} while (message == null);

					if (message != "File not found") {
						// Create a directory for downloads. If one exists, the
						// below line will do nothing
						new File(downloadDir).mkdir();

						PrintWriter pw = new PrintWriter(downloadDir + "/" + file);
						pw.println(message);
						pw.close();
					} else {
						System.out.println(message);
					}
				} else {
					System.out.println("Try connecting to the server (Option 1) first");
				}
				break;

			case 4:
				// Exit
				System.exit(0);
				requestSocket.close();
				console.close();
				break;

			default:
				System.out.println("Please make correct selection!");
				break;
			}
		}
	}
}
