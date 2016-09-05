// Zachary Gover
// JAV2 - 1609
// StudentFormFragment

package com.gover.zachary.datadisplayfragments.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import com.gover.zachary.datadisplayfragments.MainActivity;
import com.gover.zachary.datadisplayfragments.R;
import com.gover.zachary.datadisplayfragments.models.Student;

import static com.gover.zachary.datadisplayfragments.MainActivity.deviceStorage;

public class StudentFormFragment extends Fragment implements View.OnClickListener {

	public static final String TAG = "StudentFormFragment.TAG";

	/**
	 * MARK: Class Initializer
	 */

	public StudentFormFragment(){}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// Inflate the fragment view
		View view = inflater.inflate(R.layout.student_form_fragment, container, false);

		view.findViewById(R.id.submitBtn).setOnClickListener(this);

		return view;
	}


	@Override
	public void onClick(View view) {
		EditText name = (EditText) getView().findViewById(R.id.nameInput);
		EditText university = (EditText) getView().findViewById(R.id.universityInput);
		EditText grade = (EditText) getView().findViewById(R.id.gradeInput);
		String nameText = name.getText().toString().trim();
		String univText = university.getText().toString().trim();
		String gradeText = grade.getText().toString().trim();

		// Check if default fields are empty then check against the current objType
		if (nameText.isEmpty() || univText.isEmpty() || gradeText.isEmpty()) {
			Toast.makeText(getActivity(), "Please complete the form", Toast.LENGTH_SHORT)
			.show();
			return;
		}

		Student student = new Student();
		student.setName(nameText);
		student.setUniversity(univText);
		student.setGrade(gradeText);

		deviceStorage.savePerson(student);
		MainActivity.adapter.clear();
		MainActivity.adapter.addAll(deviceStorage.getPeople("student"));
		MainActivity.adapter.notifyDataSetChanged();

		Toast.makeText(getActivity(), "Saved student", Toast.LENGTH_SHORT)
			.show();

		Log.i("Result", "Saved student");
	}
}
