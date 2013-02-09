package org.world3.habit3.entity;

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
public class Goal {

	/**
	 * Default target date is (now + 90 days)
	 */
	private static final int DEFAULT_TIME_LINE_IN_DAYS = 90; 
	
	/**
	 * Short title for quick review
	 */
	private String title;
	
	/**
	 * Long description for goal
	 */
	private String description;
	
	private GoalType type = GoalType.ShortTerm;
	
	private Importance importance = Importance.Important;

	private Date targetDate;
	
	private List<Task> taskList = new ArrayList<Task>();
	
	public Goal(String title, String desc) {
		this(title, desc, GoalType.ShortTerm);
	}
	
	public Goal(String title, String desc, GoalType type) {
		this.title = title;
		this.description = desc;
		this.type = type;
		this.targetDate = createDefaultTargetDate();
	}
	
	public Goal(String title, String desc, GoalType type, Date targetDate) {
		this.title = title;
		this.description = desc;
		this.type = type;
		this.targetDate = targetDate;
	}

	private Date createDefaultTargetDate() {
		Calendar target = Calendar.getInstance();
		target.add(Calendar.DATE, DEFAULT_TIME_LINE_IN_DAYS);
		return target.getTime();
	}
	
	public void addTask(Task task) {
		this.taskList.add(task);
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

	public GoalType getType() {
		return type;
	}

	public void setType(GoalType type) {
		this.type = type;
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
}
