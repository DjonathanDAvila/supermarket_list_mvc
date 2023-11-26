package com.djonathan.supermarket_list_mvc.model

class Item {
    var nome: String = ""
    var isChecked: Boolean = false
    var listItems: MutableList<Item> = mutableListOf()

    companion object {
        val defautItems: List<Item> = listOf(
            Item("Arroz", false),
            Item("Feijão", false),
            Item("Macarrão", true),
            Item("Açucar", false),
            Item("Sal", false),
            Item("Óleo", true),
            Item("Maçã", false),
            Item("Banana", false)
        )
    }

    constructor()

    constructor(item: String, isChecked: Boolean = false) {
        this.nome = item
        this.isChecked = isChecked
    }

    fun getDataItems() {
        listItems.addAll(defautItems)
    }

    fun loadItems(): MutableList<Item> {
        return listItems
    }

    fun addItem(prItem: String) {
        listItems.add(Item(prItem))
    }

    fun removeItem(prItem: Item) {
        listItems.removeIf { it== prItem }
    }
}