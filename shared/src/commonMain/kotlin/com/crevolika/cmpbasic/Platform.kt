package com.crevolika.cmpbasic

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform