package com.dellas0095.assesmobpro2.ui.screen

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dellas0095.assesmobpro2.R
import com.dellas0095.assesmobpro2.screen.DisplayAlertDialog
import com.dellas0095.assesmobpro2.ui.theme.Assesmobpro2Theme
import com.dellas0095.assesmobpro2.util.ViewModelFactory

const val KEY_ID_PELANGGAN = "idPelanggan"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavHostController, id : Long? = null) {
    val context = LocalContext.current
    val factory = ViewModelFactory(context)
    val viewModel: DetailViewModel = viewModel(factory = factory)


    var nama by remember { mutableStateOf("") }
    var jumlah by remember { mutableStateOf("") }
    var variant by remember { mutableStateOf("") }

    var showDialog by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        if (id == null) return@LaunchedEffect
        val data = viewModel.getPelanggan(id) ?: return@LaunchedEffect
        nama = data.nama
        jumlah = data.jumlah
        variant = data.variant
    }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.kembali),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                title = {
                    if (id == null)
                        Text(text = stringResource(id = R.string.tambah_pelanggan))
                    else
                        Text(text = stringResource(id = R.string.edit_pelanggan))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                actions = {
                    IconButton(onClick = {
                        if (nama.isEmpty() || jumlah.isEmpty() || variant.isEmpty()) {
                            Toast.makeText(context, "Data tidak boleh kosong!", Toast.LENGTH_SHORT).show()
                            return@IconButton
                        }
                        if (id == null) {
                            viewModel.insert(nama, jumlah, variant)
                        } else {
                            viewModel.update(id, nama, jumlah, variant)
                        }
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Outlined.Check,
                            contentDescription = stringResource(R.string.simpan),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    if (id != null){
                        DeleteAction {
                            showDialog = true
                        }
                    }
                }
            )
        }
    ) { padding ->

        FormPelanggan(
            nama = nama,
            onNamaChange = { nama = it },
            jumlah = jumlah,
            onJumlahChange = { jumlah = it },
            variant = variant,
            onVariantChange = { variant = it },
            modifier = Modifier.padding(padding)
        )
    }

    if (id != null && showDialog) {
        DisplayAlertDialog(
            onDismissRequest = { showDialog = false }) {
            showDialog = false
            viewModel.delete(id)
            navController.popBackStack()
        }
    }
}

@Composable
fun DeleteAction(delete: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    IconButton(onClick = { expanded = true }) {
        Icon(
            imageVector = Icons.Filled.MoreVert,
            contentDescription = stringResource(R.string.lainnya),
            tint = MaterialTheme.colorScheme.primary
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = {
                    Text(text = stringResource(id = R.string.hapus))
                },
                onClick = {
                    expanded = false
                    delete()
                }
            )
        }
    }
}

@Composable
fun FormPelanggan(
    nama: String, onNamaChange: (String) -> Unit,
    jumlah: String, onJumlahChange: (String) -> Unit,
    variant: String, onVariantChange: (String) -> Unit,
    modifier: Modifier
) {
    val variantList = listOf("Pashmina kaos", "Pashmina Viscose", "Segiempat Paris Japan", "Segiempat Voal")

    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = nama,
            onValueChange = onNamaChange,
            label = { Text("Nama") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = jumlah,
            onValueChange = onJumlahChange,
            label = { Text("JUMLAH") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Text("Pilih Variant:", style = MaterialTheme.typography.bodyMedium)

        OutlinedCard(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
        ) {
            Column(modifier = Modifier.padding(8.dp)) {
                variantList.forEach { variantItem ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onVariantChange(variantItem) }
                            .padding(vertical = 8.dp)
                    ) {
                        RadioButton(
                            selected = (variant == variantItem),
                            onClick = { onVariantChange(variantItem) }
                        )
                        Text(
                            text = variantItem,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun DetailPelangganScreenPreview() {
    Assesmobpro2Theme {
        DetailScreen(rememberNavController())
    }
}

