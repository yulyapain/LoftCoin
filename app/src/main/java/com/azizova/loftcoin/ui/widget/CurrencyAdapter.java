package com.azizova.loftcoin.ui.widget;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.azizova.loftcoin.data.Currency;
import com.azizova.loftcoin.databinding.LiCurrencyBinding;

import java.util.Objects;

public class CurrencyAdapter extends ListAdapter<Currency, CurrencyAdapter.ViewHolder> {

    private LayoutInflater inflater;

    public CurrencyAdapter() {
        super(new DiffUtil.ItemCallback<Currency>() {
            @Override
            public boolean areItemsTheSame(@NonNull Currency oldItem, @NonNull Currency newItem) {
                return Objects.equals(oldItem.code(), newItem.code());
            }

            @Override
            public boolean areContentsTheSame(@NonNull Currency oldItem, @NonNull Currency newItem) {
                return Objects.equals(oldItem, newItem);
            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LiCurrencyBinding.inflate(inflater, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Currency currency = getItem(position);
        holder.binding.symbol.setText(currency.symbol());
        holder.binding.name.setText(currency.name());
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        inflater = LayoutInflater.from(recyclerView.getContext());
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @NonNull
        private final LiCurrencyBinding binding;

        ViewHolder(@NonNull LiCurrencyBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }

}
