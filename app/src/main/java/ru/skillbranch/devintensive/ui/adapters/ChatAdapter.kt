package ru.skillbranch.devintensive.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_chat_single.view.*
import ru.skillbranch.devintensive.R
import ru.skillbranch.devintensive.models.data.ChatItem
import ru.skillbranch.devintensive.ui.custom.AvatarImageView

class ChatAdapter : RecyclerView.Adapter<ChatAdapter.SingleViewHolder>() {
    var items: List<ChatItem> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val convertView = inflater.inflate(R.layout.item_chat_single, parent, false)
        Log.d("M_ChatAdapter", "onCreateViewHolder")
        return SingleViewHolder(convertView)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: SingleViewHolder, position: Int) {
        Log.d("M_ChatAdapter", "onBindViewHolder $position")
        holder.bind(items[position])
    }

    fun updateData(data: List<ChatItem>) {
        items = data
        notifyDataSetChanged()  //уведомляем подписчиков, что данные обновились
    }

    inner class SingleViewHolder(convertView: View) : RecyclerView.ViewHolder(convertView),
        LayoutContainer {
        override val containerView: View?
            get() = itemView

        fun bind(item: ChatItem) {
            if (item.avatar == null) {
                itemView.iv_avatar_single.setInitials(item.initials)
            } else {
                //TODO set draw
            }
            itemView.sv_indicator.visibility = if (item.isOnline) View.VISIBLE else View.GONE
            with(itemView.tv_date_single) {
                visibility = if (item.lastMessageDate != null) View.VISIBLE else View.GONE
                text = item.lastMessageDate
            }
            with(itemView.tv_counter_single) {
                visibility = if (item.messageCount > 0) View.VISIBLE else View.GONE
                text = item.messageCount.toString()
            }
            itemView.tv_title_single.text = item.title
            itemView.tv_message_single.text = item.shortDEscription
        }
    }
}