package pe.pucp.tel306.my.smartb2b.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import pe.pucp.tel306.my.smartb2b.R;
import pe.pucp.tel306.my.smartb2b.fragments.EmpresaMisDatosFragment;
import pe.pucp.tel306.my.smartb2b.fragments.EmpresaMisFacturasFragment;
import pe.pucp.tel306.my.smartb2b.fragments.EmpresaMisFacturasRecibidasFragment;

public class EmpresaActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    BottomNavigationView botnav;

    private static final String TAG = "EmpresaActivity";
    FirebaseUser fbu = FirebaseAuth.getInstance().getCurrentUser();

    //verifica el logueo del usuario
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(fbu != null){
            //si esta logueado carga el contenido de la actividad de la empresa
            Log.d(TAG, "onCreate: FirebaseAuth dice que currentUser no es null: UID = " + fbu.getUid() );
            setContentView(R.layout.activity_empresa);
            botnav = findViewById(R.id.botnav);
            botnav.setOnNavigationItemSelectedListener(this);
            Log.d(TAG, "onCreate: EmpresaActivity inicializada");
        }else {

            //si no esta logueado vuelve al main
            Log.d(TAG, "onCreate: cambiando a MainActivity porque FirebaseAuth dice que currentUser es null");
            startActivity(new Intent(this, MainActivity.class));
        }

    }
    //menu de abajito
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.navigation_misdatos:
                openFragment(EmpresaMisDatosFragment.newInstance());
                return true;
            case R.id.navigation_facturas_emitidas:
                openFragment(EmpresaMisFacturasFragment.newInstance());
                return true;
            case R.id.navigation_facturas_recibidas:
                openFragment(EmpresaMisFacturasRecibidasFragment.newInstance());
                return true;
        }
        return false;
    }
    //metodo para abrir fragment
    public void openFragment( Fragment fragment ){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayoutForFragment,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}