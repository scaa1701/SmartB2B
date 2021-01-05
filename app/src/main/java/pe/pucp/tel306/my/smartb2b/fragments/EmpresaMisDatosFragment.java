package pe.pucp.tel306.my.smartb2b.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pe.pucp.tel306.my.smartb2b.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EmpresaMisDatosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EmpresaMisDatosFragment extends Fragment {

    public EmpresaMisDatosFragment() {
    }

    public static EmpresaMisDatosFragment newInstance() {
        EmpresaMisDatosFragment fragment = new EmpresaMisDatosFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_empresa_mis_datos, container, false);
    }
}