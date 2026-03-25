package com.feedback.anonymousfeedback.dto;

public class AdminStatsResponse {

    private long totalFeedback;
    private long todayFeedback;

    public AdminStatsResponse(long totalFeedback, long todayFeedback) {
        this.totalFeedback = totalFeedback;
        this.todayFeedback = todayFeedback;
    }

    public long getTotalFeedback() {
        return totalFeedback;
    }

    public long getTodayFeedback() {
        return todayFeedback;
    }
}