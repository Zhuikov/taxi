package taxiApp.core;

//import taxiApp.repository.OrderRepository;

import javax.persistence.*;

@Entity
@Table(name = "Drivers")
@PrimaryKeyJoinColumn(name = "id_user")
public class Driver extends User {

    @Enumerated
    @Column(name = "status", columnDefinition = "smallint")
    private DriverStatus status = DriverStatus.FREE;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_car", referencedColumnName = "id")
    private Car car;
    // todo check it when login
    @Column
    private boolean active = false;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_order", referencedColumnName = "id")
    private Order order = null;

    public Driver(String login, PersonInfo personInfo, Car car) {
        super(login, personInfo);
        this.car = car;
    }

    public Driver() {
        super();
    }

    public DriverStatus getStatus() {
        return status;
    }

    public void setStatus(DriverStatus status) {
        this.status = status;
    }

    public Car getCar() {
        return car;
    }

    public Order getOrder() {
        return order;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public boolean acceptOrder(Order order) {
//        if (this.order != null || status != DriverStatus.FREE) {
//            return false;
//        }
//        Message message = new Message(
//                messageRepository.getUnusedId(), id, role, order.getManagerId(),
//                UserRole.MANAGER, MessageType.ACK, order.id
//        );
//        messageRepository.add(message);

        return true;
    }

    public boolean rejectOrder(Order order) {
//        Message message = new Message(
//                messageRepository.getUnusedId(), id, role, order.getManagerId(),
//                UserRole.MANAGER, MessageType.NACK, order.id
//        );
//        messageRepository.add(message);

        return true;
    }

    public void finishOrder() {
        this.status = DriverStatus.FREE;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
