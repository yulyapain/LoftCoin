package com.azizova.loftcoin.ui.rates;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.azizova.loftcoin.R;
import com.azizova.loftcoin.data.CmcApi;
import com.azizova.loftcoin.data.CmcApiProvider;
import com.azizova.loftcoin.data.Listings;
import com.azizova.loftcoin.databinding.FragmentRatesBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class RatesFragment extends Fragment {

    private CmcApi api;

    private RatesAdapter adapter;

    private FragmentRatesBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = new CmcApiProvider().get();
        adapter = new RatesAdapter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentRatesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        binding.recycler.setLayoutManager(new LinearLayoutManager(view.getContext()));
        binding.recycler.setHasFixedSize(true);
        binding.recycler.swapAdapter(adapter, false);
        binding.refresher.setOnRefreshListener(this::refresh);
        refresh();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.rates, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (R.id.currency == item.getItemId()) {
            new RatesCurrencyDialog().show(getChildFragmentManager(), RatesCurrencyDialog.class.getName());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroyView() {
        binding.recycler.swapAdapter(null, false);
        super.onDestroyView();
    }

    private void refresh() {
        binding.refresher.setRefreshing(true);
        api.listings().enqueue(new Callback<Listings>() {
            @Override
            public void onResponse(Call<Listings> call, Response<Listings> response) {
                binding.refresher.setRefreshing(false);
                final Listings body = response.body();
                if (body != null) {
                    adapter.submitList(body.coins());
                }
            }

            @Override
            public void onFailure(Call<Listings> call, Throwable t) {
                binding.refresher.setRefreshing(false);
                Timber.d(t);
            }
        });
    }

}
