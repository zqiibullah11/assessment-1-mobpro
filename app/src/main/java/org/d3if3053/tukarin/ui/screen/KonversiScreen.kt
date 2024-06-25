package org.d3if3053.tukarin.ui.screen

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults.outlinedTextFieldColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults.colors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.d3if3053.tukarin.R
import org.d3if3053.tukarin.navigation.Screen
import org.d3if3053.tukarin.ui.theme.CustomDarkGrayBG
import org.d3if3053.tukarin.ui.theme.CustomGrayCard
import org.d3if3053.tukarin.ui.theme.CustomOrange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KonversiScreen(navHostController: NavHostController) {
    Scaffold(topBar = {
        TopAppBar(
            title = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { navHostController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(R.string.app_info_icon),
                            tint = Color.White,
                        )
                    }
                    Text(
                        text = stringResource(R.string.konversi_title),
                        fontWeight = FontWeight.Bold
                    )
                    IconButton(onClick = { navHostController.navigate(Screen.About.route) }) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = stringResource(R.string.app_info_icon),
                            tint = Color.White,
                        )
                    }
                }
            },
            colors = topAppBarColors(
                containerColor = Color.Transparent,
                titleContentColor = Color.White
            )
        )
    }, containerColor = CustomDarkGrayBG) {
        ScreenContent(modifier = Modifier.padding(it))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ScreenContent(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var nominal by rememberSaveable { mutableStateOf("") }
    var mataUang by rememberSaveable { mutableStateOf("") }
    var options = listOf("Rupiah", "Dollar")
    var isSelected by rememberSaveable { mutableStateOf(false) }
    var isClicked by rememberSaveable { mutableStateOf(false) }
    var dataError by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 48.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.nominal_title),
            modifier = Modifier.fillMaxWidth(),
            color = Color.White,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            enabled = !isClicked,
            shape = RoundedCornerShape(10.dp),
            value = nominal,
            onValueChange = { nominal = it },
            colors = outlinedTextFieldColors(
                unfocusedContainerColor = CustomGrayCard,
                focusedContainerColor = CustomGrayCard,
                disabledContainerColor = CustomGrayCard,
                focusedBorderColor = CustomOrange,
                unfocusedBorderColor = CustomOrange,
                disabledBorderColor = CustomOrange,
                unfocusedPrefixColor = Color.White,
                focusedPrefixColor = Color.White,
                disabledPrefixColor = Color.White,
                focusedSuffixColor = Color.White,
                unfocusedSuffixColor = Color.White,
                disabledSuffixColor = Color.White
            ),
            prefix = if (mataUang != "" && mataUang == "Dollar") {
                { Text(text = "Rp. ") }
            } else {
                null
            },
            suffix = if (mataUang != "" && mataUang == "Rupiah") {
                { Text(text = "$") }
            } else {
                null
            },
            textStyle = TextStyle(
                color = Color.White
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(R.string.konversi_kemana_title),
            modifier = Modifier.fillMaxWidth(),
            color = Color.White,
            fontWeight = FontWeight.SemiBold
        )
        Column(modifier = Modifier.fillMaxWidth()) {
            options.forEach {
                RadioOption(text = it, isSelected = it == mataUang && !isClicked) {
                    if (!isClicked) {
                        mataUang = it
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        if (isClicked) {
            if (mataUang == "Rupiah")
                Text(text = calculate(mataUang, nominal), color = Color.White)
            else
                Text(text = calculate(mataUang, nominal), color = Color.White)
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                Button(
                    onClick = {
                        mataUang = ""
                        nominal = ""
                        isClicked = false
                    },
                    colors = buttonColors(containerColor = CustomOrange, contentColor = Color.White)
                ) {
                    Text(text = stringResource(R.string.reset_button))
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(
                    onClick = {
                        shareData(
                            context, context.getString(
                                R.string.bagikan_template,
                                if (mataUang == "Dollar") formatRupiah(nominal.toLong()) else "$nominal$",
                                mataUang,
                                calculate(mataUang, nominal)
                            )
                        )
                    },
                    colors = buttonColors(containerColor = CustomOrange, contentColor = Color.White)
                ) {
                    Text(text = stringResource(R.string.bagikan_button))
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 52.dp),
            shape = RoundedCornerShape(10.dp),
            onClick = {
                if (mataUang.isNotEmpty() && nominal.isNotEmpty())
                    isClicked = true
                else
                    Toast.makeText(context,
                        context.getString(R.string.harap_lengkapi_semua_data_toast), Toast.LENGTH_LONG).show()
            },
            colors = buttonColors(containerColor = CustomOrange, contentColor = Color.White)
        ) {
            Text(text = stringResource(R.string.hitung_button_title))
        }
    }
}

@Composable
fun RadioOption(
    modifier: Modifier = Modifier,
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        RadioButton(
            selected = isSelected,
            onClick = { onClick() },
            colors = colors(selectedColor = CustomOrange, unselectedColor = Color.White)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = text, color = Color.White)
    }
}


@SuppressLint("DefaultLocale")
private fun calculate(mataUang: String, nominal: String): String {
    val oneUsdInRupiah = 16373
    if (mataUang == "Dollar") {
        return "${String.format(" % .2f", nominal.toDouble() / oneUsdInRupiah).toDouble()}$"
    } else {
        return formatRupiah((nominal.toLong() * oneUsdInRupiah).toLong())
    }
}

@SuppressLint("DefaultLocale")
fun formatRupiah(amount: Long): String {
    val formattedAmount = String.format("%,d", amount).replace(',', '.')
    return "Rp. $formattedAmount"
}

@SuppressLint("QueryPermissionsNeeded")
private fun shareData(context: Context, message: String) {
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, message)
    }
    if (shareIntent.resolveActivity(context.packageManager) != null) {
        context.startActivity(shareIntent)
    }
}