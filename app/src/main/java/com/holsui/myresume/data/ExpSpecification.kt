package com.holsui.myresume.data

open class ExpSpecification(open val content: String) {

    data class ExpSpecHeader(override val content: String) : ExpSpecification(content)
    data class DotDescription(override val content: String) : ExpSpecification(content)
}
