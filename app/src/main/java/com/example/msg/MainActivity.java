package com.example.msg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.msg.fragment.AccountFragment;
import com.example.msg.fragment.ChatFragment;
import com.example.msg.fragment.HomeFragment;
import com.example.msg.fragment.ReservationFragment;
import com.example.msg.fragment.WriteFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;  //하단 바
    private FragmentManager fm;
    private FragmentTransaction ft;
    private HomeFragment homeFragment;
    private ChatFragment chatFragment;
    private WriteFragment writeFragment;
    private ReservationFragment reservationFragment;
    private AccountFragment accountFragment;
    private Button backLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        backLogin=(Button)findViewById(R.id.mainActivity_button_backLogin);
        backLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "로그인 화면으로 돌아갑니다.", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        bottomNavigationView=findViewById(R.id.mainactivity_bottomnavigationview);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_home:
                        setFrag(0);
                        break;
                    case R.id.action_chat:
                        setFrag(1);
                        break;
                    case R.id.action_write:
                        setFrag(2);
                        break;
                    case R.id.action_reservation:
                        setFrag(3);
                        break;
                    case R.id.action_account:
                        setFrag(4);
                        break;
                }
                return true;
            }
        });
        homeFragment=new HomeFragment();
        chatFragment=new ChatFragment();
        writeFragment=new WriteFragment();
        reservationFragment=new ReservationFragment();
        accountFragment=new AccountFragment();
        setFrag(0); //첫 fragment 화면을 무엇으로 지정해줄 것인지 선택

    }

    //fragment 교체가 일어나는 실행문.
    private void setFrag(int n){
        fm=getSupportFragmentManager();
        ft=fm.beginTransaction();
        switch (n){
            case 0:
                ft.replace(R.id.mainactivity_framelayout,homeFragment);
                ft.commit();
                break;
            case 1:
                ft.replace(R.id.mainactivity_framelayout,chatFragment);
                ft.commit();
                break;
            case 2:
                ft.replace(R.id.mainactivity_framelayout,writeFragment);
                ft.commit();
                break;
            case 3:
                ft.replace(R.id.mainactivity_framelayout,reservationFragment);
                ft.commit();
                break;
            case 4:
                ft.replace(R.id.mainactivity_framelayout,accountFragment);
                ft.commit();
                break;
//kyudong
        }
    }

    //BBBBBBBB
}
