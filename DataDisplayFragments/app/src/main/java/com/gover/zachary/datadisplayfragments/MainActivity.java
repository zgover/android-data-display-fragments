// Zachary Gover
// JAV2 - 1609
// MainActivity

package com.gover.zachary.datadisplayfragments;

import android.app.FragmentTransaction;
import android.app.Fragment;
import android.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import com.gover.zachary.datadisplayfragments.fragments.PersonListFragment;
import com.gover.zachary.datadisplayfragments.models.*;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

	/**
	 * MARK: Global Properties
	 */

	public static Storage deviceStorage;
	public static ArrayAdapter adapter;

	/**
	 * MARK: View Connections
	 */

	public Spinner objType;
	public ListFragment listViewFragment;
	public Fragment studentFormFragment;
	public Fragment teacherFormFragment;
	public Fragment adminFormFragment;

	/**
	 * MARK: Default Methods
	 */

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		deviceStorage = new Storage(this);
		deviceStorage.getPeople("student");

		// Setup default properties and view connections
		objType = (Spinner) findViewById(R.id.objType);
		adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,
									  deviceStorage.getPeople("student"));
		listViewFragment = PersonListFragment.newInstance();
		listViewFragment.setListAdapter(adapter);

		studentFormFragment  = getFragmentManager()
								   .findFragmentById(R.id.studentFormFragment);
		teacherFormFragment  = getFragmentManager()
								   .findFragmentById(R.id.teacherFormFragment);
		adminFormFragment    = getFragmentManager()
								   .findFragmentById(R.id.adminFormFragment);

		// Setup listeners
		objType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
				switchObj();
			}

			@Override
			public void onNothingSelected(AdapterView<?> adapterView) {}
		});
	}

	/**
	 * MARK: View Actions
	 */

	public void refreshView(View view) {
		adapter.clear();
		adapter.addAll(deviceStorage.getPeople());
		adapter.notifyDataSetChanged();
	}

	/**
	 * MARK: Custom Methods
	 */

	public void switchObj() {
		// Get the current object type and show the correct fragment
		FragmentTransaction fragTrans = getFragmentManager().beginTransaction();

		Log.i("ObjectType", objType.getSelectedItem().toString());

		switch(objType.getSelectedItem().toString()) {
			case "Student":
				adapter.clear();
				adapter.addAll(deviceStorage.getPeople("student"));
				fragTrans.show(studentFormFragment);
				fragTrans.hide(teacherFormFragment);
				fragTrans.hide(adminFormFragment);

				break;

			case "Teacher":
				adapter.clear();
				adapter.addAll(deviceStorage.getPeople("teacher"));
				fragTrans.show(teacherFormFragment);
				fragTrans.hide(studentFormFragment);
				fragTrans.hide(adminFormFragment);

				break;

			case "Administrator":
				adapter.clear();
				adapter.addAll(deviceStorage.getPeople("admin"));
				fragTrans.show(adminFormFragment);
				fragTrans.hide(teacherFormFragment);
				fragTrans.hide(studentFormFragment);

				break;
		}

		fragTrans.commit();
		adapter.notifyDataSetChanged();
	}

	/**
	 * MARK: Notification Methods
	 */

	public void showToast(String message) {
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}

}
