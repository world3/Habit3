package org.world3.habit3.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * A task is a job that can be done within a short time.
 * 
 */
@Document(collection = "task")
public class Task {

	public enum Importance {
		Important,
		NotImportant
	}

	public enum TaskStatus {
		NotStarted,
		Started,
		Completed,
		Suspended,
		Cancelled
	}

	public enum Priority {
		Urgent,
		NotUrgent
	}

	@Id
	private String id;

	@Indexed
	private String userId;

	@Indexed
	private String goalId;

	private String title;

	@Field("desc")
	private String description;

	/**
	 * If planned ahead, the task should not be urgent.
	 */
	private String priority;

	/**
	 * It is important if it highly attached to the goal.
	 */
	private String importance;

	private Date endDate;

	private String status;

	protected Task() {}

	public Task(String title, String description, Date targetDate) {
		this.title = title;
		this.description = description;
		this.endDate = targetDate;
		this.priority = Priority.NotUrgent.name();
		this.importance = Importance.Important.name();
		this.status = TaskStatus.NotStarted.name();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@JsonIgnore
	public Priority getPriorityEnum() {
		return Priority.valueOf(this.priority);
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	@JsonIgnore
	public Importance getImportanceEnum() {
		return Importance.valueOf(importance);
	}

	public String getImportance() {
		return importance;
	}

	public void setImportance(String importance) {
		this.importance = importance;
	}

	@JsonIgnore
	public TaskStatus getTaskStatus() {
		return TaskStatus.valueOf(status);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
