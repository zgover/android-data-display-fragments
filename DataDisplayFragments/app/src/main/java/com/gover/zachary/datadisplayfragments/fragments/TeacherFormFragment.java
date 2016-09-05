// Zachary Gover
// JAV2 - 1609
// TeacherFormFragment

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
import com.gover.zachary.datadisplayfragments.models.Teacher;

import static com.gover.zachary.datadisplayfragments.MainActivity.deviceStorage;

public class TeacherFormFragment extends Fragment implements View.OnClickListener {

	public static final String TAG = "TeacherFormFragment.TAG";

	/**
	 * MARK: Class Initializer
	 */

	public TeacherFormFragment(){}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// Inflate the fragment view
		View view = inflater.inflate(R.layout.teacher_form_fragment, container, false);

		view.findViewById(R.id.submitBtn).setOnClickListener(this);

		return view;
	}

	@Override
	public void onClick(View view) {
		EditText name = (EditText) getView().findViewById(R.id.nameInput);
		EditText university = (EditText) getView().findViewById(R.id.universityInput);
		EditText course = (EditText) getView().findViewById(R.id.courseInput);
		String nameText = name.getText().toString().trim();
		String univText = university.getText().toString().trim();
		String courseText = course.getText().toString().trim();

		// Check if default fields are empty then check against the current objType
		if (nameText.isEmpty() || univText.isEmpty() || courseText.isEmpty()) {
			Toast.makeText(getActivity(), "Please complete the form", Toast.LENGTH_SHORT)
				.show();
			return;
		}

		Teacher teacher = new Teacher();
		teacher.setName(nameText);
		teacher.setUniversity(univText);
		teacher.setCourse(courseText);

		deviceStorage.savePerson(teacher);
		MainActivity.adapter.clear();
		MainActivity.adapter.addAll(deviceStorage.getPeople("teacher"));
		MainActivity.adapter.notifyDataSetChanged();

		Toast.makeText(getActivity(), "Saved teacher", Toast.LENGTH_SHORT)
			.show();

		Log.i("Result", "Saved teacher");
	}

}
