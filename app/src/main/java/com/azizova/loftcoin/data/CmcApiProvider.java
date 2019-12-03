package com.azizova.loftcoin.data;

import com.azizova.loftcoin.BuildConfig;
import com.squareup.moshi.Moshi;

import javax.inject.Provider;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class CmcApiProvider implements Provider<CmcApi> {
    @Override
    public CmcApi get() {
        return Holder.API;
    }

    private static final class Holder {
        static final CmcApi API;

        static {
            final OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
            okHttpBuilder.addInterceptor(chain -> chain.proceed(chain.request().newBuilder()
                    .addHeader(CmcApi.API_KEY, BuildConfig.CMC_API_KEY)
                    .build()));
            if (BuildConfig.DEBUG) {
                final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                interceptor.level(HttpLoggingInterceptor.Level.HEADERS);
                interceptor.redactHeader(CmcApi.API_KEY);
                okHttpBuilder.addInterceptor(interceptor);
            }
            final Moshi moshi = new Moshi.Builder().build();
            final Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpBuilder.build())
                    .baseUrl(BuildConfig.CMC_API_ENDPOINT)
                    .addConverterFactory(MoshiConverterFactory.create(moshi.newBuilder()
                            .add(Listings.class, moshi.adapter(AutoValue_Listings.class))
                            .build()))
                    .build();
            API = retrofit.create(CmcApi.class);
        }
    }
}
