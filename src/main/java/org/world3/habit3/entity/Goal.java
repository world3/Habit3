package org.world3.habit3.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * A goal is what you want to achieve in a short or longer term.
 * It must be feasible, yet it needs some effort to fulfill. 
 * A goal can be divided into many tasks so that they can 
 * be conquered step by step.
 * 
 * A goal will be considered fulfilled until all tasks are either
 * completed or cancelled.
 *
 */
@Document(collection = "goal")
public class Goal {

	public enum GoalType {
		ShortTerm, // 1 week to 6 months
		MidTerm,   // 7 months to 3 years
		LongTerm   // beyond 3 years
	}

	/**
	 * Default target date is (now + 90 days)
	 */
	private static final int DEFAULT_TIME_LINE_IN_DAYS = 90;

	@Id
	private String id;

	@Indexed
	private Long userId;

	/**
	 * Short title for quick review
	 */
	private String title;
	
	/**
	 * Long description for goal
	 */
	@Field("desc")
	private String description;
	
	private String type;
	
	@Field("end")
	private Date endDate;

	@Transient
	private List<Task> tasks = new ArrayList<Task>();

	protected Goal() {}
	
	public Goal(String title, String desc) {
		this(title, desc, GoalType.ShortTerm);
	}
	
	public Goal(String title, String desc, GoalType type) {
		this(title, desc, type, createDefaultEndDate());
	}
	
	public Goal(String title, String desc, GoalType type, Date endDate) {
		this.title = title;
		this.description = desc;
		this.type = type.name();
		this.endDate = endDate;
		this.type = GoalType.ShortTerm.name();
	}

	private static Date createDefaultEndDate() {
		Calendar target = Calendar.getInstance();
		target.add(Calendar.DATE, DEFAULT_TIME_LINE_IN_DAYS);
		return target.getTime();
	}
	
	public void addTask(Task task) {
		this.tasks.add(task);
	}
	
	public Task removeTask(Task task) {
		// TODO Remove Task here
		return task;
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

	public void setDescription(String desc) {
		this.description = desc;
	}

	@JsonIgnore
	public GoalType getGoalType() {
		return GoalType.valueOf(type);
	}

	protected String getType() { return type; }

	protected void setType(String type) {
		this.type = type;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
