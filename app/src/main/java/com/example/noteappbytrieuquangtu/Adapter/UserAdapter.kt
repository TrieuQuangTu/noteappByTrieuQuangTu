package com.example.noteappbytrieuquangtu.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.noteappbytrieuquangtu.Fragment.ListFragmentDirections
import com.example.noteappbytrieuquangtu.databinding.CustomRowItemBinding
import com.example.noteappbytrieuquangtu.db.User

class UserAdapter(val OnClickDelete:ClickItemDelete) :RecyclerView.Adapter<UserAdapter.ViewHolder>(){


    private var userList =ArrayList<User>()

    fun setData(newList: List<User>){
        userList.clear()
        userList.addAll(newList)
        notifyDataSetChanged()
    }

    inner class ViewHolder(var binding: CustomRowItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = CustomRowItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var currentItem = userList[position]
        holder.binding.tvTitle.text = currentItem.title
        holder.binding.tvNote.text = currentItem.note

        //khi ta click vao item trong layout se chuyen du lieu sang UpdateFragment
        holder.binding.LinearLayout.setOnClickListener {

            //truyen du lieu tu ListFragment sang UpdateFragment
            val action =ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)

            holder.itemView.findNavController().navigate(action)
        }

        //khi ta click vao icon delete se xoa 1 item
        holder.binding.imgDelete.setOnClickListener {
            OnClickDelete.onClickDeleteItem(userList.get(position))
        }
    }
}

interface ClickItemDelete{
    fun onClickDeleteItem(user: User)
}