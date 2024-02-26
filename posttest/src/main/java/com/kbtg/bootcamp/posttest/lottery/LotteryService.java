package com.kbtg.bootcamp.posttest.lottery;

import jakarta.transaction.Transactional;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LotteryService {
    private final LotteryRepository lotteryRepository;

    public LotteryService(LotteryRepository lotteryRepository) {
        this.lotteryRepository = lotteryRepository;
    }

    public Map<String, List<String>> getTicketList() {
        List<LotteryTicketModel>  lotteryList = lotteryRepository.findAll();

        Map<String, List<String>> responseMap = new HashMap<>();
        responseMap.put("tickets", lotteryList.stream().map(LotteryTicketModel::getTicket).collect(Collectors.toList()));

        return responseMap;
    }

    @Transactional
    public Map<String, String> createTicket(LotteryRequest request) throws Exception {
        LotteryTicketModel lottery = new LotteryTicketModel();
        lottery.setTicket(request.getTicket());
        lottery.setPrice(request.getPrice());
        lottery.setAmount(request.getAmount());
        lotteryRepository.save(lottery);

        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("ticket", lottery.getTicket());

        return responseMap;
    }

    @Transactional
    public Map<String, String> buyLotteryById(Integer userId, Integer ticketId) throws Exception {
        Optional<LotteryTicketModel> optionalLottery = lotteryRepository.findById(Long.valueOf(ticketId));
        if (optionalLottery.isEmpty()) {
            throw new BadRequestException("Invalid Ticket Id");
        }
        LotteryTicketModel lottery = optionalLottery.get();
        lottery.setOwnerId(userId.intValue());
        lotteryRepository.save(lottery);

        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("id", String.valueOf(lottery.getId()));
        return responseMap;
    }

    public Map<String, String> checkMyLottery (Integer userId) {
        Optional<List<LotteryTicketModel>> optionalLottery = lotteryRepository.findByOwnerId(userId.intValue());
        List<LotteryTicketModel> myTicket = optionalLottery.get();
        List<String> ticketList = myTicket.stream().map(LotteryTicketModel::getTicket).toList();
        List<BigDecimal> ticketPrice = myTicket.stream().map(ticket -> BigDecimal.valueOf(ticket.getPrice())).collect(Collectors.toList());
        BigDecimal sumTicketPrice = ticketPrice.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        long countTicket = myTicket.size();

        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("tickets", ticketList.toString());
        responseMap.put("count", String.valueOf(countTicket));
        responseMap.put("totalPrice", sumTicketPrice.toString());
        return responseMap;
    }

    @Transactional
    public Map<String, String> deleteTicketByTicketId(Integer userId, Integer ticketId) throws Exception{
        Optional<LotteryTicketModel> optionalLottery = lotteryRepository.findById(Long.valueOf(ticketId));
        if (optionalLottery.isEmpty()) {
            throw new BadRequestException("Invalid Ticket Id");
        }

        LotteryTicketModel lottery = optionalLottery.get();
        lotteryRepository.deleteById(Long.valueOf(ticketId));

        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("ticket", String.valueOf(lottery.getTicket()));
        return responseMap;
    }
}
