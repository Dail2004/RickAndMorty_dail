package com.example.RickAndMorty_Dail.ui.fragment.character.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.fragment.app.DialogFragment;

import com.bumptech.glide.Glide;
import com.example.RickAndMorty_Dail.databinding.FragmentDialogBinding;

public class CharacterDialogFragment extends DialogFragment {
    FragmentDialogBinding binding;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        binding = FragmentDialogBinding.inflate(LayoutInflater.from(getActivity()));
        AlertDialog builder = new AlertDialog.Builder(getActivity())
                .setView(binding.getRoot())
                .create();
        setupArgs();
        return builder;
    }

    private void setupArgs() {
        Glide.with(binding.dialogImage)
                .load(CharacterDialogFragmentArgs.fromBundle(getArguments()).getImage())
                .into(binding.dialogImage);
    }
}