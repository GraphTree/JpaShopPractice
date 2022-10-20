package jpabook.jpashop.service;

import jpabook.jpashop.domain.*;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.repository.OrderRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;



@SpringBootTest
@Transactional
public class OrderServiceTest {
    @Autowired EntityManager em;
    @Autowired OrderService orderService;
    @Autowired OrderRepository orderRepository;



    @Test
    public void 상품주문() throws Exception{


//        given
        Member member = new Member();
        member.setName("member1");
        member.setAddress(new Address("seoul", "river", "934=134"));
        em.persist(member);

        Book book = new Book();
        book.setName("pricnciple");
        book.setPrice(1000);
        book.setStockQuantity(10);
        em.persist(book);

        int orderCount = 2;


//        when
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

//        then
        Order getOrder = orderRepository.findOne(orderId);

        Assert.assertEquals("상품 주문시 상태는 order", OrderStatus.ORDER, getOrder.getStatus() );
    }

    @Test
    public void 주문취소() throws Exception{
        //given
        
        //when
        
        //then
    }
    @Test
    public void 상품주문_재고수량초과() throws Exception{
        //given
        
        //when
        
        //then
    }
}
