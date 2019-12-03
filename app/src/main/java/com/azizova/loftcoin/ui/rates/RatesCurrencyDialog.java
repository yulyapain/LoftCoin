package com.azizova.loftcoin.ui.rates;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.azizova.loftcoin.R;
import com.azizova.loftcoin.data.Currency;
import com.azizova.loftcoin.databinding.DialogCurrencyBinding;
import com.azizova.loftcoin.ui.widget.CurrencyAdapter;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.Arrays;

public class RatesCurrencyDialog extends AppCompatDialogFragment {

    private DialogCurrencyBinding binding;

    private CurrencyAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new CurrencyAdapter();
        adapter.submitList(Arrays.asList(
                Currency.create("$", getString(R.string.usd), "USD"),
                Currency.create("€", getString(R.string.eur), "EUR"),
                Currency.create("₽", getString(R.string.rub), "RUB")
        ));
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        binding = DialogCurrencyBinding.inflate(requireActivity().getLayoutInflater());
        return new MaterialAlertDialogBuilder(requireActivity())
                .setView(binding.getRoot())
                .create();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding.recycler.setLayoutManager(new LinearLayoutManager(requireActivity()));
        binding.recycler.setAdapter(adapter);
    }

    @Override
    public void onDestroy() {
        binding.recycler.setAdapter(null);
        super.onDestroy();
    }
}
