package com.example.noteappbytrieuquangtu.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.noteappbytrieuquangtu.R
import com.example.noteappbytrieuquangtu.ViewModel.UserViewModel
import com.example.noteappbytrieuquangtu.databinding.FragmentUpdateBinding
import com.example.noteappbytrieuquangtu.db.User


class UpdateFragment : Fragment() {

    private lateinit var binding:FragmentUpdateBinding
     lateinit var mViewModel: UserViewModel

    //step 1:nhan du lieu
    private val args by navArgs<UpdateFragmentArgs>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUpdateBinding.inflate(inflater,container,false)
        mViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        //step2: set du lieu vao cac EditText
        binding.editUpdateTitle.setText(args.currentUser.title)
        binding.editUpdateNote.setText(args.currentUser.note)

        //step 3: khi ta nhan update se chuyen du lieu sang listFragment tu 2 EditText
        binding.btnUpdate.setOnClickListener {
            updateData()
        }
        return binding.root
    }

    private fun updateData() {
        val titleUpdate = binding.editUpdateTitle.text.toString()
        val noteUpdate = binding.editUpdateNote.text.toString()

        val user  = User(args.currentUser.id,titleUpdate,noteUpdate)
        if(titleUpdate.isNotEmpty() && noteUpdate.isNotEmpty()){

            mViewModel.updateUser(user)
            Toast.makeText(requireContext(),"Update successfully",Toast.LENGTH_SHORT).show()

            //navigate back
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        else {
            Toast.makeText(requireContext(),"Update fail",Toast.LENGTH_SHORT).show()

        }
    }


}