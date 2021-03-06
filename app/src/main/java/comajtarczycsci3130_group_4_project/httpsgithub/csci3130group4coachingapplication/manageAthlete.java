package comajtarczycsci3130_group_4_project.httpsgithub.csci3130group4coachingapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class manageAthlete extends AppCompatActivity
{
    private Coach sample=new Coach();
    private ArrayList<Athlete> requests=sample.getRequests();
    private ArrayList<Athlete> athletes=sample.getAthletes();
    private MyApplicationData appState;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_athletes);


        String textToShow="sampleee";

        for (int i=0;i<3;i++){
//            if(requests.get(i)==null){
//                textToShow="";}
//            else{
//                textToShow="sampleee";
//            }
            int id = getResources().getIdentifier("request"+(i+1), "id", getPackageName());
            TextView text = findViewById(id);
            text.setText(textToShow);

        }

        for (int i=0;i<5;i++)
        {
//            if(athletes.get(i)==null){
//                textToShow="";}
//            else{
//                textToShow="sampleee";
//            }
            int id = getResources().getIdentifier("athleteInfo"+(i+1), "id", getPackageName());
            TextView text = findViewById(id);
            text.setText(textToShow);


        }

    }
    public void reject(View view)
    {
        int i=0;
        switch (view.getId())
        {
            case R.id.requestReject1:
                i=0;
                break;
            case R.id.requestReject2:
                i=1;
                break;
            case R.id.requestReject3:
                i=2;
                break;
        }
        if(requests.get(i)!=null)
        {
            sample.IgnoreRequest(requests.get(i));
            appState.userRef.child(sample.username).child("Requests").setValue(sample.getRequests());

        }
    }
    public void accept(View view)
    {

        int i=0;
        switch (view.getId())
        {
            case R.id.requestAccept1:
                i=0;
                break;
            case R.id.requestAccept2:
                i=1;
                break;
            case R.id.requestAccept3:
                i=2;
                break;
        }
        if(requests.get(i)!=null){
            sample.AcceptRequest(requests.get(i));
            appState.userRef.child(sample.username).child("Requests").setValue(sample.getRequests());
            appState.userRef.child(sample.username).child("athletes").setValue(sample.getAthletes());
        }
    }
    public void delete(View view)
    {
        int i=0;
        switch (view.getId())
        {
            case R.id.athleteDelete1:
                i=0;
                break;
            case R.id.athleteDelete2:
                i=1;
                break;
            case R.id.athleteDelete3:
                i=2;
                break;
            case R.id.athleteDelete4:
                i=3;
                break;
            case R.id.athleteDelete5:
                i=4;
                break;
        }
        if(athletes.get(i)!=null)
        {
            sample.removeAthlete(athletes.get(i));
            appState.userRef.child(sample.username).child("athletes").setValue(sample.getAthletes());
        }
    }
}