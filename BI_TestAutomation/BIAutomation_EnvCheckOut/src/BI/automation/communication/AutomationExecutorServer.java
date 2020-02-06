package BI.automation.communication;

/*This is a server which will listen from the client and based on that will execute the automation script*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;

import BI.automation.common.PropertiesStore;
import BI.automation.util.WriteToFile;

public class AutomationExecutorServer {

    /**
     * The port that the server listens on.
     */
    private int PORT;
    private ServerSocket serversocket;
    private static String logfilename;
    private String mentionedClassName=this.getClass().getSimpleName();
    /**
     * The set of all names of clients in the chat room.  Maintained
     * so that we can check that new clients are not registering name
     * already in use.
     */
    private static HashSet<String> names = new HashSet<String>();

    /**
     * The set of all the print writers for all the clients.  This
     * set is kept so we can easily broadcast messages.
     */
    private static HashSet<PrintWriter> writers = new HashSet<PrintWriter>();

    /**
     * The appplication main method, which just listens on a port and
     * spawns handler threads.
     */
    public static void main(String[] args) throws Exception {
    	AutomationExecutorServer as=new AutomationExecutorServer();
        as.startServer();
    }
    
    public AutomationExecutorServer(){
    	
    	loadTheProperties();
    	WriteToFile.writeAsLog(logfilename, mentionedClassName+"-"+"Log file: "+logfilename);
    	WriteToFile.writeAsLog(logfilename, mentionedClassName+"-"+"Server Port: "+Integer.toString(this.PORT));

    	
    	//this.PORT=Integer.parseInt(portnumber);
    }
    private void loadTheProperties(){
    	PropertiesStore.loadSystemProperties(System.getProperty("user.dir")+"//"+"system//systemconfig.properties");
    	PropertiesStore.loadAutomationExecutorServerProperties(System.getProperty("user.dir")+"//"+"AutomationExecutorServerCommand//command.properties");
    	logfilename=System.getProperty("user.dir")+"//"+"logs//"+PropertiesStore.getSystemPropertyValue("AutomationExecutorServerLog");
    	PORT=Integer.parseInt(PropertiesStore.getSystemPropertyValue("AutomationExecutorServerPORT"));
    }
   
    public void startServer(){
        try {
        	@SuppressWarnings("resource")
			ServerSocket serversocket = new ServerSocket(this.PORT);
        	WriteToFile.writeAsLog(logfilename, mentionedClassName+"-"+"Server Started.. ");
            while (true) {
                new Handler(serversocket.accept()).start();
            }
        }
        catch(Exception e){
        	System.out.println(e.getMessage());
        }
        finally {
        	try{
        		serversocket.close();
        		WriteToFile.writeAsLog(logfilename, mentionedClassName+"-"+"Server Stopped.. ");
        	}
        	catch(Exception se){
        		System.out.println(se.getMessage());
        	}
        }
    }

    /**
     * A handler thread class.  Handlers are spawned from the listening
     * loop and are responsible for a dealing with a single client
     * and broadcasting its messages.
     */
    private static class Handler extends Thread {
        private String name;
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;

        /**
         * Constructs a handler thread, squirreling away the socket.
         * All the interesting work is done in the run method.
         */
        public Handler(Socket socket) {
            this.socket = socket;
            this.name=this.socket.getInetAddress().getHostName();
            WriteToFile.writeAsLog(AutomationExecutorServer.logfilename,"Connected Client: "+name);
        }

        /**
         * Services this thread's client by repeatedly requesting a
         * screen name until a unique one has been submitted, then
         * acknowledges the name and registers the output stream for
         * the client in a global set, then repeatedly gets inputs and
         * broadcasts them.
         */
        public void run() {
            try {
            	
                // Create character streams for the socket.
                in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                // Request a name from this client.  Keep requesting until
                // a name is submitted that is not already used.  Note that
                // checking for the existence of a name and adding the name
                // must be done while locking the set of names.
                /*
                while (true) {
                    out.println("SUBMITNAME");
                    name = in.readLine();
                    if (name == null) {
                        return;
                    }
                    synchronized (names) {
                        if (!names.contains(name)) {
                            names.add(name);
                            break;
                        }
                    }
                }

                // Now that a successful name has been chosen, add the
                // socket's print writer to the set of all writers so
                // this client can receive broadcast messages.
                out.println("NAMEACCEPTED");
                writers.add(out);
                */
                // Accept messages from this client and broadcast them.
                // Ignore other clients that cannot be broadcasted to.
                while (true) {
                    String input = in.readLine();
                    WriteToFile.writeAsLog(AutomationExecutorServer.logfilename,"Client-"+name+"-"+"Command: "+input);
                    String commandtoexecute=PropertiesStore.getAutomationExecutorServerCommand(input);
                    WriteToFile.writeAsLog(AutomationExecutorServer.logfilename,"Client-"+name+"-"+"CommandToExecute: "+commandtoexecute);
                    AutomationCommandExecutor.executeCommand(commandtoexecute);
                    if (input == null) {
                        return;
                    }
                    for (PrintWriter writer : writers) {
                        writer.println("MESSAGE " + name + ": " + input);
                    }
                }
            } catch (Exception e) {
            	WriteToFile.writeAsLog(AutomationExecutorServer.logfilename,"Disconnected Client: "+name);
            } finally {
                // This client is going down!  Remove its name and its print
                // writer from the sets, and close its socket.
                if (name != null) {
                    names.remove(name);
                }
                if (out != null) {
                    writers.remove(out);
                }
                try {
                    socket.close();
                } catch (IOException e) {
                }
            }
        }
    }
}
