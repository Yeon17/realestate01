package com.realestate01.springboot.web;

import com.realestate01.springboot.domain.property.Property;
import com.realestate01.springboot.domain.property.PropertyRepository;
import com.realestate01.springboot.web.dto.PropertySaveRequestDto;
import com.realestate01.springboot.web.dto.PropertyUpdateRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
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
                .content("봉천동에 위치한 인테리어가 예쁜 2룸 입니다. 내부 구조가 좋아 방도 넓게 사용하실 수 있습니다.")
                .image("image")
                .represent_img("represent_img")
                .build();




        String url = "http://localhost:" + port + "api/v1/property";

        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        List<Property> all = propertyRepository.findAll();
        Property p = all.get(0);
        assertThat(p.getApart()).isEqualTo("dong");
        assertThat(p.getTitle()).isEqualTo("title");
        assertThat(p.getContent()).isEqualTo("봉천동에 위치한 인테리어가 예쁜 2룸 입니다. 내부 구조가 좋아 방도 넓게 사용하실 수 있습니다.");
        assertThat(p.getRepresent_img()).isEqualTo("represent_img");
    }

    @Test
    public void Property_수정된다() throws Exception {
        //given
        Property savedProperty = propertyRepository.save(Property.builder()
                .title("title")
                .image("image")
                .enter_date("enter_date")
                .build());

        Long updateId = savedProperty.getId();
        String expectedTitle = "title2";
        String expectedImage = "image2";

        PropertyUpdateRequestDto requestDto = PropertyUpdateRequestDto.builder()
                .title(expectedTitle)
                .image(expectedImage)
                .build();

        String url = "http://localhost:" + port + "/api/v1/property/" + updateId;
        HttpEntity<PropertyUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);
        List<Property> all = propertyRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(all.get(0).getImage()).isEqualTo(expectedImage);

    }

}
