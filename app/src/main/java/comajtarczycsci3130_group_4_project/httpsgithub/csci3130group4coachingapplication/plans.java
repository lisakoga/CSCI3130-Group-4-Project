package comajtarczycsci3130_group_4_project.httpsgithub.csci3130group4coachingapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.FirebaseDatabase;

public class plans extends AppCompatActivity {

    MyApplicationData appState;
    private User selectedUser;
    private ListView planListView;
    private FirebaseListAdapter<Activity> firebaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plans);

        selectedUser = (User)getIntent().getSerializableExtra("user");

        //Get the app wide shared variables
        appState = (MyApplicationData) getApplication();

        //Get the reference to the UI contents
        planListView = (ListView) findViewById(R.id.planView);

        //Set up the List View
        firebaseAdapter = new FirebaseListAdapter<Activity>(this, Activity.class,
                android.R.layout.simple_list_item_1, appState.database.getReference().child("Plans").child(selectedUser.getUsername())) {
            @Override
            protected void populateView(View v, Activity plan, int position) {
                TextView activityDescription = (TextView) v.findViewById(android.R.id.text1);
                activityDescription.setText(plan.getDescription());
            }
        };
        planListView.setAdapter(firebaseAdapter);
        planListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            // onItemClick method is called everytime a user clicks an item on the list
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Activity plan = (Activity) firebaseAdapter.getItem(position);
                editPlan(plan);
            }
        });
    }

    public void createPlanButton(View v)
    {
        Intent intent = new Intent(this, CreatePlan.class);
        intent.putExtra("user", selectedUser);
        startActivity(intent);
    }

    private void editPlan(Activity plan) {
        Intent intent = new Intent(this, EditPlan.class);
        intent.putExtra("plan", plan);
        intent.putExtra("user", selectedUser);
        startActivity(intent);
    }
}
