package comq.example.yigit.challaneepigra.Network;

import comq.example.yigit.challaneepigra.data.model.RetrofitClient;

/**
 * Created by yigit on 23.01.2018.
 */

public class BaseUrl {



        public static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

        public static ApiService getSOService() {
            return RetrofitClient.getClient(BASE_URL).create(ApiService.class);
        } }

