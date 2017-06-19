package name.lizhe.user.bean;

import java.util.ArrayList;
import java.util.List;

public class User {
	private String mailAddress;
	private List<User> friends = new ArrayList<User>();
	private List<User> observers = new ArrayList<User>();
	private List<User> blockers = new ArrayList<User>();
	
	public User(){
		
	}
	public User(String mailAddress) {
		this.mailAddress = mailAddress;
	}
	
	public String getMailAddress() {
		return mailAddress;
	}
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
	public List<User> getFriends() {
		return friends;
	}
	public void setFriends(List<User> friends) {
		this.friends = friends;
	}
	public List<User> getObservers() {
		return observers;
	}
	public void setObservers(List<User> observers) {
		this.observers = observers;
	}
	public List<User> getBlockers() {
		return blockers;
	}
	public void setBlockers(List<User> blockers) {
		this.blockers = blockers;
	}
}