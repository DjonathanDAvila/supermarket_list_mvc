package com.djonathan.supermarket_list_mvc.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.djonathan.supermarket_list_mvc.R
import com.djonathan.supermarket_list_mvc.controller.ItemController
import com.djonathan.supermarket_list_mvc.ui.theme.Supermarket_List_MVCTheme

class MainActivity : ComponentActivity() {
    lateinit var itemController: ItemController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Supermarket_List_MVCTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    itemController = ItemController()
                    ListItemsGrid(itemController)
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ListItemsGrid(itemController: ItemController, modifier: Modifier = Modifier) {
    // Use um estado para refletir a lista de itens
    var newItemText by remember { mutableStateOf("") }
    var itemsState by remember { mutableStateOf(itemController.item.loadItems()) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            item {
                // Adicionar EditText e botão "Adicionar" no início da lista
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = newItemText,
                        onValueChange = { newItemText = it },
                        label = { Text("Novo Item") },
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 8.dp)
                    )
                    Button(
                        onClick = {
                            itemController.addItem(newItemText)
                            // Atualizar o estado após adicionar um novo item
                            itemsState = itemController.item.listItems
                            // Limpar o texto do EditText
                            newItemText = ""
                        },
                        modifier = Modifier.padding(start = 8.dp)
                    ) {
                        Text("Adicionar")
                    }
                }
            }

            items(itemsState.size) { index ->
                val listItem = itemsState[index]
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text(
                        text = listItem.nome,
                        modifier = Modifier
                            .padding(8.dp)
                            .weight(1f)
                    )
                    val checkedState = remember { mutableStateOf(listItem.isChecked) }
                    Checkbox(
                        checked = checkedState.value,
                        onCheckedChange = {
                            checkedState.value = it
                        },
                        modifier = Modifier
                            .padding(end = 8.dp)
                    )

                    // Ícone de lixeira
                    IconButton(
                        onClick = {
                            // Lógica para remover o item aqui
                            itemController.removeItem(listItem)
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_delete_forever_24),
                            contentDescription = "Excluir Item",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Supermarket_List_MVCTheme {
        val itemController = ItemController()
        ListItemsGrid(itemController)
    }
}