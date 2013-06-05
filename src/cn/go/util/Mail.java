package cn.go.util;

import java.util.Arrays;

import javax.mail.Address;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class Mail {
	private String subject = null;
	private Address from = null;
	private Address[] to = null;
	private Address[] cc = null;
	private Address[] bcc = null;
	private String content = null;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Address getFrom() {
		return from;
	}

	public void setFrom(String from) throws AddressException {
		this.from = new InternetAddress(from);
	}

	public Address[] getTo() {
		return to;
	}

	public void setTo(String[] to) throws AddressException {
		if (to == null || to.length == 0)
			return;
		this.to = new InternetAddress[to.length];
		for (int i = 0, len = to.length; i < len; i++) {
			this.to[i] = new InternetAddress(to[i]);
		}
	}

	public Address[] getCc() {
		return cc;
	}

	public void setCc(String[] cc) throws AddressException {
		if (cc == null || cc.length == 0)
			return;
		this.cc = new InternetAddress[cc.length];
		for (int i = 0, len = cc.length; i < len; i++) {
			this.cc[i] = new InternetAddress(cc[i]);
		}
	}

	public Address[] getBcc() {
		return bcc;
	}

	public void setBcc(String[] bcc) throws AddressException {
		if (bcc == null || bcc.length == 0)
			return;
		this.bcc = new InternetAddress[bcc.length];
		for (int i = 0, len = bcc.length; i < len; i++) {
			this.bcc[i] = new InternetAddress(bcc[i]);
		}
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Mail [subject=" + subject + ", from=" + from + ", to="
				+ Arrays.toString(to) + ", cc=" + Arrays.toString(cc)
				+ ", bcc=" + Arrays.toString(bcc) + ", content=" + content
				+ "]";
	}

}
