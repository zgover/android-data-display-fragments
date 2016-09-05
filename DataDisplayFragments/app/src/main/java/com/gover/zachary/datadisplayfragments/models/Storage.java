// Zachary Gover
// JAV2 - 1609
// Storage

package com.gover.zachary.datadisplayfragments.models;

import android.content.Context;
import android.util.Log;

import java.io.*;
import java.util.ArrayList;

public class Storage {

	/**
	 * MARK: Global Properties
	 */

	private Context _context;

	private ArrayList<? extends Person> people = new ArrayList<>();
	private ArrayList<Student> students = new ArrayList<>();
	private ArrayList<Teacher> teachers = new ArrayList<>();
	private ArrayList<Administrator> admins = new ArrayList<>();

	/**
	 * MARK: Class Initializer
	 */

	public Storage(Context context) {
		this._context = context;
	}

	/**
	 * MARK: CRUD Methods
	 */

	public void savePerson(Person result) {
		// Get the file output stream
		FileOutputStream outputStream = null;
		ObjectOutputStream objectOutputStream = null;

		try {
			// Open the devices output stream so we may save to it
			outputStream = _context.openFileOutput(getFileName(result),
				_context.MODE_PRIVATE);
			objectOutputStream = new ObjectOutputStream(outputStream);

			// Add the new person the list
			if (result instanceof Student) {
				students.add((Student) result);
				objectOutputStream.writeObject(students);
			} else if (result instanceof Teacher) {
				teachers.add((Teacher) result);
				objectOutputStream.writeObject(teachers);
			} else if (result instanceof  Administrator) {
				admins.add((Administrator) result);
				objectOutputStream.writeObject(admins);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// Close the output streams
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (objectOutputStream != null) {
				try {
					objectOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public ArrayList<? extends Person> getPeople(String objType) {
		// Get the input stream
		FileInputStream inputStream = null;
		ObjectInputStream objectInputStream = null;
		people.clear();

		try {
			// Open the devices input streams so we may read the file
			inputStream = _context.openFileInput(getFileName(objType));
			objectInputStream = new ObjectInputStream(inputStream);

			// Cast the object to its correct type
			Object result = objectInputStream.readObject();

			if (objType.equals("student")) {
				people = (ArrayList<Student>) result;
			} else if (objType.equals("teacher")) {
				people = (ArrayList<Teacher>) result;
			} else if (objType.equals("admin")) {
				people = (ArrayList<Administrator>) result;
			}

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// Close the input streams
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (objectInputStream != null) {
				try {
					objectInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return people;
	}

	/**
	 * MARK: Custom Methods
	 */

	private String getFileName(Person result) {
		// Set the filename based on the instance
		String fileName = "person_details";

		if (result instanceof Student) {
			fileName = "person_details_student";
		} else if (result instanceof Teacher) {
			fileName = "person_details_teacher";
		} else if (result instanceof Administrator) {
			fileName = "person_details_admin";
		}

		System.out.println(fileName);

		return fileName;
	}

	private String getFileName(String name) {
		// Set the filename based on the instance
		String fileName = "person_details";

		if (name != "") {
			fileName = "person_details_" + name;
		}

		return fileName;
	}

	/**
	 * MARK: Getter
	 */

	public ArrayList<? extends Person> getPeople() {
		return this.people;
	}
}