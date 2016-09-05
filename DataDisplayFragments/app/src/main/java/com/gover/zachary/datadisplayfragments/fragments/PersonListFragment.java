// Zachary Gover
// JAV2 - 1609
// PersonListFragment

package com.gover.zachary.datadisplayfragments.fragments;

import android.app.ListFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import com.gover.zachary.datadisplayfragments.MainActivity;
import com.gover.zachary.datadisplayfragments.R;
import com.gover.zachary.datadisplayfragments.models.Administrator;
import com.gover.zachary.datadisplayfragments.models.Person;
import com.gover.zachary.datadisplayfragments.models.Student;
import com.gover.zachary.datadisplayfragments.models.Teacher;

import java.util.ArrayList;

public class PersonListFragment extends ListFragment {

	public static final String TAG = "PersonListFragment.TAG";

	/**
	 * MARK: Class Initializer
	 */

	public PersonListFragment(){}

	public static PersonListFragment newInstance() {
		PersonListFragment fragment = new PersonListFragment();

		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// Inflate the fragment view
		View view = inflater.inflate(R.layout.person_list_fragment, container, false);

		return view;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		setListAdapter(MainActivity.adapter);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		Person person = MainActivity.deviceStorage.getPeople().get(position);

		if (person != null) {
			EditText name = (EditText) getActivity().findViewById(R.id.nameInput);
			EditText university = (EditText) getActivity().findViewById(R.id.universityInput);
			EditText grade = (EditText) getActivity().findViewById(R.id.gradeInput);
			EditText course = (EditText) getActivity().findViewById(R.id.courseInput);
			EditText program = (EditText) getActivity().findViewById(R.id.progamInput);

			name.setText(person.getName());
			university.setText(person.getUniversity());

			if (person instanceof Student) {
				grade.setText(((Student) person).getGrade());
			} else if (person instanceof Teacher) {
				course.setText(((Teacher) person).getCourse());
			} else if (person instanceof Administrator) {
				program.setText(((Administrator) person).getProgram());
			}
		}

		Log.i("ListClick", "Clicked list item");
	}
}
