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

import static org.assertj.core.api.Fail.fail;


@SpringBootTest
@Transactional
public class OrderServiceTest {
    @Autowired EntityManager em;
    @Autowired OrderService orderService;
    @Autowired OrderRepository orderRepository;



    @Test
    public void 상품주문() throws Exception{


//        given
        Member member = createMember("member1");
        Book book = createBook("book0", 10000, 10);

        int orderCount = 2;


//        when
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

//        then
        Order getOrder = orderRepository.findOne(orderId);

        Assert.assertEquals("상품 주문시 상태는 order", OrderStatus.ORDER, getOrder.getStatus() );
    }

    private Book createBook(String name,int price,int quantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(quantity);
        em.persist(book);
        return book;
    }

    private Member createMember(String name) {
        Member member = new Member();
        member.setName(name);
        member.setAddress(new Address("seoul", "river", "934=134"));
        em.persist(member);
        return member;
    }

    @Test
    public void 주문취소() throws Exception{
        //given
        Member member = createMember("member3");
        Book book = createBook("book", 10000, 10);

        int orderCount = 10;
        //when
        
        //then
    }
    @Test
    public void 상품주문_재고수량초과() throws Exception{
        //given
        Member member = createMember("member2");
        Book book = createBook("book", 10000, 10);

        int orderCount = 10;
        //when
        orderService.order(member.getId(), book.getId(), orderCount);

        
        //then
        fail("재고수량 예외가 터져야함");
    }
}
