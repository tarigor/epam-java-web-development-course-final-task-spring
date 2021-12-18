package com.epam.hotelgrodnoinn.entity;

import javax.persistence.*;

@Entity
@Table(name = "client_request")
public class ClientRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "client_request_id")
    public Integer clientRequestID;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "client_r_id", nullable = false)
    public User user;

    public ClientRequest() {
    }

    public ClientRequest(Integer clientRequestID, User user) {
        this.clientRequestID = clientRequestID;
        this.user = user;
    }

    public Integer getClientRequestID() {
        return clientRequestID;
    }

    public void setClientRequestID(Integer clientRequestID) {
        this.clientRequestID = clientRequestID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
