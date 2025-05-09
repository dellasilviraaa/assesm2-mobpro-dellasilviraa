package com.dellas0095.assesmobpro2.navigation

import com.dellas0095.assesmobpro2.ui.screen.KEY_ID_PELANGGAN


sealed class Screen(val route: String) {

    data object Home : Screen("mainScreen")
    data object FormBaru : Screen("detailScreen")
    data object FormUbah : Screen("detailScreen/{$KEY_ID_PELANGGAN}") {

        fun withId(id: Long) = "detailScreen/$id"
    }
}