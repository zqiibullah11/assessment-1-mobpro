package org.d3if3053.tukarin.navigation

const val KEY_ID_CATATAN = "idCatatan"

sealed class Screen (val route: String) {
    data object Home: Screen("mainScreen")
    data object About: Screen("aboutScreen")
    data object Konversi: Screen("konversiScreen")
    data object Catatan: Screen("catatanScreen")
    data object FormBaru: Screen("detailScreen")
    data object FormUbah: Screen("detailScreen/{$KEY_ID_CATATAN}") {
        fun withId(id: Int) = "detailScreen/$id"
    }
}
