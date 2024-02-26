package com.kbtg.bootcamp.posttest.lottery;

import com.kbtg.bootcamp.posttest.lottery.LotteryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/")
public class LotteryTicketController {

    private final LotteryService lotteryService;

    public LotteryTicketController(LotteryService lotteryService) {
        this.lotteryService = lotteryService;
    }

    //EX02
    @GetMapping("/lotteries")
    public Map<String, List<String>> getLotteryList() { return lotteryService.getTicketList();}

    //EX03
    @PostMapping("users/{userId}/lotteries/{ticketId}")
    public Map<String, String> buyLottery (@PathVariable Integer userId, @PathVariable Integer ticketId) throws Exception {
        return lotteryService.buyLotteryById(userId, ticketId);
    }

    //EX04
    @GetMapping("/users/{userId}/lotteries")
    public Map<String, String> checkMyLottery (@PathVariable Integer userId) throws Exception {
        return lotteryService.checkMyLottery(userId);
    }

    //EX05
    @DeleteMapping("users/{userId}/lotteries/{ticketId}")
    public Map<String, String> deleteWalletById (@PathVariable Integer userId, @PathVariable Integer ticketId) throws Exception {
        return lotteryService.deleteTicketByTicketId(userId, ticketId);
    }

}
