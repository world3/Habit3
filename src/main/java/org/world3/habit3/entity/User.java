package org.world3.habit3.entity;

import java.util.List;

public class User {
	
	private long userId;
	
	private UserCredential credential;
	
	private UserProfile profile;
	
	private List<Goal> goals;
	
	public User(UserCredential credential, UserProfile profile) {
		this.credential = credential;
		this.profile = profile;
	}
	
	public void addGoal(Goal goal) {
		this.goals.add(goal);
	}

	public Goal removeGoal(Goal goal) {
		//TODO remove goals
		return goal;
	}
	
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public UserCredential getCredential() {
		return credential;
	}

	public void setCredential(UserCredential credential) {
		this.credential = credential;
	}

	public UserProfile getProfile() {
		return profile;
	}

	public void setProfile(UserProfile profile) {
		this.profile = profile;
	}
	
	
}
