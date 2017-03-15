package com.example.ola.lagosjavadevs;

import java.util.List;

/**
 * Created by 246 on 3/15/2017.
 */

public class Dev {




    private List<ItemComponents> items;



    public List<ItemComponents> getItems() {
        return items;
    }

    public void setItems(List<ItemComponents> items) {
        this.items = items;
    }

    public static class ItemComponents {
        private String login;
        private String avatar_url;
        private String url;


        public String getLogin() {

            return login;
        }





        public String getAvatar_url() {

            return avatar_url;
        }





        public String getUrl() {
            return url;
        }








    }
}
