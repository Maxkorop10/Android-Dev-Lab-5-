package com.example.lab_5

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lab_5.models.AuthorData
import com.example.lab_5.models.InterfaceItem
import com.example.lab_5.models.LibraryData

class Adapter(
    private var dataList: List<InterfaceItem>,
    private val onDeleteClicked: (InterfaceItem) -> Unit,
    private val onAddBookClicked: (Int) -> Unit,
    private val onAddAuthorClicked: (Int) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class BookViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val prodYearTextView: TextView = itemView.findViewById(R.id.prodYearTextView)
        val delBookButton: Button = itemView.findViewById(R.id.deleteBookButton)
    }

    inner class AuthorViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val author_FullName: TextView = view.findViewById(R.id.author_FullName)
        val homeCountry: TextView = view.findViewById(R.id.homeCountry)
        val delButton: Button = view.findViewById(R.id.deleteButton)
        val addBookButton: Button = itemView.findViewById(R.id.addBookButton)

    }

//    abstract class InterHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {
//        abstract fun bind(item: InterfaceItem)
//    }

    override fun getItemViewType(position: Int): Int {
        return dataList[position].getItemType()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            InterfaceItem.book -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.book_layout, parent, false)
                BookViewHolder(view)
            }
            InterfaceItem.author -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.author_layout, parent, false)
                AuthorViewHolder(view)
            }
            else -> throw IllegalArgumentException("Unknown view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = dataList[position]) {
            is LibraryData -> {
                val bookHolder = holder as BookViewHolder
                bookHolder.titleTextView.text = item.title
                bookHolder.prodYearTextView.text = item.prod_year
                bookHolder.delBookButton.setOnClickListener{
                    onDeleteClicked(item)
                }
            }
            is AuthorData -> {
                val authorHolder = holder as AuthorViewHolder
                authorHolder.author_FullName.text = item.full_name
                authorHolder.homeCountry.text = item.homecountry
                authorHolder.delButton.setOnClickListener{
                    onDeleteClicked(item)
                }
                holder.itemView.findViewById<Button>(R.id.addBookButton).setOnClickListener {
                    onAddBookClicked(item.id)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(newLib: List<InterfaceItem>) {
        dataList = newLib
        notifyDataSetChanged()
    }
}