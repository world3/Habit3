package org.world3.habit3.entity;

import java.util.Date;

/**
 * A task is a job that can be done within a short time.
 * 
 */
public class Task {
	
	private String title;
	
	private String description;
	
	/**
	 * If planned ahead, the task should not be urgent.
	 */
	private Priority priority = Priority.NotUrgent;
	
	/**
	 * It is important if it highly attached to the goal.
	 */
	private Importance importance = Importance.Important;

	private Date targetDate;
	
	private TaskStatus status = TaskStatus.NotStarted;
	
	public Task(String title, String description, Date targetDate) {
		this.title = title;
		this.description = description;
		this.targetDate = targetDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public Importance getImportance() {
		return importance;
	}

	public void setImportance(Importance importance) {
		this.importance = importance;
	}

	public Date getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}
	
}
