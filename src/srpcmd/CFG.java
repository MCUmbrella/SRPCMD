package srpcmd;
import java.io.Serializable;
public class CFG implements Serializable
{
	private static final long serialVersionUID = 1L;
	final String ver="2";
	String user,pswd,to,file,smtp,subj,body;
	public void setUser(String user) {this.user=user;};
	public void setPswd(String pswd) {this.pswd=pswd;};
	public void setTo(String to) {this.to=to;};
	public void setFile(String file) {this.file=file;};
	public void setSmtp(String smtp) {this.smtp=smtp;};
	public void setSubj(String subj) {this.subj=subj;};
	public void setBody(String body) {this.body=body;};
	@Override
	public String toString() {return("[key] [value]\n"+" ver   "+ver+"\n user  "+user+"\n pswd  "+pswd+"\n to    "+to+"\n file  "+file+"\n smtp  "+smtp+"\n subj  "+subj+"\n body  "+body);}
}

