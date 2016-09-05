// Zachary Gover
// JAV2 - 1609
// AdministratorFormFragment

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
import com.gover.zachary.datadisplayfragments.models.Administrator;
import com.gover.zachary.datadisplayfragments.models.Teacher;

import static com.gover.zachary.datadisplayfragments.MainActivity.deviceStorage;

public class AdministratorFormFragment extends Fragment implements View.OnClickListener {

	public static final String TAG = "AdministratorFormFragment.TAG";

	/**
	 * MARK: Class Initializer
	 */

	public AdministratorFormFragment(){}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// Inflate the fragment view
		View view = inflater.inflate(R.layout.administrator_form_fragment, container, false);

		view.findViewById(R.id.submitBtn).setOnClickListener(this);

		return view;
	}

	@Override
	public void onClick(View view) {
		EditText name = (EditText) getView().findViewById(R.id.nameInput);
		EditText university = (EditText) getView().findViewById(R.id.universityInput);
		EditText program = (EditText) getView().findViewById(R.id.progamInput);
		String nameText = name.getText().toString().trim();
		String univText = university.getText().toString().trim();
		String programText = program.getText().toString().trim();

		// Check if default fields are empty then check against the current objType
		if (nameText.isEmpty() || univText.isEmpty() || programText.isEmpty()) {
			Toast.makeText(getActivity(), "Please complete the form" + nameText.isEmpty(), Toast
																			.LENGTH_SHORT)
				.show();
			return;
		}

		Administrator admin = new Administrator();
		admin.setName(nameText);
		admin.setUniversity(univText);
		admin.setProgram(programText);

		deviceStorage.savePerson(admin);
		MainActivity.adapter.clear();
		MainActivity.adapter.addAll(deviceStorage.getPeople("admin"));
		MainActivity.adapter.notifyDataSetChanged();

		Toast.makeText(getActivity(), "Saved admin", Toast.LENGTH_SHORT)
			.show();

		Log.i("Result", "Saved admin");
	}

}
