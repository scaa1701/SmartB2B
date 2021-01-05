package pe.pucp.tel306.my.smartb2b.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import pe.pucp.tel306.my.smartb2b.R;
import pe.pucp.tel306.my.smartb2b.fragments.LoginFragment;

public class MainActivity extends AppCompatActivity {


    private static final int FIREBASE_OK = 100;
    private static final String TAG = "MainActivity";

    // FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            setContentView(R.layout.activity_main);
            LoginFragment loginFragment = LoginFragment.newInstance();
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction().add(R.id.myFrame, loginFragment).commit();
        } else {
            // verificar si es admin o si es empresa
            verificarRegistro();

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == FIREBASE_OK) {
            verificarRegistro();
        }

    }

    private void verificarRegistro() {
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        firestore.collection("empresas").document(uid).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    if (task.getResult().get("rol") != null) {
                        Log.d(TAG, "onComplete: " + task.getResult().get("rol"));
                        Log.d(TAG, "onComplete: iniciar la otra activity !");
                        cambiarActivity(task.getResult().get("rol").toString());
                    } else {
                        Log.d(TAG, "onComplete: " + task.getResult().get("rol"));
                        addRolEmpresa(firestore, uid);
                        Log.d(TAG, "onComplete: iniciar la otra activity !");
                        cambiarActivity(task.getResult().get("rol").toString());
                    }
                }
            }
        });
    }

    private void cambiarActivity(String rol) {
        Log.d(TAG, "cambiarActivity: " + rol);
        switch (rol) {
            case "empresa":
                Intent intent = new Intent(this,EmpresaActivity.class);
                startActivity(intent);
                break;
            case "admin":
                // TODO activity del admin
                break;
            default:
                // caso desconocido (?)
                break;
        }
    }

    //anadir el rol de la empresa en firestore
    private void addRolEmpresa(FirebaseFirestore firestore, String uid) {
        Map<String, Object> empresainfo = new HashMap<>();
        empresainfo.put("rol", "empresa");
        firestore.collection("empresas").document(uid).set(empresainfo).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(TAG, "onSuccess: DocumentSnapshot successfully written!");

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e(TAG, "onFailure: error writing document", e);
                e.printStackTrace();
            }
        });
    }
}