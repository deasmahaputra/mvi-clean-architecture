package com.deas.navigation

sealed class Screen(val params: String) {
    object Home : Screen("home_screen")
    object Detail : Screen("details_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(params)
            args.forEach { args ->
                append("/$args")
            }
        }
    }
}