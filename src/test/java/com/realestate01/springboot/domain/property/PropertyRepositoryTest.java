package com.realestate01.springboot.domain.property;

import com.realestate01.springboot.domain.request.Request;
import com.realestate01.springboot.domain.request.RequestRepository;
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
public class PropertyRepositoryTest {
    @Autowired
    PropertyRepository propertyRepository;

    @After
    public void cleanup(){
        propertyRepository.deleteAll();
    }

    @Test
    public void 내용_불러오기(){
        String address="ehd";

        String trade = "qq";

        String price = "123";

        String admin_expense = "123";

        String include = "ads";

        String floor_current = "12";

        String floor_total = "21";

        String floor_height = "fd";

        String room = "3";

        String bathroom = "2";

        String actual_area= "234";

        String actual_area_py= "324";

        String supply_area = "324";

        String supply_area_py = "345";

        String park ="3";

        String park_all = "4";

        String direction = "adsf";

        String elevator= "3";

        String enter_date= "wer";

        String heat = "qer";

        String household = "3";

        String title = "asdf";

        String content = "wer";

        String image = "QWERQWERE";


        propertyRepository.save(Property.builder()
                .address(address)
                .trade(trade)
                .price(price)
                .admin_expense(admin_expense)
                .include(include)
                .floor_current(floor_current)
                .floor_total(floor_total)
                .floor_height(floor_height)
                .room(room)
                .bathroom(bathroom)
                .actual_area(actual_area)
                .actual_area_py(actual_area_py)
                .supply_area(supply_area)
                .supply_area_py(supply_area_py)
                .park(park)
                .park_all(park_all)
                .direction(direction)
                .elevator(elevator)
                .enter_date(enter_date)
                .heat(heat)
                .household(household)
                .title(title)
                .content(content)
                .image(image)
                .build());


        List<Property> requestList = propertyRepository.findAll();

        Property all = requestList.get(0);
        assertThat(all.getAddress()).isEqualTo(address);
        assertThat(all.getTrade()).isEqualTo(trade);
        assertThat(all.getPrice()).isEqualTo(price);
        assertThat(all.getAdmin_expense()).isEqualTo(admin_expense);
        assertThat(all.getInclude()).isEqualTo(include);
        assertThat(all.getPrice()).isEqualTo(price);
        assertThat(all.getFloor_current()).isEqualTo(floor_current);
        assertThat(all.getFloor_total()).isEqualTo(floor_total);
        assertThat(all.getFloor_height()).isEqualTo(floor_height);
        assertThat(all.getRoom()).isEqualTo(room);
        assertThat(all.getBathroom()).isEqualTo(bathroom);

    }
}
