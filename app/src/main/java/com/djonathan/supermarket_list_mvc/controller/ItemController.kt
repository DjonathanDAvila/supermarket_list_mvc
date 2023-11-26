package com.djonathan.supermarket_list_mvc.controller

import com.djonathan.supermarket_list_mvc.model.Item

class ItemController {
    var nome: String = ""
    var isChecked: Boolean = false
    val item: Item = Item()


    constructor() {
        item.getDataItems()
    }


    constructor(nome: String, isChecked: Boolean) {
        this.nome = nome
        this.isChecked = isChecked
    }

    fun addItem(prItem: String) {
        item.addItem(prItem)
    }

    fun removeItem(prItem: Item) {
        item.removeItem(prItem)
    }

    fun loadList(): MutableList<Item> {
        return item.loadItems()
    }


}