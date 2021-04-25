package com.julyseven.common.entity

import javax.persistence.*

@Entity
@Table(name = "USER")
data class User(
    @Id
    @Column(name = "USER_ID")
    var userId: String,
    @Column(name = "USER_NAME")
    var userName: String,
    @Column(name = "password")
    var password: String
) {

}