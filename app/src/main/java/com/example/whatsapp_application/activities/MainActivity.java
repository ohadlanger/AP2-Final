
package com.example.whatsapp_application.activities;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.whatsapp_application.R;
import com.example.whatsapp_application.entities.Message;
import com.example.whatsapp_application.entities.User;
import com.example.whatsapp_application.repositories.MessageRepository.LoginRepository;
import com.example.whatsapp_application.repositories.MessageRepository.UserRepository;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Intent details;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
        MutableLiveData<String> token = new MutableLiveData<>();
        details = new Intent(MyApplication.getContext(), ContactsActivity.class);
        MutableLiveData<List<Message>> messages = new MutableLiveData<>();
        LoginRepository loginRepository = new LoginRepository(this);
        loginRepository.createToken("string", "string", token);
        MutableLiveData<User> user = new MutableLiveData<>();
        token.observe(this, s -> {
            UserRepository UserRepository = new UserRepository();
            UserRepository.getUser("string", "Bearer " + s, user);

        });
        messages.observe(this, messages1 -> {
            System.out.println(messages1);
        });
        user.observe(this, user1 -> {
            System.out.println(user1);
        });

        Button signBtn = findViewById(R.id.signupBtn);
        signBtn.setOnClickListener(view -> {
            Intent intent = new Intent(this, SignUpActivity.class);
            startActivity(intent);
        });


        EditText usernameEt = findViewById(R.id.usernameEt);
        EditText passwordEt = findViewById(R.id.passwordEt);
        Button registerBtn = findViewById(R.id.registerBtn);

        registerBtn.setOnClickListener(view -> { // on an connection attempt
            String username = usernameEt.getText().toString();
            String password = passwordEt.getText().toString();
            if (!username.isEmpty() && !password.isEmpty()) {
                MutableLiveData<User> result = new MutableLiveData<>(); //  user object to listen to

                result.observe(this, new Observer<User>() { //  handle when updated (found)
                    @Override
                    public void onChanged(User newValue) {
                        if (newValue != null) { //  user exists
                            details.putExtra("username", newValue.getUsername());
                            details.putExtra("displayname", newValue.getDisplayName());
                            details.putExtra("picture", newValue.getProfilePic());
                            startActivity(details);
                            finishAffinity();
                        } else {    //  user does not exist
                            Toast.makeText(getApplicationContext(), "Invalid information. Try again!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                VerifyLogin(username, password, result);    //  check information
            }
            else {  //  not all fields were filled
                Toast.makeText(getApplicationContext(), "Missing requirements. Try again!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    void VerifyLogin(String username, String password, MutableLiveData<User> user) {
        if(!username.isEmpty() && !password.isEmpty()) {
            UserRepository userRepository = new UserRepository();
            LoginRepository loginRepository = new LoginRepository(getApplicationContext());
            MutableLiveData<String> token = new MutableLiveData<>();    //  will receive the token from database

            token.observe(this, new Observer<String>() {    //  handle return of token
                @Override
                public void onChanged(String newValue) {
                    details.putExtra("token", newValue);
                    userRepository.getUser(username, "Bearer " + newValue, user); // update user with token
                }
            });

            loginRepository.createToken(username, password, token); //  request token
        }
    }
}