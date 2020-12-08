package ua.kolot.myacademyproject

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ua.kolot.myacademyproject.data.Actor

class ActorsAdapter(context: Context, private val actors: List<Actor>) :
    RecyclerView.Adapter<ActorViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        return ActorViewHolder(inflater.inflate(R.layout.view_holder_actor, parent, false))
    }

    override fun getItemCount(): Int = actors.size

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        holder.bind(actors.get(position))

    }
}