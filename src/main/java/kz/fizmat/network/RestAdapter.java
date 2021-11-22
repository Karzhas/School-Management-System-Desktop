package kz.fizmat.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RestAdapter {
    private static Retrofit retrofit = null;
    private static FizmatApi apiInterface;

    public static FizmatApi getFizmatApi() {
        if (apiInterface == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://fizmat-app-by-karzhas.herokuapp.com")
//                    .baseUrl("http://localhost:8081")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(JacksonConverterFactory.create())
                    .build();

            apiInterface = retrofit.create(FizmatApi.class);
        }
        return apiInterface;
    }


}
