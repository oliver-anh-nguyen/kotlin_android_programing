package edu.miu.profileapp

import java.io.Serializable

data class WorkEntity(
    val name: String,
    val title: String,
    val time: String,
    val location: String
) : Serializable