package jpabook.jpashop;

import jpabook.jpashop.domain.*;
import jpabook.jpashop.domain.item.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitDb {
    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
        initService.dbInit2();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService{
        private final EntityManager em;
        public void dbInit1() {
            Member member = new Member();
            member.setName("userA");
            member.setAddress(new Address("seoul", "1", "111"));
            em.persist(member );

            Book book = new Book();
            book.setName("jap1 book");
            book.setPrice(10000);
            book.setStockQuantity(100);
            em.persist(book);

            Book book1 = new Book();
            book1.setName("jap2 book");
            book1.setPrice(10000);
            book1.setStockQuantity(100);
            em.persist(book1);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book1, 10000, 2);

            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);

        }

        public void dbInit2() {
            Member member = new Member();
            member.setName("userB");
            member.setAddress(new Address("tokyo", "1", "111"));
            em.persist(member );

            Book book = new Book();
            book.setName("spring1 book");
            book.setPrice(10000);
            book.setStockQuantity(100);
            em.persist(book);

            Book book1 = new Book();
            book1.setName("spring2 book");
            book1.setPrice(10000);
            book1.setStockQuantity(100);
            em.persist(book1);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 20000, 2);
            OrderItem orderItem2 = OrderItem.createOrderItem(book1, 30000, 3);

            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);

        }
    }


}


