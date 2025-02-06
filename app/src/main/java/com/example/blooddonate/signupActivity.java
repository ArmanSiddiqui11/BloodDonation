package com.example.blooddonate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;



import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class signupActivity extends AppCompatActivity {

    private EditText etEmail, etPassword, etConfirmPassword;
    private Button btnSignUp;
    private CheckBox cbShowPassword;
    TextView textView;
    private String email, password, confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Initialize views
        etEmail = findViewById(R.id.edtEmail);
        etPassword = findViewById(R.id.edtPassword);
        etConfirmPassword = findViewById(R.id.edtConfirmPassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        textView=findViewById(R.id.tvRedirectLogin);
        cbShowPassword = findViewById(R.id.cbShowPassword);

// CheckBox listener to show/hide password
        cbShowPassword.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // Show password
                etPassword.setInputType(1); // textVisiblePassword
            } else {
                // Hide password
                etPassword.setInputType(129); // textPassword
            }
            etPassword.setSelection(etPassword.getText().length());  // Keep cursor at the end
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(signupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


        btnSignUp.setOnClickListener(v -> {
            // Get data from inputs
            email = etEmail.getText().toString();
            password = etPassword.getText().toString();
            confirmPassword = etConfirmPassword.getText().toString();

            // Validate inputs
            if (validateInput()) {
                // Proceed with sign-up (for example, save to Firebase, or server)
                signUpUser(email, password);
            }
        });
    }

    // Method to validate user inputs
    private boolean validateInput() {
        if (TextUtils.isEmpty(email)) {
            etEmail.setError("Email is required");
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            etPassword.setError("Password is required");
            return false;
        }
        if (TextUtils.isEmpty(confirmPassword)) {
            etConfirmPassword.setError("Please confirm your password");
            return false;
        }
        if (!password.equals(confirmPassword)) {
            etConfirmPassword.setError("Passwords do not match");
            return false;
        }
        return true;
    }

    // Method to handle the sign-up logic (e.g., Firebase or backend API call)
    private void signUpUser(String email, String password) {
        // Example: Using Firebase Authentication to sign up user
         FirebaseAuth mAuth = FirebaseAuth.getInstance();

         mAuth.createUserWithEmailAndPassword(email, password)
                 .addOnCompleteListener(this, task -> {
                     if (task.isSuccessful()) {
                         // Sign up success
                         Toast.makeText(signupActivity.this, "Sign Up Successful!", Toast.LENGTH_SHORT).show();
                         // Redirect to Login page
                         Intent intent = new Intent(signupActivity.this, LoginActivity.class);
                         startActivity(intent);
                         finish();
                     } else {
                         // Sign up failed
                         Toast.makeText(signupActivity.this, "Sign Up Failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                     }
                 });

        // For this example, let's assume the sign-up is successful:
        Toast.makeText(signupActivity.this, "Sign Up Successful!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(signupActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
