package srpcmd;
import java.util.*;
import java.io.*;
import org.apache.log4j.*;


public class Main {
	public static void main(String[] args) throws InterruptedException 
	 {
		Logger l=Logger.getLogger(Main.class.getName());
		CFG cfg=new CFG();
		for(byte a=0;a<64;a++) {System.out.println();}
		l.info("LOADING CONSOLE");
		for(;;)
		{
			System.out.print("? ");
			Scanner s=new Scanner(System.in);
			String cmd=s.nextLine();
			l.info("Executing "+cmd);
			if(cmd.toLowerCase().equals("exit")||cmd.toLowerCase().equals("q")) {
				l.info("EXITING CONSOLE");s.close();System.exit(0);}
			else if(cmd.toLowerCase().equals("cls")||cmd.toLowerCase().equals("clear")||cmd.toLowerCase().equals("c")) {
				for(byte a=0;a<64;a++) {System.out.println();}
			}
			else if(cmd.toLowerCase().equals("help")||cmd.toLowerCase().equals("h")||cmd.toLowerCase().equals("?")) {
				l.info("HELP\n\tls: print current config\n\tload(l)(read)(r): load config from file\n\tsave(s): save current config to file\n\texit(q): exit program\n\t[key]=[value]: set the value of [key]");}
			else if(cmd.toLowerCase().equals("test")) {
				l.info(args);}
			else if(cmd.toLowerCase().equals("ls")) {
				l.info("Config:\n"+cfg.toString());}
			else if(cmd.toLowerCase().startsWith("user=")) {
				cfg.setUser(cmd.substring(5));
			}
			else if(cmd.toLowerCase().startsWith("pswd=")) {
				cfg.setPswd(cmd.substring(5));
			}
			else if(cmd.toLowerCase().startsWith("to=")) {
				cfg.setTo(cmd.substring(3));
			}
			else if(cmd.toLowerCase().startsWith("file=")) {
				cfg.setFile(cmd.substring(5));
			}
			else if(cmd.toLowerCase().startsWith("smtp=")) {
				cfg.setSmtp(cmd.substring(5));
			}
			else if(cmd.toLowerCase().startsWith("subj=")) {
				cfg.setSubj(cmd.substring(5));
			}
			else if(cmd.toLowerCase().startsWith("body=")) {
				cfg.setBody(cmd.substring(5).replace("{DATE}",new Date().toString()).replace("{FILE}",cfg.file));
			}
			else if(cmd.toLowerCase().startsWith("ver=")) {
				l.error("Setting version is not supported");
			}
			else if(cmd.toLowerCase().equals("save")||cmd.toLowerCase().equals("s")){
				l.info("Saving config");
				try {
					ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("srp.dat"));
					o.writeObject(cfg);
					o.close();
				}catch(Throwable e) {l.error("Cannot save: ");e.printStackTrace();Thread.sleep(50);continue;}
				l.info("Save success");
			}
			else if(cmd.toLowerCase().equals("read")||cmd.toLowerCase().equals("r")||cmd.toLowerCase().equals("load")||cmd.toLowerCase().equals("l")){
				l.info("Loading config");
				try {
			        ObjectInputStream i = new ObjectInputStream(new FileInputStream(new File("srp.dat")));
			        cfg = (CFG)i.readObject();
			        i.close();
				}catch(Throwable e) {l.error("Cannot load: ");e.printStackTrace();Thread.sleep(50);continue;}
				l.info("Load success");
			}
			else {l.error(cmd+" is not a valid command");}
		}
	}
}
