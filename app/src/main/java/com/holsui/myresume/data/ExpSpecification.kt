package com.holsui.myresume.data

open class ExpSpecification(content: String) {
    data class ExpSpecHeader(val content: String) : ExpSpecification(content)
    data class DotDescription(val content: String) : ExpSpecification(content)
}
