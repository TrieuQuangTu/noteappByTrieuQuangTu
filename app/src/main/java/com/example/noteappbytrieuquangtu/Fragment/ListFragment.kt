package com.example.noteappbytrieuquangtu.Fragment

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.noteappbytrieuquangtu.Adapter.ClickItemDelete
import com.example.noteappbytrieuquangtu.Adapter.UserAdapter
import com.example.noteappbytrieuquangtu.R
import com.example.noteappbytrieuquangtu.ViewModel.UserViewModel
import com.example.noteappbytrieuquangtu.databinding.FragmentListBinding
import com.example.noteappbytrieuquangtu.db.User


class ListFragment : Fragment(), ClickItemDelete {

    private lateinit var binding:FragmentListBinding

    private lateinit var mViewModel: UserViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentListBinding.inflate(inflater,container,false)

        //1) Khi ta nhan floatting button se sang AddFragment
       binding.btnAdd.setOnClickListener {
           findNavController().navigate(R.id.action_listFragment_to_addFragment)
       }

        //2) Hien thi du lieu len listview
        val mAdapter =UserAdapter(this)
        binding.rec.layoutManager =StaggeredGridLayoutManager(2,LinearLayout.VERTICAL)
        binding.rec.adapter = mAdapter

        //UserViewModel
        mViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mViewModel.readAllData.observe(viewLifecycleOwner, Observer {user->
            mAdapter.setData(user)
        })


        //Finally : Hien thi icon DeleteAll
        setHasOptionsMenu(true)
        //override 2 ham` fun:
        //fun OnCreateOptionsMenu
        //fun onOptionsItemSelected


        return binding.root
    }



    override fun onClickDeleteItem(user: User) {
        mViewModel.deleteUser(user)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            val builder = AlertDialog.Builder(requireContext())
            builder.setPositiveButton("Yes"){_,_->

                mViewModel.deleteAll()
                Toast.makeText(requireContext(),"Xoa tat ca thanh cong ", Toast.LENGTH_SHORT).show()


            }
            builder.setNegativeButton("No"){_,_-> }
            builder.setTitle("Delete everything?")
            builder.setMessage("Are you sure want to delete everything?")
            builder.create().show()
        }
        return super.onOptionsItemSelected(item)
    }


}