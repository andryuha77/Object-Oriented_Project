package ie.gmit.sw.client;

/* A context represents the entire scope of an application, i.e.
 * we can assign "global variables" to a context.
 * 
 * This is a "bean class", containing a constructor and accessor
 * methods only.
 */
public class Context {
	public static final String CONF_FILE="conf.xml";
	private String username;
	private String host;
	private int port;
	private String dir;
	
	
	public Context() {
		super();
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	@Override
	public String toString() {
		return "Context [username=" + username + ", host=" + host + ", port=" + port + ", dir=" + dir + "]";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}
}
