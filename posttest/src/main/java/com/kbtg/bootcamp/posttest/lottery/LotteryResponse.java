package com.kbtg.bootcamp.posttest.lottery;

public class LotteryResponse {

    private String ticket;

    private LotteryTicketModel lotteryTicketModel;

    private int id;

    public LotteryResponse(String ticket, int id) {
        this.ticket = ticket;
        this.id = id;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
