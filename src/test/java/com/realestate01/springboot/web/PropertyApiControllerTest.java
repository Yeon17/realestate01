package com.realestate01.springboot.web;

import com.realestate01.springboot.domain.property.Property;
import com.realestate01.springboot.domain.property.PropertyRepository;
import com.realestate01.springboot.web.dto.PropertySaveRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PropertyApiControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    PropertyRepository propertyRepository;

    @After
    public void tearDown() throws Exception{
        propertyRepository.deleteAll();
    }
    @Test
    public void enroll_property() throws Exception{
        PropertySaveRequestDto requestDto = PropertySaveRequestDto.builder()
                .apart("dong")
                .address("address")
                .trade("trade")
                .price("12L")
                .admin_expense("12L")
                .include("include")
                .floor_current("5")
                .floor_total("14")
                .floor_height("floor_height")
                .room("4")
                .bathroom("1")
                .actual_area("13")
                .actual_area_py("13")
                .supply_area("13")
                .supply_area_py("13")
                .park("5")
                .park_all("6")
                .direction("direction")
                .elevator("13")
                .enter_date("enter_date")
                .heat("heat")
                .household("13")
                .title("title")
                .content("content")
                .image("image")
                .represent_img("represent_img")
                .build();




        String url = "http://localhost:" + port + "api/v1/property";

        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        List<Property> all = propertyRepository.findAll();
        Property p = all.get(0);
        assertThat(p.getApart()).isEqualTo("dong");
        assertThat(p.getTitle()).isEqualTo("title");
        assertThat(p.getContent()).isEqualTo("content");
        assertThat(p.getRepresent_img()).isEqualTo("represent_img");
    }
}
