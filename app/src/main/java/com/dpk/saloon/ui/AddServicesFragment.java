package com.dpk.saloon.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.dpk.saloon.databinding.FragmentAddServicesBinding;
import com.dpk.saloon.viewmodel.StoreSignupViewModel;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddServicesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddServicesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private StoreSignupViewModel storeSignupViewModel;
    private @NonNull FragmentAddServicesBinding fragmentAddServicesBinding;
    public AddServicesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddServicesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddServicesFragment newInstance(String param1, String param2) {
        AddServicesFragment fragment = new AddServicesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       storeSignupViewModel = new ViewModelProvider(this).get(StoreSignupViewModel.class);
         fragmentAddServicesBinding= FragmentAddServicesBinding.inflate(getLayoutInflater());
         fragmentAddServicesBinding.addServiceSaveButton.setOnClickListener(v -> {
             ArrayList<String> storeServices= new ArrayList<>();
             List list=fragmentAddServicesBinding.storeServicesGroup.getCheckedChipIds();
             for(int i=0;i<list.size();i++){
                 Chip c=container.findViewById((Integer) list.get(i));
                 storeServices.add(c.getText().toString());
             }
             storeSignupViewModel.saveStoreServices(storeServices,getView());
         });
        return fragmentAddServicesBinding.getRoot();
    }
}