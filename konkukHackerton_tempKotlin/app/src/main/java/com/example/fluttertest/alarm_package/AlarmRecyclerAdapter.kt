package com.example.fluttertest.alarm_package

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fluttertest.roomDB.AlarmDataModel
import com.example.fluttertest.databinding.RowBinding

class AlarmRecyclerAdapter(var items:ArrayList<AlarmDataModel>)
    : RecyclerView.Adapter<AlarmRecyclerAdapter.ViewHolder>() {

    interface OnItemClickListener{ // 완전히 독립적인 class를 만들 수 있게 함
        fun OnItemClick(position: Int, item: AlarmDataModel)
    }

    var itemClickListener: OnItemClickListener?= null

    inner class ViewHolder(val binding: RowBinding) : RecyclerView.ViewHolder(binding.root) {
        init{ // 초기화
            binding.row.setOnClickListener {
                itemClickListener?.OnItemClick(adapterPosition, items[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = RowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.serialNumText.text = items[position].serialNum.toString()
        holder.binding.alarmCodeText.text = items[position].alarm_code.toString()
        holder.binding.timeText.text = items[position].time
        holder.binding.contentText.text = items[position].content
    }
}