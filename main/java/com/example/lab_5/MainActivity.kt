package com.example.lab_5

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab_5.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import androidx.lifecycle.Observer
import com.example.lab_5.models.AuthorData
import com.example.lab_5.models.InterfaceItem
import com.example.lab_5.models.LibraryData

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private val vm: MyViewModel by viewModels()
    private lateinit var adapter: Adapter

    private val viewModel: MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = Adapter(emptyList(),
            onDeleteClicked = { item -> DeleteItem(item) },
            onAddBookClicked = { authorId -> showAddBookDialog(authorId) },
            onAddAuthorClicked = { showAddAuthorDialog() }
        )
        recyclerView.adapter = adapter

        vm.items.observe(this, Observer { itemList ->
            adapter.updateItems(itemList)
        })

        findViewById<Button>(R.id.addAuthorButton).setOnClickListener {
            showAddAuthorDialog()
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun DeleteItem(item: InterfaceItem) {
        if (item is AuthorData) {
            viewModel.deleteAuthor(item)
            Toast.makeText(this, "Author is deleted", Toast.LENGTH_SHORT).show()
        } else if (item is LibraryData) {
            viewModel.deleteBook(item)
            Toast.makeText(this, "Book is deleted", Toast.LENGTH_SHORT).show()
        }
    }


    private fun showAddBookDialog(authorId: Int) {
        val builder = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.newbook_form, null)
        val titleEditText = dialogView.findViewById<EditText>(R.id.newBookTitle)
        val prodYearEditText = dialogView.findViewById<EditText>(R.id.newProdYear)

        builder.setView(dialogView)
            .setPositiveButton("Add") { dialog, _ ->
                val title = titleEditText.text.toString()
                val prod_year = prodYearEditText.text.toString()

                // Add the new book to the database with the specified author ID
                val newBook = LibraryData(authorID = authorId, title = title, prod_year = prod_year)
                viewModel.addBook(newBook)

                Toast.makeText(this, "Book added", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->  dialog.dismiss() }
        builder.create().show()
    }


    private fun showAddAuthorDialog() {
        val builder = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.newauthor_form, null)
        val nameEditText = dialogView.findViewById<EditText>(R.id.newFullName)
        val countryEditText = dialogView.findViewById<EditText>(R.id.newHomecountry)

        builder.setView(dialogView)
            .setPositiveButton("Add") { dialog, _ ->
                val full_name = nameEditText.text.toString()
                val homecountry = countryEditText.text.toString()

                val newAuthor = AuthorData(full_name = full_name, homecountry = homecountry)

                // Add the new author to the database
                lifecycleScope.launch {
                    viewModel.addAuthor(newAuthor)
                    Toast.makeText(this@MainActivity, "Author added", Toast.LENGTH_SHORT).show()
                }

                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }

        builder.create().show()
    }
}