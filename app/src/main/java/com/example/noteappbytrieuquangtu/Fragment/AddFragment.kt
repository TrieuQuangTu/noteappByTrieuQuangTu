package com.example.noteappbytrieuquangtu.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.noteappbytrieuquangtu.R
import com.example.noteappbytrieuquangtu.ViewModel.UserViewModel
import com.example.noteappbytrieuquangtu.databinding.FragmentAddBinding
import com.example.noteappbytrieuquangtu.db.User


class AddFragment : Fragment() {

    private lateinit var binding:FragmentAddBinding
    private lateinit var mViewModel :UserViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(inflater,container,false)

        mViewModel = ViewModelProvider(this).get(UserViewModel::class.java)


        //Khi ta nhan INSERT se "insert du lieu" va navigate back lai listFragment
        binding.btnInsert.setOnClickListener {
            InsertData()
        }
        return binding.root
    }

    private fun InsertData() {
        val title = binding.editTitle.text.toString()
        val note =binding.editNote.text.toString()

        val user = User(0,title,note)
        if(title.isNotEmpty() && note.isNotEmpty()){
            mViewModel.addUser(user)
            Toast.makeText(requireContext(),"Insert successfully",Toast.LENGTH_SHORT).show()

            //navigate back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }
        else {
            Toast.makeText(requireContext(),"Insert fail",Toast.LENGTH_SHORT).show()
        }
    }


}