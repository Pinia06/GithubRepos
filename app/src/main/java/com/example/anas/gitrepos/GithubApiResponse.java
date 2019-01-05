package com.example.anas.gitrepos;

import java.util.List;

//This Class contains the 3 keys (total_count + incomplete_results + items) the API returns in JSON format
// Retrofit will associate each attribute to its value

public class GithubApiResponse {

    private int total_count;
    private Boolean incomplete_results;
    private List<Item> items;

    public GithubApiResponse(int total_count, Boolean incomplete_results, List<Item> items) {
        this.total_count = total_count;
        this.incomplete_results = incomplete_results;
        this.items = items;
    }

    public int getTotal_count() {
        return total_count;
    }

    public Boolean getIncomplete_results() {
        return incomplete_results;
    }

    public List<Item> getItems() {
        return items;
    }
}
