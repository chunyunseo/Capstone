package com.example.msg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;


import com.example.msg.DatabaseModel.ReserveModel;
import com.example.msg.Domain.ReserveApi;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.messaging.FirebaseMessaging;

public class ReservationActivity extends AppCompatActivity {

    private Button button;
    private EditText editText;
    Spinner spinner;

    String tmp;

    String[] categories = {"육류","어류","채소","향신료","주류"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        button = (Button)findViewById(R.id.reservationActivity_button_item);
        editText = (EditText)findViewById(R.id.reservationActivity_edittext_item);
        spinner = (Spinner)findViewById(R.id.reservationActivity_spinner_category);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                tmp = categories[position];
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                final String uid = user.getUid();
                ReserveModel reserveModel = new ReserveModel();
                reserveModel.user_id = uid;
                reserveModel.category = tmp;
                reserveModel.keyword = editText.getText().toString();
                ReserveApi.postReservation(reserveModel);

                FirebaseMessaging.getInstance().subscribeToTopic(reserveModel.keyword)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                //String msg = getString(R.string.msg_subscribed);
                                if (!task.isSuccessful()) {
                                    //  msg = getString(R.string.msg_subscribe_failed);
                                }
                                //Log.d(TAG, msg);
                                //Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

    }
}