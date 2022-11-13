package com.listuser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.listuser.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val listAdapter = UserListAdapter()
    val userViewModel by viewModels<UserViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding.userlist) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            hasFixedSize()
            adapter = listAdapter
        }

        userViewModel.users.observe(this) {
            listAdapter.itemList.addAll(it)
            listAdapter.notifyDataSetChanged()
        }
    }

    override fun onResume() {
        super.onResume()
        userViewModel.getUserList()
    }
}