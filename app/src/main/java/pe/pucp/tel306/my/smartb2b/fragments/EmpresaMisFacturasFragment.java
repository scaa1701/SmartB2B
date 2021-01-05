package pe.pucp.tel306.my.smartb2b.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pe.pucp.tel306.my.smartb2b.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EmpresaMisFacturasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EmpresaMisFacturasFragment extends Fragment {

    public EmpresaMisFacturasFragment() {
        // Required empty public constructor
    }

    public static EmpresaMisFacturasFragment newInstance() {
        EmpresaMisFacturasFragment fragment = new EmpresaMisFacturasFragment();
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_empresa_mis_facturas, container, false);
    }
}