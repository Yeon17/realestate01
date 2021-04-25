package com.realestate01.springboot.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.realestate01.springboot.domain.property.Property;
import com.realestate01.springboot.domain.property.PropertyRepository;
import com.realestate01.springboot.web.dto.PropertySaveRequestDto;
import com.realestate01.springboot.web.dto.PropertyUpdateRequestDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @After
    public void tearDown() throws Exception{
        propertyRepository.deleteAll();
    }

    @Test
    @WithMockUser(roles="USER")
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
                .uid(21L)
                .build();

        String url = "http://localhost:" + port + "api/v1/property";

        mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        List<Property> all = propertyRepository.findAll();

        assertThat(all.get(0).getApart()).isEqualTo("dong");
        assertThat(all.get(0).getTitle()).isEqualTo("title");
        assertThat(all.get(0).getContent()).isEqualTo("봉천동에 위치한 인테리어가 예쁜 2룸 입니다. 내부 구조가 좋아 방도 넓게 사용하실 수 있습니다.");
        assertThat(all.get(0).getRepresent_img()).isEqualTo("represent_img");
    }

    @Test
    @WithMockUser(roles="USER")
    public void Property_수정된다() throws Exception {
        //given
        Property savedProperty = propertyRepository.save(Property.builder()
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
                .uid(21L)
                .build());

        Long updateId = savedProperty.getId();
        String expectedTitle = "title2";
        String expectedImage = "image2";

        PropertyUpdateRequestDto requestDto = PropertyUpdateRequestDto.builder()
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
                .title("title2")
                .content("봉천동에 위치한 인테리어가 예쁜 2룸 입니다. 내부 구조가 좋아 방도 넓게 사용하실 수 있습니다.")
                .image("image2")
                .represent_img("represent_img")
                .build();

        String url = "http://localhost:" + port + "/api/v1/property/" + updateId;

        mvc.perform(put(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        List<Property> all = propertyRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(all.get(0).getImage()).isEqualTo(expectedImage);

    }

}
