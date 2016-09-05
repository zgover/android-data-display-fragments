// Zachary Gover
// JAV2 - 1609
// Person

package com.gover.zachary.datadisplayfragments.models;

import java.io.Serializable;
import java.util.Date;

public class Person implements Serializable {

	/**
	 * MARK: Global Properties
	 */

	private String name;
	private String university;

	@Override
	public String toString() {
		if (this instanceof Student) {
			return name + " | Student";
		} else if (this instanceof Teacher) {
			return name + " | Teacher";
		} else if (this instanceof Administrator) {
			return name + " | Admin";
		}

		return name;
	}

	/**
	 * MARK: Getter and Setters
	 */

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}
}
