package com.devdroiddev.databinding.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import androidx.viewbinding.ViewBinding
import com.devdroiddev.databinding.databinding.StudentListRowBinding
import com.devdroiddev.databinding.databinding.StudentRowRightBinding
import com.devdroiddev.databinding.interfaces.OnItemClickListener
import com.devdroiddev.databinding.model.User

class StudentAdapter(
    private val context: Context,
    private val studentRecordsList: MutableList<User>,
    private val itemClickListener: OnItemClickListener<User>
) : RecyclerView.Adapter<StudentAdapter.MyViewHolder>()  {


    private val VIEW_TYPE_FIRST = 1
    private val VIEW_TYPE_SECOND = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ViewBinding = when (viewType) {
            VIEW_TYPE_FIRST -> StudentListRowBinding.inflate(inflater, parent, false)
            VIEW_TYPE_SECOND -> StudentRowRightBinding.inflate(inflater, parent, false)
            else -> throw IllegalArgumentException("Invalid view type")
        }
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentStudentModel = studentRecordsList[position]
        when (holder.binding) {
            is StudentListRowBinding -> {
                holder.binding.studentModel = currentStudentModel
                holder.binding.itemClickListener = itemClickListener
                holder.binding.executePendingBindings()
            }

            is StudentRowRightBinding  -> {
                holder.binding.studentModel = currentStudentModel
                holder.binding.itemClickListener = itemClickListener
                holder.binding.executePendingBindings()
            }

            else -> throw IllegalArgumentException("Invalid view holder type")
        }
    }

    override fun getItemCount(): Int {
        return studentRecordsList.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (position % 2 == 0) VIEW_TYPE_FIRST else VIEW_TYPE_SECOND
    }

    class MyViewHolder(val binding: ViewBinding) : ViewHolder(binding.root)

}