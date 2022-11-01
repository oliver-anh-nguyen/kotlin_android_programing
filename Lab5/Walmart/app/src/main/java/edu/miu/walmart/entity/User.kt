package edu.miu.walmart.entity

import java.io.Serializable

class User(
    val firstName: String,
    val lastName: String,
    val username: String,
    val password: String
) : Serializable