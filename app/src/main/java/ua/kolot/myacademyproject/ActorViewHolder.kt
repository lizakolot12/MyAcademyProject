package ua.kolot.myacademyproject

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ua.kolot.myacademyproject.data.Actor

class ActorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val avatarView: ImageView = itemView.findViewById(R.id.iv_avatar)
    private val nameView: TextView = itemView.findViewById(R.id.tv_name)

    fun bind(actor: Actor) {
        avatarView.setImageDrawable(itemView.context.resources.getDrawable(actor.avatarId))
        nameView.text = actor.name
    }
}