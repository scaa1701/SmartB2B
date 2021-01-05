package pe.pucp.tel306.my.smartb2b.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.firebase.ui.auth.AuthUI;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

import pe.pucp.tel306.my.smartb2b.R;
import pe.pucp.tel306.my.smartb2b.activities.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment implements View.OnClickListener{

    public LoginFragment() {
        // Required empty public constructor
    }

    @NotNull
    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        // Bundle args = new Bundle();
        // fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // if (getArguments() != null) { }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        Button btnLogin = view.findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        return view;
    }

    public void login() {
        List<AuthUI.IdpConfig> proveedores = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build()
        );
        AuthUI instance = AuthUI.getInstance();
        Intent intent = instance
                .createSignInIntentBuilder()
                .setLogo(R.mipmap.cloud_computing)
                .setIsSmartLockEnabled(false)
                .setAvailableProviders(proveedores)
                .build();
        ((MainActivity) getActivity()).startActivityForResult(intent,1);
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnLogin){
            login();
        }
    }
}