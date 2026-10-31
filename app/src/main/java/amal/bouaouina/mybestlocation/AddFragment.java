package amal.bouaouina.mybestlocation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import amal.bouaouina.mybestlocation.databinding.FragmentAddBinding;
import amal.bouaouina.mybestlocation.databinding.FragmentHomeBinding;

public class AddFragment extends Fragment {

    FragmentAddBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddBinding.inflate(inflater);
        return binding.getRoot();
    }
}