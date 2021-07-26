package local;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;

@Entity
@Table(name="Order_table")
public class Order {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;         //주문번호
    private Long menuId;     //메뉴번호
    private String menuNm;   //메뉴명
    private String custNm;   //고객명
    private Long price;      //가격
    private String status;   //주문상태     

    @PostPersist
    public void onPostPersist(){;

        // 주문 요청함 ( Req / Res : 동기 방식 호출)
        local.external.Menu menu = new local.external.Menu();
        menu.setMenuId(getMenuId());
        // mappings goes here
        OrderManageApplication.applicationContext.getBean(local.external.MenuService.class)
            .orderRequest(menu.getMenuId(),menu);

        OrderRequested orderRequested = new OrderRequested();
        BeanUtils.copyProperties(this, orderRequested);
        orderRequested.publishAfterCommit();
    }

    @PostUpdate
    public void onPostUpdate(){

        System.out.println("#### onPostUpdate :" + this.toString());

        if("CANCELED".equals(this.getStatus())) {                   // Order에서 취소상태로 UPDATE시 주문취소로 Publish
            OrderCanceled orderCanceled = new OrderCanceled();
            BeanUtils.copyProperties(this, orderCanceled);
            orderCanceled.publishAfterCommit();
        }
        else if("FORCE_CANCELED".equals(getStatus())){              // Menu에서 DELETE시 강제주문취소로 Publish
            ForceCanceled forceCanceled = new ForceCanceled();
            BeanUtils.copyProperties(this, forceCanceled);
            forceCanceled.publishAfterCommit();
        }        
//        else if("PRODUCTION_COMPLETED".equals(getStatus())){        
//            System.out.println(getStatus());
//            System.out.println("## REQ Info : " + this.getMenuId());
//            OrderCompleted orderCompleted = new OrderCompleted();
//            BeanUtils.copyProperties(this, orderCompleted);
//            orderCompleted.publishAfterCommit();
//        }

    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getMenuId() {
        return menuId;
    }
    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public String getCustNm() {
        return custNm;
    }
    public void setCustNm(String custNm) {
        this.custNm = custNm;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getMenuNm() {
        return menuNm;
    }
    public void setMenuNm(String menuNm) {
        this.menuNm = menuNm;
    }

    public Long getPrice() {
        return price;
    }
    public void setPrice(Long price) {
        this.price = price;
    }

}
