package com.example.laboratorio_no5_kmrl;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import com.example.laboratorio_no5_kmrl.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

private FragmentFirstBinding binding;

//declaracion de variables
private RadioButton tel1;
private RadioButton tel2;
private RadioButton tel3;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

      binding = FragmentFirstBinding.inflate(inflater, container, false);
      return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnLlamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //creacion de variable intento y llamada al metodo para obtener el numero
                Intent intent = new Intent(Intent.ACTION_DIAL);

                String telefono = getPhone();

                intent.setData(Uri.parse(telefono));
                startActivity(intent);

            }

            /**
             * Metodo que regresa el numero de telefono
             * @return
             */
            private String getPhone(){
                tel1 = view.findViewById(R.id.rdbTel1);
                tel2 = view.findViewById(R.id.rdbTel2);
                tel3 = view.findViewById(R.id.rdbTel3);

                String num = "tel:";

                if(tel1.isChecked()){
                    num = num + "4959561635";
                }else if(tel2.isChecked()){
                    num = num + "4959567228";
                }else if(tel3.isChecked()){
                    num = num + "4493206501";
                }
                return num;
            }

        });
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}