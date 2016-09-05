// Zachary Gover
// JAV2 - 1609
// Teacher

package com.gover.zachary.datadisplayfragments.models;

import java.util.Date;

public class Teacher extends Person {

	/**
	 * MARK: Global Properties
	 */

	private String course;

	/**
	 * MARK: Class Initializer
	 */

	public Teacher(){}

	/**
	 * MARK: Getter and Setters
	 */

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

}
