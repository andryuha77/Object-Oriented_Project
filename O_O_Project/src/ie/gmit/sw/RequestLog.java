package ie.gmit.sw;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

public class RequestLog extends Thread {
	private BlockingQueue<Request> queue;
	private FileWriter log;

	public RequestLog(BlockingQueue<Request> a) {
		this.queue = a;
	}

	public void run() {
		try {
			log = new FileWriter(new File("log.txt"));

			while (true) {
				Request r = queue.poll();

				if (r != null) {
					if (r.getCommand() == "1") {
						log.write("Log: Client connected from " + r.getHost() + " at " + r.getDate() + "\n");
						log.flush();
					} else if (r.getCommand() == "2") {
						log.write("Log: File listing requested from " + r.getHost() + " at " + r.getDate() + "\n");
						log.flush();
					} else if (r.getCommand() == "3") {
						log.write("Log: File downloaded from " + r.getHost() + " at " + r.getDate() + "\n");
						log.flush();
					} else if (r.getCommand() == "4") {
						log.write("Log: Disconnected from client " + r.getHost() + " at " + r.getDate() + "\n");
						log.close();
					}
					r = null;
				} else {
					try {
						Thread.sleep(1000); // wait for 10 seconds
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		} catch (IOException e) {

		}
	}

}
