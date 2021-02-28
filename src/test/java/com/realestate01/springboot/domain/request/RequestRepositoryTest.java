package com.realestate01.springboot.domain.request;

import com.realestate01.springboot.web.dto.RequestSaveRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RequestRepositoryTest {

    @Autowired
    RequestRepository requestRepository;

    @After
    public void cleanup(){
        requestRepository.deleteAll();
    }

    @Test
    public void 의뢰내용_불러오기(){
        String trade = "buy";
        String addressKindU = "동천파크자이";
        String addressKindD = "61㎡ A형";
        String transaction = "전세";
        String price = "3억원~4억원";
        String date = "2021 8월";
        String visit = "1/27 오후 3시";
        String name = "김도연";
        String phone = "010-5380-8840";
        String message = "1/1/1에 찾아가겠습니다.";


        requestRepository.save(Request.builder()
                .trade(trade)
                .addressKindU(addressKindU)
                .addressKindD(addressKindD)
                .transaction(transaction)
                .price(price)
                .date(date)
                .visit(visit)
                .name(name)
                .phone(phone)
                .message(message)
                .build());


        List<Request> requestList = requestRepository.findAll();

        Request request = requestList.get(0);
        assertThat(request.getTrade()).isEqualTo(trade);
        assertThat(request.getAddressKindU()).isEqualTo(addressKindU);
        assertThat(request.getAddressKindD()).isEqualTo(addressKindD);
        assertThat(request.getTransaction()).isEqualTo(transaction);
        assertThat(request.getPrice()).isEqualTo(price);
        assertThat(request.getDate()).isEqualTo(date);
        assertThat(request.getVisit()).isEqualTo(visit);
        assertThat(request.getName()).isEqualTo(name);
        assertThat(request.getPhone()).isEqualTo(phone);
        assertThat(request.getMessage()).isEqualTo(message);

    }
}
